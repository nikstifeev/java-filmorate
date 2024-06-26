package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Film.
 */

@Data
@Builder
public class Film {
    private Long id;

    @NotBlank(message = "название не может быть пустым")
    private String name;

    @Size(max = 200, message = "максимальная длина описания не больше 200 символов")
    private String description;

    @NotNull(message = "дата не может быть null")
    @PastOrPresent(message = "дата релиза не может быть в будущем")
    private LocalDate releaseDate;

    @NotNull(message = "продолжительность не может быть null")
    @Positive(message = "продолжительность фильма должна быть положительным числом")
    private Long duration;
}
