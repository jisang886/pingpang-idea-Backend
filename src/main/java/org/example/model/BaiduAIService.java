/*package org.example.model;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaiduAIService {
    private final BaiduAIClient baiduAIClient;

    @Value("${qianfan.access-key}")
    private String accessKey;

    @Value("${qianfan.secret-key}")
    private String secretKey;

    public BaiduResponse analyzeVideo(String videoBase64) {
        try {
            BaiduRequestvideo request = new BaiduRequestvideo();
            request.setVideoBase64(videoBase64);
            request.setAk(accessKey);
            request.setSk(secretKey);


            return baiduAIClient.analyze(request, accessKey, secretKey);
        } catch (Exception e) {
            System.err.println("调用百度大模型失败: " + e.getMessage());
            return null;
        }
    }
}
*/
