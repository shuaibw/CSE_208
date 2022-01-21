import java.util.Random;

public class RandomWord {
    public static String[] generateWords(int len, int numOfWords) {
        Random random = new Random();
        String[] words = new String[numOfWords];
        int i = 0;
        while (i < numOfWords) {
            char[] word = new char[len];
            for (int j = 0; j < len; j++) word[j] = (char) ('a' + random.nextInt(26));
            String s = new String(word);
            words[i++] = new String(word);
        }
        return words;
    }

    public static void main(String[] args) {
        //Test word generator
        String[] words = generateWords(7, 10);
        for (String word : words) {
            System.out.println(word);
        }
    }
}
