package io.hhplus.lecture.business.repository;

import java.util.Optional;

public interface UserRepository {
    boolean existsById(Long id);
}
