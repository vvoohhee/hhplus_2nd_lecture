package io.hhplus.lecture.persistence.repository;

import io.hhplus.lecture.business.domain.LectureApplyHistory;
import io.hhplus.lecture.business.repository.LectureApplyHistoryRepository;
import io.hhplus.lecture.persistence.entity.LectureApplyHistoryEntity;
import io.hhplus.lecture.persistence.mapper.LectureApplyHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LectureApplyHistoryRepositoryImpl implements LectureApplyHistoryRepository {

    private final LectureApplyHistoryJpaRepository lectureApplyHistoryJpaRepository;
    private final LectureApplyHistoryMapper lectureApplyHistoryMapper;

    @Override
    public Optional<LectureApplyHistory> findById(Long id) {
        return lectureApplyHistoryJpaRepository
                .findById(id)
                .map(lectureApplyHistoryMapper::toDomain);
    }

    @Override
    public List<LectureApplyHistory> findByLectureSessionId(Long lectureSessionId) {
        return lectureApplyHistoryJpaRepository
                .findByLectureSessionId(lectureSessionId)
                .stream()
                .map(lectureApplyHistoryMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<LectureApplyHistory> findByUserIdAndLectureSessionId(Long userId, Long lectureSessionId) {
        return lectureApplyHistoryJpaRepository
                .findByUserIdAndLectureSessionId(userId, lectureSessionId)
                .map(lectureApplyHistoryMapper::toDomain);
    }

    @Override
    public LectureApplyHistory save(LectureApplyHistory lectureApplyHistory) {
        LectureApplyHistoryEntity entity = lectureApplyHistoryMapper.toEntity(lectureApplyHistory);
        entity = lectureApplyHistoryJpaRepository.save(entity);
        return lectureApplyHistoryMapper.toDomain(entity);
    }
}
