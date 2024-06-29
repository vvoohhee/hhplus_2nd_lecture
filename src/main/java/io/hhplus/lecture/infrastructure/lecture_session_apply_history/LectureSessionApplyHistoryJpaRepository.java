package io.hhplus.lecture.infrastructure.lecture_session_apply_history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureSessionApplyHistoryJpaRepository extends JpaRepository<LectureSessionApplyHistoryEntity, Long> {
    Optional<LectureSessionApplyHistoryEntity> findByUserIdAndLectureSessionId(Long userId, Long lectureSessionId);
}
