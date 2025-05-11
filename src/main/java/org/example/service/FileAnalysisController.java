/*package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.filedownload.FileStorageService;
import org.example.model.BaiduAIService;
import org.example.model.BaiduResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class FileAnalysisController {
    private final FileStorageService fileStorageService;
    private final BaiduAIService baiduAIService;
    private final AnalysisResultService analysisResultService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAndAnalyzeFile(@RequestParam("file") MultipartFile file) {
        // 1. 上传文件
        String fileUrl = fileStorageService.storeFile(file);

        // 2. 调用百度 API 进行分析
        BaiduResponse response = baiduAIService.analyzeImage(fileUrl);

        // 3. 存储分析结果到数据库
        AnalysisResult result = analysisResultService.saveAnalysisResult(fileUrl, response.getResult());

        // 返回分析结果
        return ResponseEntity.ok(result);
    }
}
*/
