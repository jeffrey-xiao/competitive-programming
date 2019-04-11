/*
 * A B-tree is a self-balancing tree data structure that keeps data stored and allows searches, accesses, insertions and deletions in logarithmic time.
 * The B-tree is a generalization of a binary search tree in that a node can have more than two children.
 *
 * Time complexity:
 *  - Remove: O(log N)
 *  - Insertion: O(log N)
 *  - Search: O(log N)
 *  - Access: O(log N)
 */

package codebook.datastructures;

public class BTree {
  // max child = M - 1
  private int M;
  private Node root;
  private int height;

  BTree() {
    this(4);
  }

  BTree(int M) {
    this.M = M;
    this.root = new Node(0);
  }

  public static void main(String[] args) {
    BTree t = new BTree();
    for (int i = 0; i < 100; i++)
      t.put(i, i);
    for (int i = 0; i < 100; i++)
      System.out.println(t.get(i));
  }

  public Integer get(Integer key) {
    return get(root, key, height);
  }

  private Integer get(Node n, Integer key, Integer h) {
    if (h == 0) {
      for (int i = 0; i < n.m; i++)
        if (key == n.child[i].key)
          return n.child[i].value;
    } else {
      for (int i = 0; i < n.m; i++)
        if (i + 1 == n.m || key < n.child[i + 1].key)
          return get(n.child[i].next, key, h - 1);
    }
    return null;
  }

  public void put(Integer key, Integer value) {
    Node newNode = put(root, key, value, height);
    if (newNode == null)
      return;

    Node newRoot = new Node(2);
    newRoot.child[0] = new Entry(root.child[0].key, null, root);
    newRoot.child[1] = new Entry(newNode.child[0].key, null, newNode);
    root = newRoot;
    height++;
  }

  private Node put(Node n, Integer key, Integer value, Integer h) {
    Entry newEntry = new Entry(key, value, null);
    int i;
    if (h == 0) {
      for (i = 0; i < n.m; i++)
        if (key < n.child[i].key)
          break;
    } else {
      for (i = 0; i < n.m; i++) {
        if (i + 1 == n.m || key < n.child[i + 1].key) {
          Node newNode = put(n.child[i++].next, key, value, h - 1);
          if (newNode == null)
            return null;
          newEntry.key = newNode.child[0].key;
          newEntry.next = newNode;
          break;
        }
      }
    }
    for (int j = n.m; j > i; j--)
      n.child[j] = n.child[j - 1];
    n.child[i] = newEntry;
    n.m++;
    if (n.m < M)
      return null;
    return split(n);
  }

  private Node split(Node n) {
    Node newNode = new Node(M / 2);
    n.m = M / 2;
    for (int i = 0; i < M / 2; i++)
      newNode.child[i] = n.child[i + M / 2];
    return newNode;
  }

  private class Node {
    private int m;
    private Entry[] child = new Entry[M];

    private Node(int m) {
      this.m = m;
    }
  }

  private class Entry {
    private Integer key, value;
    private Node next;

    private Entry(Integer key, Integer value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }
}
