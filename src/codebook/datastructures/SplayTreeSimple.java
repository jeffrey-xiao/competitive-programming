/*
 * A splay tree is a balanced binary tree with the additional property that recently accessed nodes are quick to access again.
 * When accessing node x, it brings the node to the root of the tree using a splay operation. This particular implementation
 * supports multiple same keys.
 *
 * Time complexity:
 *  - Remove: O(log N)
 *  - Insertion: O(log N)
 *  - Search: O(log N)
 *  - Access: O(log N)
 */

package codebook.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SplayTreeSimple {
	final static Node NULL = new Node(0, 0);

	// root of the tree
	Node root = NULL;

	// object representing a pair of nodes
	static class NodePair {
		Node left, right;

		NodePair (Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}

	// object representing a node of the tree
	static class Node implements Comparable<Node> {
		int key, value, sz, cnt;
		Node[] child = new Node[2];
		Node par;

		Node (int key) {
			this.cnt = 1;
			this.sz = 1;
			this.key = key;
			this.value = key;
			this.child[0] = this.child[1] = this.par = this;
		}

		Node (int key, int value) {
			this.cnt = 1;
			this.sz = 1;
			this.key = key;
			this.value = value;
			this.child[0] = this.child[1] = this.par = this;
		}

		@Override
		public int compareTo (Node o) {
			return key - o.key;
		}
	}

	static int getSize (Node u) {
		return u == NULL ? 0 : u.sz;
	}

	static void update (Node u) {
		if (u != NULL)
			u.sz = u.cnt + getSize(u.child[0]) + getSize(u.child[1]);
	}

	static int getDir (Node u, Node p) {
		return p.child[0] == u ? 0 : 1;
	}

	static void connect (Node u, Node v, int dir) {
		u.child[dir] = v;
		v.par = u;
	}

	static Node rotate (Node u, int dir) {
		Node c = u.child[dir ^ 1], p = u.par, pp = p.par;
		connect(p, c, dir);
		connect(u, p, dir ^ 1);
		connect(pp, u, getDir(p, pp));
		update(p);
		update(u);
		update(pp);
		return u;
	}

	static Node splay (Node u) {
		while (u.par != NULL) {
			Node p = u.par, pp = p.par;
			int dp = getDir(u, p), dpp = getDir(p, pp);
			if (pp == NULL) {
				rotate(u, dp);
			} else if (dp == dpp) {
				rotate(p, dpp);
				rotate(u, dp);
			} else {
				rotate(u, dp);
				rotate(u, dpp);
			}
		}
		return u;
	}

	// closest node to val
	static Node closest (Node u, int val) {
		if (u == NULL)
			return u;
		Node ret = u;
		while (u != NULL) {
			ret = u;
			if (u.key < val)
				u = u.child[1];
			else if (u.key > val)
				u = u.child[0];
			else
				return u;
		}
		return ret;
	}

	int nodeAt (int index) {
		if (root == NULL)
			return -1;
		Node u = root;
		while (u != NULL) {
			if (getSize(u.child[0]) + u.cnt < index) {
				index -= getSize(u.child[0]) + u.cnt;
				u = u.child[1];
			} else if (getSize(u.child[0]) + 1 > index) {
				u = u.child[0];
			} else {
				int ret = u.key;
				root = splay(u);
				return ret;
			}
		}
		return -1;
	}

	int indexOf (int val) {
		if (root == NULL || !contains(val))
			return -1;
		int ret = 0;
		Node u = root;
		while (u != NULL) {
			if (u.key < val) {
				ret += getSize(u.child[0]) + u.cnt;
				u = u.child[1];
			} else if (u.key > val) {
				u = u.child[0];
			} else {
				ret += getSize(u.child[0]) + 1;
				root = splay(u);
				return ret;
			}
		}
		assert false;
		return -1;
	}

	// precondition: all values of u are smaller than all values of v
	static Node join (Node u, Node v) {
		if (u == NULL)
			return v;
		while (u.child[1] != NULL)
			u = u.child[1];
		splay(u);
		u.child[1] = v;
		update(u);
		if (v != NULL)
			v.par = u;
		return u;
	}

	static NodePair split (Node u, int val) {
		if (u == NULL)
			return new NodePair(NULL, NULL);
		splay(u = closest(u, val));

		if (u.key <= val) {
			Node ret = u.child[1];
			u.child[1] = (u.child[1].par = NULL);
			update(ret);
			update(u);
			return new NodePair(u, ret);
		} else {
			Node ret = u.child[0];
			u.child[0] = (u.child[0].par = NULL);
			update(ret);
			update(u);
			return new NodePair(ret, u);
		}
	}

	// in order traversal of the tree
	public static void traverse (Node n) {
		if (n == NULL)
			return;
		traverse(n.child[0]);
		for (int i = 0; i < n.cnt; i++)
			out.print(n.key + " ");
		traverse(n.child[1]);
	}

	public boolean contains (int val) {
		Node curr = root;
		while (curr != NULL) {
			if (curr.key < val)
				curr = curr.child[1];
			else if (curr.key > val)
				curr = curr.child[0];
			else
				return true;
		}
		return false;
	}

	public void add (int val) {
		NodePair res = split(root, val);
		if (res.left != NULL && res.left.key == val) {
			res.left.cnt++;
			update(res.left);
			root = join(res.left, res.right);
		} else {
			root = new Node(val);
			root.par = NULL;
			root.child[0] = res.left;
			root.child[1] = res.right;
			update(root);
			if (root.child[0] != NULL)
				root.child[0].par = root;
			if (root.child[1] != NULL)
				root.child[1].par = root;
		}
	}

	public void remove (int val) {
		Node curr = closest(root, val);
		if (curr == NULL || curr.key != val)
			return;
		splay(curr);
		if (curr.cnt > 1) {
			curr.cnt--;
			update(curr);
			root = curr;
		} else if (curr.cnt == 1) {
			curr.child[0].par = curr.child[1].par = NULL;
			root = join(curr.child[0], curr.child[1]);
		}
	}

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt(), M = readInt();
		SplayTreeSimple s = new SplayTreeSimple();

		for (int i = 0; i < N; i++)
			s.add(readInt());
		int lastAns = 0;
		for (int i = 0; i < M; i++) {
			char c = next().charAt(0);
			int v = readInt();
			if (c == 'I')
				s.add(v ^ lastAns);
			else if (c == 'R')
				s.remove(v ^ lastAns);
			else if (c == 'S')
				out.println(lastAns = s.nodeAt(v ^ lastAns));
			else if (c == 'L')
				out.println(lastAns = s.indexOf(v ^ lastAns));
		}
		SplayTreeSimple.traverse(s.root);
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
