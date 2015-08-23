package contest.other;

import java.math.BigInteger;
import java.util.Scanner;

public class Good_Predictions {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		int n1 = scan.nextInt();
		int n2 = scan.nextInt();
		int n3 = scan.nextInt();
		int n4 = scan.nextInt();

		BigInteger a = BigInteger.valueOf(1);
		for (int i = 2; i <= n1; i++)
			a = a.multiply(BigInteger.valueOf(i));

		for (int i = 2; i <= n2; i++)
			a = a.divide(BigInteger.valueOf(i));
		for (int i = 2; i <= n3; i++)
			a = a.divide(BigInteger.valueOf(i));
		for (int i = 2; i <= n4; i++)
			a = a.divide(BigInteger.valueOf(i));
		System.out.println(a.mod(new BigInteger("1000000007")));
		scan.close();
	}
}
