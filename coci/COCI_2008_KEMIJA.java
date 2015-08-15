package coci;

import java.util.Scanner;

public class COCI_2008_KEMIJA {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder s = new StringBuilder(scan.nextLine());
		String vowels = "aeiou";
		for (int x = 1; x < s.length() - 1; x++) {
			if (s.charAt(x) == 'p' && vowels.indexOf(s.charAt(x - 1)) != -1
					&& vowels.indexOf(s.charAt(x + 1)) != -1) {
				s.delete(x, x + 2);
			}
		}
		System.out.println(s);
		scan.close();
	}

}
