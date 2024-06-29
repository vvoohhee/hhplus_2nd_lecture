package io.hhplus.lecture.domain.user;

import io.hhplus.lecture.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validateId(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new CustomException("존재하지 않는 유저");
        }
    }
}
