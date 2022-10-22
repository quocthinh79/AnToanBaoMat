import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VigenereEncoding extends DisplacementEncoding {
    public static List<Integer> createKeyRandom(int keySize) {
        List<Integer> resultKey = new ArrayList<>();
        for (int i = 1; i <= keySize; i++) {
            int randomNumber = createKey();
            resultKey.add(randomNumber);
        }
        return resultKey;
    }

    public static List<Integer> createKeyFromString(int keySize, String keyWord) {
        List<Integer> resultKey = new ArrayList<>();
        char[] inputToArray = keyWord.toCharArray();
        if (keySize != keyWord.length()) {
            System.out.println("Key size must be equal to the number of characters of the keyword");
//            return null;
        } else {
            for (int i = 0; i < keyWord.length(); i++) {
                int numberKeyItem = Model.generalAlphabetList.indexOf(inputToArray[i]);
                resultKey.add(numberKeyItem);
            }
        }
        return resultKey;
    }

    public static void readKey() {

    }

    public static String encrypt(String input, List<Integer> key) {
        String result = "";
        char[] inputToArray = input.toCharArray();
        for (int i = 0; i < inputToArray.length; i++) {
            result += encrypt(String.valueOf(inputToArray[i]), key.get(i % key.size()));
        }
        return result;
    }

    public static String decrypt(String input, List<Integer> key) {
        String result = "";
        char[] inputToArray = input.toCharArray();
        for (int i = 0; i < inputToArray.length; i++) {
            result += decrypt(String.valueOf(inputToArray[i]), key.get(i % key.size()));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> key = createKeyRandom(6);
        key.forEach(x -> {
            System.out.print(x + "\t");
        });
        System.out.println();
        System.out.println(encrypt("Lương Hữu Luân ay", key));
        System.out.println(decrypt(encrypt("Lương Hữu Luân ay", key), key));
    }
}
