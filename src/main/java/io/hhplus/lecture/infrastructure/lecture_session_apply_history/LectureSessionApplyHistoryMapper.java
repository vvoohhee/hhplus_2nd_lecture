package io.hhplus.lecture.infrastructure.lecture_session_apply_history;

import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyHistory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LectureSessionApplyHistoryMapper {
    public LectureSessionApplyHistory toDomain(LectureSessionApplyHistoryEntity entity) {
        return LectureSessionApplyHistory.builder()
                .id(entity.getId())
                .lectureSessionId(entity.getLectureSessionId())
                .userId(entity.getUserId())
                .result(entity.getResult())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public LectureSessionApplyHistoryEntity toEntity(LectureSessionApplyHistory domain) {
        return LectureSessionApplyHistoryEntity.builder()
                .lectureSessionId(domain.getLectureSessionId())
                .userId(domain.getUserId())
                .result(domain.getResult())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
