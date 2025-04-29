import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreGenerator {
    DataStudy dataStudy;
    private Map<String, Float> wordScore = new HashMap<>();
    public ScoreGenerator() {
        dataStudy = new DataStudy();
        calculateScore(dataStudy.reader.words);
    }

    private void calculateScore(ArrayList<ArrayList<String>> words) {
        for (ArrayList<String> wordLetter : words) {
            for (String word : wordLetter) {
                float score = 0;
                for (int i = 0; i < word.length(); i++) {
                    char letter = word.charAt(i);
                    int position = Alphabet.valueOf(String.valueOf(letter).toUpperCase()).ordinal();
                    score += dataStudy.getFrequencyPercentage()[i][position];
                }
                this.wordScore.put(word, score);
            }
        }
    }

    public String getBestWord(String regex){
        String bestWord = null;
        float bestScore = 0;
        for (Map.Entry<String, Float> entry : wordScore.entrySet()) {
            String word = entry.getKey();
            float score = entry.getValue();
            if (word.matches(regex) && score > bestScore) {
                bestScore = score;
                bestWord = word;
            }
        }
        return bestWord;
    }
}
