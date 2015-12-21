	package contest.coci;
	
	import java.util.*;
	import java.io.*;
	
	public class COCI_2015_HAN {
	
		static BufferedReader br;
		static PrintWriter out;
		static StringTokenizer st;
	
		public static void main (String[] args) throws IOException {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new OutputStreamWriter(System.out));
			//br = new BufferedReader(new FileReader("in.txt"));
			//out = new PrintWriter(new FileWriter("out.txt"));
	
			int q = readInt();
			int[] cnt = new int[26];
			int index = 1;
			int pos = 0;
			int total = 0;
			int inc = 1;
			for (int i = 0; i < q; i++) {
				String command = next();
				if (command.equals("SMJER")) {
					int n = readInt();
					total += (n - index + 1) / 26;
					index += 26 * ((n - index + 1) / 26);
					for (; index <= n; index++, pos = (pos + inc + 26) % 26)
						cnt[pos]++;
					pos = (pos - 2*inc + 26) % 26;
					inc = -inc;
				} else if (command.equals("UPIT")) {
					int n = readInt();
					char x = readCharacter();
					total += (n - index + 1) / 26;
					index += 26 * ((n - index + 1) / 26);
					for (; index <= n; index++, pos = (pos + inc + 26) % 26)
						cnt[pos]++;
					out.println(cnt[x - 'a'] + total);
				}
			}
			
			out.close();
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
	
