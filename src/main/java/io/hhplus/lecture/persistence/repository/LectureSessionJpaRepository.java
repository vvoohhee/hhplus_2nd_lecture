package io.hhplus.lecture.persistence.repository;

import io.hhplus.lecture.persistence.entity.LectureSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureSessionJpaRepository extends JpaRepository<LectureSessionEntity, Long> {
    List<LectureSessionEntity> findByLectureId(Long lectureId);
}
