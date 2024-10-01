package io.hhplus.lecture.infrastructure.lecture_session_apply;

import io.hhplus.lecture.domain.lecture_session_apply.LectureSessionApply;
import io.hhplus.lecture.domain.lecture_session_history.LectureSessionApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureSessionApplyRepositoryImpl implements LectureSessionApplyRepository {
    private final LectureSessionApplyMapper lectureSessionApplyMapper;

    private final LectureSessionApplyJpaRepository lectureSessionApplyJpaRepository;

    @Override
    public boolean existsByUserIdAndLectureSessionId(Long userId, Long lectureSessionId) {
        return lectureSessionApplyJpaRepository.existsByUserIdAndLectureSessionId(userId, lectureSessionId);
    }

    @Override
    public LectureSessionApply save(LectureSessionApply lectureSessionApply) {
        return lectureSessionApplyMapper.toDomain(
                lectureSessionApplyJpaRepository.save(
                        lectureSessionApplyMapper.toEntity(lectureSessionApply)
                )
        );
    }

    @Override
    public List<LectureSessionApply> getByUserId(Long userId) {
        return lectureSessionApplyJpaRepository.findByUserId(userId).stream()
                .map(lectureSessionApplyMapper::toDomain)
                .toList();
    }
}
