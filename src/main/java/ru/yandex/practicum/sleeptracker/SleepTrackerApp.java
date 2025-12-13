package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"); //30.10.25 23:50

    public static void main(String[] args) {
        List<SleepingSession> sleepingSessions;
        List<Function<List<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();
        try {
            sleepingSessions = getLogFromFile(args[0]).stream()
                    .map(SleepTrackerApp::getSleepingSessionFromLine)
                    .toList();
        } catch (IOException e) {
            return;
        }
        functions.add(new SessionsCounter());
        functions.add(new MinSleepingSession());
        functions.add(new MaxSleepingSession());
        functions.add(new AvgSleepingSession());
        functions.add(new BadSleepingSessionCounter());
        functions.add(new NightWithoutSleepingCounter());
        functions.add(new UsersClassificator());

        List<SleepAnalysisResult> results = functions.stream()
                .map(func -> func.apply(sleepingSessions))
                .peek(System.out::println)
                .toList();
    }

    private static List<String> getLogFromFile(String fileName) throws IOException {
        return Files.readAllLines(Path.of(fileName));
    }

    private static SleepingSession getSleepingSessionFromLine(String line) {
        String[] array = line.split(";");
        return new SleepingSession(
                LocalDateTime.parse(array[0], INPUT_FORMATTER),
                LocalDateTime.parse(array[1], INPUT_FORMATTER),
                SleepingStatus.valueOf(array[2])
        );
    }
}