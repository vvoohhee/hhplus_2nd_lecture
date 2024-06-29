package io.hhplus.lecture.presentation.controller;

import io.hhplus.lecture.domain.lecture_session.LectureSessionService;
import io.hhplus.lecture.presentation.dto.LectureApplyDto;
import io.hhplus.lecture.presentation.dto.LecturesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureSessionService lectureSessionService;

    /**
     * 특강 신청 구현
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
     * 특강 신청 결과 조회 구현
     *
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
     * 수강 신청이 가능한 특강 조회 구현
     *
     * @param userId 회원 ID
     * @return LecturesDto.Response 강의 정보
     */
    @GetMapping("")
    public LecturesDto.Response lectures(long userId) {
        return LecturesDto.of(lectureSessionService.lectures(userId));
    }
}
