package contest.woburn;

/*Problem 5: Plentiful Paths

 Given is an M by N grid and on each square of the grid there may or may not be an apple on it. Let A be the bottom left square and B be the upper right square of the grid. Find the path from A to B (shown below) going up and right only that passes through the most number of squares with apples in them. For this path output the number of apples on it.

 For example, here is a 4 by 4 grid:

 .a.a <-B
 ..aa
 a.a.
 A-> ....
 Each square can have at most one apple (this includes squares A and B).

 Input

 Your program should read in the size of the grid, M N, where 1 ≤ M, N ≤ 100. The locations of the apples where A is at (1, 1) and B is at (M, N). Inputs will end with 0 0 and have the same format as the one below. In our example, the input would be

 4 4
 2 1
 2 3
 3 3
 3 4
 4 2
 4 4
 0 0
 Output

 Give the number of apples on the path with the most number of them. In this case

 5*/
import java.util.Scanner;

public class Woburn_Challenge_1996_Plentiful_Paths {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int m = scan.nextInt();
		int n = scan.nextInt();
		int[][] table = new int[m][n];
		int a;
		while ((a = scan.nextInt()) != 0)
			table[m - a][scan.nextInt() - 1] = 1;
		scan.next();
		int solution[][] = new int[m][n];
		for (int x = 0; x < m; x++) {
			for (int y = n - 1; y >= 0; y--) {
				a = x - 1 < 0 ? 0 : solution[x - 1][y];
				int b = y + 1 > n - 1 ? 0 : solution[x][y + 1];
				solution[x][y] = table[x][y] + (a > b ? a : b);
			}
		}
		System.out.println(solution[m - 1][0]);
	}
}
