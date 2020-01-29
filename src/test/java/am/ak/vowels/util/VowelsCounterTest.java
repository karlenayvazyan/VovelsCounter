package am.ak.vowels.util;

import am.ak.vowels.model.AverageResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VowelsCounterTest {

    @Test
    public void countWhenCollectionIsEmpty() {
        List<String> words = Collections.emptyList();
        VowelsCounter vowelsCounter = new VowelsCounter();
        List<AverageResult> result = vowelsCounter.count(words);
        assertNotNull(result);
        assertThat(result, empty());
    }

    @Test
    public void count() {
        List<String> words = Arrays.asList(" Platon  ", "mAde", "bambOo", "boaTs");
        VowelsCounter vowelsCounter = new VowelsCounter();
        List<AverageResult> result = vowelsCounter.count(words);
        assertNotNull(result);
        assertThat(result, not(empty()));
        assertThat(result, hasSize(3));
        AverageResult averageResult = result.get(2);
        assertNotNull(averageResult);
        double average = averageResult.getAverage();
        assertEquals(2.5, average, 1);
    }
}