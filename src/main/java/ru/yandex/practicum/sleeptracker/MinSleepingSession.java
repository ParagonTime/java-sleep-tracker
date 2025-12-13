package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSleepingSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Long min = sleepingSessions.stream()
                .map(sleepingSession ->
                        Duration.between(sleepingSession.startSleep, sleepingSession.endSleep))
                .map(Duration::toMinutes)
                .min(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult(
                "Min sleeping session in minutes: ",
                min.intValue());
    }
}
