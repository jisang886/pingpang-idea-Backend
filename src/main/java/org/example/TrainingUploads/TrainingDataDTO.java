package org.example.TrainingUploads;

import lombok.Data;

@Data
public class TrainingDataDTO {
    private Long userId;
    private String opponentInfo;
    private String strategy;
    private String reflection;
    private String startTime;
    private String endTime;
    private Integer durationMinutes;
    private Integer totalHits;
    private Integer forehandHits;
    private Integer backhandHits;
    private Integer forehandSuccess;
    private Integer backhandSuccess;
    private Integer forehandTarget;
    private Integer backhandTarget;
    private Integer overCompleted;
    private Integer underCompleted;
    private String feeling;
    private String physicalState;
    private Integer selfRating;

    private Long id;  // 添加 id 属性

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
