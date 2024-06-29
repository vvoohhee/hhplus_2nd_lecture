package io.hhplus.lecture.domain.user;

public interface UserRepository {
    boolean existsById(Long id);
}
