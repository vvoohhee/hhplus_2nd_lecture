package io.hhplus.lecture.infrastructure.lecture_session;

import io.hhplus.lecture.domain.lecture_session.LectureSession;
import org.springframework.stereotype.Component;

@Component
public class LectureSessionEntityMapper {
    public LectureSession toDomain(LectureSessionEntity lectureSessionEntity) {
        return LectureSession.builder()
                .id(lectureSessionEntity.getId())
                .capacity(lectureSessionEntity.getCapacity())
                .applyCount(lectureSessionEntity.getApplyCount())
                .isFull(lectureSessionEntity.getIsFull())
                .build();
    }
}
