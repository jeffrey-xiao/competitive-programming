package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2006_Deciphering_Mayan_Writing {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int[] charsInGlyph = new int[52]; // cap letters and lower case letters
		int[] charsInSeq = new int[52];
		int g = nextInt();
		int s = nextInt();
		for (int x = 0; x < g; x++) {
			char c = (char) br.read();
			int index = (c) - 65 > 25 ? (c) - 71 : (c) - 65;
			charsInGlyph[index]++;
		}
		nextLine();
		char[] sequence = nextLine().toCharArray();

		int indexOfSeq = 0;
		int occurences = 0;
		for (int x = 0; x < s; x++) {
			char c = sequence[x];
			int index = (c) - 65 > 25 ? (c) - 71 : (c) - 65;
			charsInSeq[index]++;
			if (charsInGlyph[index] == 0) {
				charsInSeq = new int[52];
				indexOfSeq = x + 1;
			} else {
				while (charsInSeq[index] > charsInGlyph[index]) {
					char charToRemove = sequence[indexOfSeq++];

					int indexToRemove = (charToRemove) - 65 > 25 ? (charToRemove) - 71 : (charToRemove) - 65;
					charsInSeq[indexToRemove]--;
				}
			}
			// System.out.println(indexOfSeq + " " + x);
			if (x - indexOfSeq + 1 == g)
				occurences++;
		}
		System.out.println(occurences);
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

	static int nextInt () {
		return Integer.parseInt(next());
	}

	static String nextLine () {
		String s = "";
		try {
			s = br.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

}
