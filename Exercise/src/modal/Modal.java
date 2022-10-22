package modal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Modal {
	public Map<Character, Integer> frequencyLetter = new HashMap<>();
	public Map<String, Integer> frequencyLetter2 = new HashMap<>();
	public Map<String, Integer> frequencyLetter22 = new HashMap<>();
	public Map<String, Integer> frequencyLetter3 = new HashMap<>();
	public Map<String, Integer> frequencyLetter33 = new HashMap<>();
//	public String frequencyLetterString = "e:12.575645,t:9.085226,a:8.000395,o:7.591270,i:6.920007,n:6.903785,s:6.340880,h:6.236609,r:5.959034,d:4.317924,l:4.057231,u:2.841783,c:2.575785,m:2.560994,f:2.350463,w:2.224893,g:1.982677,y:1.900888,p:1.795742,b:1.535701,v:0.981717,k:0.739906,x:0.179556,j:0.145188,q:0.117571,z:0.079130";
//	public char[] frequencyLetterChar = { 'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'u', 'c', 'm', 'f',
//			'w', 'g', 'y', 'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z' };
//	public char[] frequencyLetterCharUpper = { 'Z', 'Q', 'J', 'X', 'K', 'V', 'B', 'Y', 'W', 'G', 'P', 'F', 'M', 'U',
//			'C', 'D', 'L', 'H', 'R', 'S', 'N', 'I', 'O', 'A', 'T', 'E' };
	List<Character> frequencyLetterCharUpper = List.of('Z', 'Q', 'J', 'X', 'K', 'V', 'B', 'Y', 'W', 'G', 'P', 'F', 'M',
			'U', 'C', 'D', 'L', 'H', 'R', 'S', 'N', 'I', 'O', 'A', 'T', 'E');
	LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
	LinkedHashMap<String, Integer> sortedMapString = new LinkedHashMap<>();
	List<Integer> list = new ArrayList<>();
	List<Character> listSortedFrequencyLetter = new ArrayList<>();
	List<String> listSortedFrequencyLetter2 = new ArrayList<>();
	List<String> listSortedFrequencyLetter22 = new ArrayList<>();
	List<String> listSortedFrequencyLetter3 = new ArrayList<>();
	List<String> listSortedFrequencyLetter33 = new ArrayList<>();
	List<Character> key = new ArrayList<>();
	public Map<Character, Integer> keyMap = new HashMap<>();
	List<Character> alphabet = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

	List<String> oneLetterWord = List.of("A", "I");
	List<String> mostFreqTwoLetterWord = List.of("of", "to", "in", "it", "is", "be", "as", "at", "so", "we", "he", "by",
			"or", "on", "do", "if", "me", "my", "up", "an", "go", "no", "us", "am");
	List<String> mostFreqThreeLetterWord = List.of("the", "and", "for", "are", "but", "not", "you", "all", "any", "can",
			"had", "her", "was", "one", "our", "out", "day", "get", "has", "him", "his", "how", "man", "new", "now",
			"old", "see", "two", "way", "who", "boy", "did", "its", "let", "put", "say", "she", "too", "use");
	List<String> mostFreqFourLetterWord = List.of("that", "with", "have", "this", "will", "your", "from", "they",
			"know", "want", "been", "good", "much", "some", "time");
//	public char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
//			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	String input;
	String plainText;
	String cipherText;

	public Modal(String plainText, String cipherText) {
		// TODO Auto-generated constructor stub
		this.plainText = plainText;
		this.cipherText = cipherText;
		frequencyLetterOfText(cipherText);
		frequencyLetterOfText2(cipherText);
		frequencyLetterOfText3(cipherText);
		frequencyLetterOfText22(cipherText);
		frequencyLetterOfText33(cipherText);
		sortListFrequency(frequencyLetter, listSortedFrequencyLetter);
		sortListFrequencyString(frequencyLetter2, listSortedFrequencyLetter2);
		sortListFrequencyString(frequencyLetter22, listSortedFrequencyLetter22);
		sortListFrequencyString(frequencyLetter3, listSortedFrequencyLetter3);
		sortListFrequencyString(frequencyLetter33, listSortedFrequencyLetter33);
	}

	public void frequencyLetterOfText(String input) {
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			int cnt = 0;
			if (Character.isAlphabetic(ch)) {
				ch = Character.toUpperCase(ch);
				if (frequencyLetter.containsKey(ch))
					cnt = frequencyLetter.get(ch);
				frequencyLetter.put(ch, cnt + 1);
			}
		}
		for (int i = 0; i < frequencyLetterCharUpper.size(); i++) {
			if (!frequencyLetter.containsKey(frequencyLetterCharUpper.get(i))) {
				frequencyLetter.put(frequencyLetterCharUpper.get(i), 0);
			}
		}
	}

	public void frequencyLetterOfText2(String input) {
		String[] inputToArr = input.split(" ");
		for (int i = 0; i < inputToArr.length; i++) {
			for (int j = 0; j < inputToArr[i].length() - 1; j++) {
				String twoLetter = inputToArr[i].substring(j, j + 2);
				int cnt = 0;
				if (frequencyLetter2.containsKey(twoLetter)) {
					cnt = frequencyLetter2.get(twoLetter);
				}
				frequencyLetter2.put(twoLetter, cnt + 1);
			}
		}
	}

	public void frequencyLetterOfText22(String input) {
		String[] inputToArr = input.split(" ");
		for (int i = 0; i < inputToArr.length; i++) {
			int cnt = 0;
			if (inputToArr[i].length() == 2) {
				if (frequencyLetter22.containsKey(inputToArr[i])) {
					cnt = frequencyLetter22.get(inputToArr[i]);
				}
				frequencyLetter22.put(inputToArr[i], cnt + 1);
			}
		}
	}

	public void frequencyLetterOfText3(String input) {
		String[] inputToArr = input.split(" ");
		for (int i = 0; i < inputToArr.length; i++) {
			for (int j = 0; j < inputToArr[i].length() - 2; j++) {
				String twoLetter = inputToArr[i].substring(j, j + 3);
				int cnt = 0;
				if (frequencyLetter3.containsKey(twoLetter)) {
					cnt = frequencyLetter3.get(twoLetter);
				}
				frequencyLetter3.put(twoLetter, cnt + 1);
			}
		}
	}

	public void frequencyLetterOfText33(String input) {
		String[] inputToArr = input.split(" ");
		for (int i = 0; i < inputToArr.length; i++) {
			int cnt = 0;
			if (inputToArr[i].length() == 3) {
				if (frequencyLetter33.containsKey(inputToArr[i])) {
					cnt = frequencyLetter33.get(inputToArr[i]);
				}
				frequencyLetter33.put(inputToArr[i], cnt + 1);
			}
		}
	}

	public void sortListFrequency(Map<Character, Integer> frequencyLetter, List<Character> listDes) {
		list.clear();
		sortedMap.clear();
		for (Map.Entry<Character, Integer> entry : frequencyLetter.entrySet()) {
			list.add(entry.getValue());
		}
		Collections.sort(list);
		for (int num : list) {
			for (Entry<Character, Integer> entry : frequencyLetter.entrySet()) {
				if (entry.getValue().equals(num)) {
					sortedMap.put(entry.getKey(), num);
				}
			}
		}
		for (Map.Entry<Character, Integer> entry : sortedMap.entrySet()) {
			listDes.add(entry.getKey());
		}
	}

	public void sortListFrequencyString(Map<String, Integer> frequencyLetter, List<String> listDes) {
		list.clear();
		sortedMapString.clear();
		for (Map.Entry<String, Integer> entry : frequencyLetter.entrySet()) {
			list.add(entry.getValue());
		}
		Collections.sort(list);
		for (int num : list) {
			for (Entry<String, Integer> entry : frequencyLetter.entrySet()) {
				if (entry.getValue().equals(num)) {
					sortedMapString.put(entry.getKey(), num);
				}
			}
		}
		for (Map.Entry<String, Integer> entry : sortedMapString.entrySet()) {
			listDes.add(entry.getKey());
		}
	}

	int count = 0;

	public String findKey(String cipherText) {
		char[] key = new char[listSortedFrequencyLetter.size()];
		char[] newCipher = new char[cipherText.length()];
		char[] cipherToArray = cipherText.toCharArray();
		for (int i = 0; i < cipherToArray.length; i++) {
			for (int j = listSortedFrequencyLetter.size() - 1; j > 0; j--) {
				if (listSortedFrequencyLetter.get(j).equals(cipherToArray[i])) {
					newCipher[i] = frequencyLetterCharUpper.get(j);
				}
			}
		}
		String testText = String.valueOf(newCipher);
		return testText;
	}

	public int longestWord() {
		int maxValue = Integer.MIN_VALUE;
		String[] cipherToArray = cipherText.split(" ");
		for (int i = 0; i < cipherToArray.length; i++) {
			maxValue = Math.max(maxValue, cipherToArray[i].length());
		}
		return maxValue;
	}

	public void splitAndCheck() {
		String[] inputAfterSplit = input.trim().split(" ");
		for (int i = 0; i < inputAfterSplit.length; i++) {
			loop: for (int j = frequencyLetterCharUpper.size() - 1; j > 0; j--) {
				if (inputAfterSplit[i].length() == 1) {
					if (oneLetterWord.contains(String.valueOf(frequencyLetterCharUpper.get(j)))) {
						System.out.println(input.replace(Character.valueOf(inputAfterSplit[i].charAt(0)),
								frequencyLetterCharUpper.get(j)));
						break loop;
					}
				}
			}
//			for (int j = 0; j < listSortedFrequencyLetter.size(); j++) {
//				for (int k = 0; k < frequencyLetterCharUpper.size(); k++) {
//
//				}
//			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String plainText = "Nine requisites for contented living Health enough to make work a pleasure Wealth enough to support your needs Strength to battle with difficulties and overcome them Grace enough to confess your sins and forsake them Patience enough to toil until some good is accomplished Charity enough to see some good in your neighbour Love enough to move you to be useful and helpful to others Faith enough to make real the things of God Hope enough to remove all anxious fears concerning the future";
		String cipherText = "BMBU SUWKMNMOUN LXS VXBOUBOUH TMYMBR DUQTOD UBXKRD OX FQZU GXSZ Q ITUQNKSU GUQTOD UBXKRD OX NKIIXSO EXKS BUUHN NOSUBROD OX JQOOTU GMOD HMLLMVKTOMUN QBH XYUSVXFU ODUF RSQVU UBXKRD OX VXBLUNN EXKS NMBN QBH LXSNQZU ODUF IQOMUBVU UBXKRD OX OXMT KBOMT NXFU RXXH MN QVVXFITMNDUH VDQSMOE UBXKRD OX NUU NXFU RXXH MB EXKS BUMRDJXKS TXYU UBXKRD OX FXYU EXK OX JU KNULKT QBH DUTILKT OX XODUSN LQMOD UBXKRD OX FQZU SUQT ODU ODMBRN XL RXH DXIU UBXKRD OX SUFXYU QTT QBAMXKN LUQSN VXBVUSBMBR ODU LKOKSU";
		Modal modal = new Modal(plainText, cipherText);
		// modal.splitAndCheck();
		modal.listSortedFrequencyLetter.forEach(x -> {
			System.out.print(x + " ");
		});
		System.out.println();
		System.out.println();
		modal.frequencyLetterCharUpper.forEach(x -> {
			System.out.print(x + " ");
		});
		System.out.println();
		System.out.println();
		modal.listSortedFrequencyLetter2.forEach(x -> {
			System.out.print(x + " ");
		});
		System.out.println();
		System.out.println();
		modal.listSortedFrequencyLetter22.forEach(x -> {
			System.out.print(x + " ");
		});
		System.out.println();
		System.out.println();
		modal.listSortedFrequencyLetter3.forEach(x -> {
			System.out.print(x + " ");
		});
		System.out.println();
		System.out.println();
		modal.listSortedFrequencyLetter33.forEach(x -> {
			System.out.print(x + " ");
		});
		System.out.println();
		System.out.println();
		System.out.println(modal.findKey(cipherText));
	}

}
