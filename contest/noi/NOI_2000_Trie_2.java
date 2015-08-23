package contest.noi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NOI_2000_Trie_2 {
	static int counter = 0;
	static String end = "";
	static int countNodes = 1;
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		NOI_2000_Trie_2.TrieNode tn = new NOI_2000_Trie_2().new TrieNode(
				'\u0000', false);

		String s = br.readLine();
		while (s != null && s.length() != 0) {
			tn.addWord(s);
			s = br.readLine();
		}

		System.out.println(countNodes);
	}

	static String next () {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine().trim());
			} catch (IOException e) {
			}
		}
		return st.nextToken();
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
					NOI_2000_Trie_2.countNodes++;
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
