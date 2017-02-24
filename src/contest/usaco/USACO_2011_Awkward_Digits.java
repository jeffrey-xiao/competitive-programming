package contest.usaco;

import java.util.Scanner;

public class USACO_2011_Awkward_Digits {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		StringBuilder n1 = new StringBuilder(scan.nextLine());
		StringBuilder n2 = new StringBuilder(scan.nextLine());
		for (int x = 0; x < n1.length(); x++) {
			for (int y = 0; y < n2.length(); y++) {
				for (int z = 1; z <= 2; z++) {
					int a1 = (n1.charAt(x) + 1 - '0') % 2 + '0';
					n1.setCharAt(x, (char)a1);
					int a2 = (n2.charAt(y) + z - '0') % 3 + '0';
					n2.setCharAt(y, (char)a2);
					if (Integer.valueOf(n1.toString(), 2).equals(Integer.valueOf(n2.toString(), 3))) {
						System.out.println(Integer.valueOf(n1.toString(), 2));
						System.exit(0);
					}
					a1 = (n1.charAt(x) + 1 - '0') % 2 + '0';
					n1.setCharAt(x, (char)a1);
					a2 = (n2.charAt(y) + 3 - z - '0') % 3 + '0';
					n2.setCharAt(y, (char)a2);

				}
			}
		}
	}
}
