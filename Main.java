import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		System.out.println(scrambleWord(""));
		byte x = 0;
		pr.close();
	}

	public static void scrambleOrRemove (List<String> wordList) {
		for (int i = wordList.size() - 1; i >= 0; i--) {
			if (wordList.get(i).equals(scrambleWord(wordList.get(i)))) {
				wordList.remove(i);
			}
		}
	}

	public static String scrambleWord (String word) {
		char[] in = word.toCharArray();
		for (int i = 0; i < in.length - 1; i++) {
			if (in[i] == 'A' && in[i + 1] != 'A') {
				char temp = in[i];
				in[i] = in[i + 1];
				in[i + 1] = temp;
				i++;
			}
		}
		return new String(in);
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
