package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSleepingSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Long max = sleepingSessions.stream()
                .map(sleepingSession ->
                        Duration.between(sleepingSession.startSleep, sleepingSession.endSleep))
                .map(Duration::toMinutes)
                .max(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult(
                "Max sleeping session in minutes: ",
                max.intValue());
    }
}