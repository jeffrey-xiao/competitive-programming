/*
 * Aho-Corasick algorithm is a string searching algorithm that searches for elements of a finite set of strings with in an input text.
 *
 * Time complexity:
 *  - Add word: O(N)
 *  - Search text: O(N)
 */

package codebook.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AhoCorasick {

  // constant to represent the shift from the ASCII value to the proper index
  private static final int SHIFT = 'a';

  // Object representing the root of the search tree
  private Node root = new Node(0, 0);

  AhoCorasick() {
    root = new Node(0, 0);
    root.parent = root;
  }

  public static void main(String[] args) {
    AhoCorasick tree = new AhoCorasick();
    tree.addWord("apple");
    tree.addWord("banana");
    tree.computeFall();
    tree.search("askfjasjfklasjfasfjpabananasfjawofjoawjfopjapapple");
  }

  public void addWord(String s) {
    root.addWord(s);
  }

  public void computeFall() {
    Queue<Node> q = new LinkedList<Node>();
    // the fall of the root is the root
    root.fall = root;
    q.offer(root);
    while (!q.isEmpty()) {
      Node curr = q.poll();
      // push the children of the current node into the queue
      for (Integer i : curr.c)
        q.offer(curr.child[i]);
      // if the fall has already been computed then skip the current node
      if (curr.fall != null)
        continue;
      // let the fall first be the parent's fall
      Node fall = curr.parent.fall;
      // while the current fall node does not have a child with the value
      // of the current character, then go fall back
      while (fall.child[curr.index] == null && fall != root)
        fall = fall.fall;
      // the fall node of the current node will be the child of the fall
      // node we found
      curr.fall = fall.child[curr.index];
      // if the current node's fall is null or if it is the current node,
      // then set to root
      if (curr.fall == null || curr.fall == curr)
        curr.fall = root;
    }
  }

  public void search(String s) {
    Node currState = root;
    for (int i = 0; i < s.length(); i++) {
      char curr = s.charAt(i);
      // loop until we have a match or we are at the root
      while (currState.child[curr - SHIFT] == null && currState != root)
        currState = currState.fall;
      // handle the root case
      if (currState == root) {
        if (currState.child[curr - SHIFT] != null)
          currState = currState.child[curr - SHIFT];
      } else {
        currState = currState.child[curr - SHIFT];
      }
      // falling back to check for other shorter matches
      Node other = currState;
      while (other != root) {
        if (other.isEnd) {
          System.out.println(printWord(other) + " at " + i);
        }
        other = other.fall;
      }
    }
  }

  private String printWord(Node n) {
    if (n != root)
      return printWord(n.parent) + (char)(n.index + 'a');
    return "";
  }

  class Node {
    int depth, index;
    Node[] child;
    Node fall;
    Node parent;
    boolean isEnd = false;
    ArrayList<Integer> c;

    Node(int depth, int index) {
      this.depth = depth;
      this.index = index;
      child = new Node[26];
      c = new ArrayList<Integer>();
      for (int i = 0; i < 26; i++)
        child[i] = null;
    }

    private void addWord(String s) {
      // marking it as a leaf node if it is the end of the word
      if (depth == s.length()) {
        isEnd = true;
        return;
      }
      char curr = s.charAt(depth);
      // creating a new node if it does not already exist
      if (child[curr - SHIFT] == null) {
        child[curr - SHIFT] = new Node(depth + 1, curr - SHIFT);
        child[curr - SHIFT].parent = this;
        c.add(curr - SHIFT);
      }
      // recursively add the rest of the word
      child[curr - SHIFT].addWord(s);
    }

    // auxiliary method to print out the words in the trie
    public void printWord(String curr) {
      if (isEnd)
        System.out.println(curr);
      for (Integer i : c) {
        child[i].printWord(curr + (char)(i + SHIFT));
      }
    }
  }
}
