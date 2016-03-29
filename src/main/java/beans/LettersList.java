package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Developer on 28.03.2016.
 */
public class LettersList {

    public static void main(String[] args) {
        Character[] russianLetters = new LettersList().getRussianLetters();
        System.out.println(Arrays.toString(russianLetters));
    }

    public Character[] getRussianLetters() {
        List<Character> list = new ArrayList<>();

        char a = 'а';
        for (int i = 0; i < 32; i++) {
            list.add(a++);
        }

        char aEngl = 'a';
        for (int i = 0; i < 26; i++) {
            list.add(aEngl++);
        }

        return list.toArray(new Character[list.size()]);
    }
}
