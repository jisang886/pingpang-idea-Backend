package org.example.TrainingUploads;

import jakarta.persistence.*;

@Entity
@Table(name = "training_feedback")
public class TrainingFeedback {

    @Id // 主键标识
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Long id; // 主键字段

    @Column(name = "record_id")
    private Long recordId; // 外键，指向 TrainingRecord 表的 id

    private String feeling; // 训练感受
    private String physicalState; // 体能状态
    private Integer selfRating; // 自我评分

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TrainingRecord trainingRecord; // 训练记录

    // Getter 和 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getPhysicalState() {
        return physicalState;
    }

    public void setPhysicalState(String physicalState) {
        this.physicalState = physicalState;
    }

    public Integer getSelfRating() {
        return selfRating;
    }

    public void setSelfRating(Integer selfRating) {
        this.selfRating = selfRating;
    }

    public TrainingRecord getTrainingRecord() {
        return trainingRecord;
    }

    public void setTrainingRecord(TrainingRecord trainingRecord) {
        this.trainingRecord = trainingRecord;
    }
}
