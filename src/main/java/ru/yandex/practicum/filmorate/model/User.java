package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * User.
 */

@Data
@Builder
public class User {
    private Long id;

    @NotBlank(message = "электронная почта не может быть пустой")
    @Email(message = "неверный формат электронной почты")
    private String email;

    @NotBlank(message = "логин не может быть пустым")
    @Pattern(regexp = "\\S+", message = "логин не может содержать пробелы")
    private String login;

    private String name;

    @NotNull(message = "дата не может быть null")
    @Past(message = "дата рождения не может быть в будущем")
    private LocalDate birthday;
}
