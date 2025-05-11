/*
package org.example.filedownload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    // 存储路径，配置文件中设置
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) {
        // 创建文件夹，如果没有的话
        try {
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("无法创建存储目录", e);
        }

        // 获取文件原始名称，并生成唯一文件名
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "-" + originalFileName;

        // 将文件保存到本地
        try {
            Path filePath = Paths.get(uploadDir, uniqueFileName);
            Files.copy(file.getInputStream(), filePath);
            return filePath.toString();  // 返回文件的存储路径
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }
}
*/
