package io.hhplus.lecture.business;

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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LectureSessionRepository lectureSessionRepository;

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
}
