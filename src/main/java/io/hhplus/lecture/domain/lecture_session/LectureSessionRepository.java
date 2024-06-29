package io.hhplus.lecture.domain.lecture_session;

import java.util.List;
import java.util.Optional;

public interface LectureSessionRepository {
    Optional<LectureSession> findWithPessimisticLockById(Long id);
    LectureSession save(LectureSession lectureSession);
    boolean existsById(Long id);

    List<LectureSession> findByLectureIdAndIsFullIsFalse(Long lectureId);
}
