package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testApplyDoveTypeWhenMixedPatterns() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 14, 0),
                        LocalDateTime.of(2023, 10, 30, 15, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 23, 45),
                        LocalDateTime.of(2023, 10, 31, 9, 15),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 31, 21, 30),
                        LocalDateTime.of(2023, 11, 1, 6, 45),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 11, 1, 22, 30),
                        LocalDateTime.of(2023, 11, 2, 7, 30),
                        SleepingStatus.GOOD
                )
        );

        SleepAnalysisResult result = usersClassificator.apply(sessions);

        assertNotNull(result);
        assertTrue(result.toString().contains("Dove"));
    }
}