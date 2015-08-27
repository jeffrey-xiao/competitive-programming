package contest.ccc;

import java.util.*;

class CCC_2010_J5 {
	public static Scanner scan = new Scanner(System.in);
	public static int minMoves = 100;
	public static int endX;
	public static int endY;
	public static int[][] board = { {100, 100, 100, 100, 100, 100, 100, 100}, {100, 100, 100, 100, 100, 100, 100, 100}, {100, 100, 100, 100, 100, 100, 100, 100}, {100, 100, 100, 100, 100, 100, 100, 100}, {100, 100, 100, 100, 100, 100, 100, 100}, {100, 100, 100, 100, 100, 100, 100, 100}, {100, 100, 100, 100, 100, 100, 100, 100}, {100, 100, 100, 100, 100, 100, 100, 100}};

	public static void main (String[] args) {
		String point1 = scan.nextLine();
		String point2 = scan.nextLine();
		String[] arrayPoint1 = point1.split(" ");
		String[] arrayPoint2 = point2.split(" ");
		int startX = Integer.parseInt(arrayPoint1[0]);
		int startY = Integer.parseInt(arrayPoint1[1]);
		endX = Integer.parseInt(arrayPoint2[0]);
		endY = Integer.parseInt(arrayPoint2[1]);
		checkMove(startX, startY, 0);
		System.out.println(minMoves);
	}

	public static void checkMove (int startX, int startY, int currentMoves) {

		if (startX == endX && startY == endY && minMoves > currentMoves) {
			minMoves = currentMoves;
			return;
		}
		if (startX >= 1 && startX <= 8 && startY >= 1 && startY <= 8) {
			if (board[startX - 1][startY - 1] > currentMoves) {
				board[startX - 1][startY - 1] = currentMoves;
				checkMove(startX + 2, startY + 1, currentMoves + 1);
				checkMove(startX + 2, startY - 1, currentMoves + 1);
				checkMove(startX - 2, startY + 1, currentMoves + 1);
				checkMove(startX - 2, startY - 1, currentMoves + 1);
				checkMove(startX + 1, startY + 2, currentMoves + 1);
				checkMove(startX - 1, startY + 2, currentMoves + 1);
				checkMove(startX + 1, startY - 2, currentMoves + 1);
				checkMove(startX - 1, startY - 2, currentMoves + 1);
			}
		}
	}
}