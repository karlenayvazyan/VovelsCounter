package am.ak.vowels.util;

import am.ak.vowels.model.AverageResult;
import am.ak.vowels.model.Pair;
import am.ak.vowels.model.Word;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

public class VowelsCounter {

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public List<AverageResult> count(List<String> words) {
        return words.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .map(this::convertToWord)
                .collect(groupingBy(Pair::getLeft, averagingDouble(Pair::getRight)))
                .entrySet()
                .stream()
                .map(entry -> new AverageResult(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private Pair<Word, Double> convertToWord(String w) {
        Set<Character> vowels = new HashSet<>();
        int vowelsCount = 0;
        for (int i = 0; i < w.length(); i++) {
            char charAt = w.charAt(i);
            if (VOWELS.contains(charAt)) {
                vowels.add(charAt);
                vowelsCount++;
            }
        }
        Word word = new Word(vowels, w.length());
        return new Pair<Word, Double>(word, (double) vowelsCount);
    }
}
