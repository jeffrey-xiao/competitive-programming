package contest.dmoj;
import java.util.*;
import java.io.*;

public class Yokan {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));
		
		int n = readInt();
		int m = readInt();
		int[] in = new int[n];
		for (int i = 0; i < n; i++) {
			in[i] = readInt();
		}
		int q = readInt();
		for (int i = 0; i < q; i++) {
			int l = readInt()-1;
			int r = readInt()-1;
			int cnt = 0;
			
			double v = ((r - l + 1)/3.0d);
			//if ((r - l + 1)/3 == 0) {
				//System.out.println("YES");
				//continue;
			//}
			
			for (int k = 1; k <= m; k++) {
				int curr = 0;
				for (int j = l; j <= r; j++) {
					if (in[j] == k)
						curr++;
				}
				cnt += (int)(curr / v);
			}
			if (cnt >= 2)
				System.out.println("YES");
			else
				System.out.println("NO");
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}

