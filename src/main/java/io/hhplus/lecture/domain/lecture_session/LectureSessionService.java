package io.hhplus.lecture.domain.lecture_session;

import io.hhplus.lecture.common.exception.CustomException;
import io.hhplus.lecture.domain.lecture.Lecture;
import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyHistory;
import io.hhplus.lecture.domain.lecture_session_apply.LectureSessionApply;
import io.hhplus.lecture.domain.lecture_session_apply.LectureApplyHistoryRepository;
import io.hhplus.lecture.domain.lecture.LectureRepository;
import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyRepository;
import io.hhplus.lecture.domain.user.UserValidator;
import io.hhplus.lecture.common.enums.LectureApplyResultEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureSessionService {
    private final LectureRepository lectureRepository;
    private final LectureSessionRepository lectureSessionRepository;
    private final LectureSessionApplyRepository lectureSessionApplyRepository;
    private final LectureApplyHistoryRepository lectureApplyHistoryRepository;

    private final UserValidator userValidator;
    private final LectureSessionValidator lectureSessionValidator;

    @Transactional
    public boolean apply(Long userId, Long lectureSessionId) {
        LectureApplyResultEnum result = LectureApplyResultEnum.FAIL;

        userValidator.validateId(userId);

        LectureSession lectureSession = lectureSessionRepository.findWithPessimisticLockById(lectureSessionId)
                .orElseThrow(() -> new CustomException("존재하지 않는 강의 회차 정보"));

        try {
            if (lectureSession.isFull() || lectureSession.hasUserApplied(lectureSessionApplyRepository, userId)) {
                return false;
            }

            lectureSession.addApplyCount();
            lectureSessionRepository.save(lectureSession);

            lectureSessionApplyRepository.save(
                    LectureSessionApply.builder()
                            .lectureSessionId(lectureSessionId)
                            .userId(userId)
                            .build()
            );

            result = LectureApplyResultEnum.SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();

        } finally {
            LectureSessionApplyHistory lectureSessionApplyHistory = LectureSessionApplyHistory.builder()
                    .lectureSessionId(lectureSessionId)
                    .userId(userId)
                    .result(result)
                    .build();
            lectureApplyHistoryRepository.save(lectureSessionApplyHistory);
        }

        return true;
    }

    public boolean applyResult(Long userId, Long lectureSessionId) {
        userValidator.validateId(userId);
        lectureSessionValidator.validateId(lectureSessionId);

        return lectureSessionApplyRepository.existsByUserIdAndLectureSessionId(userId, lectureSessionId);
    }

    public List<Lecture> lectures(long userId) {
        userValidator.validateId(userId);

        List<Lecture> lectureList = lectureRepository.findAll();

        lectureList = lectureList.stream()
                .filter(lecture -> {
                    List<LectureSession> lectureSesionList = lectureSessionRepository.findByLectureIdAndIsFullIsFalse(lecture.getId());
                    lecture.setLectureSessionList(lectureSesionList.stream()
                            .filter(session -> !session.hasUserApplied(lectureSessionApplyRepository, userId))
                            .toList());

                    return !lecture.hasAnySession();
                })
                .toList();

        return lectureList;
    }
}
