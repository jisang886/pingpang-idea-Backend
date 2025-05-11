package org.example.TrainingUploads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingFeedbackRepository extends JpaRepository<TrainingFeedback, Long> {

    List<TrainingFeedback> findByRecordId(Long recordId);

}
