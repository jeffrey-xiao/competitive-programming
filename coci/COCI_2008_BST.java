package coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2008_BST {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int count = -1;

	public static void main (String[] args) throws IOException {

		int x = readInt();
		Node n = new Node(0);
		for (int y = 0; y < x; y++) {
			n.insert(readInt());
			System.out.println(count);
		}

	}

	static class Node {
		int value;
		Node leftNode;
		Node rightNode;

		Node (int value) {
			this.value = value;
		}

		void insert (int value) {
			Node curr = this;
			count++;
			while (curr.value != 0) {
				if (value < curr.value) {
					if (curr.leftNode == null) {
						curr.leftNode = new Node(value);
						return;
					}
					curr = curr.leftNode;
					count++;
				} else if (value > curr.value) {
					if (curr.rightNode == null) {
						curr.rightNode = new Node(value);
						return;
					}
					curr = curr.rightNode;
					count++;
				}
			}
			curr.value = value;
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
