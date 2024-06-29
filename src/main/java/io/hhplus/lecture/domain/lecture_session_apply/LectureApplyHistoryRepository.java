package io.hhplus.lecture.domain.lecture_session_apply;

import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyHistory;

import java.util.List;
import java.util.Optional;

public interface LectureApplyHistoryRepository {
    Optional<LectureSessionApplyHistory> findByUserIdAndLectureSessionId(Long userId, Long lectureSessionId);
    LectureSessionApplyHistory save(LectureSessionApplyHistory lectureSessionApplyHistory);
}
