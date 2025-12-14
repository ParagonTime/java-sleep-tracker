package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BadSleepingSessionCounterTest {

    private BadSleepingSessionCounter badSleepingSessionCounter;

    @BeforeEach
    void setUp() {
        badSleepingSessionCounter = new BadSleepingSessionCounter();
    }

    @Test
    void testApplyCountBadSessions() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 22, 0),
                        LocalDateTime.of(2023, 10, 31, 6, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 31, 23, 0),
                        LocalDateTime.of(2023, 11, 1, 5, 0),
                        SleepingStatus.BAD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 11, 1, 22, 0),
                        LocalDateTime.of(2023, 11, 2, 8, 0),
                        SleepingStatus.BAD
                )
        );

        SleepAnalysisResult result = badSleepingSessionCounter.apply(sessions);

        assertNotNull(result);
        assertTrue(result.description().contains("Bad sleeping session count:"));
        assertEquals(2, result.value());
    }

    @Test
    void testApplyCountNoBadSessions() {
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
                )
        );

        SleepAnalysisResult result = badSleepingSessionCounter.apply(sessions);

        assertNotNull(result);
        assertTrue(result.description().contains("Bad sleeping session count:"));
        assertEquals(0, result.value());
    }
}