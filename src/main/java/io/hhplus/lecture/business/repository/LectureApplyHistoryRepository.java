package io.hhplus.lecture.business.repository;

import io.hhplus.lecture.business.domain.LectureApplyHistory;

import java.util.List;
import java.util.Optional;

public interface LectureApplyHistoryRepository {
    Optional<LectureApplyHistory> findById(Long id);
    List<LectureApplyHistory> findByLectureSessionId(Long lectureSessionId);
    Optional<LectureApplyHistory> findByUserIdAndLectureSessionId(Long userId, Long lectureSessionId);
    LectureApplyHistory save(LectureApplyHistory lectureApplyHistory);
}
