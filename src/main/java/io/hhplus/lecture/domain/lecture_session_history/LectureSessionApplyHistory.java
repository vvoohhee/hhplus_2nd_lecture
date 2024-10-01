package io.hhplus.lecture.domain.lecture_session_history;

import io.hhplus.lecture.common.enums.LectureApplyResultEnum;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureSessionApplyHistory {
    private Long id;
    private Long lectureSessionId;
    private Long userId;
    private LectureApplyResultEnum result;
    private LocalDateTime createdAt;
}
