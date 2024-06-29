package io.hhplus.lecture.infrastructure.lecture_session_apply_history;

import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyHistory;
import io.hhplus.lecture.domain.lecture_session_apply.LectureApplyHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LectureSessionApplyHistoryRepositoryImpl implements LectureApplyHistoryRepository {

    private final LectureSessionApplyHistoryJpaRepository lectureApplyHistoryJpaRepository;
    private final LectureSessionApplyHistoryMapper lectureSessionApplyHistoryMapper;

    @Override
    public Optional<LectureSessionApplyHistory> findByUserIdAndLectureSessionId(Long userId, Long lectureSessionId) {
        return lectureApplyHistoryJpaRepository
                .findByUserIdAndLectureSessionId(userId, lectureSessionId)
                .map(lectureSessionApplyHistoryMapper::toDomain);
    }

    @Override
    public LectureSessionApplyHistory save(LectureSessionApplyHistory lectureSessionApplyHistory) {
        LectureSessionApplyHistoryEntity entity = lectureSessionApplyHistoryMapper.toEntity(lectureSessionApplyHistory);
        entity = lectureApplyHistoryJpaRepository.save(entity);
        return lectureSessionApplyHistoryMapper.toDomain(entity);
    }
}
