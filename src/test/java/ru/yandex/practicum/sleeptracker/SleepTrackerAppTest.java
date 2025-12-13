package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {

    @Test
    public void testGetLogFromFile(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("test.log");
        String content = """
            30.10.25 23:50;31.10.25 07:30;GOOD
            31.10.25 23:15;01.11.25 06:45;BAD
            01.11.25 22:30;02.11.25 08:00;GOOD
            """;
        Files.writeString(testFile, content);
        List<String> lines = SleepTrackerApp.getLogFromFile(testFile.toString());

        assertEquals(3, lines.size());
        assertEquals("30.10.25 23:50;31.10.25 07:30;GOOD", lines.get(0));
        assertEquals("31.10.25 23:15;01.11.25 06:45;BAD", lines.get(1));
        assertEquals("01.11.25 22:30;02.11.25 08:00;GOOD", lines.get(2));
    }

    @Test
    public void testSetAnalysisFunctions_AddsAllFunctions() {
        List<Function<List<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();

        SleepTrackerApp.setAnalysisFunctions(functions);

        assertEquals(7, functions.size());
        assertInstanceOf(SessionsCounter.class, functions.get(0));
        assertInstanceOf(MinSleepingSession.class, functions.get(1));
        assertInstanceOf(MaxSleepingSession.class, functions.get(2));
        assertInstanceOf(AvgSleepingSession.class, functions.get(3));
        assertInstanceOf(BadSleepingSessionCounter.class, functions.get(4));
        assertInstanceOf(NightWithoutSleepingCounter.class, functions.get(5));
        assertInstanceOf(UsersClassificator.class, functions.get(6));
    }

    @Test
    void testGetSleepingSessionFromLine_ValidGoodSession() {
        String line = "30.10.25 23:50;31.10.25 07:30;GOOD";

        SleepingSession session = SleepTrackerApp.getSleepingSessionFromLine(line);

        assertNotNull(session);
        assertEquals(LocalDateTime.of(2025, 10, 30, 23, 50), session.startSleep);
        assertEquals(LocalDateTime.of(2025, 10, 31, 7, 30), session.endSleep);
        assertEquals(SleepingStatus.GOOD, session.status);
    }

}