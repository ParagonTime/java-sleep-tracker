package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AvgSleepingSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String AVG_SLEEPING_SESSION_MESSAGE = "Avg sleeping session in minutes: ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        double avg = sleepingSessions.stream()
                .map(sleepingSession ->
                        Duration.between(sleepingSession.startSleep, sleepingSession.endSleep))
                .map(Duration::toMinutes)
                .mapToInt(Long::intValue)
                .average()
                .orElse(0);

        return new SleepAnalysisResult(
                AVG_SLEEPING_SESSION_MESSAGE,
                (int) avg);
    }
}
