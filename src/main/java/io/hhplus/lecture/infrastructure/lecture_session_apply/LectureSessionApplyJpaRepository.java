package io.hhplus.lecture.infrastructure.lecture_session_apply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureSessionApplyJpaRepository extends JpaRepository<LectureSessionApplyEntity,Long> {
    boolean existsByUserIdAndLectureSessionId(Long userId, Long lectureSessionId);

    List<LectureSessionApplyEntity> findByUserId(Long userId);
}
