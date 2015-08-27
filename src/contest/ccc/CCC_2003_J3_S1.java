package contest.ccc;

import java.util.Scanner;

public class CCC_2003_J3_S1 {
	public static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int square = 1;
		int dice = scan.nextInt();
		while (dice != 0) {
			if (dice + square <= 100) {
				square += dice;
				square = checkMove(square);
			}
			System.out.println("You are now on square " + square);
			if (square == 100)
				break;
			dice = scan.nextInt();
		}
		System.out.println(square == 100 ? "You Win!" : "You Quit!");
	}

	public static int checkMove (int square) {
		switch (square) {
			case 9:
				square = 34;
				break;
			case 54:
				square = 19;
				break;
			case 40:
				square = 64;
				break;
			case 90:
				square = 48;
				break;
			case 67:
				square = 86;
				break;
			case 99:
				square = 77;
				break;
		}
		return square;
	}
}
