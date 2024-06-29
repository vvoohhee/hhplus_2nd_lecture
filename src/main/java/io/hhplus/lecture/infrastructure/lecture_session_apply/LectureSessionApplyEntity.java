package io.hhplus.lecture.infrastructure.lecture_session_apply;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecture_session_apply")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureSessionApplyEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_session_id", nullable = false)
    private Long lectureSessionId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public LectureSessionApplyEntity(Long lectureSessionId, Long userId) {
        this.lectureSessionId = lectureSessionId;
        this.userId = userId;
    }
}
