import java.sql.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DisplacementEncoding {
    public static int createKey() {
        // Random số từ 1 -> 29
        return ThreadLocalRandom.current().nextInt(1, 30);
    }

    public static void readKey() {
        System.out.println(createKey());
    }

    public static String encrypt(String input, int k) {
        String handledInput = Model.removeAccented(input);
        return Model.handleInputString(handledInput, Model.listAlphabet, Model.listAlphabetUpper, k);
    }


    public static String decrypt(String input, int k) {
        List<Character> alphabetReverse = Model.reverseList(Model.listAlphabet);
        List<Character> alphabetUpperReverse = Model.reverseList(Model.listAlphabetUpper);
        return Model.addAccented(Model.handleInputString(input, alphabetReverse, alphabetUpperReverse, k));
    }

    public static void main(String[] args) {
        System.out.println(encrypt("Lê Quốc Thịnh x y", 1));
        System.out.println(decrypt(encrypt("Lê Quốc Thịnh x y", 1), 1));
    }
}
