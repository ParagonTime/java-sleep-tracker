package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SessionsCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String SESSION_COUNTER_MESSAGE = "Sleeping sessions in log: ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult(
                SESSION_COUNTER_MESSAGE,
                sleepingSessions.size());
    }
}
