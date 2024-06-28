package io.hhplus.lecture.domain.service;

import io.hhplus.lecture.domain.lecture.Lecture;
import io.hhplus.lecture.domain.lecture_session_apply.LectureSessionApply;
import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyHistory;
import io.hhplus.lecture.domain.lecture_session.LectureSession;
import io.hhplus.lecture.domain.lecture_session.LectureSessionService;
import io.hhplus.lecture.domain.lecture_session_apply.LectureApplyHistoryRepository;
import io.hhplus.lecture.domain.lecture.LectureRepository;
import io.hhplus.lecture.domain.lecture_session.LectureSessionRepository;
import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyRepository;
import io.hhplus.lecture.domain.user.UserRepository;
import io.hhplus.lecture.domain.lecture_session.LectureSessionValidator;
import io.hhplus.lecture.domain.user.UserValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

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

    @Mock
    LectureSessionApplyRepository lectureSessionApplyRepository;

    @InjectMocks
    private LectureSessionService lectureSessionService;

    @Test
    @DisplayName("수강신청_테스트_성공")
    void applyTest_성공() {
        // given
        Long userId = 1L;
        Long lectureSessionId = 1L;

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
        Optional<LectureSession> lectureSession = Optional.of(
                new LectureSession(lectureSessionId, capacity, applyCount, false)
        );

        // when
        when(lectureSessionRepository.findWithPessimisticLockById(lectureSessionId)).thenReturn(lectureSession);

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

        LectureSessionApplyHistory lectureSessionApplyHistory = LectureSessionApplyHistory.builder()
                .id(1L)
                .lectureSessionId(lectureSessionId)
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();

        // when
        when(lectureApplyHistoryRepository.findByUserIdAndLectureSessionId(userId, lectureSessionId)).thenReturn(Optional.ofNullable(lectureSessionApplyHistory));

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

    @Test
    @DisplayName("수강신청_가능한_특강_조회_성공_테스트")
    void lecturesTest_수강신청_가능한_특강_조회_성공_테스트() {
        // given
        long userId = 1L;

        Integer capacity = 10;

        LectureSession session1 = new LectureSession(1L, capacity, 6, false);
        LectureSession session2 = new LectureSession(2L, capacity, 6, false);
        LectureSession session3 = new LectureSession(3L, capacity, 6, false);
        LectureSession session4 = new LectureSession(4L, capacity, capacity, true);

        Lecture lecture1 = new Lecture(1L, "Lecture 1", Arrays.asList(session1, session2));
        Lecture lecture2 = new Lecture(2L, "Lecture 2", List.of(session3));
        Lecture lecture3 = new Lecture(3L, "Lecture 3", List.of(session4));

        List<Lecture> lectureList = new ArrayList<>();
        lectureList.add(lecture1);
        lectureList.add(lecture2);
        lectureList.add(lecture3);

        LectureSessionApply applied = new LectureSessionApply(1L, session1.getId(), userId);

        // when
        when(lectureSessionRepository.findByLectureIdAndIsFullIsFalse(session1.getId())).thenReturn(List.of(session1));
        when(lectureSessionRepository.findByLectureIdAndIsFullIsFalse(session2.getId())).thenReturn(List.of(session2));
        when(lectureSessionRepository.findByLectureIdAndIsFullIsFalse(session3.getId())).thenReturn(List.of(session3));
        when(lectureSessionRepository.findByLectureIdAndIsFullIsFalse(session4.getId())).thenReturn(Collections.emptyList());

        when(lectureSessionApplyRepository.getByUserId(userId)).thenReturn(List.of(applied));

        // then
        List<Lecture> expected = new ArrayList<>();
        expected.add(new Lecture(1L, "Lecture 1", List.of(session2)));
        expected.add(lecture2);

        List<Lecture> actual = lectureSessionService.lectures(userId);

        assertTrue(actual.containsAll(expected));

    }


}
