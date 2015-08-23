package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class IOI_2012_Crayfish {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		String currString = "";
		int n = readInt();
		Node root = new Node('\u0000');
		int state = 1;
		for (int x = 0; x < n; x++) {
			char command = next().charAt(0);
			if (command == 'T') {
				currString += next().charAt(0);
				root = insert(root, currString, state);
			}
		}
	}

	static class Node {
		boolean end;
		HashSet<Integer> states;
		Node[] letters;
		char value;

		Node (char value) {
			this.value = value;
			states = new HashSet<Integer>();
			letters = new Node[26];
		}

	}

	private static Node insert (Node n, String s, int state) {
		char currLetter = s.charAt(0);
		int index = currLetter - 97;
		if (n.letters[index] == null) {
			System.out.println(index + " " + currLetter);
			n.letters[index] = new Node(currLetter);
		}

		n.states.add(state);

		if (s.length() == 1) {
			n.letters[index].end = true;
			n.letters[index].states.add(state);
		} else
			n.letters[index] = insert(n.letters[index], s.substring(1), state);

		return n;
	}

	private static void print (Node n, String s) {
		if (n.end)
			System.out.println(s + n.value + n.states);
		for (int x = 0; x < 26; x++) {
			if (n.letters[x] != null) {
				print(n.letters[x], s + n.value + n.states);
			}
		}
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}

	public IOI_2012_Crayfish () {
	}
}
