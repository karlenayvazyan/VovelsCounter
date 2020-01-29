package am.ak.vowels.model;

import lombok.Data;

import java.util.Iterator;
import java.util.Set;

@Data
public class Word {

    private final Set<Character> vowels;
    private final int wordLength;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (vowels.isEmpty()) {
            sb.append("{}");
        } else {
            sb.append("{");
            for (Character vowel : vowels) {
                sb.append(vowel);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("}");
        }
        sb.append(", ");
        sb.append(wordLength);
        sb.append(")");
        return sb.toString();
    }
}
