package io.hhplus.lecture.persistence.repository;

import io.hhplus.lecture.business.domain.Lecture;
import io.hhplus.lecture.business.repository.LectureRepository;
import io.hhplus.lecture.persistence.entity.LectureEntity;
import io.hhplus.lecture.persistence.mapper.LectureEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository lectureJpaRepository;

    private final LectureEntityMapper lectureEntityMapper;

    @Override
    public Optional<Lecture> findById(Long id) {
        Optional<LectureEntity> result = lectureJpaRepository.findById(id);
        return result.map(lectureEntityMapper::toDomain);
    }
}
