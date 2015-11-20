/*
 * Implementation of a persistent singly linked list.
 *
 * Time complexity:
 *  - Remove: O(N)
 *  - Insertion: O(N)
 *  - Search: O(N)
 *  - Access: O(N)
 */

package codebook.datastructures;

import java.util.ArrayList;

public class PersistentLinkedList {
	private ArrayList<Node> versions;
	private int versionNumber;

	private class Node {
		private Integer value;
		private Node next;

		Node (Integer value) {
			this.value = value;
		}

		@Override
		public String toString () {
			return value + (next == null ? "" : (", " + next.toString()));
		}
	}

	PersistentLinkedList () {
		versions = new ArrayList<Node>();
		versions.add(null);
		versionNumber = 0;
	}

	public void insert (Integer val, int index) {
		versions.add(insert(versions.get(versionNumber++), 0, val, index));
	}

	private Node insert (Node curr, int i, Integer val, int j) {
		if (i == j) {
			Node ret = new Node(val);
			ret.next = curr;
			return ret;
		}
		Node ret = new Node(curr.value);
		ret.next = insert(curr.next, i + 1, val, j);
		return ret;
	}

	public void delete (int index) {
		versions.add(delete(versions.get(versionNumber++), 0, index));
	}

	private Node delete (Node curr, int i, int j) {
		if (i == j) {
			if (curr.next == null)
				return null;
			Node ret = new Node(curr.next.value);
			ret.next = curr.next.next;
			return ret;
		}
		Node ret = new Node(curr.value);
		ret.next = delete(curr.next, i + 1, j);
		return ret;
	}

	@Override
	public String toString () {
		StringBuilder res = new StringBuilder();
		for (Node n : versions) {
			if (n == null)
				res.append(String.format("[]"));
			else
				res.append(String.format("[%s]\n", n.toString()));
		}
		return res.toString();
	}

	public static void main (String[] args) {
		PersistentLinkedList l = new PersistentLinkedList();
		l.insert(1, 0);
		l.insert(2, 1);
		l.insert(3, 2);
		l.insert(4, 0);
		l.delete(1);
		l.delete(2);
		l.insert(5, 0);
		l.insert(6, 0);
		l.delete(0);
		l.delete(1);
		System.out.println(l);
	}
}
