package io.hhplus.lecture.infrastructure.lecture_session;

import io.hhplus.lecture.domain.lecture_session.LectureSession;
import io.hhplus.lecture.domain.lecture_session.LectureSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LectureSessionRepositoryImpl implements LectureSessionRepository {

    private final LectureSessionJpaRepository lectureSessionJpaRepository;
    private final LectureSessionEntityMapper lectureSessionEntityMapper;

    @Override
    public Optional<LectureSession> findWithPessimisticLockById(Long id) {
        Optional<LectureSessionEntity> result = lectureSessionJpaRepository.findWithPessimisticLockById(id);

        return result.map(lectureSessionEntityMapper::toDomain);
    }

    @Override
    public LectureSession save(LectureSession lectureSession) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return lectureSessionJpaRepository.existsById(id);
    }

    @Override
    public List<LectureSession> findByLectureIdAndIsFullIsFalse(Long lectureId) {
        return lectureSessionJpaRepository.findByLectureIdAndIsFullIsFalse(lectureId).stream()
                .map(lectureSessionEntityMapper::toDomain)
                .toList();
    }
}
