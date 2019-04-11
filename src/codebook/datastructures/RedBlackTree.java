/*
 * A red-black tree is a balanced binary tree that simulates a 2-3-4 tree.
 * This implementation is a particular form of the red-black tree called the left leaning red-black tree
 *
 * Time complexity:
 *  - Remove: O(log N)
 *  - Insertion: O(log N)
 *  - Search: O(log N)
 *  - Access: O(log N)
 */

package codebook.datastructures;

public class RedBlackTree {
  // constants for the color
  private static final boolean RED = true;
  private static final boolean BLACK = false;

  // represents the root of the tree
  private Node root;

  public void remove(int key) {
    // if both children of the root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;
    root = remove(root, key);
    if (root != null)
      root.color = BLACK;
  }

  private Node remove(Node n, int key) {
    if (n == null)
      return n;
    if (key < n.key) {
      if (!isRed(n.left) && !isRed(n.left.left))
        n = shiftLeft(n);
      n.left = remove(n.left, key);
    } else {
      if (isRed(n.left))
        n = rotateRight(n);
      if (key == n.key && n.right == null)
        return null;
      if (!isRed(n.right) && !isRed(n.right.left))
        n = shiftRight(n);
      if (key == n.key) {
        Node x = getMinNode(n.right);
        n.key = x.key;
        n.value = x.value;
        n.right = removeMinNode(n.right);
      }
      get(n.right, key);
    }
    return balance(n);
  }

  public boolean contains(int key) {
    return contains(root, key);
  }

  private boolean contains(Node n, int key) {
    if (n == null)
      return false;
    if (key < n.key)
      return contains(n.left, key);
    else if (key > n.key)
      return contains(n.right, key);
    else
      return true;
  }

  public Integer get(int key) {
    return get(root, key);
  }

  private Integer get(Node n, int key) {
    if (n == null)
      return null;
    if (key < n.key)
      get(n.left, key);
    else if (key > n.key)
      get(n.right, key);
    return n.value;
  }

  public void add(int key, int value) {
    root = add(root, key, value);
    root.color = BLACK;
  }

  private Node add(Node n, int key, int value) {
    if (n == null)
      return new Node(key, value, RED);
    if (key < n.key)
      n.left = add(n.left, key, value);
    else if (key > n.key)
      n.right = add(n.right, key, value);
    else
      n.value = value;
    // fix any right-leaning links
    if (isRed(n.right) && !isRed(n.left))
      n = rotateLeft(n);
    if (isRed(n.left) && isRed(n.left.left))
      n = rotateRight(n);
    if (isRed(n.left) && isRed(n.right))
      flipColors(n);
    return n;
  }

  private Node rotateLeft(Node n) {
    Node x = n.right;
    n.right = x.left;
    x.left = n;
    x.color = n.color;
    n.color = RED;
    return x;
  }

  private Node rotateRight(Node n) {
    Node x = n.left;
    n.left = x.right;
    x.right = n;
    x.color = n.color;
    n.color = RED;
    return x;
  }

  private void flipColors(Node n) {
    n.color = RED;
    n.left.color = n.right.color = BLACK;
  }

  private boolean isRed(Node n) {
    if (n == null)
      return false;
    return n.color == RED;
  }

  // restore red-black tree invariant
  private Node balance(Node n) {
    if (isRed(n.right))
      n = rotateLeft(n);
    if (isRed(n.left) && isRed(n.left.left))
      n = rotateRight(n);
    if (isRed(n.left) && isRed(n.right))
      flipColors(n);
    return n;
  }

  // Assuming that h is red and both h.left and h.left.left
  // are black, make h.left or one of its children red.
  private Node shiftLeft(Node n) {
    flipColors(n);
    if (isRed(n.right.left)) {
      n.right = rotateRight(n.right);
      n = rotateLeft(n);
      flipColors(n);
    }
    return n;
  }

  // Assuming that h is red and both h.right and h.right.left
  // are black, make h.right or one of its children red.
  private Node shiftRight(Node n) {
    flipColors(n);
    if (isRed(n.left.left)) {
      n = rotateRight(n);
      flipColors(n);
    }
    return n;
  }

  public Node getMinNode(Node n) {
    Node curr = n;
    while (curr.left != null)
      curr = curr.left;
    return curr;
  }

  public Node getMaxNode(Node n) {
    Node curr = n;
    while (curr.right != null)
      curr = curr.right;
    return curr;
  }

  private Node removeMinNode(Node n) {
    if (n.left == null)
      return null;
    if (!isRed(n.left) && !isRed(n.left.left))
      n = shiftLeft(n);
    n.left = removeMinNode(n.left);
    return balance(n);
  }

  @SuppressWarnings("unused")
  private Node removeMaxNode(Node n) {
    if (n.right == null)
      return null;
    if (!isRed(n.right) && !isRed(n.right.left))
      n = shiftRight(n);
    n.right = removeMaxNode(n.right);
    return balance(n);
  }

  // object representing the nodes of the tree
  private class Node {
    int key, value;
    Node left, right;
    boolean color;

    Node(int key, int value, boolean color) {
      this.key = key;
      this.value = value;
      this.color = color;
    }
  }
}
