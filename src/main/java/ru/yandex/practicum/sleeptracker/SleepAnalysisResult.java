package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult{
    private final String description;
    private final Integer value;

    public SleepAnalysisResult(String description, Integer value) {
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return description + value;
    }
}
