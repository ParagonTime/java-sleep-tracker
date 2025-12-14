package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSleepingSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String MAX_SLEEPING_SESSION_MESSAGE = "Max sleeping session in minutes: ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Long max = sleepingSessions.stream()
                .map(sleepingSession ->
                        Duration.between(sleepingSession.startSleep, sleepingSession.endSleep))
                .map(Duration::toMinutes)
                .max(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult(
                MAX_SLEEPING_SESSION_MESSAGE,
                max.intValue());
    }
}