package io.hhplus.lecture.persistence.repository;

import io.hhplus.lecture.persistence.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
}
