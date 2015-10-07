package contest.spoj;

import java.util.*;
import java.io.*;

public class DynamicLca {
	private static Node[] nodes;

	private static class Node {
		Node path_parent;
		// tree pointers
		Node parent, left, right;
		Integer id;
		Node (Integer id) {
			this.id = id;
		}
	}
	// precondition: n must be a root node, and n and m must be in different trees
	public static void link (Node n, Node m) {
		access(n);
		access(m);

		n.left = m;
		m.parent = n;
		m.path_parent = null;
	}
	// precondition: n is not a root node
	public static void cut (Node n) {
		access(n);
		if (n.left != null) {
			n.left.parent = null;
			n.left.path_parent = null;
			n.left = null;
		}
	}

	public static Node getRoot (Node n) {
		access(n);
		while (n.left != null)
			n = n.left;
		splay(n);
		return n;
	}

	public static void access (Node n) {
		splay(n);
		if (n.right != null) {
			n.right.path_parent = n;
			n.right.parent = null;
			n.right = null;
		}
		Node m = n;
		while (m.path_parent != null) {
			Node nextTree = m.path_parent;
			splay(nextTree);
			if (nextTree.right != null) {
				nextTree.right.path_parent = nextTree;
				nextTree.right.parent = null;
			}
			nextTree.right = m;
			m.parent = nextTree;
			m.path_parent = null;

			m = nextTree;
		}
		splay(n);
	}
	private static void splay (Node n) {
		while (n.parent != null) {
			Node p = n.parent;
			Node pp = n.parent.parent;
			if (pp == null) {
				rotate(n);
			} else if ((pp.left == p && p.left == n) || (pp.right == p && p.right == n)) {
				rotate(p);
				rotate(n);
			} else {
				rotate(n);
				rotate(n);
			}
		}
	}
	private static void rotate (Node n) {
		Node p = n.parent;
		Node pp = n.parent.parent;
		if (p.left == n) {
			p.left = n.right;
			n.right = p;
			p.parent = n;
			if (p.left != null)	
				p.left.parent = p;
		} else if (p.right == n) {
			p.right = n.left;
			n.left = p;
			p.parent = n;
			if (p.right != null)
				p.right.parent = p;
		}
		n.parent = pp;
		if (pp != null) {
			if (pp.left == p)
				pp.left = n;
			else
				pp.right = n;
		}
		n.path_parent = p.path_parent;
		p.path_parent = null;
	}

	public static Node lca (Node n, Node m) {
		if (getRoot(n) != getRoot(m))
			return null;
		access(m);
		splay(n);
		if (n.right != null) {
			n.right.path_parent = n;
			n.right.parent = null;
			n.right = null;
		}
		Node res = n;
		Node k = n;
		while (k.path_parent != null) {
			Node nextTree = k.path_parent;
			splay(nextTree);
			if (nextTree.path_parent == null)
				res = nextTree;
			if (nextTree.right != null) {
				nextTree.right.path_parent = nextTree;
				nextTree.right.parent = null;
			}
			nextTree.right = k;
			k.parent = nextTree;
			k.path_parent = null;

			k = nextTree;
		}
		splay(n);
		return res;
	}

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		
		int n = readInt();
		int m = readInt();
		nodes = new Node[n];
		for (int i = 0; i < n; i++)
			nodes[i] = new Node(i+1);
		
		for (int i = 0; i < m; i++) {
			String command = next();
			if (command.equals("link")) {
				int j = readInt()-1;
				int k = readInt()-1;
				link(nodes[j], nodes[k]);
			} else if (command.equals("cut")) {
				int j = readInt()-1;
				cut(nodes[j]);
			} else {
				int j = readInt()-1;
				int k = readInt()-1;
				Node lca = lca(nodes[j], nodes[k]);
				out.println(lca.id);
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

