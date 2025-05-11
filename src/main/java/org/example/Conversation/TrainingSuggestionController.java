package org.example.Conversation;


import org.example.TrainingUploads.TrainingFeedback;
import org.example.TrainingUploads.TrainingFeedbackRepository;
import org.example.TrainingUploads.TrainingRecord;
import org.example.TrainingUploads.TrainingRecordRepository;
import org.example.login.User;
import org.example.login.UserRepository;
import org.example.Conversation.WenxinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/training")
public class TrainingSuggestionController {

    @Autowired
    private TrainingRecordRepository trainingRecordRepository;

    @Autowired
    private TrainingFeedbackRepository trainingFeedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WenxinService wenxinService;

    /**
     * 根据指定训练记录生成训练建议
     * URL 示例：GET /api/training/suggestion/{recordId}
     */
    @GetMapping("/suggestion/{id}")
    public ResponseEntity<?> getTrainingSuggestion(@PathVariable Long id, HttpSession session) {
        // 校验当前登录用户
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        // 根据 ID 查询训练记录，并验证该记录是否归属于当前用户
        TrainingRecord record = trainingRecordRepository.findById(id).orElse(null);
        if (record == null || !record.getUserId().equals(userId)) {
            return ResponseEntity.status(404).body("记录不存在或无权限");
        }

        // 获取训练记录对应的反馈信息（如果存在）
        TrainingFeedback feedback = trainingFeedbackRepository.findByRecordId(record.getId())
                .stream().findFirst().orElse(null);

        // 构建发送给大模型的提示信息 (prompt)
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("请根据以下训练记录提供详细的训练建议：\n")
                .append("【训练记录】\n")
                .append("对手信息：").append(record.getOpponentInfo()).append("\n")
                .append("训练策略：").append(record.getStrategy()).append("\n")
                .append("训练心得：").append(record.getReflection()).append("\n")
                .append("训练时间：从 ").append(record.getStartTime())
                .append(" 到 ").append(record.getEndTime()).append("\n")
                .append("数据指标：总击球次数 ").append(record.getTotalHits())
                .append("，正手击球 ").append(record.getForehandHits())
                .append("，反手击球 ").append(record.getBackhandHits())
                .append("；正手成功 ").append(record.getForehandSuccess())
                .append("，反手成功 ").append(record.getBackhandSuccess())
                .append("；超额完成 ").append(record.getOverCompleted())
                .append("，未完成 ").append(record.getUnderCompleted()).append("\n");
        if (feedback != null) {
            promptBuilder.append("【反馈信息】\n")
                    .append("训练感受：").append(feedback.getFeeling()).append("\n")
                    .append("体能状态：").append(feedback.getPhysicalState()).append("\n")
                    .append("自我评分：").append(feedback.getSelfRating()).append("\n");
        }
        promptBuilder.append("请结合以上数据，提供技术改进、体能调整和心理准备等方面的训练建议。");

        // 调用文心服务发送 prompt 并获取建议
        String suggestion = wenxinService.chatWithWenxin(promptBuilder.toString());
        return ResponseEntity.ok(suggestion);
    }
}