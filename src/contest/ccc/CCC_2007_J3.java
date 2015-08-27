package contest.ccc;

import java.util.*;

public class CCC_2007_J3 {
	public static Scanner scan = new Scanner(System.in);
	public static List<Integer> briefcases = new ArrayList<Integer>(Arrays.asList(100, 500, 1000, 5000, 10000, 25000, 50000, 100000, 500000, 1000000));

	public static void main (String[] args) {
		int openedNumCases = scan.nextInt();
		ArrayList<Integer> openedCases = new ArrayList<Integer>();
		for (int x = 0; x < openedNumCases; x++) {
			openedCases.add(scan.nextInt());
		}
		Collections.sort(openedCases);
		for (int x = openedCases.size() - 1; x >= 0; x--) {
			briefcases.remove(openedCases.get(x) - 1);
		}
		int average = 0;
		for (int x = 0; x < briefcases.size(); x++) {
			average += briefcases.get(x);
		}
		average /= briefcases.size();
		System.out.println(scan.nextInt() > average ? "deal" : "no deal");
	}
}
