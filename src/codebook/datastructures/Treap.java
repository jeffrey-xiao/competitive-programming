/*
 * A treap is a randomized balanced binary search tree. 
 * It is built on the observation that randomized binary search trees are usually balanaced to a certian degree. 
 * When a node is inserted, it has another attribute called the priority.
 * Through tree rotations, a treap maintains a heap invariant with the priorities and a binary search tree invariant with the keys.
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

public class Treap {
  // root of the tree
  Node root = null;

  public static void main(String[] args) {
    Treap t = new Treap();
    long c = System.currentTimeMillis();
    TreeSet<Integer> hs = new TreeSet<Integer>();
    for (int x = 0; x < 100000; x++) {
      int ran = (int) (Math.random() * (100000)) + 5;
      hs.add(ran);
      t.add(ran);
    }
    System.out.println(hs.size());
    for (Integer i : hs)
      System.out.print(i + " ");
    System.out.println();
    t.traverse(t.root);
    System.out.println();
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
    root = add(root, k, k);
  }

  public void add(Integer k, Integer v) {
    root = add(root, k, v);
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
      res.offer(n.key);
      range(n.left, loK, hiK, res);
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
    int cmp = k.compareTo(n.key);
    if (cmp < 0)
      n.left = remove(n.left, k);
    else if (cmp > 0)
      n.right = remove(n.right, k);
    else {
      if (n.left == null && n.right == null)
        return null;
      else if (n.left == null || (n.right != null && n.left.priority > n.right.priority)) {
        n = rotateLeft(n);
        n.left = remove(n.left, k);
      } else {
        n = rotateRight(n);
        n.right = remove(n.right, k);
      }
    }
    return n;
  }

  // auxiliary function to insert
  private Node add(Node n, Integer k, Integer v) {
    if (n == null)
      return new Node(k, v);
    int cmp = k.compareTo(n.key);
    // going left
    if (cmp < 0) {
      n.left = add(n.left, k, v);
      if (n.priority < n.left.priority) {
        n = rotateRight(n);
      }
    }
    // going right
    else if (cmp > 0) {
      n.right = add(n.right, k, v);
      if (n.priority < n.right.priority)
        n = rotateLeft(n);
    } else
      n.value = v;
    return n;
  }

  public Integer getFirst() {
    return getFirst(root).value;
  }

  public Integer getLast() {
    return getLast(root).value;
  }

  private Node getFirst(Node n) {
    while (n.left != null)
      n = n.left;
    return n;
  }

  private Node getLast(Node n) {
    while (n.right != null)
      n = n.right;
    return n;
  }

  // rotate left
  private Node rotateLeft(Node n) {
    Node x = n.right;
    n.right = x.left;
    x.left = n;
    return x;
  }

  // rotate right
  private Node rotateRight(Node n) {
    Node x = n.left;
    n.left = x.right;
    x.right = n;
    return x;
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
}
