package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_Smallest_Latin_Square2 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int x = scan.nextInt(); x > 0; x--) {
			int n = scan.nextInt();
			switch (n) {
				case 2:
					System.out.println("12 21");
					break;
				case 3:
					System.out.println("123 231 312");
					break;
				case 4:
					System.out.println("1234 2143 3421 4312");
					break;
				case 5:
					System.out.println("12345 21453 34512 45231 53124");
					break;
				case 6:
					System.out.println("123456 214365 345612 436521 562134 651243");
					break;
				case 7:
					System.out
							.println("1234567 2143675 3456712 4367251 5672123 6715234 7521346");
					break;
				case 8:
					System.out
							.println("12345678 21436587 34217856 43128765 56781234 65872143 78563412 87654321");
					break;
				case 9:
					System.out
							.println("123456789 214365897 341278956 432189675 567891234 658917342 789623123 895734218 97654238");
					break;
			}
		}
	}

}
