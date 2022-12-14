package model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlternativeEncoding {
	public static List<Character> listLowerCase = new ArrayList<>(Model.listAlphabet);
	public static List<Character> listUpperCase = new ArrayList<>(Model.listAlphabetUpper);
	public static List<Character> newList = new ArrayList<>(
			Stream.concat(listLowerCase.stream(), listUpperCase.stream()).toList());
	public static List<Character> copyList = new ArrayList<>(newList);

	public static void setNewList(List<Character> newList) {
		AlternativeEncoding.newList = newList;
	}

	public static String createKey() {
		String result = "";
		newList = new ArrayList<>(Stream.concat(listLowerCase.stream(), listUpperCase.stream()).toList());
		while (newList.size() > 0) {
			int random = ThreadLocalRandom.current().nextInt(0, newList.size());
			result += newList.get(random);
			newList.remove(random);
		}
		return result;
	}

	public static void readKey() {
		System.out.println(createKey());
	}

	public static String encrypt(String input, String key) {
		String handledInput = Model.removeAccented(input);
		char[] keyToArray = key.toCharArray();
		char[] inputToArray = handledInput.toCharArray();
		String result = "";
		for (int i = 0; i < inputToArray.length; i++) {
			if (copyList.contains(inputToArray[i])) {
				inputToArray[i] = keyToArray[copyList.indexOf(inputToArray[i])];
			}
		}
		for (int i = 0; i < inputToArray.length; i++) {
			result += inputToArray[i];
		}
		return result;
	}

	public static String decrypt(String input, String key) {
		List<Character> listKey = key.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
		char[] inputToArray = input.toCharArray();
		String result = "";
		for (int i = 0; i < inputToArray.length; i++) {
			if (listKey.contains(inputToArray[i])) {
				inputToArray[i] = copyList.get(listKey.indexOf(inputToArray[i]));
			}
		}
		for (int i = 0; i < inputToArray.length; i++) {
			result += inputToArray[i];
		}
		return result;
	}

	public static void main(String[] args) {
		String key = createKey();
		System.out.println(key);
		for (int i = 0; i < copyList.size(); i++) {
			System.out.print(copyList.get(i));
		}
		System.out.println();
		System.out.println(encrypt("L?? Qu???c Th???nh", key));
		System.out.println(decrypt(encrypt("L?? Qu???c Th???nh", key), key));
	}
}
