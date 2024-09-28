package io.kit.file;

import io.kit.file.vo.FileMetaDataVo;
import lombok.extern.slf4j.Slf4j;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.api.MediaInfo;
import org.jcodec.api.PictureWithMetadata;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Picture;
import org.jcodec.common.model.Rational;
import org.jcodec.common.model.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class FileCompressService {
    @Value("${video.compress.to.frameRate:30}")
    int compressToFrameRate;

    @Value("${video.block.size:1}")
    public int blockSize; //MB为单位
    @Value("${video.block.nameSuffix:'block_'}")
    public String nameSuffix; //MB为单位

    /**
     * 压缩视频文件
     *
     * @param inputFilePath  输入视频文件路径
     * @param outputFilePath 输出视频文件路径
     * @throws IOException
     */
    public FileMetaDataVo compressVideo(String inputFilePath, String outputFilePath) throws IOException, JCodecException {
        File inputFile = new File(inputFilePath);
        FileMetaDataVo metaData = new FileMetaDataVo();
        metaData.setOriginSize(inputFile.length());
        metaData.setName(inputFile.getName());
        try(FileChannelWrapper fileChannelWrapper = NIOUtils.readableChannel(inputFile)) {
            FrameGrab grab = FrameGrab.createFrameGrab(fileChannelWrapper);
            grab.getVideoTrack().getCurFrame();
            MediaInfo mediaInfo = grab.getMediaInfo();
            Size videoSize = mediaInfo.getDim();
            metaData.setWidth(videoSize.getWidth());
            metaData.setHeight(videoSize.getHeight());
            PictureWithMetadata nativeFrameWithMetadata = grab.getNativeFrameWithMetadata();
            metaData.setFileType(getFileType(inputFile));
            metaData.setDuration(nativeFrameWithMetadata.getDuration());
            SequenceEncoder encoder = null;
            try (SeekableByteChannel out = NIOUtils.writableFileChannel(outputFilePath)) {
                encoder = SequenceEncoder.createWithFps(out, Rational.R(compressToFrameRate, 1));
                Picture picture;
                while ((picture = grab.getNativeFrame()) != null) {
                    encoder.encodeNativeFrame(picture);
                }
            } finally {
                Optional.ofNullable(encoder).ifPresent(f -> {
                    try {
                        f.finish();
                    } catch (Exception e) {
                        log.warn("Unable compress video when do finish, input: {}, output: {}, message: {}",
                                inputFile, outputFilePath, e.getMessage());
                    }
                });
            }
        }
        metaData.setSize(new File(outputFilePath).length());
        metaData.setFrameRate(compressToFrameRate);
        return metaData;
    }


    private String getFileType(File file) {
        String name = file.getName();
        return name.substring(name.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 视频文件分块
     *
     * @param inputFilePath  输入视频文件路径
     * @param outputDir 输出视频文件路径
     * @throws IOException
     */
    public ArrayList<File> splitVideoFile(String inputFilePath, String outputDir) throws IOException {
        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            throw new IllegalArgumentException("Input file does not exist: " + inputFilePath);
        }
        ArrayList<File> blocks = new ArrayList<>();
        long chunkSize = blockSize * 1024 * 1024;
        String fileType = getFileType(inputFile);
        try (RandomAccessFile raf = new RandomAccessFile(inputFile, "r");
             FileChannel sourceChannel = raf.getChannel()) {
            long totalSize = sourceChannel.size();
            long numChunks = (long) Math.ceil((double) totalSize / chunkSize);
            for (int i = 0; i < numChunks; i++) {
                long position = i * chunkSize;
                long remaining = Math.min(chunkSize, totalSize - position);
                Path outputFile = Paths.get(outputDir, String.format("%s_%s.%s", nameSuffix, (i + 1), fileType));
                try (FileChannel outputChannel = (FileChannel) Files.newByteChannel(outputFile,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                    sourceChannel.transferTo(position, remaining, outputChannel);
                }

                System.out.println("Created: " + outputFile.toString() + " (" + remaining + " bytes)");
            }
        }
        return blocks;
    }

    public ArrayList<File> splitVideoFile(MultipartFile inputFile, String outputDir) throws IOException {
        if (Objects.isNull(inputFile)) {
            throw new IllegalArgumentException("Input MultipartFile does not exist: ");
        }
        ArrayList<File> blocks = new ArrayList<>();
        long chunkSize = blockSize * 1024 * 1024;
        long fileSize = inputFile.getSize();
        long chunks = (fileSize + chunkSize - 1) / chunkSize; // 计算块数

        String fileType = inputFile.getContentType();
        try (InputStream inputStream = inputFile.getInputStream()) {
            byte[] buffer = new byte[(int) chunkSize];
            for (int i = 0; i < chunks; i++) {
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) break;
                File chunkFile = new File(outputDir, String.format("%s_%s.%s", nameSuffix, (i + 1), fileType));
                try (FileOutputStream fos = new FileOutputStream(chunkFile)) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
        }
        return blocks;
    }
}
