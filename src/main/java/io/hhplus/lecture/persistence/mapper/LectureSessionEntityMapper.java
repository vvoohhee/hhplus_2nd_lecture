package io.hhplus.lecture.persistence.mapper;

import io.hhplus.lecture.business.domain.LectureSession;
import io.hhplus.lecture.persistence.entity.LectureSessionEntity;
import org.springframework.stereotype.Component;

@Component
public class LectureSessionEntityMapper {
    public LectureSession toDomain(LectureSessionEntity lectureSessionEntity) {
        return LectureSession.builder()
                .id(lectureSessionEntity.getId())
                .capacity(lectureSessionEntity.getCapacity())
                .applyCount(lectureSessionEntity.getApplyCount())
                .build();
    }
}
