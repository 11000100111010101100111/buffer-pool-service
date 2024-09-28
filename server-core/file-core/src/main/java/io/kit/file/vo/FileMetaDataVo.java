package io.kit.file.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileMetaDataVo {
    long originSize;
    long size;
    int width;
    int height;
    String fileType;
    double duration;
    String name;

    int originFrameRate;
    int frameRate;
}
