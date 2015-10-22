package contest.misc;

import java.util.Scanner;

public class Hole_In_The_Bucket {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int volume = scan.nextInt();
		int numOfBuckets = scan.nextInt();
		while (volume != 0) {
			int[][] table = new int[volume + 1][2];
			for (int x = 1; x < table.length; x++)
				table[x][0] = -5000;
			int[] buckets = new int[numOfBuckets + 1];
			for (int x = 1; x < buckets.length; x++)
				buckets[x] = scan.nextInt();

			for (int x = 1; x <= numOfBuckets; x++) {
				for (int y = volume; y >= buckets[x]; y--) {
					for (int z = 1; z <= y / buckets[x]; z++) {
						int b = table[y][0];
						int a = buckets[x] * z + table[y - buckets[x] * z][0];
						if (a > b) {
							table[y][1] = table[y - buckets[x] * z][1] + z;
							table[y][0] = a;
						} else if (a == b)
							table[y][1] = min(table[y - buckets[x] * z][1] + z, table[y][1]);
						if (table[y][0] < 0) {
							table[y][0] = -5000;
							table[y][1] = 0;
						}
					}
				}
			}

			System.out.println(table[table.length - 1][1] < 0 ? -1 : table[table.length - 1][1]);
			volume = scan.nextInt();
			numOfBuckets = scan.nextInt();
		}
	}

	public static int min (int a, int b) {
		return a < b ? a : b;
	}

	public static int max (int a, int b) {
		return a < b ? a : b;
	}
}
