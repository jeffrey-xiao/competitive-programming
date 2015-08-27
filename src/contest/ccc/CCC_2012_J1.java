package contest.ccc;

import java.util.Scanner;

class CCC_2012_J1 {
	public static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int speedLimit = scan.nextInt();
		int recordedSpeed = scan.nextInt();
		int fine = 0;
		if (speedLimit - recordedSpeed >= 0) {
			System.out.println("Congratulations, you are within the speed limit!");
		} else if (Math.abs(speedLimit - recordedSpeed) > 30) {
			fine = 500;
		} else if (Math.abs(speedLimit - recordedSpeed) > 20) {
			fine = 270;
		} else {
			fine = 100;
		}
		if (fine > 0)
			System.out.format("You are speeding and your fine is $%d.", fine);
	}
}
