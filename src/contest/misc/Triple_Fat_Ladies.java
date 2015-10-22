package contest.misc;

import java.util.Scanner;

public class Triple_Fat_Ladies {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int n = scan.nextInt(); n > 0; n--) {
			int x = scan.nextInt();
			int a = 0;
			for (a = 19; 10 * a + 2 <= x; a += 25)
				;
			System.out.println(10 * a + 2);
		}
	}
}
