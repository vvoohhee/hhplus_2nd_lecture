package io.hhplus.lecture.business.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LectureSession {
    @NotNull
    private Long id;

    @NotNull
    private int capacity;

    @NotNull
    private int applyCount;
}
