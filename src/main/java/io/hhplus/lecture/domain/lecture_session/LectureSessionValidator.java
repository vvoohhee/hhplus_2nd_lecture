package io.hhplus.lecture.domain.lecture_session;

import io.hhplus.lecture.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LectureSessionValidator {
    private final LectureSessionRepository lectureSessionRepository;

    public void validateId(Long lectureSessionId) {
        if(!lectureSessionRepository.existsById(lectureSessionId)) {
            throw new CustomException("존재하지 않는 강의 회차 정보");
        }
    }
}
