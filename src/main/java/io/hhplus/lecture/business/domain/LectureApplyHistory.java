package io.hhplus.lecture.business.domain;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureApplyHistory {
    private Long id;
    private Long lectureSessionId;
    private Long userId;
    private LocalDateTime createdAt;
}
