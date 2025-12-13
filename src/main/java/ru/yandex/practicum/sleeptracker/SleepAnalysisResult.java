package ru.yandex.practicum.sleeptracker;

public record SleepAnalysisResult(
        String description,
        Integer value
) {

    @Override
    public String toString() {
        return description + value;
    }
}
