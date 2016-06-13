package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2006_LISTA {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static Node[] nodes;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		
		nodes = new Node[N];
		
		for (int i = 0; i < N; i++)
			nodes[i] = new Node(i);
		
		for (int i = 0; i < N; i++) {
			if (i > 0)
				nodes[i].left = nodes[i - 1];
			if (i < N - 1)
				nodes[i].right = nodes[i + 1];
		}
		
		for (int i = 0; i < M; i++) {
			char command = readCharacter();
			int a = readInt() - 1;
			int b = readInt() - 1;
			
			if (nodes[a].left != null)
				nodes[a].left.right = nodes[a].right;
			
			if (nodes[a].right != null)
				nodes[a].right.left = nodes[a].left;
			
			if (command == 'A') {
				if (nodes[b].left != null)
					nodes[b].left.right = nodes[a];
				nodes[a].left = nodes[b].left;
				
				nodes[a].right = nodes[b];
				nodes[b].left = nodes[a];
			} else {
				if (nodes[b].right != null)
					nodes[b].right.left = nodes[a];
				nodes[a].right = nodes[b].right;
				
				nodes[a].left = nodes[b];
				nodes[b].right = nodes[a];
			}
		}
		
		Node start = null;
		for (int i = 0; i < N; i++)
			if (nodes[i].left == null)
				start = nodes[i];
		
		int[] seq = new int[N];
		int[] dp = new int[N + 1]; // dp[len] = index
		int[] pre = new int[N + 1];
		
		for (int i = 0; i < N; i++) {
			seq[i] = start.val;
			start = start.right;
		}
		
		int maxLen = 0;
		for (int i = 0; i < N; i++) {
			int lo = 1;
			int hi = maxLen;
			
			while (lo <= hi) {
				int mid = (lo + hi) >> 1;
				if (seq[dp[mid]] >= seq[i])
					hi = mid - 1;
				else
					lo = mid + 1;
			}
			
			dp[lo] = i;
			pre[i] = dp[lo - 1];
			if (lo > maxLen)
				maxLen = lo;
		}
		
		int min = 1 << 30;
		boolean[] in = new boolean[N];
		
		int curr = dp[maxLen];
		for (int i = 0; i < maxLen; i++) {
			min = Math.min(min, seq[curr]);
			in[seq[curr]] = true;
			curr = pre[curr];
		}
		
		
		out.println(N - maxLen);
		for (int i = 0; i < N; i++) {
			if (!in[i]) {
				if (i == 0) {
					out.printf("A 1 %d\n", min + 1);
				} else {
					out.printf("B %d %d\n", i + 1, i);
				}
			}
		}
		out.close();
	}

	static class Node {
		Node left, right;
		int val;
		
		Node (int val) {
			this.val = val;
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

