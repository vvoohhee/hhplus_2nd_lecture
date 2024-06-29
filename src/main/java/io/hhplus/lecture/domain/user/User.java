package io.hhplus.lecture.domain.user;

import io.hhplus.lecture.domain.lecture_session.LectureSession;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class User {
    private Long id;
    private List<LectureSession> appliedLectureSessionList;
}
