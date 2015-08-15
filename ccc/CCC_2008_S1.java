package ccc;

import java.util.Scanner;

public class CCC_2008_S1 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		String s = "";
		int min = 201;
		String city = "";
		do {
			city = scan.next();
			int temp = scan.nextInt();
			if (temp < min) {
				s = city;
				min = temp;
			}

		} while (!city.equals("Waterloo"));
		System.out.println(s);
	}

}
