package io.hhplus.lecture.infrastructure.lecture;

import io.hhplus.lecture.domain.lecture.Lecture;
import io.hhplus.lecture.domain.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository lectureJpaRepository;

    private final LectureEntityMapper lectureEntityMapper;

    @Override
    public List<Lecture> findAll() {
        return lectureJpaRepository.findAll().stream()
                .map(lectureEntityMapper::toDomain)
                .toList();
    }
}
