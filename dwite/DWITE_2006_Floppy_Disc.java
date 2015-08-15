package dwite;

import java.util.Scanner;

public class DWITE_2006_Floppy_Disc {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int t = 0; t < 5; t++) {
			boolean[] disc = new boolean[1441];
			disc[0] = true;
			int numOfFiles = scan.nextInt();
			int[] files = new int[numOfFiles + 1];
			for (int x = 1; x <= numOfFiles; x++)
				files[x] = scan.nextInt();
			int max = 0;
			for (int x = 1; x <= numOfFiles; x++) {
				for (int y = disc.length - 1; y > 0 && files[x] <= y; y--) {
					if (!disc[y]) {
						disc[y] = disc[y - files[x]];
					}
					if (disc[y] && y > max)
						max = y;
				}
			}
			System.out.println(1440 - max);
		}
	}
}
