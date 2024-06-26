package io.hhplus.lecture.business.repository;

import io.hhplus.lecture.business.domain.Lecture;

import java.util.Optional;

public interface LectureRepository {
    Optional<Lecture> findById(Long id);
}
