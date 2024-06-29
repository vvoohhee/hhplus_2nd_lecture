package io.hhplus.lecture.infrastructure.lecture;

import io.hhplus.lecture.infrastructure.lecture_session.LectureSessionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "lecture")
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title", nullable = false)
    public String title;

    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime createdAt;

    public LectureEntity(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }
}
