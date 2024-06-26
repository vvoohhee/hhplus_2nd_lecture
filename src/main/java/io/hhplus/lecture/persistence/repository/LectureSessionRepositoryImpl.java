package io.hhplus.lecture.persistence.repository;

import io.hhplus.lecture.business.domain.LectureSession;
import io.hhplus.lecture.business.repository.LectureSessionRepository;
import io.hhplus.lecture.persistence.entity.LectureSessionEntity;
import io.hhplus.lecture.persistence.mapper.LectureSessionEntityMapper;
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
    public Optional<LectureSession> findById(Long id) {
        Optional<LectureSessionEntity> result = lectureSessionJpaRepository.findById(id);

        return result.map(lectureSessionEntityMapper::toDomain);
    }

    @Override
    public List<LectureSession> findByLectureId(Long lectureId) {
        List<LectureSessionEntity> result =
                lectureSessionJpaRepository.findByLectureId(lectureId);

        return result.stream()
                .map(lectureSessionEntityMapper::toDomain)
                .toList();


    }

    @Override
    public LectureSession save(LectureSession lectureSession) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return lectureSessionJpaRepository.existsById(id);
    }
}
