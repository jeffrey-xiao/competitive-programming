package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2010_Stage_2_Tree_Pruning_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static final boolean WHITE = true;
	static final boolean BLACK = false;
	static Node[] nodes;
	static final int K = 300;

	public static void main (String[] args) throws IOException {
		// INPUT
		int n = readInt();
		int d = readInt();
		nodes = new Node[n];
		for (int x = 0; x < n; x++) {
			int id = readInt();
			int color = readInt();
			int children = readInt();
			nodes[id] = new Node(color);
			if (children == 1)
				nodes[id].left = readInt();
			else if (children == 2) {
				nodes[id].left = readInt();
				nodes[id].right = readInt();
			}
		}
		computeColors(nodes[0]);
		int curr = nodes[0].numWhite - nodes[0].numBlack;
		// for(int x = 0; x < n; x++)
		// System.out.println(nodes[x].numWhite + " " + nodes[x].numBlack);
		int[][] matrix = new int[n + 1][601];
		for (int x = 0; x <= n; x++) {
			for (int y = 0; y < 601; y++) {
				if (y != curr + K)
					matrix[x][y] = -1;
				else
					matrix[x][y] = 0;
			}
		}
		// for(int x = 0; x <= n; x++){
		// for(int y = -10+K; y < 10+K; y++){
		// System.out.print(matrix[x][y] + " ");
		// }
		// System.out.println();
		// }

		for (int x = 1; x <= n; x++) {
			int diff = -nodes[x - 1].numWhite + nodes[x - 1].numBlack;
			// System.out.println(diff);
			for (int y = 600; y >= 0; y--) {
				matrix[x][y] = matrix[x - 1][y];
				if (y - diff >= 0 && y - diff < 601) {
					if (matrix[x - 1][y - diff] != -1) {
						if (matrix[x - 1][y] == -1) {
							matrix[x][y] = matrix[x - 1][y - diff] + 1;
						} else {

							matrix[x][y] = Math.min(matrix[x - 1][y],
									matrix[x - 1][y - diff] + 1);
						}
					}
				}
			}
		}
		// for(int x = 0; x <= n; x++){
		// for(int y = -10+K; y < 10+K; y++){
		// System.out.printf("%3d ",matrix[x][y]);
		// }
		// System.out.println();
		// }
		// System.out.println(curr);
		System.out.println(matrix[n][(d - curr) + curr + K]);
	}

	private static void computeColors (Node n) {
		if (n.color == WHITE)
			n.numWhite++;
		else if (n.color == BLACK)
			n.numBlack++;
		if (n.left != -1) {
			computeColors(nodes[n.left]);
			n.numWhite += nodes[n.left].numWhite;
			n.numBlack += nodes[n.left].numBlack;
		}
		if (n.right != -1) {
			computeColors(nodes[n.right]);
			n.numWhite += nodes[n.right].numWhite;
			n.numBlack += nodes[n.right].numBlack;
		}

	}

	static class Node implements Comparable<Node> {
		boolean color;
		int left = -1;
		int right = -1;
		int numWhite = 0;
		int numBlack = 0;

		Node (int color) {
			this.color = color == 1;
		}

		@Override
		public int compareTo (Node n) {
			return -(numWhite - numBlack) + (n.numWhite - n.numBlack);
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
}
