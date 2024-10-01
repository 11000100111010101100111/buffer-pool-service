package io.test;

import com.kit.common.utils.uuid.UUID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonExecutor {

    public static void main(String[] args) {
        String pythonScriptPath = "D:\\kit\\github\\stable-diffusion\\generate.py"; // Python 脚本路径

        // 获取操作系统类型
        String os = System.getProperty("os.name").toLowerCase();
        String virtualEnvPath;

        // 根据操作系统设置 Python 可执行文件路径
        if (os.contains("win")) {
            virtualEnvPath = "D:\\kit\\github\\stable-diffusion\\venv\\Scripts\\python.exe"; // Windows 路径
        } else if (os.contains("mac")) {
            virtualEnvPath = "path/to/venv/bin/python"; // macOS 路径
        } else if (os.contains("nix") || os.contains("nux")) {
            virtualEnvPath = "path/to/venv/bin/python"; // Linux 路径
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    virtualEnvPath,
                    pythonScriptPath,
                    "--name", "D:\\kit\\github\\SmallQuestionNotes\\kit-admin\\src\\main\\test\\io\\test\\my_" + UUID.fastUUID().toString() + ".png",
                    "--depth", "50",
                    "--scale", "15",
                    "--height", "512",
                    "--width", "512",
                    "--module", "CompVis/stable-diffusion-v1-4",
                    "--prompt", "A futuristic city with flying cars at sunset");
            processBuilder.redirectErrorStream(true); // 合并错误流

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("%")) {
                    System.out.println(line.split("\\|")[0]);
                } else if (line.contains("==>")) {
                    System.out.println(line);
                }
            }

            // 等待脚本执行完成
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
