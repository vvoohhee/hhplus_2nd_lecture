package io.hhplus.lecture.presentation.controller;

import io.hhplus.lecture.business.service.LectureSessionService;
import io.hhplus.lecture.presentation.dto.LectureApplyDto;
import io.hhplus.lecture.presentation.dto.ApplyResultDto;
import io.hhplus.lecture.presentation.dto.LecturesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureSessionService lectureSessionService;

    /**
     * TODO - 특강 신청 구현
     *
     * @param request 강의 ID, 학생 ID
     * @return boolean 성공/실패 여부를 리턴
     */
    @PostMapping("/apply")
    public boolean apply(
            @RequestBody LectureApplyDto.Request request
    ) {
        return lectureSessionService.apply(request.userId(), request.lectureSessionId());
    }

    /**
     * TODO - 특강 신청 결과 조회 구현
     * @param userId 학생 ID
     * @return boolean 신청 성공 시 true, 실패 시 false
     */
    @GetMapping("/application/{userId}")
    public boolean applyResult(
            @PathVariable long userId,
            @RequestParam(value = "lecture_session") long lectureSessionId
    ) {
        return lectureSessionService.applyResult(userId, lectureSessionId);
    }

    /**
     * TODO - 특강 조회 구현 (심화)
     *
     * @param request
     * @return
     */
    @GetMapping("")
    public LecturesDto.Response lectures(
            @RequestBody LecturesDto.Request request
    ) {
        return new LecturesDto.Response(0L, "아키텍처");
    }
}
