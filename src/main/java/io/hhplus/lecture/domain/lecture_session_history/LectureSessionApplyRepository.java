package io.hhplus.lecture.domain.lecture_session_history;

import io.hhplus.lecture.domain.lecture_session_apply.LectureSessionApply;

import java.util.List;

public interface LectureSessionApplyRepository {
    boolean existsByUserIdAndLectureSessionId(Long userId, Long lectureSessionId);
    LectureSessionApply save(LectureSessionApply lectureSessionApply);
    List<LectureSessionApply> getByUserId(Long id);
}
