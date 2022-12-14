package model;

import java.text.ParsePosition;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class HillEncoding {
	public static String createKeyRandom() {
		String result = "";
		for (int i = 0; i < 4; i++) {
			int numRandom = ThreadLocalRandom.current().nextInt(1, 60);
			if (i == 4 / 2) {
				result += "| " + numRandom + " ";
			} else {
				result += numRandom + " ";
			}
		}
		return result;
	}

	public static String encrypt(String input, String key) {
		String handledInput = Model.removeAccented(input);
		String result = "";
		char[] array = handledInput.toCharArray();
		char[] inputToArray;
		if (array.length % 2 != 0) {
			inputToArray = new char[array.length + 1];
			for (int i = 0; i < inputToArray.length; i++) {
				if (i < inputToArray.length - 1) {
					inputToArray[i] = array[i];
				} else {
					inputToArray[i] = ' ';
				}
			}
		} else {
			inputToArray = new char[array.length];
			inputToArray = array;
		}
		for (int i = 0; i < inputToArray.length; i++) {
			if (i != 0) {
				if (i % 2 == 0) {
					String keyString = key.split(" \\| ")[0];
					int keyNum1 = Integer.parseInt(keyString.split(" ")[0]);
					int keyNum2 = Integer.parseInt((keyString.split(" ")[1]));
					char text1 = inputToArray[i];
					char text2 = inputToArray[i + 1];
					int posText1 = Model.generalAlphabetListWithSpace.indexOf(text1);
					int posText2 = Model.generalAlphabetListWithSpace.indexOf(text2);
					int posTextEncrypt = (posText1 * keyNum1 + posText2 * keyNum2) % 59;
					result += Model.generalAlphabetListWithSpace.get(posTextEncrypt);
				} else {
					String keyString = key.split(" \\| ")[1];
					int keyNum1 = Integer.parseInt(keyString.split(" ")[0]);
					int keyNum2 = Integer.parseInt((keyString.split(" ")[1]));
					char text1 = inputToArray[i];
					char text2 = inputToArray[i - 1];
					int posText1 = Model.generalAlphabetListWithSpace.indexOf(text1);
					int posText2 = Model.generalAlphabetListWithSpace.indexOf(text2);
					int posTextEncrypt = (posText2 * keyNum1 + posText1 * keyNum2) % 59;
					result += Model.generalAlphabetListWithSpace.get(posTextEncrypt);
				}
			} else {
				String keyString = key.split(" \\| ")[0];
				int keyNum1 = Integer.parseInt(keyString.split(" ")[0]);
				int keyNum2 = Integer.parseInt(keyString.split(" ")[1]);
				char text1 = inputToArray[i];
				char text2 = inputToArray[i + 1];
				int posText1 = Model.generalAlphabetListWithSpace.indexOf(text1);
				int posText2 = Model.generalAlphabetListWithSpace.indexOf(text2);
				int posTextEncrypt = (posText1 * keyNum1 + posText2 * keyNum2) % 59;
				result += Model.generalAlphabetListWithSpace.get(posTextEncrypt);
			}
		}
		return result;
	}

	public static int detNghichDao(String matrix) {
		int result = 0;
		int num1 = Integer.parseInt(matrix.split(" \\| ")[0].split(" ")[0]);
		int num2 = Integer.parseInt(matrix.split(" \\| ")[0].split(" ")[1]);
		int num3 = Integer.parseInt(matrix.split(" \\| ")[1].split(" ")[0]);
		int num4 = Integer.parseInt(matrix.split(" \\| ")[1].split(" ")[1]);
		int temp = (num1 * num4 - num2 * num3);
		int detK = 0;
		if (temp > 0) {
			detK = (temp) % 59;
		} else {
			detK = temp - ((temp / 59 - 1) * 59);
		}
		for (int i = 0; i < 59; i++) {
			if (i * detK % 59 == 1) {
				return i;
			}
		}
		return result;
	}

	public static String kNghichDao(String matrix) {
		int detNghichDao = detNghichDao(matrix);
		int num1 = Integer.parseInt(matrix.split(" \\| ")[0].split(" ")[0]);
		int num2 = Integer.parseInt(matrix.split(" \\| ")[0].split(" ")[1]);
		int num3 = Integer.parseInt(matrix.split(" \\| ")[1].split(" ")[0]);
		int num4 = Integer.parseInt(matrix.split(" \\| ")[1].split(" ")[1]);
//		System.out.println(num1);
		String result = "";
		result += num4 * detNghichDao % 59 + " ";
		result += (59 - num2) * detNghichDao % 59 + " | ";
		result += (59 - num3) * detNghichDao % 59 + " ";
		result += num1 * detNghichDao % 59;
		return result;
	}

	public static void main(String[] args) {
		System.out.println(createKeyRandom());
		System.out.println(encrypt("L?? Qu???c Th???nh", "22 33 | 50 54"));
//        System.out.println(detNghichDao("3 2 | 3 5"));
//        System.out.println(kNghichDao("11 12 | 24 26"));
		System.out.println(encrypt("??????axUVy lAbhC", kNghichDao("22 33 | 50 54")));
//        System.out.println(encrypt("DPLE", "15 20 | 17 9"));
	}
}
