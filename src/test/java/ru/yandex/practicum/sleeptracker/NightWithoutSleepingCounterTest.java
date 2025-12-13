package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NightWithoutSleepingCounterTest {

    private NightWithoutSleepingCounter counter;

    @BeforeEach
    void setUp() {
        counter = new NightWithoutSleepingCounter();
    }

    @Test
    void testApplyCalculateNightsWithoutSleep() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 22, 0),
                        LocalDateTime.of(2023, 10, 31, 6, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 11, 1, 23, 0),
                        LocalDateTime.of(2023, 11, 2, 7, 0),
                        SleepingStatus.GOOD
                )
        );

        SleepAnalysisResult result = counter.apply(sessions);

        assertNotNull(result);
        assertTrue(result.toString().contains("Night without sleeping count:"));

        int dayCount = 3;
        long sleepingNightsCount = 2;
        int expectedNightsWithoutSleep = dayCount - (int) sleepingNightsCount;

        assertEquals(expectedNightsWithoutSleep, result.value());
    }

    @Test
    void testApplyWithDaytimeSleepSessions() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 14, 0),
                        LocalDateTime.of(2023, 10, 30, 15, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 31, 22, 0),
                        LocalDateTime.of(2023, 11, 1, 6, 0),
                        SleepingStatus.GOOD
                )
        );

        SleepAnalysisResult result = counter.apply(sessions);

        assertNotNull(result);

        int dayCount = 2;
        long sleepingNightsCount = 1;
        int expectedNightsWithoutSleep = dayCount - (int) sleepingNightsCount;

        assertEquals(expectedNightsWithoutSleep, result.value());
    }
}