package org.example.TrainingUploads;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "training_record")
public class TrainingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    private Long userId; // 用户 ID
    private String opponentInfo; // 对手信息
    private String strategy; // 训练策略
    private String reflection; // 心得体会
    private String startTime; // 训练开始时间
    private String endTime; // 训练结束时间
    private Integer durationMinutes; // 训练时长（可选）

    private Integer totalHits; // 总击球次数
    private Integer forehandHits; // 正手击球次数
    private Integer backhandHits; // 反手击球次数
    private Integer forehandSuccess; // 正手击球成功次数
    private Integer backhandSuccess; // 反手击球成功次数

    private Integer forehandTarget; // 正手目标击球成功率
    private Integer backhandTarget; // 反手目标击球成功率

    private Integer overCompleted; // 超额完成次数
    private Integer underCompleted; // 未完成次数
}
