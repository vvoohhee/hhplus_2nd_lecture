package io.hhplus.lecture.presentation.dto;

import io.hhplus.lecture.business.domain.Lecture;

public class LectureDtoMapper {
    Lecture toDomain(LectureApplyDto.Request dto) {
        return new Lecture();
    }

}
