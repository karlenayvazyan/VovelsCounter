package am.ak.vowels.util;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationTest {

    Application application = new Application();

    @Test
    public void test() {
        String input = "INPUT.txt";
        String output = "OUTPUT.txt";
        application.printVowelsByFile(input, output);
        try (FileInputStream inputStream = new FileInputStream(output);
             Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                assertNotNull(line);
                assertEquals("({a,o}, 5) -> 2.0", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}