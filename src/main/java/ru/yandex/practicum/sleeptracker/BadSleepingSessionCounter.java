package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadSleepingSessionCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String BAD_SLEEPING_SESSION_MESSAGE = "Bad sleeping session count: ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long count = sleepingSessions.stream()
                .map(sleepingSession -> sleepingSession.status)
                .filter(status -> status.equals(SleepingStatus.BAD))
                .count();

        return new SleepAnalysisResult(
                BAD_SLEEPING_SESSION_MESSAGE,
                (int) count);
    }
}

