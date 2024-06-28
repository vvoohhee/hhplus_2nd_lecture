package io.hhplus.lecture.domain.lecture_session;

import io.hhplus.lecture.domain.lecture_session_apply.LectureSessionApply;
import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyRepository;
import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LectureSession {
    private Long id;
    private Integer capacity;
    private Integer applyCount;
    private boolean isFull;

    public void addApplyCount() {
        this.applyCount++;
    }

    public boolean hasUserApplied(LectureSessionApplyRepository lectureSessionApplyRepository, Long userId) {
        return lectureSessionApplyRepository.getByUserId(userId).stream()
                .map(LectureSessionApply::getLectureSessionId)
                .anyMatch(sessionId -> Objects.equals(sessionId, id));
    }

    public boolean isFull() {
        return isFull;
    }
}
