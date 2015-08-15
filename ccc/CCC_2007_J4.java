package ccc;

import java.util.Arrays;
import java.util.Scanner;

public class CCC_2007_J4 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		char[] s1 = scan.nextLine().replaceAll("\\s+", "").toCharArray();
		char[] s2 = scan.nextLine().replaceAll("\\s+", "").toCharArray();
		Arrays.sort(s1);
		Arrays.sort(s2);
		for (int x = 0; x < s1.length; x++) {
			if (s1[x] != s2[x]) {
				System.out.println("Is not an anagram.");
				return;
			}
		}
		System.out.println("Is an anagram.");

	}

}
