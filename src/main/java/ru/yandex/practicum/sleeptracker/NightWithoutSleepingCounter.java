package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class NightWithoutSleepingCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String NIGHT_WITHOUT_SLEEPING_MESSAGE = "Night without sleeping count: ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int dayCount = sleepingSessions.getLast().endSleep.getDayOfYear()
                - sleepingSessions.getFirst().startSleep.getDayOfYear();
        long sleepingNightsCount = sleepingSessions.stream()
                .filter(SleepfullNightPredicate::isSleepfull)
                .count();

        return new SleepAnalysisResult(
                NIGHT_WITHOUT_SLEEPING_MESSAGE,
                dayCount - (int) sleepingNightsCount);
    }
}