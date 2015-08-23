package contest.ccc;

import java.util.Scanner;

class CCC_2012_J3 {
	public static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		char[][] board = { {'*', 'x', '*'}, {' ', 'x', 'x'},
				{'*', ' ', '*'}};
		int scaling = scan.nextInt();
		char[][] resizedBoard = new char[3 * scaling][3 * scaling];
		for (int x = 0; x < 3 * scaling; x++) {
			for (int y = 0; y < 3; y++) {
				for (int z = 0; z < scaling; z++) {
					resizedBoard[x][(y * scaling) + z] = board[(int) Math
							.floor(x / scaling)][y];
				}
			}
		}
		for (int i = 0; i < 3 * scaling; i++) {
			for (int j = 0; j < 3 * scaling; j++) {
				System.out.print(resizedBoard[i][j]);
			}
			System.out.println();
		}
	}
}