package io.hhplus.lecture.domain.lecture_session_apply;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LectureSessionApply {
    private Long id;
    private Long userId;
    private Long lectureSessionId;
}
