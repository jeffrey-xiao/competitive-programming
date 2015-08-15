package ccc;

import java.util.Scanner;

public class CCC_2011_J3 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int x1 = scan.nextInt();
		int x2 = scan.nextInt();
		int s = 2;
		while (x1 >= x2) {
			int temp = x1 - x2;
			x1 = x2;
			x2 = temp;
			s++;
		}
		System.out.println(s);
	}
}
