package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilmValidationTest {

    @Test
    void test_ValidFilm() {
        Film film = Film.builder()
                .name("Название фильма")
                .description("Описание фильма")
                .releaseDate(LocalDate.of(2024, 3, 4))
                .duration(120L)
                .build();

        assertTrue(film.getName() != null && !film.getName().isEmpty());
        assertTrue(film.getDescription().length() <= 200);
        assertTrue(film.getReleaseDate().isAfter(LocalDate.of(1895, 12, 28)));
        assertTrue(film.getDuration() > 0);
    }

    @Test
    void test_InvalidFilm_EmptyName() {
        Film film = Film.builder()
                .name("")
                .description("Описание фильма")
                .releaseDate(LocalDate.of(2024, 3, 4))
                .duration(120L)
                .build();

        assertFalse(film.getName() != null && !film.getName().isEmpty());
    }

    @Test
    void test_InvalidFilm_LongDescription() {
        Film film = Film.builder()
                .name("Название фильма")
                .description("Описание фильма".repeat(30))
                .releaseDate(LocalDate.of(2024, 3, 4))
                .duration(320L)
                .build();

        assertFalse(film.getDescription().length() <= 200);
    }

    @Test
    void test_InvalidFilm_ReleaseDate() {
        Film film = Film.builder()
                .name("Название фильма")
                .description("Описание фильма")
                .releaseDate(LocalDate.of(1500, 1, 1))
                .duration(120L)
                .build();

        assertFalse(film.getReleaseDate().isAfter(LocalDate.of(1895, 12, 28)));
    }

    @Test
    void test_InvalidFilm_NegativeDuration() {
        Film film = Film.builder()
                .name("Название фильма")
                .description("Описание фильма")
                .releaseDate(LocalDate.of(2024, 3, 4))
                .duration(-120L)
                .build();

        assertFalse(film.getDuration() > 0);
    }
}
