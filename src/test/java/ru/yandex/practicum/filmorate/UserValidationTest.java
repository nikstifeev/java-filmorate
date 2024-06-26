package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidationTest {

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
