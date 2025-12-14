package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class UsersClassificator implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String OWL_TYPE_MESSAGE = "Your sleeping type is Owl, nights like Owl: ";
    private static final String LARK_TYPE_MESSAGE = "Your sleeping type is Lark, nights like Lark: ";
    private static final String DOVE_TYPE_MESSAGE = "Your sleeping type is Dove, nights like Dove: ";
    private static final int START_DAYTIME_SLEEP = 12;
    private static final int END_DAYTIME_SLEEP = 16;
    private static final int END_NIGHT_SLEEP = 6;
    private static final int OWL_START_SLEEP = 23;
    private static final int OWL_END_SLEEP = 9;
    private static final int LARK_START_SLEEP = 22;
    private static final int LARK_END_SLEEP = 7;


    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        AtomicInteger owlSleepCount = new AtomicInteger();
        AtomicInteger larkSleepCount = new AtomicInteger();
        AtomicInteger doveSleepCount = new AtomicInteger();
        String userSleepingType;
        int maxSleepLikeType;
        sleepingSessions.stream()
                .filter(session ->
                        session.startSleep.getHour() < START_DAYTIME_SLEEP
                                || session.startSleep.getHour() > END_DAYTIME_SLEEP)
                .filter(session ->
                        session.startSleep.getDayOfYear() != session.endSleep.getDayOfYear()
                                || session.startSleep.getHour() < END_NIGHT_SLEEP
                )
                .forEach(session -> {
                    if ((session.startSleep.getHour() >= OWL_START_SLEEP || session.startSleep.getHour() < OWL_END_SLEEP)
                            && (session.endSleep.getHour() >= OWL_END_SLEEP)
                    ) {
                        owlSleepCount.getAndIncrement();
                    } else if ((session.startSleep.getHour() < LARK_START_SLEEP)
                            && (session.endSleep.getHour() < LARK_END_SLEEP)
                    ) {
                        larkSleepCount.getAndIncrement();
                    } else {
                        doveSleepCount.getAndIncrement();
                    }
                });
        if (owlSleepCount.intValue() > larkSleepCount.intValue()
                && owlSleepCount.intValue() > doveSleepCount.intValue()
        ) {
            maxSleepLikeType = owlSleepCount.intValue();
            userSleepingType = OWL_TYPE_MESSAGE;
        } else if (larkSleepCount.intValue() > owlSleepCount.intValue()
                && larkSleepCount.intValue() > doveSleepCount.intValue()
        ) {
            maxSleepLikeType = larkSleepCount.intValue();
            userSleepingType = LARK_TYPE_MESSAGE;
        } else {
            maxSleepLikeType = doveSleepCount.intValue();
            userSleepingType = DOVE_TYPE_MESSAGE;
        }
        return new SleepAnalysisResult(
                userSleepingType,
                maxSleepLikeType);
    }
}