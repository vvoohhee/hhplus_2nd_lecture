package io.hhplus.lecture.infrastructure.lecture_session;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface LectureSessionJpaRepository extends JpaRepository<LectureSessionEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<LectureSessionEntity> findWithPessimisticLockById(Long id);

    List<LectureSessionEntity> findByLectureId(Long lectureId);

    List<LectureSessionEntity> findByLectureIdAndIsFullIsFalse(Long lectureId);
}
