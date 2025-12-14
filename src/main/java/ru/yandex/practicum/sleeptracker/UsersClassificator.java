package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UsersClassificator implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String OWL_TYPE_MESSAGE = "Your sleeping type is Owl, nights like Owl: ";
    private static final String LARK_TYPE_MESSAGE = "Your sleeping type is Lark, nights like Lark: ";
    private static final String DOVE_TYPE_MESSAGE = "Your sleeping type is Dove, nights like Dove: ";
    private static final LocalTime OWL_START_SLEEP = LocalTime.of(23, 0, 0);
    private static final LocalTime OWL_END_SLEEP = LocalTime.of(9, 0, 0);
    private static final LocalTime LARK_START_SLEEP = LocalTime.of(22, 0, 0);
    private static final LocalTime LARK_END_SLEEP = LocalTime.of(7, 0, 0);


    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        String userSleepingType;
        int maxSleepLikeType;

        Map.Entry<Chronotype, Long> userType = sleepingSessions.stream()
                .filter(SleepfullNightPredicate::isSleepfull)
                .map(session -> {
                    if ((session.startSleep.toLocalTime().isAfter(OWL_START_SLEEP)
                            || session.startSleep.toLocalTime().isBefore(OWL_END_SLEEP))
                            && (session.endSleep.toLocalTime().isAfter(OWL_END_SLEEP))
                    ) {
                        return Chronotype.OWL;
                    }
                    if ((session.startSleep.toLocalTime().isBefore(LARK_START_SLEEP))
                            && (session.endSleep.toLocalTime().isBefore(LARK_END_SLEEP))
                    ) {
                        return Chronotype.LARK;
                    }
                    return Chronotype.DOVE;
                }).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .orElse(Map.entry(Chronotype.DOVE, 1L));

        switch (userType.getKey()) {
            case OWL: {
                userSleepingType = OWL_TYPE_MESSAGE;
                maxSleepLikeType = userType.getValue().intValue();
                break;
            }
            case LARK: {
                userSleepingType = LARK_TYPE_MESSAGE;
                maxSleepLikeType = userType.getValue().intValue();
                break;
            }
            default: {
                userSleepingType = DOVE_TYPE_MESSAGE;
                maxSleepLikeType = userType.getValue().intValue();
            }
        }

        return new SleepAnalysisResult(
                userSleepingType,
                maxSleepLikeType);
    }
}