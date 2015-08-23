package contest.coci;

import java.util.Scanner;

public class COCI_2008_SKOCIMIS {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		System.out.println(Math.max(b - a - 1, c - b - 1));
	}
}
