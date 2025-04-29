import java.util.ArrayList;
import java.util.Scanner;

public class WordleSolver {
    private static final Scanner in = new Scanner(System.in);
    private static final ScoreGenerator scoreGenerator = new ScoreGenerator();
    private static String absentLetters = "";
    private static String[] notHere = {"", "", "", "", ""};
    private static String[] right = new String[5];

    public static void main(String[] args) {
        String currentWord = "";
        System.out.println("Welcome to Wordle Solver!");
        System.out.println("For each attempt you should introduce your current state of the word compared to the previous one.");
        System.out.println("Use '.' when the letter is not in the word.");
        System.out.println("Use '?' when the letter is in the word but not in the correct position.");
        System.out.println("Use the letter when the letter is in the correct position.");
        currentWord = scoreGenerator.getBestWord(".....");
        System.out.println("You should start with: " + currentWord);
        while (true) {
            System.out.print("Introduce the current state of the word: \n\t-");
            String currentState = in.nextLine();
            if(currentWord.equals("restart")) {
                absentLetters = "";
                notHere = new String[]{"", "", "", "", ""};
                right = new String[5];
                currentWord = scoreGenerator.getBestWord(".....");
                System.out.println("You should start with: " + currentWord);
            }else{
                if (currentState.length() != 5) {
                    System.out.println("The word should be 5 letters long.");
                    continue;
                }
                String regex = generateRegex(currentWord, currentState);
                System.out.println(regex);
                currentWord = scoreGenerator.getBestWord(regex);
                System.out.println("The best word to try is: " + currentWord);
            }
        }
    }

    private static String generateRegex(String currentWord, String currentState) {
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < currentState.length(); i++) {
            char c = currentState.charAt(i);
            char currentChar = currentWord.charAt(i);
            if (c == '.') {
                if(!absentLetters.contains(String.valueOf(currentChar))) {
                    if(rightContains(String.valueOf(currentChar))) {
                        notHere[i] = notHere[i].concat(String.valueOf(currentChar));
                    } else {
                        absentLetters = absentLetters.concat(String.valueOf(currentChar));
                    }
                }
            } else if (c == '?') {
                if (!notHere[i].contains(String.valueOf(currentChar))) {
                    notHere[i] = notHere[i].concat(String.valueOf(currentChar));
                }
            } else {
                right[i] = String.valueOf(currentChar);
            }
        }
        regex.append("(?!.*[").append(absentLetters).append("])");
        for (String letters: notHere) {
            if(!letters.isEmpty()){
                regex.append("(?=.*[").append(letters).append("])");
            }
        }
        for (int i = 0; i < right.length; i++) {
            if(right[i] != null){
                regex.append("[").append(right[i]).append("]");
            } else if (right[i] == null && !notHere[i].isEmpty()) {
                regex.append("[^").append(notHere[i]).append("]");
            } else {
                regex.append("[\\wñ]");
            }
        }
        return regex.toString();
    }

    private static boolean rightContains(String letter){
        for (String rightLetter : right) {
            if(rightLetter != null){
                if(rightLetter.equals(letter)){
                    return true;
                }
            }
        }
        return false;
    }
}
