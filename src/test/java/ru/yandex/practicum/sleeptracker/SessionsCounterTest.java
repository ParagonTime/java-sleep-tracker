package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionsCounterTest {

    private SessionsCounter sessionsCounter;

    @BeforeEach
    void setUp() {
        sessionsCounter = new SessionsCounter();
    }

    @Test
    void testApplyCountMultipleSessions() {
        List<SleepingSession> sessions = Arrays.asList(
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 30, 22, 0),
                        LocalDateTime.of(2023, 10, 31, 6, 0),
                        SleepingStatus.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 10, 31, 23, 0),
                        LocalDateTime.of(2023, 11, 1, 7, 0),
                        SleepingStatus.BAD
                ),
                new SleepingSession(
                        LocalDateTime.of(2023, 11, 1, 22, 30),
                        LocalDateTime.of(2023, 11, 2, 8, 0),
                        SleepingStatus.GOOD
                )
        );

        SleepAnalysisResult result = sessionsCounter.apply(sessions);

        assertNotNull(result);
        assertTrue(result.toString().contains("Sleeping sessions in log:"));
        assertTrue(result.toString().contains("3"));
    }

    @Test
    void testApplyCountEmptyList() {
        List<SleepingSession> sessions = Collections.emptyList();

        SleepAnalysisResult result = sessionsCounter.apply(sessions);

        assertNotNull(result);
        assertTrue(result.toString().contains("Sleeping sessions in log:"));
        assertTrue(result.toString().contains("0"));
    }
}