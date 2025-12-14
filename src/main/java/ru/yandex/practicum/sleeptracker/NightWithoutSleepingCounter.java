package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class NightWithoutSleepingCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String NIGHT_WITHOUT_SLEEPING_MESSAGE = "Night without sleeping count: ";
    private static final int START_DAYTIME_SLEEP = 12;
    private static final int END_DAYTIME_SLEEP = 16;
    private static final int END_NIGHT_SLEEP = 6;

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int dayCount = sleepingSessions.getLast().endSleep.getDayOfYear()
                - sleepingSessions.getFirst().startSleep.getDayOfYear();
        long sleepingNightsCount = sleepingSessions.stream()
                .filter(session ->
                        session.startSleep.getHour() < START_DAYTIME_SLEEP
                                || session.startSleep.getHour() > END_DAYTIME_SLEEP)
                .filter(session ->
                        session.startSleep.getDayOfYear() != session.endSleep.getDayOfYear()
                                || session.startSleep.getHour() < END_NIGHT_SLEEP
                )
                .count();

        return new SleepAnalysisResult(
                NIGHT_WITHOUT_SLEEPING_MESSAGE,
                dayCount - (int) sleepingNightsCount);
    }
}