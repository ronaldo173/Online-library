package beans;

import java.util.Arrays;

/**
 * Created by Developer on 28.03.2016.
 */
public class LettersList {

    public char[] getRussianLetters() {
        char[] letters = new char[32];
        char a = 'а';
        for (int i = 0; i < 32; i++) {
            letters[i] = a++;
        }

        return letters;
    }

    public static void main(String[] args) {
        char[] russianLetters = new LettersList().getRussianLetters();
        System.out.println(Arrays.toString(russianLetters));
    }
}
