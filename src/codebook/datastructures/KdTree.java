/*
 * A kd tree is a space partitioning data structure for organizing points in a k-dimensional space.
 * This implementation deals with the second dimension.
 *
 * Time complexity:
 *  - Remove: O(log N)
 *  - Insertion: O(log N)
 *  - Search: O(log N)
 *  - Access: O(log N)
 *  - Nearest neighbour: O(log N)
 *  - Range query: O(log N + K)
 */

package codebook.datastructures;

import java.util.ArrayList;
import java.util.Stack;

public class KdTree {
  private int size;
  private Node root;

  public KdTree() {
    root = null;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void insert(Point p) {
    if (p == null)
      return;
    root = insert(root, p, 0, 0.0, 0.0, 1.0, 1.0);
  }

  public boolean contains(Point p) {
    return search(root, p, 0);
  }

  public Point nearest(Point p) {
    if (isEmpty())
      return null;
    return nearest(root, p, null);
  }

  private Point nearest(Node curr, Point p, Point min) {
    if (curr == null || (min != null && min.distanceSquaredTo(p) <= curr.area.distanceSquaredTo(p)))
      return min;
    if (min == null || curr.value.distanceSquaredTo(p) < min.distanceSquaredTo(p))
      min = curr.value;
    if (curr.right != null && curr.right.area.contains(p)) {
      min = nearest(curr.right, p, min);
      min = nearest(curr.left, p, min);
    } else {
      min = nearest(curr.left, p, min);
      min = nearest(curr.right, p, min);
    }
    return min;
  }

  public Iterable<Point> range(Rect rect) {
    ArrayList<Point> ps = new ArrayList<Point>();
    Stack<Node> moves = new Stack<Node>();
    if (root != null)
      moves.push(root);
    while (!moves.isEmpty()) {
      Node curr = moves.pop();
      if (curr == null || !rect.intersects(curr.area))
        continue;
      if (rect.contains(curr.value))
        ps.add(curr.value);
      moves.push(curr.left);
      moves.push(curr.right);
    }
    return ps;
  }

  private Node insert(Node n, Point v, int height, double xmin, double ymin, double xmax, double ymax) {
    if (n == null) {
      size++;
      return new Node(v, height, new Rect(xmin, ymin, xmax, ymax));
    }
    double compare = compare(n.value, v, height);

    if (compare > 0) {
      if (height % 2 == 0)
        n.left = insert(n.left, v, height + 1, xmin, ymin, n.value.getX(), ymax);
      else
        n.left = insert(n.left, v, height + 1, xmin, ymin, xmax, n.value.getY());
    } else if (compare < 0) {
      if (height % 2 == 0)
        n.right = insert(n.right, v, height + 1, n.value.getX(), ymin, xmax, ymax);
      else
        n.right = insert(n.right, v, height + 1, xmin, n.value.getY(), xmax, ymax);
    }
    return n;
  }

  private int compare(Point p1, Point p2, int height) {
    if (height % 2 == 0) {
      int result = new Double(p1.getX()).compareTo(p2.getX());
      if (result == 0)
        return new Double(p1.getY()).compareTo(p2.getY());
      return result;
    }
    int result = new Double(p1.getY()).compareTo(p2.getY());
    if (result == 0)
      return new Double(p1.getX()).compareTo(p2.getX());
    return result;
  }

  private boolean search(Node n, Point v, int height) {
    if (n == null)
      return false;
    double compare = compare(n.value, v, height);
    if (compare > 0)
      return search(n.left, v, height + 1);
    else if (compare < 0)
      return search(n.right, v, height + 1);
    else
      return true;
  }

  private static class Node {
    int height;
    Point value;
    Node left, right;
    Rect area;

    Node(Point value, int height, Rect area) {
      this.value = value;
      this.height = height;
      this.area = area;
    }
  }

  private class Point {
    private double x, y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public double distanceTo(Point p) {
      return Math.sqrt(distanceSquaredTo(p));
    }

    public double distanceSquaredTo(Point p) {
      return (x - p.x) * (x - p.x) + (y - p.x) * (y - p.y);
    }

    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }
  }

  public class Rect {
    private double xmin, ymin, xmax, ymax;

    public Rect(double xmin, double ymin, double xmax, double ymax) {
      if (xmax < xmin || ymax < ymin)
        throw new IllegalArgumentException("Invalid rectangle");
      this.xmin = xmin;
      this.ymin = ymin;
      this.xmax = xmax;
      this.ymax = ymax;
    }

    public double xmin() {
      return xmin;
    }

    public double ymin() {
      return ymin;
    }

    public double xmax() {
      return xmax;
    }

    public double ymax() {
      return ymax;
    }

    public boolean intersects(Rect rect) {
      if (rect == null)
        throw new NullPointerException();
      return this.xmax >= rect.xmin && this.ymax >= rect.ymin && rect.xmax >= this.xmin && rect.ymax >= this.ymin;
    }

    public boolean contains(Point p) {
      if (p == null)
        throw new NullPointerException();
      return (p.getX() >= xmin) && (p.getX() <= xmax) && (p.getY() >= ymin) && (p.getY() <= ymax);
    }

    public double distanceTo(Point p) {
      return Math.sqrt(distanceSquaredTo(p));
    }

    public double distanceSquaredTo(Point p) {
      double dx = 0.0, dy = 0.0;
      if (contains(p))
        return 0.0d;
      if (p.getX() < xmin)
        dx = p.getX() - xmin;
      else if (p.getX() > xmax)
        dx = p.getX() - xmax;
      if (p.getY() < ymin)
        dy = p.getY() - ymin;
      else if (p.getY() > ymax)
        dy = p.getY() - ymax;
      return dx * dx + dy * dy;
    }
  }
}
