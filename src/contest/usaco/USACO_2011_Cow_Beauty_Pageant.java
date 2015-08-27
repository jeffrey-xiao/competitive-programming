package contest.usaco;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class USACO_2011_Cow_Beauty_Pageant {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		char[][] cow = new char[scan.nextInt()][scan.nextInt()];
		scan.nextLine();
		for (int x = cow.length - 1; x >= 0; x--)
			cow[x] = scan.nextLine().toCharArray();
		ArrayList<Point> spot1 = new ArrayList<Point>();
		ArrayList<Point> spot2 = new ArrayList<Point>();
		for (int x = 0, counter = 1; x < cow.length; x++)
			for (int y = 0; y < cow[0].length; y++) {
				if (cow[x][y] == 'X') {
					fillSpots(cow, x, y, counter);
					counter++;
				}
				if (cow[x][y] == '1')
					spot1.add(new Point(x, y));
				else if (cow[x][y] == '2')
					spot2.add(new Point(x, y));
			}
		int minDistance = 101;
		for (Point p1 : spot1)
			for (Point p2 : spot2)
				if (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()) < minDistance)
					minDistance = (int) (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
		System.out.println(minDistance - 1);

	}

	private static void fillSpots (char[][] cow, int x, int y, int counter) {
		if (x < 0 || x >= cow.length || y < 0 || y >= cow[0].length || cow[x][y] != 'X')
			return;
		cow[x][y] = (char) (counter + 48);
		fillSpots(cow, x + 1, y, counter);
		fillSpots(cow, x - 1, y, counter);
		fillSpots(cow, x, y + 1, counter);
		fillSpots(cow, x, y - 1, counter);

	}
}
