package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxSleepingSessionTest {

    private MaxSleepingSession maxSleepingSession;

    @BeforeEach
    void setUp() {
        maxSleepingSession = new MaxSleepingSession();
    }

    @Test
    void testApplyFindMaxSleepDuration() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 22, 0),
                        LocalDateTime.of(2023, 10, 31, 6, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 31, 23, 0),
                        LocalDateTime.of(2023, 11, 1, 5, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 11, 1, 22, 0),
                        LocalDateTime.of(2023, 11, 2, 8, 0),
                        SleepingStatus.GOOD
                )
        );

        SleepAnalysisResult result = maxSleepingSession.apply(sessions);

        assertNotNull(result);
        assertTrue(result.description().contains("Max sleeping session in minutes:"));

        int expectedMaxMinutes = 10 * 60;
        assertEquals(expectedMaxMinutes, result.value());
    }

    @Test
    void testApplyFindMaxWithLongSession() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 22, 0),
                        LocalDateTime.of(2023, 10, 31, 6, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 31, 23, 0),
                        LocalDateTime.of(2023, 11, 1, 23, 0),
                        SleepingStatus.BAD
                )
        );

        SleepAnalysisResult result = maxSleepingSession.apply(sessions);

        assertNotNull(result);

        int expectedMaxMinutes = 24 * 60;
        assertEquals(expectedMaxMinutes, result.value());
    }
}