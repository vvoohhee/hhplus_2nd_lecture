package io.hhplus.lecture.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "lecture_session")
public class LectureSessionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_id", nullable = false)
    private long lectureId;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "apply_count",
            nullable = false,
            columnDefinition = "INT DEFAULT 0")
    private int applyCount;

    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public LectureSessionEntity(long lectureId, int capacity, int applyCount) {
        this.lectureId = lectureId;
        this.capacity = capacity;
        this.applyCount = applyCount;
        this.createdAt = LocalDateTime.now();
    }

}
