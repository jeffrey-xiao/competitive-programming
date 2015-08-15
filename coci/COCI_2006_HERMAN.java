package coci;

import java.util.Scanner;

public class COCI_2006_HERMAN {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		double input = scan.nextInt();
		System.out.printf("%.6f\n", input * input * Math.PI);
		System.out.printf("%.6f", input * input * 2);
	}
}
