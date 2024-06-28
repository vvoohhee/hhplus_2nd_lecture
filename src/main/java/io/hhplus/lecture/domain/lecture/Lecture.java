package io.hhplus.lecture.domain.lecture;

import io.hhplus.lecture.domain.lecture_session.LectureSession;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Lecture {
    private Long id;
    private String title;
    private List<LectureSession> lectureSessionList;

    public boolean hasAnySession() {
        return Optional.ofNullable(lectureSessionList)
                .map(list -> !list.isEmpty())
                .orElse(false);
    }
}
