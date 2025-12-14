package ru.yandex.practicum.sleeptracker;

public class SleepfullNightPredicate {

    private static final int START_DAYTIME_SLEEP = 12;
    private static final int END_DAYTIME_SLEEP = 16;
    private static final int END_NIGHT_SLEEP = 6;

    public static boolean isSleepfull(SleepingSession session) {
        if (!(session.startSleep.getHour() < START_DAYTIME_SLEEP
                || session.startSleep.getHour() > END_DAYTIME_SLEEP)) {
            return false;
        }
        return session.startSleep.getDayOfYear() != session.endSleep.getDayOfYear()
                || session.startSleep.getHour() < END_NIGHT_SLEEP;
    }
}
