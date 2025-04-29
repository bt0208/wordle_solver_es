public class DataStudy {
    Reader reader = new Reader("5letters.txt");
    int[][] frequency = new int[5][27];
    int totalWords = 0;
    float[][] frequencyPercentage = new float[5][27];


    public static void main(String[] args) {
        DataStudy ds = new DataStudy();
        ds.printFrequency();
    }

    public DataStudy() {
        frequencyStudy();
        frequencyPercentageStudy();
    }

    public void frequencyStudy(){
        for (int i = 0; i < reader.words.size(); i++) {
            for (int j = 0; j < reader.words.get(i).size(); j++) {
                String word = reader.words.get(i).get(j);
                for (int k = 0; k < 5; k++){
                    frequency[k][Alphabet.valueOf(String.valueOf(word.charAt(k)).toUpperCase()).ordinal()]++;
                }
            }
            totalWords+= reader.words.get(i).size();
        }
    }

    public void frequencyPercentageStudy(){
        for(int i = 0; i < frequency.length; i++){
            for(int j = 0; j < frequency[i].length; j++){
                frequencyPercentage[i][j] = ((float) frequency[i][j] /totalWords) * 100;
            }
        }
    }

    public void printFrequency(){
        for(int i = 0; i < frequency.length; i++){
            System.out.println("Frequency of letter in position " + i + ": ");
            for(int j = 0; j < frequency[i].length; j++){
                System.out.println(Alphabet.getCharFromPosition(j)+ ": " + frequencyPercentage[i][j]);
            }
        }
    }
}
