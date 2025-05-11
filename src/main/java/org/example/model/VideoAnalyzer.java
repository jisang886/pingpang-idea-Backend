/*
package org.example.model;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.BceClientConfiguration;
import com.baidubce.services.vca.VcaClient;
import com.baidubce.services.vca.model.AnalyzeRequest;
import com.baidubce.services.vca.model.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VideoAnalyzer {

    private final VcaClient vcaClient;

    public VideoAnalyzer(@Value("${qianfan.access-key}") String ak,
                         @Value("${qianfan.secret-key}") String sk) {
        // 配置百度 VCA 客户端
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("http://vca.bj.baidubce.com"); // VCA 端点
        this.vcaClient = new VcaClient(config);
    }

    public AnalyzeResponse analyzeVideo(String bosPath) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(bosPath); // 设置 BOS 地址
        AnalyzeResponse response = vcaClient.analyze(request);
        System.out.println("Analyze Response: " + response);
        /*if (response.getTaskId() == null) {
            throw new RuntimeException("任务提交失败，未返回 taskId！");
        }*/
        /*return vcaClient.analyze(request);
    }
}
*/
