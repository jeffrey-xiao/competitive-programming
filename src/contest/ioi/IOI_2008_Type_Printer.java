package contest.ioi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class IOI_2008_Type_Printer {
	static int counter = 0;
	static String end = "";
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		IOI_2008_Type_Printer.TrieNode tn = new IOI_2008_Type_Printer().new TrieNode(
				'\u0000', false);
		int numOfWords = scan.nextInt();
		String[] s = new String[numOfWords];
		for (int x = 0; x < numOfWords; x++) {
			s[x] = scan.next();

		}
		Arrays.sort(s, new Comparator<String>() {

			@Override
			public int compare (String o1, String o2) {
				return o1.length() - o2.length();
			}

		});

		for (int x = 0; x < numOfWords; x++) {
			tn.addWord(s[x]);
		}
		tn.printWords(true);
		System.out.println(counter);
		/*
		 * for(int x = 0; x < end.length(); x++){
		 * System.out.println(end.charAt(x)); }
		 */
		System.out.println(end);
	}

	class TrieNode {
		char letter;
		TrieNode[] links;
		boolean fullWord;

		TrieNode (char letter, boolean fullWord) {
			this.letter = letter;
			links = new TrieNode[26];
			this.fullWord = fullWord;
		}

		void addWord (String s) {
			if (s.length() == 0)
				return;
			for (int x = 0; x < links.length; x++) {
				if (links[x] != null && links[x].letter == s.charAt(0)) {
					links[x].addWord(s.substring(1, s.length()));
					break;
				}
				if (links[x] == null) {
					if (s.length() == 1)
						links[x] = new TrieNode(s.charAt(0), true);
					else
						links[x] = new TrieNode(s.charAt(0), false);
					links[x].addWord(s.substring(1, s.length()));
					break;
				}
			}
		}

		void printWords (boolean lastWord) {
			boolean currLastWord = lastWord;
			if (fullWord) {
				end += letter;
				end += "P";
				counter += 2;
			}
			if (letter != '\u0000' && !fullWord) {
				end += letter;
				counter++;
			}
			for (int x = 0; x < links.length; x++) {
				if (links[x] != null) {
					if ((x + 1 != links.length && links[x + 1] != null)
							|| !lastWord) {
						links[x].printWords(false);
						currLastWord = false;
					} else {
						links[x].printWords(true);
						currLastWord = true;
					}
					if (currLastWord) {
						return;
					}

				} else {
					break;
				}
			}
			if (!currLastWord)
				end += "-";
			counter++;
		}
	}
}
