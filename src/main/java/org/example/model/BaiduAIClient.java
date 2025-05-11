/*package org.example.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "baiduAI", url = "https://api.baidu.com/ai/v1")
public interface BaiduAIClient {
    @PostMapping("/analyze-video")
    BaiduResponse analyze(
            @RequestBody BaiduRequestvideo request,
            @RequestHeader("QIANFAN_ACCESS_KEY") String accessKey,
            @RequestHeader("QIANFAN_SECRET_KEY") String secretKey
    );
}
*/
