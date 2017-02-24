package contest.dwite;

import java.util.Scanner;

public class DWITE_2007_Cubes_In_A_Pyramid {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] arg) {
		double l = scan.nextDouble();
		double h = scan.nextDouble();
		System.out.println((int)Math.ceil(l * l * h * (1.0 / 3.0)));
	}
}
