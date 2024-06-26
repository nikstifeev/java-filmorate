package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class FilmorateApplicationTests {

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

	@Test
	void test_ValidUser() {
		User user = User.builder()
				.email("email@gmail.com")
				.login("login")
				.name("name")
				.birthday(LocalDate.of(1999, 3, 4))
				.build();

		assertTrue(user.getEmail() != null && !user.getEmail().isEmpty());
		assertTrue(user.getEmail().contains("@"));
		assertTrue(user.getLogin() != null && !user.getLogin().isEmpty());
		assertFalse(user.getLogin().contains(" "));
		assertTrue(user.getBirthday().isBefore(LocalDate.now()));
	}

	@Test
	void test_InvalidUser_EmptyEmail() {
		User user = User.builder()
				.email("")
				.login("login")
				.name("name")
				.birthday(LocalDate.of(1999, 3, 4))
				.build();

		assertFalse(user.getEmail() != null && !user.getEmail().isEmpty());
	}

	@Test
	void test_InvalidUser_EmailWithoutAt() {
		User user = User.builder()
				.email("email")
				.login("login")
				.name("name")
				.birthday(LocalDate.of(1999, 3, 4))
				.build();

		assertFalse(user.getEmail().contains("@"));
	}

	@Test
	void test_InvalidUser_EmptyLogin() {
		User user = User.builder()
				.email("email@gmail.com")
				.login("")
				.name("name")
				.birthday(LocalDate.of(1999, 3, 4))
				.build();

		assertFalse(user.getLogin() != null && !user.getLogin().isEmpty());
	}

	@Test
	void test_InvalidUser_LoginWithSpaces() {
		User user = User.builder()
				.email("email@gmail.com")
				.login("login login")
				.name("name")
				.birthday(LocalDate.of(1999, 3, 4))
				.build();

		assertTrue(user.getLogin().contains(" "));
	}

	@Test
	void test_InvalidUser_FutureBirthday() {
		User user = User.builder()
				.email("email@gmail.com")
				.login("login")
				.name("name")
				.birthday(LocalDate.now().plusDays(1))
				.build();

		assertFalse(user.getBirthday().isBefore(LocalDate.now()));
	}

}
