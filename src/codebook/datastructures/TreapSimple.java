/*
 * A treap is a randomized balanced binary search tree. 
 * It is built on the observation that randomized binary search trees are usually balanaced to a certian degree. 
 * When a node is inserted, it has another attribute called the priority.
 * Through merges and splits, a treap maintains a heap invariant with the priorities and a binary search tree invariant with the keys.
 *
 * Time complexity:
 *  - Remove: O(log N)
 *  - Insertion: O(log N)
 *  - Search: O(log N)
 *  - Access: O(log N)
 */

package codebook.datastructures;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;

public class TreapSimple {
  // root of the tree
  Node root = null;

  public static void main(String[] args) {
    TreapSimple t = new TreapSimple();
    long c = System.currentTimeMillis();
    TreeSet<Integer> hs = new TreeSet<Integer>();
    for (int x = 0; x < 1000000; x++) {
      int ran = (int) (Math.random() * (100000)) + 5;
      hs.add(ran);
      t.add(ran);
    }
    System.out.println(hs.size());
    //		for (Integer i : hs)
    //			System.out.print(i + " ");
    //		System.out.println();
    //		t.traverse(t.root);
    //		System.out.println();
    t.add(1);
    assert t.contains(t.root, 1);
    assert !t.contains(t.root, 2);
    t.remove(1);
    assert !t.contains(t.root, 1);
    System.out.println(System.currentTimeMillis() - c);
    for (Integer i : hs) {
      t.remove(i);
      assert !t.contains(t.root, i);
    }
    System.out.println("SUCCESS");
  }

  public void remove(Integer k) {
    root = remove(root, k);
  }

  public void add(Integer k) {
    root = add(root, new Node(k));
  }

  public void add(Integer k, Integer v) {
    root = add(root, new Node(k, v));
  }

  public boolean contains(Integer k) {
    return contains(root, k);
  }

  public Integer get(Integer k) {
    return get(root, k);
  }

  public Iterable<Integer> range(Integer loK, Integer hiK) {
    Queue<Integer> res = new ArrayDeque<Integer>();
    range(root, loK, hiK, res);
    return res;
  }

  // in order traversal of nodes
  public void traverse(Node n) {
    if (n == null)
      return;
    traverse(n.left);
    System.out.print(n.key + " ");
    traverse(n.right);
  }

  // auxiliary function for range
  private void range(Node n, Integer loK, Integer hiK, Queue<Integer> res) {
    if (n == null)
      return;
    if (n.key > hiK)
      range(n.left, loK, hiK, res);
    if (loK <= n.key && n.key <= hiK) {
      range(n.left, loK, hiK, res);
      res.offer(n.key);
      range(n.right, loK, hiK, res);
    }
    if (n.key < loK)
      range(n.right, loK, hiK, res);
  }

  // auxiliary function for contains
  private boolean contains(Node n, Integer k) {
    if (n == null)
      return false;
    int cmp = k.compareTo(n.key);
    if (cmp < 0)
      return contains(n.left, k);
    else if (cmp > 0)
      return contains(n.right, k);
    return true;
  }

  // auxiliary function for get
  private Integer get(Node n, Integer k) {
    if (n == null)
      return null;
    int cmp = k.compareTo(n.key);
    if (cmp < 0)
      return get(n.left, k);
    else if (cmp > 0)
      return get(n.right, k);
    return n.value;
  }

  // auxiliary function to delete
  private Node remove(Node n, Integer k) {
    if (n == null)
      return n;
    NodePair nodes = split(n, k);
    return merge(nodes.left, nodes.right);
  }

  // auxiliary function to merge
  private Node merge(Node t1, Node t2) {
    if (t1 == null)
      return t2;
    else if (t2 == null)
      return t1;

    if (t1.priority > t2.priority) {
      t1.right = merge(t1.right, t2);
      return t1;
    } else {
      t2.left = merge(t1, t2.left);
      return t2;
    }
  }

  // auxiliary function to split
  private NodePair split(Node n, Integer key) {
    NodePair res = new NodePair(null, null);
    if (n == null)
      return res;

    if (n.key > key) {
      res = split(n.left, key);
      n.left = res.right;
      res.right = n;
      return res;
    } else if (n.key < key) {
      res = split(n.right, key);
      n.right = res.left;
      res.left = n;
      return res;
    } else {
      return new NodePair(n.left, n.right);
    }
  }

  // auxiliary function to insert
  private Node add(Node n, Node m) {
    if (n == null)
      return m;
    NodePair nodes = split(n, m.key);
    return merge(nodes.left, merge(m, nodes.right));
  }

  // object representing the nodes of the tree
  class Node {
    Integer key;
    Integer value;
    Double priority;
    Node left, right;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
      priority = Math.random();
    }

    Node(int key) {
      this.key = key;
      this.value = key;
      priority = Math.random();
    }
  }

  // object representing a pair of nodes of the tree
  class NodePair {
    Node left, right;

    NodePair(Node left, Node right) {
      this.left = left;
      this.right = right;
    }
  }
}
