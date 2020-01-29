package am.ak.vowels.util;

import am.ak.vowels.exception.FileReadException;
import am.ak.vowels.model.AverageResult;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

    private final VowelsCounter vowelsCounter;

    public Application() {
        this.vowelsCounter = new VowelsCounter();
    }

    public void printVowelsByFile(String input, String output) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(input);
             Scanner sc = new Scanner(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(output))) {
            printVowels(sc, writer);
        } catch (IOException e) {
            throw new FileReadException("Fail to read file");
        }
    }

    private void printVowels(Scanner sc, BufferedWriter writer) throws IOException {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            List<String> words = getWordsByLine(line);
            List<AverageResult> averageResults = vowelsCounter.count(words);
            printFile(averageResults, writer);
        }
    }

    private List<String> getWordsByLine(String line) {
        return Arrays.stream(line.replace('.', ' ').split(" "))
                .map(String::trim)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
    }

    private void printFile(List<AverageResult> averageResults, BufferedWriter writer) throws IOException {
        for (AverageResult result : averageResults) {
            writer.write(result.toString());
            writer.newLine();
        }
    }
}
