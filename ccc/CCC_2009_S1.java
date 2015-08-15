package ccc;

import java.util.Scanner;

public class CCC_2009_S1 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int a = scan.nextInt();
		int b = scan.nextInt();
		int sixth = (int) Math.pow(b + 1, 1.0 / 6.0)
				- (int) Math.pow(a - 1, 1.0 / 6.0);
		System.out.println(sixth);
	}

}
