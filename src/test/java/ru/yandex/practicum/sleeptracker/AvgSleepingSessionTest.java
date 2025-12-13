package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvgSleepingSessionTest {

    private AvgSleepingSession avgSleepingSession;

    @BeforeEach
    void setUp() {
        avgSleepingSession = new AvgSleepingSession();
    }

    @Test
    void testApplyCalculateAverageDuration() {
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

        SleepAnalysisResult result = avgSleepingSession.apply(sessions);

        assertNotNull(result);
        assertTrue(result.description().contains("Avg sleeping session in minutes:"));

        int avgMinutes = (8 * 60 + 6 * 60 + 10 * 60) / 3;
        assertEquals(avgMinutes, result.value());
    }

    @Test
    void testApplyCalculateAverageWithSingleSession() {
        List<SleepingSession> sessions = Collections.singletonList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 22, 0),
                        LocalDateTime.of(2023, 10, 31, 6, 0),
                        SleepingStatus.GOOD
                )
        );

        SleepAnalysisResult result = avgSleepingSession.apply(sessions);

        assertNotNull(result);

        int avgMinutes = 8 * 60;
        assertEquals(avgMinutes, result.value());
    }
}