/* 
 * A rope is a data structure that is able to manipulate strings very efficiently. It is essentially a balanced binary search tree that is composed
 * of a series of smaller strings. Unfortunately, it relies on the assumption that substring takes O(1) time. As Java 1.7u6, String.substring()
 * takes O(N) time making the entire process inefficient.
 *
 * Time complexity:
 *  - Index at: O(log N)
 *  - Concatanate: O(log N)
 *  - Split: O(log N)
 *  - Insert: O(log N)
 *  - Delete: O(log N)
 *  - Report: O(K + log N) where K is the length of the string to report
 */

package codebook.string;

public class Rope {

  private Node root;

  Rope() {
    this.root = null;
  }

  Rope(Node n) {
    this.root = n;
  }

  Rope(String s) {
    this.root = new Node(s);
  }

  public static void main(String[] args) {
    Rope a = new Rope("HELLO+");
    Rope b = new Rope("MY+");
    Rope c = new Rope("NAME+");
    Rope d = new Rope("JEFFREY");

    a.concat(b);
    a.concat(c);
    a.concat(d);

    for (int i = 1; i <= 22; i++) {
      a.insert(new Rope("AAA"), i);
      System.out.println(a);
      a.delete(i, i + 2);
    }

    for (int i = 1; i <= 21; i++) {
      for (int j = i; j <= 21; j++) {
        System.out.println(a.report(i, j));
      }
    }

  }

  @Override
  public String toString() {
    if (root == null)
      return "";
    return report(1, root.weight);
  }

  public String report(Integer i, Integer j) {
    return report(root, i, j);
  }

  // prints out the string from [i, j]. It uses one-based indexing
  private String report(Node n, Integer i, Integer j) {
    if (n == null)
      return "";
    if (n.value != null && n.weight >= i)
      return n.value.substring(i - 1, Math.min(n.value.length(), j));
    String str = "";
    if (getSize(n) >= i)
      str += report(n.left, i, j);
    if (j > getSize(n))
      str += report(n.right, Math.max(1, i - getSize(n)), j - getSize(n));
    return str;
  }

  // Concatenates a rope to the end of the rope
  public void concat(Rope n) {
    root = merge(root, n.root);
  }

  // Inserts a rope at position i. 1 <= i <= size+1
  public void insert(Rope r, Integer i) {
    NodePair n = split(new NodePair(null, null), root, i - 1);
    root = merge(merge(n.n1, r.root), n.n2);
  }

  // Deletes the substring [i, j]. Uses one-based indexing
  public void delete(Integer i, Integer j) {
    NodePair n1 = split(new NodePair(null, null), root, j);
    NodePair n2 = split(new NodePair(null, null), n1.n1, i - 1);
    root = merge(n2.n1, n1.n2);
  }

  public void traverse() {
    traverse(root);
  }

  private void traverse(Node n) {
    if (n.left != null) {
      System.out.println("LEFT");
      traverse(n.left);
    }
    System.out.println(n.value + " " + n.weight);
    if (n.right != null) {
      System.out.println("RIGHT");
      traverse(n.right);
    }
  }

  // Gets the character at i. Uses one-based indexing
  public Character indexAt(Integer i) {
    return indexAt(root, i);
  }

  // one-based indexing
  private Character indexAt(Node n, Integer i) {
    if (getSize(n) < i)
      return indexAt(n.right, i - getSize(n));
    if (n.value != null)
      return n.value.charAt(i - 1);
    return indexAt(n.left, i);
  }

  private Node merge(Node n1, Node n2) {
    if (n1 == null)
      return n2;
    if (n2 == null)
      return n1;
    Node par = new Node();
    par.left = n1;
    par.right = n2;
    reset(par);
    par = balance(par);
    return par;
  }

  public NodePair split(Integer i) {
    return split(new NodePair(null, null), root, i);
  }

  private NodePair split(NodePair curr, Node n, Integer i) {
    //		System.out.println(getSize(n));
    if (n.value != null && n.weight <= i)
      curr.n1 = merge(curr.n1, n);
    else if (getSize(n) <= i) {
      curr.n1 = merge(curr.n1, n.left);
      if (n.right != null)
        curr = split(curr, n.right, i - getSize(n));
    } else {
      if (n.left != null)
        curr = split(curr, n.left, i);
      if (n.value != null) {
        Node n1 = new Node(n.value.substring(0, i));
        Node n2 = new Node(n.value.substring(i));
        curr.n1 = merge(curr.n1, n1);
        curr.n2 = merge(curr.n2, n2);
      }
      curr.n2 = merge(curr.n2, n.right);
    }
    return curr;
  }

  private Node balance(Node n) {
    Integer cmp1 = getHeight(n.left) - getHeight(n.right);
    if (cmp1 >= 2) {
      Integer cmp2 = getHeight(n.left.left) - getHeight(n.left.right);
      if (cmp2 <= -1)
        n.left = rotateLeft(n.left);
      n = rotateRight(n);
    } else if (cmp1 <= -2) {
      Integer cmp2 = getHeight(n.right.left) - getHeight(n.right.right);
      if (cmp2 >= 1)
        n.right = rotateRight(n.right);
      n = rotateLeft(n);
    }
    return n;
  }

  private Node rotateRight(Node n) {
    Node x = n.left;
    n.left = x.right;
    x.right = n;
    reset(n);
    reset(x);
    return x;
  }

  private Node rotateLeft(Node n) {
    Node x = n.right;
    n.right = x.left;
    x.left = n;
    reset(n);
    reset(x);
    return x;
  }

  private void reset(Node n) {
    n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
    n.weight = getWeights(n.right) + getWeights(n.left) + (n.value == null ? 0 : n.value.length());
  }

  private Integer getWeights(Node n) {
    return n == null ? 0 : n.weight;
  }

  private Integer getHeight(Node n) {
    return n == null ? -1 : n.height;
  }

  private Integer getSize(Node n) {
    return n.weight - (n.right == null ? 0 : n.right.weight);
  }

  static class Node {
    Node left, right;
    String value;
    Integer height, weight;

    Node() {
      this(null);
    }

    Node(String value) {
      this.value = value;
      this.height = 0;
      this.weight = value == null ? 0 : value.length();
    }
  }

  static class NodePair {
    Node n1, n2;

    NodePair(Node n1, Node n2) {
      this.n1 = n1;
      this.n2 = n2;
    }
  }
}
