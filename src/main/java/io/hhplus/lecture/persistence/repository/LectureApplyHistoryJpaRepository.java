package io.hhplus.lecture.persistence.repository;

import io.hhplus.lecture.persistence.entity.LectureApplyHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureApplyHistoryJpaRepository extends JpaRepository<LectureApplyHistoryEntity, Long> {
    List<LectureApplyHistoryEntity> findByLectureSessionId(Long lectureSessionId);
    Optional<LectureApplyHistoryEntity> findByUserIdAndLectureSessionId(Long userId, Long lectureSessionId);
}
