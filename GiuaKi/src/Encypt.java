public class Encypt {
    public static String encrypt(String input, int k) {
        StringBuilder result = new StringBuilder();
        char[] inputToArray = input.toCharArray();
        int startLowerCase = 'a';
        int endLowerCase = 'z';
        int startUpperCase = 'A';
        int endUpperCase = 'Z';
        for (int i = 0; i < inputToArray.length; i++) {
            if (startLowerCase <= inputToArray[i] && inputToArray[i] <= endLowerCase) {
                result.append((char) ((((inputToArray[i] - startLowerCase) + k) % 26) + startLowerCase));
            }
            if (startUpperCase <= inputToArray[i] && inputToArray[i] <= endUpperCase) {
                result.append((char) ((((inputToArray[i] - startUpperCase) + k) % 26) + startUpperCase));
            }
        }
        return result.toString();
    }

    public static String decrypt(String input, int k) {
        StringBuilder result = new StringBuilder();
        char[] inputToArray = input.toCharArray();
        int startLowerCase = 'a';
        int endLowerCase = 'z';
        int startUpperCase = 'A';
        int endUpperCase = 'Z';
        int space = ' ';
        for (int i = 0; i < inputToArray.length; i++) {
            if (inputToArray[i] == space) {
                result.append(" ");
            } else {
                if (startLowerCase <= inputToArray[i] && inputToArray[i] <= endLowerCase) {
                    result.append((char) ((((inputToArray[i] - startLowerCase) - k) % 26) + startLowerCase));
                }
                if (startUpperCase <= inputToArray[i] && inputToArray[i] <= endUpperCase) {
                    result.append((char) ((((inputToArray[i] - startUpperCase) - k) % 26) + startUpperCase));
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(decrypt(encrypt("Le Quoc Thinh", 1), 1));
        System.out.println((int)'ầ');
    }
}
