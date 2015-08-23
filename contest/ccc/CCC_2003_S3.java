package contest.ccc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class CCC_2003_S3 {
	private static Scanner scan = new Scanner(System.in);
	private static char[][] house;
	private static ArrayList<Integer> roomSizes = new ArrayList<Integer>();
	private static int roomsFilled = 0;
	private static int rows;
	private static int cols;

	public static void main (String[] args) {
		int flooring = scan.nextInt();
		rows = scan.nextInt();
		cols = scan.nextInt();
		// System.out.println(flooring + " " + rows + " " + cols);
		scan.nextLine();
		house = new char[rows][];
		for (int x = 0; x < rows; x++) {
			house[x] = scan.nextLine().toCharArray();
		}

		for (int x = 0; x < house.length; x++) {
			for (int y = 0; y < house[x].length; y++) {
				if (house[x][y] == '.') {
					roomSizes.add(getLinkedSpaceArea(x, y, 0));
				}
			}
		}
		Collections.sort(roomSizes);// COLLECTION SORT SORTS ARRAYLIST IN
									// NATURAL ORDER, SMALLEST TO GREATEST

		for (int x = roomSizes.size() - 1; x >= 0; x--) {
			if (flooring - roomSizes.get(x) >= 0) {
				flooring -= roomSizes.get(x);
				roomsFilled++;
			} else
				break;
		}

		System.out.println((roomsFilled == 1 ? "1 room, " : roomsFilled
				+ " rooms, ")
				+ flooring + " square metre(s) left over");
	}

	private static int getLinkedSpaceArea (int row, int col, int count) {
		if (row >= 0 && row < rows && col >= 0 && col < cols
				&& house[row][col] == '.') {
			house[row][col] = 'X';
			count++;
			count = getLinkedSpaceArea(row, col + 1, count);
			count = getLinkedSpaceArea(row, col - 1, count);

			count = getLinkedSpaceArea(row - 1, col - 1, count);
			count = getLinkedSpaceArea(row - 1, col, count);
			count = getLinkedSpaceArea(row - 1, col + 1, count);

			count = getLinkedSpaceArea(row + 1, col - 1, count);
			count = getLinkedSpaceArea(row + 1, col, count);
			count = getLinkedSpaceArea(row + 1, col + 1, count);
		}
		return count;
	}
}