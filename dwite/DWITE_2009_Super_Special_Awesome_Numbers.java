package dwite;

import java.util.Scanner;

public class DWITE_2009_Super_Special_Awesome_Numbers {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		for (int y = 0; y < 5; y++) {

			int start = scan.nextInt();
			int end = scan.nextInt();
			int count = 0;
			for (int x = start; x <= end; x++) {
				if (isSuperAndSpecial(x) && isAwesome(x)) {
					count++;
				}
			}
			System.out.println(count);
		}
		scan.close();
	}

	public static boolean isSuperAndSpecial (int n) {
		int sum = 0;
		int curr = -1;
		while (n != 0) {
			sum += n % 2;
			if (curr == -1)
				curr = n % 10;
			else if (curr <= n % 10)
				return false;
			else
				curr = n % 10;
			n /= 10;
		}
		return sum % 2 == 0;
	}

	public static boolean isAwesome (int n) {
		for (int x = 2; x * x <= n; x++) {
			if (n % (x * x) == 0) {
				return false;
			}
		}
		return true;
	}
}
