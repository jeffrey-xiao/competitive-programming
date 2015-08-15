package ioi;

import java.util.Scanner;

public class IOI_2014_Station {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int numOfStations = scan.nextInt();
		int maxDistance = scan.nextInt();
		int stations[] = new int[numOfStations + 1];
		int[] days = new int[numOfStations + 1];
		for (int x = 1; x < stations.length; x++) {
			stations[x] = scan.nextInt();
			if (x > 1)
				days[x] = -1;
		}

		main : for (int x = 1; x < days.length; x++) {
			for (int y = Math.min(days.length - 1, x + maxDistance); y > x; y--) {
				if (days[x] != -1 && stations[y] == 1) {
					if (days[y] == -1) {
						days[y] = days[x] + 1;
						x = y - 1;
						continue main;
					} else {
						days[y] = Math.min(days[y], days[x] + 1);
						x = y - 1;
						continue main;
					}
				}
			}
		}
		/*
		 * for(int x :days) System.out.print(x+" "); System.out.println("\n");
		 */
		System.out.println(days[days.length - 1]);
	}
}
