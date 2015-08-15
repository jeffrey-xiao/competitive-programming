package woburn;

import java.util.Scanner;

public class Woburn_Challenge_1996_Row_and_Column_Compressions {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int t = 0; t < 5; t++) {
			int r = scan.nextInt();
			int c = scan.nextInt();
			char[][] grid = new char[r][c];
			// fill
			int count = 0;
			String in = scan.next();
			int repeat = 0;
			char curr = ' ';
			String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			for (int y = 0; y < c; y++) {
				for (int x = 0; x < r; x++) {
					if (repeat != 0) {
						repeat--;
						grid[x][y] = curr;
					} else if (alpha.indexOf(in.charAt(count)) != -1) {
						grid[x][y] = in.charAt(count);
						count++;
					} else if (in.charAt(count) == '1'
							&& in.charAt(count + 1) == '0') {
						repeat = 10;
						curr = in.charAt(count + 2);
						grid[x][y] = curr;
						count += 3;
					} else {
						repeat = in.charAt(count) - '1';
						curr = in.charAt(count + 1);
						grid[x][y] = curr;
						count += 2;
					}
				}
			}
			count = 1;
			char prev = ' ';
			String finalS = "";
			for (int x = 0; x < r; x++) {
				for (int y = 0; y < c; y++) {
					if (prev == ' ')
						prev = grid[x][y];
					else if (prev == grid[x][y]) {
						count++;
					} else if (prev != grid[x][y]) {
						if (count != 1)
							finalS += count;
						finalS += prev;
						prev = grid[x][y];
						count = 1;
					}
				}
				if (count != 1)
					finalS += count;
				finalS += prev;
				count = 1;
				prev = ' ';
			}
			System.out.println(finalS);
		}
	}
}
