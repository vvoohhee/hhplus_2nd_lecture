package io.hhplus.lecture.presentation.dto;

public class LecturesDto {
    public record Request(
            long id
    ) {
    }

    public record Response(
            long id,
            String title
    ) {
    }
}
