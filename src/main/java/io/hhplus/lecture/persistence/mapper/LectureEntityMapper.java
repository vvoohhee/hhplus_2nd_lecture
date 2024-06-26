package io.hhplus.lecture.persistence.mapper;

import io.hhplus.lecture.business.domain.Lecture;
import io.hhplus.lecture.persistence.entity.LectureEntity;
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
