package ua.edu.lab1.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void printsCorrectDemo() throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream output = new PrintStream(bytes, true,
                StandardCharsets.UTF_8)) {
            Main.run(output);
        }
        String actual = bytes.toString(StandardCharsets.UTF_8)
                .replace("\r\n", "\n");
        try (var stream = getClass().getResourceAsStream("/expected-output.txt")) {
            assertNotNull(stream);
            String expected = new String(stream.readAllBytes(),
                    StandardCharsets.UTF_8).replace("\r\n", "\n");
            assertEquals(expected, actual);
        }
    }

    @Test
    void entryPointUsesStandardOutput() {
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream output = new PrintStream(bytes, true,
                StandardCharsets.UTF_8)) {
            System.setOut(output);
            Main.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        assertTrue(bytes.toString(StandardCharsets.UTF_8)
                .contains("Усього клієнтів: 4100"));
    }

    @Test
    void utilityConstructorIsPrivate() throws Exception {
        var constructor = Main.class.getDeclaredConstructor();
        assertTrue(java.lang.reflect.Modifier.isPrivate(
                constructor.getModifiers()));
        constructor.setAccessible(true);
        assertNotNull(constructor.newInstance());
    }
}
