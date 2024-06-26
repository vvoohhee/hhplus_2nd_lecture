package io.hhplus.lecture.business.repository;

import io.hhplus.lecture.business.domain.LectureSession;

import java.util.List;
import java.util.Optional;

public interface LectureSessionRepository {
    Optional<LectureSession> findById(Long id);
    List<LectureSession> findByLectureId(Long lectureId);
    LectureSession save(LectureSession lectureSession);
    boolean existsById(Long id);
}
