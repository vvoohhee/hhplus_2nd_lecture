package io.hhplus.lecture.presentation.dto;

public class ApplyDto {
    public record Request (
            Long userId,
            Long lectureId
    ) {}
    public record Response (
            boolean isSuccess
    ) {}
}
