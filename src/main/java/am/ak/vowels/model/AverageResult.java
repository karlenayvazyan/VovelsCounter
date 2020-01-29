package am.ak.vowels.model;

import lombok.Data;

@Data
public class AverageResult {

    private final Word word;
    private final double average;

    @Override
    public String toString() {
        return word + " -> " + average;
    }
}
