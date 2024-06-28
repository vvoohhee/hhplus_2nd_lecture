package io.hhplus.lecture.domain.lecture;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    List<Lecture> findAll();
}
