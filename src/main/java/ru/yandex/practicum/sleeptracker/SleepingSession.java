package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    final LocalDateTime startSleep;
    final LocalDateTime endSleep;
    final SleepingStatus status;

    public SleepingSession(
            LocalDateTime startSleep,
            LocalDateTime endSleep,
            SleepingStatus status) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.status = status;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startSleep=" + startSleep +
                ", endSleep=" + endSleep +
                ", status=" + status +
                '}';
    }
}
