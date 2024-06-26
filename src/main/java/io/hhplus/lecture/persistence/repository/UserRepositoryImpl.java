package io.hhplus.lecture.persistence.repository;

import io.hhplus.lecture.business.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
    }
}
