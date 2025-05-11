/*
package org.example.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  // 注意使用 jakarta.persistence.Id
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileUrl;  // 上传文件的存储路径或 URL

    @Column(columnDefinition = "TEXT")
    private String analysisData;  // 百度 API 返回的分析结果

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();  // 分析记录的创建时间

    public void setAnalysisResult(String analysisResult) {
    }
}
*/
