package io.hhplus.lecture.business.service;

import io.hhplus.lecture.business.domain.LectureApplyHistory;
import io.hhplus.lecture.business.domain.LectureSession;
import io.hhplus.lecture.business.repository.LectureApplyHistoryRepository;
import io.hhplus.lecture.business.repository.LectureSessionRepository;
import io.hhplus.lecture.business.validator.LectureSessionValidator;
import io.hhplus.lecture.business.validator.UserValidator;
import io.hhplus.lecture.common.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureSessionService {
    private final LectureSessionRepository lectureSessionRepository;
    private final LectureApplyHistoryRepository lectureApplyHistoryRepository;

    private final UserValidator userValidator;
    private final LectureSessionValidator lectureSessionValidator;

    @Transactional
    public boolean apply(Long userId, Long lectureSessionId) {
        // userId validation
        userValidator.validateId(userId);

        // find lectureSession
        LectureSession lectureSession = lectureSessionRepository.findById(lectureSessionId)
                .orElseThrow(() -> new CustomException("존재하지 않는 강의 회차"));

        // lectureSession capacity validation
        if(Objects.equals(lectureSession.getApplyCount(), lectureSession.getCapacity())) {
            return false;
        }

        // applyCount 1 증가시킨 후 lectureSession update
        lectureSession.setApplyCount(lectureSession.getApplyCount() + 1);
        lectureSessionRepository.save(lectureSession);

        // lectureApplyHistory save
        LectureApplyHistory lectureApplyHistory = LectureApplyHistory.builder()
                .lectureSessionId(lectureSessionId)
                .userId(userId)
                .build();

        lectureApplyHistoryRepository.save(lectureApplyHistory);

        return true;
    }

    public boolean applyResult(Long userId, Long lectureSessionId) {
        // userId validation
        userValidator.validateId(userId);

        // lectureSessionId validation
        lectureSessionValidator.validateId(lectureSessionId);

        // find lectureApplyHistory
        Optional<LectureApplyHistory> lectureApplyHistory = lectureApplyHistoryRepository.findByUserIdAndLectureSessionId(userId, lectureSessionId);

        return lectureApplyHistory.isPresent();
    }
}
