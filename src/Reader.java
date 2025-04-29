import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    File file;
    ArrayList<ArrayList<String>> words;

    public Reader(String filename) {
        file = new File(filename);
        words = readWords();
    }

    private ArrayList<ArrayList<String>> readWords() {
        ArrayList<ArrayList<String>> words = new ArrayList<>();
        initialize(words);
        try{
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String word = input.nextLine();
                if(word.charAt(0) >= 'a' && word.charAt(0) <= 'z'){
                    words.get((word.charAt(0) - 'a')).add(word);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static void initialize(ArrayList<ArrayList<String>> words){
        for (int i = 0; i < 26; i++){
            words.add(new ArrayList<>());
        }
    }

}
