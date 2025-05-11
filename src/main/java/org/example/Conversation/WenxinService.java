package org.example.Conversation;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.QianfanV2;
import com.baidubce.qianfan.model.chat.v2.request.RequestV2;
import com.baidubce.qianfan.model.chat.v2.response.StreamResponseV2;
import com.baidubce.qianfan.core.StreamIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class WenxinService {

    @Autowired
    private WenxinConfig wenxinConfig;  // 确保这个字段会被注入

    private QianfanV2 client;

    // 构造方法不再初始化client，改为在注入后初始化
    @Autowired
    public WenxinService(WenxinConfig wenxinConfig) {
        if (wenxinConfig != null) {
            this.client = new Qianfan(wenxinConfig.getApiKey()).v2();  // 使用API Key认证方式
        } else {
            throw new IllegalStateException("WenxinConfig is not properly initialized");
        }
    }

    // 发送对话请求
    public String chatWithWenxin(String prompt) {
        StringBuilder responseContent = new StringBuilder();

        try {
            // 构建请求，替换模型、消息等信息
            RequestV2 request = client.chatCompletion()
                    .model("ernie-x1-32k-preview")  // 使用指定的模型
                    .addMessage("user", prompt)  // 用户消息
                    .temperature(0.7)  // 设置温度值（控制回复的随机性）
                    .build();  // 构建请求

            // 获取流式响应
            StreamIterator<StreamResponseV2> responses = client.chatCompletionStream(request);

            // 定义处理响应的消费函数
            Consumer<StreamResponseV2> consumer = response -> {
                String content = response.getChoices().get(0).getDelta().getContent();
                if (content != null) {
                    responseContent.append(content);  // 将每个响应的内容追加到最终的回答中
                }
            };

            // 逐个处理响应
            responses.forEachRemaining(consumer);

        } catch (Exception e) {
            e.printStackTrace();
            return "请求失败：" + e.getMessage();  // 处理异常
        }

        return responseContent.toString();  // 返回大模型的完整回答
    }
}
