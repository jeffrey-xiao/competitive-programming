import java.util.*;
class Nim {

	static int n, sum;
	static int[] a;
	// last one wins
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++)
			sum += a[i] = scan.nextInt();
		boolean computerTurn = getSum() != 0;
		while (sum > 0) {
			System.out.print("Current piles: ");
			for (int i = 0; i < n; i++)
				System.out.print(a[i] + " ");
			System.out.println();
			if (!computerTurn) {
				System.out.print("Enter the pile to take from: ");
				int pile = scan.nextInt();
				System.out.print("Enter the number of coins to take: ");
				int coins = scan.nextInt();
				a[pile] -= coins;
				sum -= coins;
			} else {
				int coins = getSum();
				int pile = -1;
				int taken = 0;
				for (int i = 0; i < n; i++) {
					taken = 0;
					for (int j = 0; j < 32; j++) {
						if ((coins & 1 << j) > 0) {
							if ((a[i] & 1 << j) > 0) {
								taken += 1 << j;
							} else {
								taken -= 1 << j;
							}
						}
					}
					if (taken <= a[i] && taken >= 0) {
						pile = i;
						break;
					}
				}
				System.out.printf("The computer removes %d coins from pile %d\n", taken, pile);
				a[pile] -= taken;
				sum -= taken;
			}

			computerTurn = !computerTurn;
		}
	}
	static int getSum () {
		int sum = 0;
		for (int i = 0; i < 32; i++) {
			int curr = 0;
			for (int j = 0; j < n; j++) {
				curr += (a[j] & 1 << i) > 0 ? 1 : 0;
			}
			sum += (curr % 2) << i;
		}
		return sum;
	}
}
