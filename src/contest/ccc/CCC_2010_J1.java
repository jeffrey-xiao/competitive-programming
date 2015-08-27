package contest.ccc;

import java.util.Scanner;

public class CCC_2010_J1 {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println(3 - Math.abs(5 - scan.nextInt()) / 2);
		scan.close();
	}
}