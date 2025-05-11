package org.example.Conversation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WenxinConfig {

    @Value("${wenxin.api-key}")
    private String apiKey;

    @Value("${wenxin.secret-key}")
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
