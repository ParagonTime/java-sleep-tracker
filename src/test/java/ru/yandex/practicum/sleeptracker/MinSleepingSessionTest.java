package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinSleepingSessionTest {

    private MinSleepingSession minSleepingSession;

    @BeforeEach
    void setUp() {
        minSleepingSession = new MinSleepingSession();
    }

    @Test
    void testApplyFindMinSleepDuration() {
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

        SleepAnalysisResult result = minSleepingSession.apply(sessions);

        assertNotNull(result);
        assertTrue(result.description().contains("Min sleeping session in minutes:"));

        int expectedMinMinutes = 6 * 60;
        assertEquals(expectedMinMinutes, result.value());
    }

    @Test
    void testApplyFindMinWithShortSession() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 22, 0),
                        LocalDateTime.of(2023, 10, 31, 6, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 31, 23, 0),
                        LocalDateTime.of(2023, 11, 1, 23, 30),
                        SleepingStatus.BAD
                )
        );

        SleepAnalysisResult result = minSleepingSession.apply(sessions);

        assertNotNull(result);

        int expectedMinMinutes = 8 * 60;
        assertEquals(expectedMinMinutes, result.value());
    }
}