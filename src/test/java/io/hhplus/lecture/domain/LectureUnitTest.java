package io.hhplus.lecture.domain;

import io.hhplus.lecture.domain.lecture.Lecture;
import io.hhplus.lecture.domain.lecture_session.LectureSession;
import io.hhplus.lecture.domain.lecture_session.LectureSessionRepository;
import io.hhplus.lecture.domain.lecture_session_apply.LectureSessionApply;
import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyRepository;
import io.hhplus.lecture.domain.user.UserRepository;
import io.hhplus.lecture.domain.lecture_session.LectureSessionValidator;
import io.hhplus.lecture.domain.user.UserValidator;
import io.hhplus.lecture.common.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LectureSessionRepository lectureSessionRepository;

    @Mock
    private LectureSessionApplyRepository lectureSessionApplyRepository;

    @InjectMocks
    private UserValidator userValidator;

    @InjectMocks
    private LectureSessionValidator lectureSessionValidator;


    @Test
    @DisplayName("수강신청_테스트_실패_존재하지_않는_회원")
    void applyTest_실패_존재하지_않는_회원() {
        // given
        Long userId = 999L;

        // when
        when(userRepository.existsById(userId)).thenReturn(false);

        // then
        assertThrows(CustomException.class, () -> userValidator.validateId(userId));
    }

    @Test
    @DisplayName("수강신청_테스트_실패_존재하지_않는_특강")
    void applyTest_실패_존재하지_않는_특강() {
        // given
        Long lectureSessionId = 1L;

        // when - then
        when(lectureSessionRepository.existsById(lectureSessionId)).thenReturn(false);
        assertThrows(CustomException.class, () -> lectureSessionValidator.validateId(lectureSessionId));
    }

    @Test
    @DisplayName("Lecture_도메인클래스_강의_회차_존재_여부_확인")
    void Lecture_hasAnySession_강의_회차_존재_여부_확인() {
        // given
        Lecture lecture = new Lecture(1L, "강의제목", new ArrayList<>());

        // when - then
        assertFalse(lecture.hasAnySession());
    }

    @Test
    @DisplayName("Lecture_도메인클래스_강의_회차_리스트가_널")
    void Lecture_hasAnySession_강의_회차_리스트가_널() {
        // given
        Lecture lecture = new Lecture(1L, "강의제목", null);

        // when - then
        assertFalse(lecture.hasAnySession());
    }

    @Test
    @DisplayName("Lecture_도메인클래스_강의_회차_존재")
    void Lecture_hasAnySession_강의_회차_존재() {
        // given
        LectureSession lectureSession = new LectureSession(1L, 10, 0, false);
        Lecture lecture = new Lecture(1L, "강의제목", List.of(lectureSession));

        // when - then
        assertTrue(lecture.hasAnySession());
    }

    @Test
    @DisplayName("LectureSession_도메인클래스_applyCount_증가")
    void LectureSession_addApplyCount_applyCount_증가() {
        // given
        Integer applyCount = 0;
        LectureSession lectureSession = new LectureSession(1L, 10, applyCount, false);

        // when
        lectureSession.addApplyCount();

        // then
        assertEquals(++applyCount, lectureSession.getApplyCount());
    }

    @Test
    @DisplayName("LectureSession_hasUserApplied_신청함")
    void LectureSession_hasUserApplied_신청함() {
        // given
        Long userId = 1L;
        LectureSession lectureSession = new LectureSession(1L, 10, 0, false);
        List<LectureSessionApply> applyList = new ArrayList<>();
        applyList.add(new LectureSessionApply(1L, userId, lectureSession.getId()));


        // when
        when(lectureSessionApplyRepository.getByUserId(userId)).thenReturn(applyList);
        boolean result = lectureSession.hasUserApplied(lectureSessionApplyRepository, userId);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("LectureSession_hasUserApplied_신청안함")
    void LectureSession_hasUserApplied_신청안함() {
        // given
        Long userId = 1L;
        LectureSession lectureSession = new LectureSession(1L, 10, 0, false);
        List<LectureSessionApply> applyList = new ArrayList<>();

        // when
        when(lectureSessionApplyRepository.getByUserId(userId)).thenReturn(applyList);
        boolean result = lectureSession.hasUserApplied(lectureSessionApplyRepository, userId);

        // then
        assertFalse(result);
    }
}
