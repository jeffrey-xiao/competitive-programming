/*
 * The Palindrome Tree counts the number of palindromes within a certain string.
 * 
 * Time complexity:
 *  - Add character: O(N)
 *  - Count palindromes: O(1)
 *  - Delete character: O(1)
 */

package codebook.string;

import java.util.ArrayList;

public class PalindromeTreeSimple {

  static final int SHIFT = 'a';

  private ArrayList<Node> tree;
  private ArrayList<Character> seq;
  private Node root, zero;
  private int ans = 0;

  // initializing the tree and the roots
  PalindromeTreeSimple () {
    tree = new ArrayList<Node>();
    seq = new ArrayList<Character>();
    // the root has a length of negative one for convenience
    root = new Node(0, -1);
    // the "zero root" will compute the palindromes of even length
    zero = new Node(0, 0);
    root.suffixLink = root;
    zero.suffixLink = root;
    tree.add(root);
  }

  class Node {
    private int num, len;
    private Node suffixLink;
    private Node[] nextLink;

    Node () {
      this(0, 0);
    }

    Node (int num, int len) {
      this.num = num;
      this.len = len;
      this.suffixLink = null;
      this.nextLink = new Node[26];
    }
  }

  public void addCharacter (char c) {
    seq.add(c);
    Node curr = tree.get(tree.size() - 1);
    // find the suffix to extend so that when we add character c it will be a palindrome
    while (seq.size() - curr.len - 2 < 0 || seq.get(seq.size() - curr.len - 2) != c)
      curr = curr.suffixLink;
    // after we found the suffix, we check if the palindrome node has already been created
    Node next = curr.nextLink[c - SHIFT];
    if (next == null) {
      // if the new palindrome node has not been created, then create one
      next = (curr.nextLink[c - SHIFT] = new Node());
      // initialize the length
      next.len = curr.len + 2;
      // if the length is one, then we set the suffix to the zero root to compute even length palindromes in the future
      // otherwise, we find find suffix link of the new palindrome node
      if (next.len == 1) {
        next.suffixLink = zero;
        next.num = 1;
      } else {
        curr = curr.suffixLink;
        while (seq.size() - curr.len - 2 < 0 || seq.get(seq.size() - curr.len - 2) != c)
          curr = curr.suffixLink;
        curr = curr.nextLink[c - SHIFT];
        next.suffixLink = curr;
        next.num = curr.num + 1;
      }
    }
    // add the number of palindromes contained in our new suffix palindrome
    ans += next.num;
    // add the suffix into our tree for removal later
    tree.add(next);
  }

  public void deleteCharacter () {
    // subtract the answer from the last palindrome node
    ans -= tree.get(tree.size() - 1).num;
    // remove the palindrome node and the last character added
    tree.remove(tree.size() - 1);
    seq.remove(tree.size() - 1);
  }

  public int getPalindromeCount () {
    return ans;
  }

  public static void main (String[] args) {
    PalindromeTreeSimple m = new PalindromeTreeSimple();
    m.addCharacter('a');
    assert m.getPalindromeCount() == 1;
    m.addCharacter('a');
    assert m.getPalindromeCount() == 3;
    m.deleteCharacter();
    m.deleteCharacter();
    m.addCharacter('a');
    assert m.getPalindromeCount() == 1;
  }
}
