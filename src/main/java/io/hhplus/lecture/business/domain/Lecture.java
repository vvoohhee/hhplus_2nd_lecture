package io.hhplus.lecture.business.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Lecture {
    private Long id;
    private String title;
    private List<LectureSession> lectureSessionList;
}
