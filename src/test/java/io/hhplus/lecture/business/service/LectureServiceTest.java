package io.hhplus.lecture.business.service;

import io.hhplus.lecture.business.domain.LectureApplyHistory;
import io.hhplus.lecture.business.domain.LectureSession;
import io.hhplus.lecture.business.repository.LectureApplyHistoryRepository;
import io.hhplus.lecture.business.repository.LectureRepository;
import io.hhplus.lecture.business.repository.LectureSessionRepository;
import io.hhplus.lecture.business.repository.UserRepository;
import io.hhplus.lecture.business.validator.LectureSessionValidator;
import io.hhplus.lecture.business.validator.UserValidator;
import io.hhplus.lecture.common.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureServiceTest {
    @Mock
    private UserValidator userValidator;

    @Mock
    private LectureSessionValidator lectureSessionValidator;

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LectureApplyHistoryRepository lectureApplyHistoryRepository;

    @Mock
    LectureSessionRepository lectureSessionRepository;

    @InjectMocks
    private LectureSessionService lectureSessionService;

    @Test
    @DisplayName("수강신청_테스트_성공")
    void applyTest_성공() {
        // given
        Long userId = 1L;
        Long lectureSessionId = 1L;

        // when
//        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(userId));
//        when(lectureRepository.findBySessionId(lecture.getSessionId())).thenReturn(Optional.of(lecture));

        // then
        Boolean result = lectureSessionService.apply(userId, lectureSessionId);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("수강신청_테스트_실패_정원_초과")
    void applyTest_실패_회원_정원_초과() {
        // given
        long lectureSessionId = 1L;
        long userId = 1L;
        int capacity = 30;
        int applyCount = 30;
        Optional<LectureSession> lectureSession = Optional.of(new LectureSession(lectureSessionId, capacity, applyCount));

        // when
        when(lectureSessionRepository.findById(lectureSessionId)).thenReturn(lectureSession);

        // then
        boolean result = lectureSessionService.apply(userId, lectureSessionId);
        assertFalse(result);
    }

    @Test
    @DisplayName("수강신청_결과_조회_테스트_성공")
    void applyResultTest_성공() {
        // given
        long lectureSessionId = 1L;
        long userId = 1L;

        LectureApplyHistory lectureApplyHistory = LectureApplyHistory.builder()
                .id(1L)
                .lectureSessionId(lectureSessionId)
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();

        // when
        when(lectureApplyHistoryRepository.findByUserIdAndLectureSessionId(userId, lectureSessionId)).thenReturn(Optional.ofNullable(lectureApplyHistory));

        // then
        boolean result = lectureSessionService.applyResult(userId, lectureSessionId);
        assertTrue(result);
    }

    @Test
    @DisplayName("수강신청_결과_조회_테스트_실패_히스토리_조회_실패")
    void applyResultTest_실패_히스토리_조회_실패() {
        // given
        long lectureSessionId = 1L;
        long userId = 1L;

        // when
        when(lectureApplyHistoryRepository.findByUserIdAndLectureSessionId(userId, lectureSessionId)).thenReturn(Optional.empty());

        // then
        boolean result = lectureSessionService.applyResult(userId, lectureSessionId);
        assertFalse(result);
    }


}
