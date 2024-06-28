package io.hhplus.lecture.infrastructure.lecture_session_apply_history;

import io.hhplus.lecture.common.enums.LectureApplyResultEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lecture_session_apply_history")
public class LectureSessionApplyHistoryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_session_id", nullable = false)
    private Long lectureSessionId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name ="result", nullable = false)
    private LectureApplyResultEnum result;

    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
