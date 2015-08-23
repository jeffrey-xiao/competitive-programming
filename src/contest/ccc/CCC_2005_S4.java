package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2005_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {

			int n = readInt();
			String[] messages = new String[n];
			for (int x = 0; x < n; x++)
				messages[x] = next();
			Node root = new Node(new Node(""), messages[n - 1]);
			Node curr = root;
			for (int x = 0; x < n; x++) {
				curr = curr.addNode(messages[x], curr);
			}
			// System.out.println(root.child.get(0).curr);
			int newTime = (root.traverse() - 20);
			// System.out.println(newTime);
			System.out.println((n * 10) - newTime);
		}
	}

	static class Node {
		ArrayList<Node> child = new ArrayList<Node>();
		Node parent;
		String curr;

		Node (String curr) {
			this.curr = curr;
		}

		Node (Node parent, String curr) {
			this.parent = parent;
			this.curr = curr;
		}

		Node addNode (String node, Node currentNode) {
			// System.out.println(node + " " + parent.curr + " " + curr);
			if (node.equalsIgnoreCase(parent.curr)) {
				// System.out.println("ENTER");
				return parent;
			}
			Node n = new Node(currentNode, node);
			int index = child.indexOf(n);
			if (index == -1) {
				// System.out.println(node);
				child.add(n);
				return n;
			}
			return child.get(index);
		}

		int traverse () {
			int total = 0;
			for (int x = 0; x < child.size(); x++) {

				total = Math.max(child.get(x).traverse(), total);
				// System.out.println(child.get(x).curr + " " + total + " " +
				// curr);
			}
			return total + 20;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Node) {
				Node n = (Node) o;
				return curr.equals(n.curr);
			}
			return false;
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
