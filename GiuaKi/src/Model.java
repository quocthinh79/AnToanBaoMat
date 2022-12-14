import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    public static List<Character> listAlphabet = List.of('a', 'ă', 'â', 'b', 'c', 'd', 'đ', 'e', 'ê', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'ô', 'ơ',
            'p', 'q', 'r', 's', 't', 'u', 'ư', 'v', 'x', 'y');
    public static List<Character> listAlphabetUpper = List.of('A', 'Ă', 'Â', 'B', 'C', 'D', 'Đ', 'E', 'Ê', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'Ô', 'Ơ', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ư', 'V', 'X', 'Y');
    public static List<Character> listAccented = List.of(
            'á', 'à', 'ã', 'ạ', 'ả',
            'ắ', 'ằ', 'ẳ', 'ẵ', 'ặ',
            'ấ', 'ầ', 'ẩ', 'ẫ', 'ậ',
            'é', 'è', 'ẻ', 'ẽ', 'ẹ',
            'ề', 'ế', 'ể', 'ễ', 'ệ',
            'í', 'ì', 'ỉ', 'ĩ', 'ị',
            'ò', 'ó', 'ỏ', 'õ', 'ọ',
            'ồ', 'ố', 'ổ', 'ỗ', 'ộ',
            'ờ', 'ớ', 'ở', 'ỡ', 'ợ',
            'ù', 'ú', 'ủ', 'ũ', 'ụ',
            'ừ', 'ứ', 'ử', 'ữ', 'ự');
    public static List<Character> listAccentedUpper = List.of(
            'Á', 'À', 'Ã', 'Ạ', 'Ả',
            'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ',
            'Ấ', 'Ầ', 'Ẩ', 'Ẫ', 'Ậ',
            'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ',
            'Ề', 'Ế', 'Ể', 'Ễ', 'Ệ',
            'Í', 'Ì', 'Ỉ', 'Ĩ', 'Ị',
            'Ò', 'Ó', 'Ỏ', 'Õ', 'Ọ',
            'Ồ', 'Ố', 'Ổ', 'Ỗ', 'Ộ',
            'Ờ', 'Ớ', 'Ở', 'Ỡ', 'Ợ',
            'Ù', 'Ú', 'Ủ', 'Ũ', 'Ụ',
            'Ừ', 'Ứ', 'Ử', 'Ữ', 'Ự'
    );

    public static Map<Integer, Character> map = new HashMap<>();

    public static String removeAccented(String input) {
        String result = "";
        char[] inputToArray = input.toCharArray();
        for (int i = 0; i < inputToArray.length; i++) {
            for (int j = 0; j < Model.listAccented.size(); j++) {
                if (Model.listAccented.get(j).equals(inputToArray[i])) {
                    map.put(i, inputToArray[i]);
                    if (j < 5) {
                        result += "a";
                    } else if (j < 10) {
                        result += "ă";
                    } else if (j < 15) {
                        result += "â";
                    } else if (j < 20) {
                        result += "e";
                    } else if (j < 25) {
                        result += "ê";
                    } else if (j < 30) {
                        result += "i";
                    } else if (j < 35) {
                        result += "o";
                    } else if (j < 40) {
                        result += "ô";
                    } else if (j < 45) {
                        result += "ơ";
                    } else if (j < 50) {
                        result += "u";
                    } else {
                        result += "ư";
                    }
                } else if (Model.listAccentedUpper.get(j).equals(inputToArray[i])) {
                    map.put(i, inputToArray[i]);
                    if (j < 5) {
                        result += "A";
                    } else if (j < 10) {
                        result += "Ă";
                    } else if (j < 15) {
                        result += "Â";
                    } else if (j < 20) {
                        result += "E";
                    } else if (j < 25) {
                        result += "Ê";
                    } else if (j < 30) {
                        result += "I";
                    } else if (j < 35) {
                        result += "O";
                    } else if (j < 40) {
                        result += "Ô";
                    } else if (j < 45) {
                        result += "Ơ";
                    } else if (j < 50) {
                        result += "U";
                    } else {
                        result += "Ư";
                    }
                }
            }
            if (!Model.listAccented.contains(inputToArray[i]) && !Model.listAccentedUpper.contains(inputToArray[i])) {
                result += String.valueOf(inputToArray[i]);
            }
        }
        return result;
    }

    public static String addAccented(String input) {
        char[] inputToArray = input.toCharArray();
        String result = "";
        map.forEach((index, item) -> {
            inputToArray[index] = item;
        });
        for (int i = 0; i < inputToArray.length; i++) {
            result += inputToArray[i];
        }
        return result;
    }

    public static List<Character> reverseList(List<Character> inputList) {
        List<Character> result = new ArrayList<>();
        for (int i = inputList.size() - 1; i >= 0; i--) {
            result.add(inputList.get(i));
        }
        return result;
    }

    public static String handleInputString(String input, List<Character> listLowerCase, List<Character> listUpperCase, int k) {
        StringBuilder result = new StringBuilder();
        char[] inputToArray = input.toCharArray();
        for (int i = 0; i < inputToArray.length; i++) {
            if (inputToArray[i] == ' ')
                result.append(" ");
            for (int j = 0; j < listLowerCase.size(); j++) {
                if (listLowerCase.get(j).equals(inputToArray[i])) {
                    result.append(listLowerCase.get((j + k) % listLowerCase.size()));
                }
            }
            for (int j = 0; j < listUpperCase.size(); j++) {
                if (listUpperCase.get(j).equals(inputToArray[i])) {
                    result.append(listUpperCase.get((j + k) % listUpperCase.size()));
                }
            }
        }
        return result.toString();
    }
}
