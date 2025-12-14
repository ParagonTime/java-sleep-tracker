package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsersClassificatorTest {

    private UsersClassificator usersClassificator;

    @BeforeEach
    void setUp() {
        usersClassificator = new UsersClassificator();
    }

    @Test
    void testApplyOwlTypeWhenMostSessionsAreOwlPattern() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 23, 30),
                        LocalDateTime.of(2023, 10, 31, 9, 30),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 31, 0, 15),
                        LocalDateTime.of(2023, 11, 1, 9, 15),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 11, 1, 21, 0),
                        LocalDateTime.of(2023, 11, 2, 7, 30),
                        SleepingStatus.GOOD
                )
        );

        SleepAnalysisResult result = usersClassificator.apply(sessions);

        assertNotNull(result);
        assertTrue(result.toString().contains("Owl"));
        assertTrue(result.toString().contains("2"));
    }
}