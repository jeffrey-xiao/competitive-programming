package contest.ccc;

import java.util.Scanner;

public class CCC_2004_S4 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int x = scan.nextInt();
		int y = scan.nextInt();
		int z = scan.nextInt();
		int x1 = scan.nextInt();
		int y1 = scan.nextInt();
		int z1 = scan.nextInt();
		x = x1 - x;
		y = y1 - y;
		z = z1 - z;
		double min = x * x + y * y + z * z;
		int distance = scan.nextInt();
		int nx = x - distance;
		if (nx * x < 0)
			min = Math.min(min, y * y + z * z);
		else
			min = Math.min(min, nx * nx + y * y + z * z);
		char turn = scan.next().charAt(0);
		while (turn != 'E') {
			// if direction changes to up then z is changed so swap x and z
			if (turn == 'U') {
				x = z;
				z = -nx;
			}
			// if direction changes to down then z is also changed so swap x
			// and z but reverse
			else if (turn == 'D') {
				x = -z;
				z = nx;
			}
			// same thing with r and l
			else if (turn == 'R') {
				x = -y;
				y = nx;
			} else if (turn == 'L') {
				x = y;
				y = -nx;
			}
			distance = scan.nextInt();
			nx = x - distance;
			if (nx * x < 0)
				min = Math.min(min, y * y + z * z);
			else
				min = Math.min(min, nx * nx + y * y + z * z);
			turn = scan.next().charAt(0);
		}
		System.out.printf("%.2f", Math.sqrt(min));
	}
}
