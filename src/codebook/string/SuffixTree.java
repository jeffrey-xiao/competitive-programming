/*
 * Online construction of a suffix tree using Ukkonen's algorithm
 *
 * Time complexity:
 *  - Add character: O(1)
 *  - Add string: O(N)
 */

package codebook.string;

public class SuffixTree {

  // constants
  static final int END = 1 << 30;

  // attributes of the input data
  private String input;
  private int len;

  private int currentPos;
  private Node root;

  private Node activeNode;
  private int activeEdge;
  private int activeLength;

  private int remainder;

  private boolean firstNodeCreated;
  private Node lastNodeCreated;

  SuffixTree (String input) {
    this.input = input;
    initialize();
  }

  public void setString (String string) {
    this.input = string;
    initialize();
  }

  public String getString () {
    return input;
  }

  private void initialize () {
    this.len = input.length();
    this.root = new Node(0, 0);
    this.activeEdge = 0;
    this.activeLength = 0;
    this.remainder = 0;
    this.activeNode = root;
    this.currentPos = 0;
    this.lastNodeCreated = null;
    this.firstNodeCreated = false;
    // looping through the input and adding the suffixes one by one
    for (currentPos = 0; currentPos < len; currentPos++)
      addSuffix();
  }

  public void printTree () {
    printTree(root, 0);
  }

  private void printTree (Node curr, int depth) {
    System.out.printf("%d [rank=%d]\n", curr.index, depth);
    for (int i = 0; i < 256; i++) {
      if (curr.child[i] != null) {
        System.out.printf("%d--%d[label=\"%s\"]\n", curr.index, curr.child[i].index, input.substring(curr.child[i].start, curr.child[i].end == END ? input.length() : curr.child[i].end));
        printTree(curr.child[i], depth + 1);
      }
    }
  }

  private void addSuffix () {
    // how many previous suffixes do we need to compute
    remainder++;
    firstNodeCreated = true;
    while (remainder > 0) {
      // if the active length is zero, then we reset the active edge
      if (activeLength == 0)
        activeEdge = currentPos;

      // creating a new leaf node
      if (activeNode.child[input.charAt(activeEdge)] == null) {
        activeNode.child[input.charAt(activeEdge)] = new Node(currentPos, END);
        // if a previous node has already been created during this
        // iteration, then we create a suffix link
        addSuffixLink(activeNode);
      } else {
        // if the current length required is greater than the edge
        // length, then we advance to the next edge down the tree
        int nextLen = activeNode.child[input.charAt(activeEdge)].getLength();
        if (activeLength >= nextLen) {
          activeNode = activeNode.child[input.charAt(activeEdge)];
          activeEdge += nextLen;
          activeLength -= nextLen;
          continue;
        }
        // the current position of the suffix overlaps with a suffix
        // already inserted
        if (input.charAt(activeNode.child[input.charAt(activeEdge)].start + activeLength) == input.charAt(currentPos)) {
          activeLength++;
          // if a previous node has already been created during this
          // iteration, then we create a suffix link
          addSuffixLink(activeNode);
          break;
        } else {
          // since we found that the current position of the suffix is
          // entirely new, we have to split the edge into two

          Node old = activeNode.child[input.charAt(activeEdge)];

          // creating the split node
          Node split = new Node(old.start, old.start + activeLength);
          activeNode.child[input.charAt(activeEdge)] = split;

          // creating the new leaf node
          Node leaf = new Node(currentPos, END);

          // adding the leaf node to split
          split.child[input.charAt(currentPos)] = leaf;

          // shifting the old node's start by active length
          old.start += activeLength;

          // adding the old node to split
          split.child[input.charAt(old.start)] = old;
          addSuffixLink(split);
        }
      }
      remainder--;
      // if we added a node at the root, we just decrease the active
      // length by one and adjust the active edge so that it is the first
      // character of the next suffix to be added
      if (activeNode == root && activeLength > 0) {
        activeLength--;
        activeEdge = currentPos - remainder + 1;
      }
      // if it is an internal node, then go to suffix link. If there is no
      // suffix link, then go to root
      else {
        if (activeNode.suffix != null) {
          activeNode = activeNode.suffix;
        } else {
          activeNode = root;
        }
      }
    }
  }

  private void addSuffixLink (Node curr) {
    if (firstNodeCreated == false)
      lastNodeCreated.suffix = curr;
    firstNodeCreated = false;
    lastNodeCreated = curr;
  }

  private class Node {
    // represents the string [start, end)
    int start, end;
    Node[] child;
    Node suffix;
    int index;

    Node (int start, int end) {
      child = new Node[256];
      suffix = null;
      this.start = start;
      this.end = end;
      index = (int)(Math.random() * 1000);
    }

    private int getLength () {
      return Math.min(currentPos + 1, end) - start;
    }
  }

  public static void main (String[] args) {
    SuffixTree st = new SuffixTree("abcabxabcd$");
    st.printTree();
  }
}
