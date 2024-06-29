package io.hhplus.lecture.presentation.dto;

import io.hhplus.lecture.domain.lecture.Lecture;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class LecturesDto {
    public record Request(
            long userId
    ) {
    }

    public record Response(
            List<LectureResponse> lectureList
    ) {
    }

    @Builder
    public record LectureResponse(
            Long id,
            String title,
            List<LectureSessionResponse> lectureSessionList
    ) {
    }

    public record LectureSessionResponse(
            Long id,
            Integer capacity,
            Integer applyCount
    ) {
    }

    public static LecturesDto.Response of(List<Lecture> lectureList) {
        List<LectureResponse> lectureResponseList = new ArrayList<>();
        lectureList.forEach(lecture ->
                {
                    LectureResponse lectureResponse = new LectureResponse(lecture.getId(), lecture.getTitle(), new ArrayList<>());

                    lecture.getLectureSessionList().forEach(session ->
                            lectureResponse.lectureSessionList.add(
                                    new LectureSessionResponse(session.getId(), session.getCapacity(), session.getApplyCount())
                            )
                    );

                    lectureResponseList.add(lectureResponse);
                }
        );

        return new LecturesDto.Response(lectureResponseList);
    }
}
