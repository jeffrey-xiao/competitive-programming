package contest.woburn;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Woburn_Challenge_1998_Y2K {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int numOfPoints = scan.nextInt();
    Point[] points = new Point[numOfPoints];
    int min = 16001;
    int index = 0;
    for (int x = 0; x < points.length; x++) {
      points[x] = new Point(scan.nextInt(), scan.nextInt());
      if (points[x].getY() < min) {
        min = (int) points[x].getY();
        index = x;
      }
    }
    Point temp = points[index];
    points[index] = points[0];
    points[0] = temp;
    final Point startPoint = points[0];
    Arrays.sort(points, 1, points.length, new Comparator<Point>() {
      @Override
      public int compare(Point p1, Point p2) {
        return p1.getX() * p2.getY() + p2.getX() * startPoint.getY() + startPoint.getX() * p1.getY() - p2.getY() * startPoint.getX() - startPoint.getY() * p1.getX() - p1.getY() * p2.getX() > 0 ? -1 : 1;
      }

    });
    ArrayList<Point> p = new ArrayList<Point>(Arrays.asList(points));
    for (int x = p.size() - 1; x >= 1; x--) {
      if ((p.get(x).getY() - startPoint.getY()) / (p.get(x).getX() - startPoint.getX()) == (p.get(x - 1).getY() - startPoint.getY()) / (p.get(x - 1).getX() - startPoint.getX())) {
        if (Math.abs(p.get(x).getX() - startPoint.getX()) > Math.abs(p.get(x - 1).getX() - startPoint.getX()))
          p.remove(x - 1);
        else
          p.remove(x);
      }
    }
    ArrayList<Point> convexHull = new ArrayList<Point>();
    convexHull.add(p.get(0));
    convexHull.add(p.get(1));
    for (int x = 2; x < p.size(); x++) {
      int y = convexHull.size() - 1;
      while (ccw(convexHull.get(y - 1), convexHull.get(y), p.get(x)) <= 0) {
        convexHull.remove(y);
        y--;
      }
      convexHull.add(p.get(x));
    }

    for (int x = convexHull.size() - 1; x >= 0; x--) {
      System.out.println((int) convexHull.get(x).getX() + " " + (int) convexHull.get(x).getY());
    }
  }

  @SuppressWarnings("unused")
  private static double crossProduct(Point p1, Point p2) {
    return p1.getX() * p2.getY() - p1.getY() * p2.getX();
  }

  private static double ccw(Point p1, Point p2, Point p3) {
    return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
  }

  @SuppressWarnings("unused")
  private static double polar(Point p1, Point p2, Point startPoint) {
    return p1.getX() * p2.getY() + p2.getX() * startPoint.getY() + startPoint.getX() * p1.getY() - p2.getY() * startPoint.getX() - startPoint.getY() * p1.getX() - p1.getY() * p2.getX() > 0 ? -1 : 1;
  }
}
