package io.hhplus.lecture.presentation.dto;

import io.hhplus.lecture.domain.lecture.Lecture;

public class LectureDtoMapper {
    Lecture toDomain(LectureApplyDto.Request dto) {
        return new Lecture();
    }

}
