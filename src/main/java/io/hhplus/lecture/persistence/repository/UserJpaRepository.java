package io.hhplus.lecture.persistence.repository;

import io.hhplus.lecture.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
