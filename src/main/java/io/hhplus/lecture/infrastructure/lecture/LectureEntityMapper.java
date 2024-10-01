package io.hhplus.lecture.infrastructure.lecture;

import io.hhplus.lecture.domain.lecture.Lecture;
import org.springframework.stereotype.Component;

@Component
public class LectureEntityMapper {
    public Lecture toDomain(LectureEntity entity) {
        return Lecture.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();
    }
}
