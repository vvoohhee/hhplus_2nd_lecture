package io.hhplus.lecture.business.validator;

import io.hhplus.lecture.business.repository.LectureSessionRepository;
import io.hhplus.lecture.common.CustomException;
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
