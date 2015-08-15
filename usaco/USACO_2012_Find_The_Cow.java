package usaco;

import java.util.Scanner;

public class USACO_2012_Find_The_Cow {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		String s = scan.next();
		int sum = 0;
		int first = 0;
		for (int x = 0; x < s.length() - 1; x++) {
			if (s.charAt(x) == '(' && s.charAt(x + 1) == '(')
				first++;
			else if (s.charAt(x) == ')' && s.charAt(x + 1) == ')')
				sum += first;
		}
		System.out.println(sum);
	}
}
