package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSleepingSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String MIN_SLEEPING_SESSION_MESSAGE = "Min sleeping session in minutes: ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Long min = sleepingSessions.stream()
                .map(sleepingSession ->
                        Duration.between(sleepingSession.startSleep, sleepingSession.endSleep))
                .map(Duration::toMinutes)
                .min(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult(
                MIN_SLEEPING_SESSION_MESSAGE,
                min.intValue());
    }
}
