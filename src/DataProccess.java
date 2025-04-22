import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataProccess {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> words = readData();
        writeData(words);
    }

    public static ArrayList<ArrayList<String>> readData(){
        ArrayList<ArrayList<String>> words = new ArrayList<>();
        try{
            initialize(words);
            File initalFile = new File("es.txt");
            Scanner input = new Scanner(initalFile);
            while(input.hasNextLine()){
                String word = input.nextLine();
                if(word.length() == 5){
                    if(word.charAt(0) >= 'a' && word.charAt(0) <= 'z'){
                        words.get((word.charAt(0) - 'a')).add(word);
                    }
                }
            }
            input.close();
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

    private static void writeData(ArrayList<ArrayList<String>> words){
        try{
            File newFile = new File("5letters.txt");
            FileWriter fileWriter = new FileWriter(newFile);
            for(ArrayList<String> word : words){
                for(String s : word){
                    fileWriter.write(s + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
