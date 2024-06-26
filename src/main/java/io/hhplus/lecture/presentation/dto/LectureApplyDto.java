package io.hhplus.lecture.presentation.dto;

public class LectureApplyDto {
    public record Request(
            long userId,
            long lectureSessionId
    ) {
    }

    public record Response(
            boolean isSuccess
    ) {
    }
}
