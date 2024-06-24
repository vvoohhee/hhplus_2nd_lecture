package io.hhplus.lecture.presentation.dto;

public class LecturesDto {
    public record Request(
            Long id
    ) {
    }

    public record Response(
            Long id,
            String title
    ) {
    }
}
