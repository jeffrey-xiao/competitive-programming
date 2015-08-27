package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cyclopian_Puzzle {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main (String[] args) throws NumberFormatException, IOException {
		solve(Integer.parseInt(br.readLine()), 'A', 'B', 'C');
	}

	public static void solve (int n, char origin, char temp, char dest) {
		if (n == 0)
			return;

		solve(n - 1, origin, dest, temp);
		System.out.println(origin + "" + dest);
		solve(n - 1, temp, origin, dest);
	}
}
