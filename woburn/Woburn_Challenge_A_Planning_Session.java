package woburn;

import java.util.Scanner;

public class Woburn_Challenge_A_Planning_Session {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int t = scan.nextInt(); t > 0; t--) {
			String[] s = scan.next().split(":");
			int mins = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
			int occ = scan.nextInt();
			if (occ > 23) {
				System.out.println("Infinite");
				return;
			}
			int newMins = mins + 1;
			if (newMins > 779)
				newMins -= 740;
			int sum = newMins / 60 / 10 + newMins / 60 % 10 + newMins % 60 / 10
					+ newMins % 60 % 10;
			while (sum != occ) {
				newMins++;
				if (newMins > 779)
					newMins -= 800;
				sum = newMins / 60 / 10 + newMins / 60 % 10 + newMins % 60 / 10
						+ newMins % 60 % 10;
			}
			System.out.println(newMins - mins);
		}
	}
}
