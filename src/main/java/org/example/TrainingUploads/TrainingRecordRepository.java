package org.example.TrainingUploads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRecordRepository extends JpaRepository<TrainingRecord, Long> {
    // 你可以根据需要添加自定义查询方法
    List<TrainingRecord> findByUserId(Long userId);

}
