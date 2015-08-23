package contest.ccc;

import java.util.Scanner;

public class CCC_2011_S2 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int num = scan.nextInt();
		char[] response = new char[num];
		for (int x = 0; x < num; x++)
			response[x] = scan.next().charAt(0);
		int counter = 0;
		for (int x = 0; x < num; x++)
			if (scan.next().charAt(0) == response[x])
				counter++;
		System.out.println(counter);
	}
}
