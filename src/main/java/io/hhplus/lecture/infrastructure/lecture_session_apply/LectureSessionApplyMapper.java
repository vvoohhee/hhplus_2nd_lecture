package io.hhplus.lecture.infrastructure.lecture_session_apply;

import io.hhplus.lecture.domain.lecture_session_apply.LectureSessionApply;
import org.springframework.stereotype.Component;

@Component
public class LectureSessionApplyMapper {
    public LectureSessionApplyEntity toEntity(LectureSessionApply domain) {
        return LectureSessionApplyEntity.builder()
                .userId(domain.getUserId())
                .lectureSessionId(domain.getLectureSessionId())
                .build();
    }

    public LectureSessionApply toDomain(LectureSessionApplyEntity entity) {
        return LectureSessionApply.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .lectureSessionId(entity.getLectureSessionId())
                .build();
    }
}
