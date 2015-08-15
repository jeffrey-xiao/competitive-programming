package ccc;

import java.util.Scanner;

public class CCC_2003_S4 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int length = scan.nextInt();
		scan.nextLine();
		for (; length > 0; length--) {
			String s = scan.nextLine();
			int count = 0;
			for (int x = 0; x < s.length(); x++) {
				for (int y = x + 1; y <= s.length(); y++) {
					String ss = s.substring(x, y);
					// System.out.println(ss);
					if (s.indexOf(ss) == x)
						count++;
				}
			}
			System.out.println(count + 1);
		}
	}
}
