/*
package org.example.filedownload;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoUploadController {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${model.api.url}")
    private String modelApiUrl;

    private final RestTemplate restTemplate;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAndProcessVideo(@RequestParam("file") MultipartFile file) {
        try {
            // 1. 创建上传目录
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // 2. 保存上传的文件到本地
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File savedFile = new File(uploadPath, filename);
            file.transferTo(savedFile);

            // 3. 调用本地模型进行处理
            FileSystemResource fileResource = new FileSystemResource(savedFile);
            Map<String, Object> modelResponse = sendToModelForProcessing(fileResource);

            // 4. 获取处理后视频 URL
            String processedVideoUrl = (String) modelResponse.get("video_url");

            return ResponseEntity.ok().body(
                    Map.of(
                            "message", "视频处理成功",
                            "processedVideoUrl", processedVideoUrl
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("视频保存失败：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("视频处理失败：" + e.getMessage());
        }
    }

    private Map<String, Object> sendToModelForProcessing(FileSystemResource fileResource) {
        // 构造 multipart/form-data 请求
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("video", fileResource); // 注意这里必须和 Flask 中 request.files['video'] 对应

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 发送请求
        return restTemplate.postForObject(modelApiUrl, requestEntity, Map.class);
    }
}
*/
