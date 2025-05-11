package org.example.Conversation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wenxin")
public class WenxinController {

    @Autowired
    private WenxinService wenxinService;

    // 使用 @RequestBody 注解来接收 JSON 请求体
    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        return wenxinService.chatWithWenxin(message);
    }
}
