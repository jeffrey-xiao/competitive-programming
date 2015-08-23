package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_Goldfinger {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int input = scan.nextInt();
		while (input != -1) {
			boolean[] primes = new boolean[16000];
			primes[0] = true;
			for (int x = 1; x < primes.length; x += 2)
				if (x + 1 % 2 == 0)
					primes[x] = true;
			for (int x = 1; x * x < primes.length; x++)
				if (primes[x] == false)
					for (int y = (x + 1) * (x + 1) - 1; y < primes.length; y += (x + 1))
						primes[y] = true;
			primes[0] = false;
			for (int x = 1; x <= input / 2; x++) {
				if (!primes[x - 1] && !primes[input - x - 1])
					System.out.println(x + " " + (input - x));
			}
			input = scan.nextInt();
		}
	}

}
