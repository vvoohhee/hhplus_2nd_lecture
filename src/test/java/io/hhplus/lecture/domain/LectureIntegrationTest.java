package io.hhplus.lecture.domain;

import io.hhplus.lecture.common.enums.LectureApplyResultEnum;
import io.hhplus.lecture.domain.lecture.Lecture;
import io.hhplus.lecture.domain.lecture_session.LectureSessionService;
import io.hhplus.lecture.common.exception.CustomException;
import io.hhplus.lecture.infrastructure.lecture_session_apply.LectureSessionApplyEntity;
import io.hhplus.lecture.infrastructure.lecture_session_apply.LectureSessionApplyJpaRepository;
import io.hhplus.lecture.infrastructure.lecture_session_apply.LectureSessionApplyMapper;
import io.hhplus.lecture.infrastructure.lecture_session_apply_history.LectureSessionApplyHistoryEntity;
import io.hhplus.lecture.infrastructure.lecture.LectureEntity;
import io.hhplus.lecture.infrastructure.lecture_session.LectureSessionEntity;
import io.hhplus.lecture.infrastructure.user.UserEntity;
import io.hhplus.lecture.infrastructure.lecture_session_apply_history.LectureSessionApplyHistoryJpaRepository;
import io.hhplus.lecture.infrastructure.lecture.LectureJpaRepository;
import io.hhplus.lecture.infrastructure.lecture_session.LectureSessionJpaRepository;
import io.hhplus.lecture.infrastructure.user.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LectureIntegrationTest {

    private final LectureSessionService lectureSessionService;

    private final UserJpaRepository userJpaRepository;
    private final LectureJpaRepository lectureJpaRepository;
    private final LectureSessionJpaRepository lectureSessionJpaRepository;
    private final LectureSessionApplyHistoryJpaRepository lectureApplyHistoryJpaRepository;
    private final LectureSessionApplyJpaRepository lectureApplyJpaRepository;

    private Long userId;
    @Autowired
    private LectureSessionApplyMapper lectureSessionApplyMapper;
    @Autowired
    private LectureSessionApplyJpaRepository lectureSessionApplyJpaRepository;

    @Autowired
    public LectureIntegrationTest(LectureSessionService lectureSessionService,
                                  UserJpaRepository userJpaRepository,
                                  LectureJpaRepository lectureJpaRepository,
                                  LectureSessionJpaRepository lectureSessionJpaRepository,
                                  LectureSessionApplyJpaRepository lectureApplyJpaRepository,
                                  LectureSessionApplyHistoryJpaRepository lectureApplyHistoryJpaRepository
    ) {
        this.lectureSessionService = lectureSessionService;
        this.userJpaRepository = userJpaRepository;
        this.lectureJpaRepository = lectureJpaRepository;
        this.lectureSessionJpaRepository = lectureSessionJpaRepository;
        this.lectureApplyJpaRepository = lectureApplyJpaRepository;
        this.lectureApplyHistoryJpaRepository = lectureApplyHistoryJpaRepository;
    }

    @BeforeEach
    @DisplayName("회원, 특강 데이터 생성")
    void setUp() {
        UserEntity user = userJpaRepository.save(new UserEntity());

        int capacity = 10;

        LectureEntity lecture1 = new LectureEntity("Lecture 1");
        LectureEntity lecture2 = new LectureEntity("Lecture 2");
        LectureEntity lecture3 = new LectureEntity("Lecture 3");

        lectureJpaRepository.save(lecture1);
        lectureJpaRepository.save(lecture2);
        lectureJpaRepository.save(lecture3);

        LectureSessionEntity session1 = new LectureSessionEntity(lecture1.getId(), capacity, 6, false);
        LectureSessionEntity session2 = new LectureSessionEntity(lecture1.getId(), capacity, 6, false);
        LectureSessionEntity session3 = new LectureSessionEntity(lecture2.getId(), capacity, 6, false);
        LectureSessionEntity session4 = new LectureSessionEntity(lecture3.getId(), capacity, capacity, true);

        lectureSessionJpaRepository.save(session1);
        lectureSessionJpaRepository.save(session2);
        lectureSessionJpaRepository.save(session3);
        lectureSessionJpaRepository.save(session4);

        LectureSessionApplyEntity applied = new LectureSessionApplyEntity(session1.getId(), user.getId());

        lectureApplyJpaRepository.save(applied);
        userId = user.getId();
    }

    @Test
    @DisplayName("특강_신청_테스트_성공")
    void applyTest_특강_신청_테스트_성공() {
        // given
        long userId = 1L;
        long lectureSessionId = 1L;

        // when - then
        boolean result = lectureSessionService.apply(userId, lectureSessionId);
        assertTrue(result);
    }

    @Test
    @DisplayName("특강_신청_테스트_실패_존재하지_않는_회원_아이디")
    void applyTest_특강_신청_테스트_실패_존재하지_않는_회원_아이디() {
        // given
        long userId = 100L;
        long lectureSessionId = 1L;

        // when - then
        assertThrows(CustomException.class, () -> lectureSessionService.apply(userId, lectureSessionId));
    }

    @Test
    @DisplayName("특강_신청_테스트_실패_존재하지_않는_특강_아이디")
    void applyTest_특강_신청_테스트_실패_존재하지_않는_특강_아이디() {
        // given
        long userId = 1L;
        long lectureSessionId = 100L;

        // when - then
        assertThrows(CustomException.class, () -> lectureSessionService.apply(userId, lectureSessionId));
    }

    @Test
    @DisplayName("특강_신청_테스트_실패_정원_초과")
    void applyTest_특강_신청_테스트_실패_정원_초과() {
        // given
        int capacity = 10;
        int applyCount = 0;
        LectureApplyResultEnum resultEnum = LectureApplyResultEnum.FAIL;

        LectureEntity lecture = lectureJpaRepository.save(new LectureEntity("동시성특강"));
        LectureSessionEntity lectureSession = lectureSessionJpaRepository.save(new LectureSessionEntity(lecture.getId(), capacity, applyCount, false));

        for (int i = applyCount; i < capacity; i++) {
            UserEntity user = userJpaRepository.save(new UserEntity());

            try {
                applyCount++;
                lectureSessionApplyJpaRepository.save(new LectureSessionApplyEntity(lectureSession.getId(), user.getId()));
                lectureSessionJpaRepository.save(new LectureSessionEntity(lectureSession.getId(), lectureSession.getLectureId(), capacity, applyCount));

                resultEnum = LectureApplyResultEnum.SUCCESS;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                lectureApplyHistoryJpaRepository.save(LectureSessionApplyHistoryEntity.builder()
                        .userId(user.getId())
                        .lectureSessionId(lectureSession.getId())
                        .result(resultEnum)
                        .createdAt(LocalDateTime.now())
                        .build());
            }

        }

        UserEntity user = userJpaRepository.save(new UserEntity());

        // then
        boolean result = lectureSessionService.apply(user.getId(), lectureSession.getId());
        assertFalse(result);
    }

    @Test
    @DisplayName("수강신청 가능한 특강 리스트 조회")
    void lecturesTest_수강신청_가능한_특강_리스트_조회() {
        List<Lecture> lectureList = lectureSessionService.lectures(userId);
        assertEquals(2, lectureList.size());
    }
}
