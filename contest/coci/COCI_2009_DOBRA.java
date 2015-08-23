package contest.coci;

import java.util.Scanner;

public class COCI_2009_DOBRA {
	static Scanner scan = new Scanner(System.in);
	static String vowels = "AEIOU";
	static String consonants = "BCDFGHJKLMNPQRSTVYXZ";

	public static void main (String[] args) {
		String s = scan.next();
		boolean isL = s.indexOf('L') == -1 ? false : true;
		int sum = getSum(s, isL);
		System.out.println(sum);
	}

	private static int getSum (String s, boolean b) {
		int sum = 0;

		int indexOf_ = s.indexOf('_');
		if (indexOf_ == -1 && !b)
			return 0;

		else if (indexOf_ == -1) {
			// System.out.println(s);
			return 1;
		}
		for (int x = 1; x < indexOf_ - 1; x++) {
			if ((vowels.indexOf(s.charAt(x)) != -1
					&& vowels.indexOf(s.charAt(x - 1)) != -1 && vowels
					.indexOf(s.charAt(x + 1)) != -1)
					|| (consonants.indexOf(s.charAt(x)) != -1
							&& consonants.indexOf(s.charAt(x - 1)) != -1 && consonants
							.indexOf(s.charAt(x + 1)) != -1)) {
				// System.out.println("DELTED " + s);
				return 0;
			}
		}

		if (!(vowels.indexOf(s.charAt(indexOf_ - 1)) != -1 && vowels.indexOf(s
				.charAt(indexOf_ + 1)) != -1)) {
			String s1 = s.substring(0, indexOf_) + "A"
					+ s.substring(indexOf_ + 1, s.length());
			sum += (5 * getSum(s1, b));
		}
		if (!(consonants.indexOf(s.charAt(indexOf_ - 1)) != -1 && consonants
				.indexOf(s.charAt(indexOf_ + 1)) != -1)) {
			String s1 = s.substring(0, indexOf_) + "B"
					+ s.substring(indexOf_ + 1, s.length());
			sum += 20 * getSum(s1, b);
			String s2 = s.substring(0, indexOf_) + "L"
					+ s.substring(indexOf_ + 1, s.length());
			sum += getSum(s2, true);
		}

		return sum;
	}
}
