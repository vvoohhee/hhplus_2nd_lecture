package io.hhplus.lecture.persistence.mapper;

import io.hhplus.lecture.business.domain.LectureApplyHistory;
import io.hhplus.lecture.persistence.entity.LectureApplyHistoryEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LectureApplyHistoryMapper {
    public LectureApplyHistory toDomain(LectureApplyHistoryEntity entity) {
        return LectureApplyHistory.builder()
                .id(entity.getId())
                .lectureSessionId(entity.getLectureSessionId())
                .userId(entity.getUserId())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public LectureApplyHistoryEntity toEntity(LectureApplyHistory domain) {
        return LectureApplyHistoryEntity.builder()
                .lectureSessionId(domain.getLectureSessionId())
                .userId(domain.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
