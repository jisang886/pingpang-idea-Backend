/*package org.example.TrainingUploads;

import org.example.login.User;
import org.example.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRecordRepository trainingRecordRepository;

    @Autowired
    private TrainingFeedbackRepository trainingFeedbackRepository;

    // 提交训练数据接口
    @PostMapping("/submit")
    public ResponseEntity<String> submitTrainingData(@RequestBody TrainingDataDTO trainingData, HttpSession session) {
        // 获取当前登录的用户
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("用户不存在");
        }

        // 创建 TrainingRecord 实体并保存
        TrainingRecord trainingRecord = new TrainingRecord();
        trainingRecord.setUserId(user.getId());  // 使用当前登录用户的 ID
        trainingRecord.setOpponentInfo(trainingData.getOpponentInfo());
        trainingRecord.setStrategy(trainingData.getStrategy());
        trainingRecord.setReflection(trainingData.getReflection());
        trainingRecord.setStartTime(trainingData.getStartTime());
        trainingRecord.setEndTime(trainingData.getEndTime());
        trainingRecord.setDurationMinutes(trainingData.getDurationMinutes());
        trainingRecord.setTotalHits(trainingData.getTotalHits());
        trainingRecord.setForehandHits(trainingData.getForehandHits());
        trainingRecord.setBackhandHits(trainingData.getBackhandHits());
        trainingRecord.setForehandSuccess(trainingData.getForehandSuccess());
        trainingRecord.setBackhandSuccess(trainingData.getBackhandSuccess());
        trainingRecord.setForehandTarget(trainingData.getForehandTarget());
        trainingRecord.setBackhandTarget(trainingData.getBackhandTarget());
        trainingRecord.setOverCompleted(trainingData.getOverCompleted());
        trainingRecord.setUnderCompleted(trainingData.getUnderCompleted());

        trainingRecord = trainingRecordRepository.save(trainingRecord);

        // 创建 TrainingFeedback 实体并保存
        TrainingFeedback trainingFeedback = new TrainingFeedback();
        trainingFeedback.setRecordId(trainingRecord.getId());
        trainingFeedback.setFeeling(trainingData.getFeeling());
        trainingFeedback.setPhysicalState(trainingData.getPhysicalState());
        trainingFeedback.setSelfRating(trainingData.getSelfRating());

        trainingFeedbackRepository.save(trainingFeedback);

        return ResponseEntity.ok("训练数据已保存，ID = " + trainingRecord.getId());
    }

    // 获取当前用户的训练记录接口
    @GetMapping("/my")
    public ResponseEntity<?> getMyTrainingRecords(HttpSession session) {
        // 获取当前用户
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("用户不存在");
        }

        // 获取当前用户的训练记录
        List<TrainingRecord> records = trainingRecordRepository.findByUserId(user.getId());
        List<TrainingDataDTO> result = new ArrayList<>();

        for (TrainingRecord record : records) {
            TrainingFeedback feedback = trainingFeedbackRepository
                    .findByRecordId(record.getId())
                    .stream()
                    .findFirst()
                    .orElse(null);

            TrainingDataDTO dto = new TrainingDataDTO();
            // 设置 record 的字段
            dto.setId(record.getId());  // 这里是关键，确保 id 字段被设置
            dto.setUserId(record.getUserId());
            dto.setOpponentInfo(record.getOpponentInfo());
            dto.setStrategy(record.getStrategy());
            dto.setReflection(record.getReflection());
            dto.setStartTime(record.getStartTime());
            dto.setEndTime(record.getEndTime());
            dto.setDurationMinutes(record.getDurationMinutes());
            dto.setTotalHits(record.getTotalHits());
            dto.setForehandHits(record.getForehandHits());
            dto.setBackhandHits(record.getBackhandHits());
            dto.setForehandSuccess(record.getForehandSuccess());
            dto.setBackhandSuccess(record.getBackhandSuccess());
            dto.setForehandTarget(record.getForehandTarget());
            dto.setBackhandTarget(record.getBackhandTarget());
            dto.setOverCompleted(record.getOverCompleted());
            dto.setUnderCompleted(record.getUnderCompleted());

            // 设置 feedback 的字段
            if (feedback != null) {
                dto.setFeeling(feedback.getFeeling());
                dto.setPhysicalState(feedback.getPhysicalState());
                dto.setSelfRating(feedback.getSelfRating());
            }

            result.add(dto);
        }

        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        if (trainingRecordRepository.existsById(id)) {
            trainingFeedbackRepository.deleteAll(trainingFeedbackRepository.findByRecordId(id)); // 先删反馈
            trainingRecordRepository.deleteById(id); // 再删记录
            return ResponseEntity.ok("训练记录已删除");
        } else {
            return ResponseEntity.status(404).body("记录不存在");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTrainingRecord(@PathVariable Long id, @RequestBody TrainingDataDTO dto, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        TrainingRecord existingRecord = trainingRecordRepository.findById(id).orElse(null);
        if (existingRecord == null) return ResponseEntity.status(404).body("记录不存在");

        // 更新记录内容
        existingRecord.setOpponentInfo(dto.getOpponentInfo());
        existingRecord.setStrategy(dto.getStrategy());
        existingRecord.setReflection(dto.getReflection());
        existingRecord.setStartTime(dto.getStartTime());
        existingRecord.setEndTime(dto.getEndTime());
        existingRecord.setDurationMinutes(dto.getDurationMinutes());
        existingRecord.setTotalHits(dto.getTotalHits());
        existingRecord.setForehandHits(dto.getForehandHits());
        existingRecord.setBackhandHits(dto.getBackhandHits());
        existingRecord.setForehandSuccess(dto.getForehandSuccess());
        existingRecord.setBackhandSuccess(dto.getBackhandSuccess());
        existingRecord.setForehandTarget(dto.getForehandTarget());
        existingRecord.setBackhandTarget(dto.getBackhandTarget());
        existingRecord.setOverCompleted(dto.getOverCompleted());
        existingRecord.setUnderCompleted(dto.getUnderCompleted());

        trainingRecordRepository.save(existingRecord);

        // 同步更新反馈（如果存在）
        TrainingFeedback feedback = trainingFeedbackRepository.findByRecordId(existingRecord.getId()).stream().findFirst().orElse(null);
        if (feedback != null) {
            feedback.setFeeling(dto.getFeeling());
            feedback.setPhysicalState(dto.getPhysicalState());
            feedback.setSelfRating(dto.getSelfRating());
            trainingFeedbackRepository.save(feedback);
        }

        return ResponseEntity.ok("记录已更新");
    }
}
*/
package org.example.TrainingUploads;

import org.example.login.User;
import org.example.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRecordRepository trainingRecordRepository;

    @Autowired
    private TrainingFeedbackRepository trainingFeedbackRepository;

    @Autowired
    private TrainingPersistenceService trainingPersistenceService;

    // 提交训练数据接口（集成重试机制）
    @PostMapping("/submit")
    public ResponseEntity<String> submitTrainingData(@RequestBody TrainingDataDTO trainingData, HttpSession session) {
        // 用户验证逻辑
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("用户不存在");
        }

        // 构建实体对象
        TrainingRecord trainingRecord = new TrainingRecord();
        trainingRecord.setUserId(user.getId());
        trainingRecord.setOpponentInfo(trainingData.getOpponentInfo());
        trainingRecord.setStrategy(trainingData.getStrategy());
        trainingRecord.setReflection(trainingData.getReflection());
        trainingRecord.setStartTime(trainingData.getStartTime());
        trainingRecord.setEndTime(trainingData.getEndTime());
        trainingRecord.setDurationMinutes(trainingData.getDurationMinutes());
        trainingRecord.setTotalHits(trainingData.getTotalHits());
        trainingRecord.setForehandHits(trainingData.getForehandHits());
        trainingRecord.setBackhandHits(trainingData.getBackhandHits());
        trainingRecord.setForehandSuccess(trainingData.getForehandSuccess());
        trainingRecord.setBackhandSuccess(trainingData.getBackhandSuccess());
        trainingRecord.setForehandTarget(trainingData.getForehandTarget());
        trainingRecord.setBackhandTarget(trainingData.getBackhandTarget());
        trainingRecord.setOverCompleted(trainingData.getOverCompleted());
        trainingRecord.setUnderCompleted(trainingData.getUnderCompleted());

        TrainingFeedback trainingFeedback = new TrainingFeedback();
        trainingFeedback.setFeeling(trainingData.getFeeling());
        trainingFeedback.setPhysicalState(trainingData.getPhysicalState());
        trainingFeedback.setSelfRating(trainingData.getSelfRating());

        try {
            TrainingRecord savedRecord = trainingPersistenceService.saveWithRetry(trainingRecord, trainingFeedback);
            return ResponseEntity.ok("训练数据已保存，ID = " + savedRecord.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.status(503).body("服务暂时不可用，请稍后重试");
        }
    }

    // 获取当前用户的训练记录接口
    @GetMapping("/my")
    public ResponseEntity<?> getMyTrainingRecords(HttpSession session) {
        // 获取当前用户
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("用户不存在");
        }

        // 获取当前用户的训练记录
        List<TrainingRecord> records = trainingRecordRepository.findByUserId(user.getId());
        List<TrainingDataDTO> result = new ArrayList<>();

        for (TrainingRecord record : records) {
            TrainingFeedback feedback = trainingFeedbackRepository
                    .findByRecordId(record.getId())
                    .stream()
                    .findFirst()
                    .orElse(null);

            TrainingDataDTO dto = new TrainingDataDTO();
            // 设置 record 的字段
            dto.setId(record.getId());  // 这里是关键，确保 id 字段被设置
            dto.setUserId(record.getUserId());
            dto.setOpponentInfo(record.getOpponentInfo());
            dto.setStrategy(record.getStrategy());
            dto.setReflection(record.getReflection());
            dto.setStartTime(record.getStartTime());
            dto.setEndTime(record.getEndTime());
            dto.setDurationMinutes(record.getDurationMinutes());
            dto.setTotalHits(record.getTotalHits());
            dto.setForehandHits(record.getForehandHits());
            dto.setBackhandHits(record.getBackhandHits());
            dto.setForehandSuccess(record.getForehandSuccess());
            dto.setBackhandSuccess(record.getBackhandSuccess());
            dto.setForehandTarget(record.getForehandTarget());
            dto.setBackhandTarget(record.getBackhandTarget());
            dto.setOverCompleted(record.getOverCompleted());
            dto.setUnderCompleted(record.getUnderCompleted());

            // 设置 feedback 的字段
            if (feedback != null) {
                dto.setFeeling(feedback.getFeeling());
                dto.setPhysicalState(feedback.getPhysicalState());
                dto.setSelfRating(feedback.getSelfRating());
            }

            result.add(dto);
        }

        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        if (trainingRecordRepository.existsById(id)) {
            trainingFeedbackRepository.deleteAll(trainingFeedbackRepository.findByRecordId(id)); // 先删反馈
            trainingRecordRepository.deleteById(id); // 再删记录
            return ResponseEntity.ok("训练记录已删除");
        } else {
            return ResponseEntity.status(404).body("记录不存在");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTrainingRecord(@PathVariable Long id, @RequestBody TrainingDataDTO dto, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("用户未登录");
        }

        TrainingRecord existingRecord = trainingRecordRepository.findById(id).orElse(null);
        if (existingRecord == null) return ResponseEntity.status(404).body("记录不存在");

        // 更新记录内容
        existingRecord.setOpponentInfo(dto.getOpponentInfo());
        existingRecord.setStrategy(dto.getStrategy());
        existingRecord.setReflection(dto.getReflection());
        existingRecord.setStartTime(dto.getStartTime());
        existingRecord.setEndTime(dto.getEndTime());
        existingRecord.setDurationMinutes(dto.getDurationMinutes());
        existingRecord.setTotalHits(dto.getTotalHits());
        existingRecord.setForehandHits(dto.getForehandHits());
        existingRecord.setBackhandHits(dto.getBackhandHits());
        existingRecord.setForehandSuccess(dto.getForehandSuccess());
        existingRecord.setBackhandSuccess(dto.getBackhandSuccess());
        existingRecord.setForehandTarget(dto.getForehandTarget());
        existingRecord.setBackhandTarget(dto.getBackhandTarget());
        existingRecord.setOverCompleted(dto.getOverCompleted());
        existingRecord.setUnderCompleted(dto.getUnderCompleted());

        trainingRecordRepository.save(existingRecord);

        // 同步更新反馈（如果存在）
        TrainingFeedback feedback = trainingFeedbackRepository.findByRecordId(existingRecord.getId()).stream().findFirst().orElse(null);
        if (feedback != null) {
            feedback.setFeeling(dto.getFeeling());
            feedback.setPhysicalState(dto.getPhysicalState());
            feedback.setSelfRating(dto.getSelfRating());
            trainingFeedbackRepository.save(feedback);
        }

        return ResponseEntity.ok("记录已更新");
    }


}

// 新增的服务类（应放在同一文件或单独文件）
@Service
class TrainingPersistenceService {

    @Autowired
    private TrainingRecordRepository trainingRecordRepository;

    @Autowired
    private TrainingFeedbackRepository trainingFeedbackRepository;

    @Retryable(
            value = { org.springframework.dao.DataAccessException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 1.5)
    )
    @Transactional
    public TrainingRecord saveWithRetry(TrainingRecord record, TrainingFeedback feedback) {
        TrainingRecord savedRecord = trainingRecordRepository.save(record);
        feedback.setRecordId(savedRecord.getId());
        trainingFeedbackRepository.save(feedback);
        return savedRecord;
    }

    @Recover
    public TrainingRecord handleRecovery(org.springframework.dao.DataAccessException e,
                                         TrainingRecord record,
                                         TrainingFeedback feedback) {
        throw new RuntimeException("数据保存失败，已重试3次，最后错误：" + e.getMessage());
    }
}
