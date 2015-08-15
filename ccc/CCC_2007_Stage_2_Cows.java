package ccc;

//UNFINISHED

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CCC_2007_Stage_2_Cows {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int numOfPoints = scan.nextInt();
		Point[] points = new Point[numOfPoints + 1];
		int min = 16001;
		int index = 0;
		for (int x = 0; x < points.length; x++) {
			points[x] = new Point(scan.nextInt(), scan.nextInt());
			if (points[x].getY() < min) {
				min = (int) points[x].getY();
				index = x;
			}
		}
		// swapping to get lowest y coord first
		Point temp = points[index];
		points[index] = points[0];
		points[0] = temp;
		final Point startPoint = points[0];
		System.out.println("HERE");
		Arrays.sort(points, 1, points.length - 1, new Comparator<Point>() {
			@Override
			public int compare (Point p1, Point p2) {
				return p1.getX() * p2.getY() + p2.getX() * startPoint.getY()
						+ startPoint.getX() * p1.getY() - p2.getY()
						* startPoint.getX() - startPoint.getY() * p1.getX()
						- p1.getY() * p2.getX() > 0 ? -1 : 1;
			}

		});
		/*
		 * for(Point p: points){ System.out.println("x:"+p.getX() +
		 * " y:"+p.getY()); }
		 */
		points[points.length - 1] = startPoint;
		int m = 1;
		for (int x = 2; x < points.length; x++) {
			System.out.println(x);
			while (ccw(points[m - 1], points[x], points[x]) <= 0) {
				if (m > 1)
					m--;
				else if (x == points.length - 1)
					break;
				else
					x++;
			}

			m++;
			temp = points[m];
			points[m] = points[x];
			points[x] = temp;
		}
		System.out.println((int) points[0].getX() + " "
				+ (int) points[0].getY());
		for (int x = 1; x < points.length
				&& (points[x].getX() != points[0].getX() && points[x].getY() != points[0]
						.getY()); x++) {
			System.out.println((int) points[x].getX() + " "
					+ (int) points[x].getY());
		}
	}

	@SuppressWarnings ("unused")
	private static double crossProduct (Point p1, Point p2) {
		return p1.getX() * p2.getY() - p1.getY() * p2.getX();
	}

	private static double ccw (Point p1, Point p2, Point p3) {
		return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY())
				- (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
	}
}
/*
 * if(p1.getY() == 0 && p1.getX()>0) return -1; if(p2.getY() == 0 &&
 * p2.getX()>0) return 1; if(p1.getY() > 0 && p2.getY()<0) return -1;
 * if(p1.getY() < 0 && p2.getY()>0) return 1; return
 * (crossProduct(p1,p2))>0?-1:1;
 */
