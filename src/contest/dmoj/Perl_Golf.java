package contest.dmoj;

import java.util.*;
import java.io.*;

public class Perl_Golf {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));

		String s = "print\'Don Mills Online Judge";
		String chars = " !\"#$%&^()*+,-./:;<=>?@[\\]^_`'{|}~";
		out.print("''=~('(?{'");
		String[] dp = new String[257];
		for (int i = 0; i <= 256; i++)
			dp[i] = "";
		for (int i = 0; i < chars.length(); i++) {
			for (int j = 0; j <= 256; j++) {
				if (j == 0 || dp[j].length() != 0) {
					int val = j ^ chars.charAt(i);
					if (val <= 256 && (dp[val].length() > dp[j].length() + 1 || dp[val].length() == 0) && val != 0) {
						dp[val] = dp[j] + chars.charAt(i);
					}
				}
			}
		}
		dp[33] = "]|";
		dp[39] = "\\{";
		dp[32] = "@`";
		String prev1 = "";
		String prev2 = "";
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i);
			System.out.println(dp[val].length() + " " + val);
			if (dp[val].length() == 2) {
				if (dp[val].charAt(0) == '\'')
					prev1 += "\\'";
				else
					prev1 += dp[val].charAt(0);
				if (dp[val].charAt(1) == '\'')
					prev2 += "\\'";
				else
					prev2 += dp[val].charAt(1);

			} else {
				if (prev1.length() != 0) {
					out.print(".(\'" + prev1 + "\'^\'" + prev2 + "\')");
					prev1 = "";
					prev2 = "";
				}
				String res = ".(";
				if (dp[val].length() == 1)
					res = res.substring(0, res.length() - 1);
				for (int j = 0; j < dp[val].length(); j++) {
					if (dp[val].charAt(j) == '\'')
						res += "'\\''^";
					else
						res += "'" + dp[val].charAt(j) + "'^";
				}
				res = res.substring(0, res.length() - 1);
				res += ")";
				if (dp[val].length() == 1)
					res = res.substring(0, res.length() - 1);
				out.print(res);
			}
		}
		if (prev1.length() != 0) {
			out.print(".(\'" + prev1 + "\'^\'" + prev2 + "\')");
			prev1 = "";
			prev2 = "";
		}
		out.print(".\"'})\")");
		out.close();
	}

	static char get (char c) {
		return (char)(c + c);
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
}
