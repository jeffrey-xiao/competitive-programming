/*
 * Link/cut trees divide each tree in the represented forest into vertex-disjoint paths, where each path is represented by an auxiliary tree.
 * Link/cut trees facilitate cuts, joins, accesses, and path-based operations in logarithmic time.
 *
 * Time complexity:
 *  - Cut: O(log N)
 *  - Join: O(log N)
 *  - Access: O(log N)
 *  - Path-based operation: O(log N)
 */

package codebook.datastructures;

public class LinkCutTree {
  private Node[] nodes;

  LinkCutTree (int n) {
    nodes = new Node[n];
    for (int i = 0; i < n; i++)
      nodes[i] = new Node();
  }

  private class Node {
    Node path_parent;
    // tree pointers
    Node parent, left, right;
  }

  // precondition: n must be a root node, and n and m must be in different trees
  public void link (Node n, Node m) {
    access(n);
    access(m);

    n.left = m;
    m.parent = n;
    m.path_parent = null;
  }

  // precondition: n is not a root node
  public void cut (Node n) {
    access(n);
    if (n.left != null) {
      n.left.parent = null;
      n.left.path_parent = null;
      n.left = null;
    }
  }

  public Node getRoot (Node n) {
    access(n);
    while (n.left != null)
      n = n.left;
    splay(n);
    return n;
  }

  public void access (Node n) {
    splay(n);
    if (n.right != null) {
      n.right.path_parent = n;
      n.right.parent = null;
      n.right = null;
    }
    Node m = n;
    while (m.path_parent != null) {
      Node nextTree = m.path_parent;
      splay(nextTree);
      if (nextTree.right != null) {
        nextTree.right.path_parent = nextTree;
        nextTree.right.parent = null;
      }
      nextTree.right = m;
      m.parent = nextTree;
      m.path_parent = null;

      m = nextTree;
    }
    splay(n);
  }

  private void splay (Node n) {
    while (n.parent != null) {
      Node p = n.parent;
      Node pp = n.parent.parent;
      if (pp == null) {
        rotate(n);
      } else if ((pp.left == p && p.left == n) || (pp.right == p && p.right == n)) {
        rotate(p);
        rotate(n);
      } else {
        rotate(n);
        rotate(n);
      }
    }
  }

  private void rotate (Node n) {
    Node p = n.parent;
    Node pp = n.parent.parent;
    if (p.left == n) {
      p.left = n.right;
      n.right = p;
      p.parent = n;
      if (p.left != null)
        p.left.parent = p;
    } else if (p.right == n) {
      p.right = n.left;
      n.left = p;
      p.parent = n;
      if (p.right != null)
        p.right.parent = p;
    }
    n.parent = pp;
    if (pp != null) {
      if (pp.left == p)
        pp.left = n;
      else
        pp.right = n;
    }
    n.path_parent = p.path_parent;
    p.path_parent = null;
  }

  public Node lca (Node n, Node m) {
    if (getRoot(n) != getRoot(m))
      return null;
    access(m);
    splay(n);
    if (n.right != null) {
      n.right.path_parent = n;
      n.right.parent = null;
      n.right = null;
    }
    Node res = n;
    Node k = n;
    while (k.path_parent != null) {
      Node nextTree = k.path_parent;
      splay(nextTree);
      if (nextTree.path_parent == null)
        res = nextTree;
      if (nextTree.right != null) {
        nextTree.right.path_parent = nextTree;
        nextTree.right.parent = null;
      }
      nextTree.right = k;
      k.parent = nextTree;
      k.path_parent = null;

      k = nextTree;
    }
    splay(n);
    return res;
  }
}
