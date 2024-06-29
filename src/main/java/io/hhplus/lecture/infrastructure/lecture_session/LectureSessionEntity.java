package io.hhplus.lecture.infrastructure.lecture_session;

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

    @Column(name = "is_full_capacity",
            nullable = false,
            columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isFull;

    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public LectureSessionEntity(long lectureId, int capacity, int applyCount, boolean isFull) {
        this.lectureId = lectureId;
        this.capacity = capacity;
        this.applyCount = applyCount;
        this.isFull = applyCount >= capacity;
        this.createdAt = LocalDateTime.now();
    }

    public LectureSessionEntity(long id, long lectureId, int capacity, int applyCount) {
        this.id = id;
        this.lectureId = lectureId;
        this.capacity = capacity;
        this.applyCount = applyCount;
        this.isFull = applyCount >= capacity;
        this.createdAt = LocalDateTime.now();
    }

}
