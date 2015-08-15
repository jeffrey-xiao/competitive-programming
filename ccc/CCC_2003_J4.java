package ccc;

import java.util.Scanner;

public class CCC_2003_J4 {
	public static Scanner scan = new Scanner(System.in);
	public static String[] rhymes;

	public static void main (String[] args) {
		int numOfVerses = scan.nextInt();
		scan.nextLine();
		rhymes = new String[numOfVerses];
		for (int y = 0; y < numOfVerses; y++) {
			String[] a = scan.nextLine().split(" ");
			String[] b = scan.nextLine().split(" ");
			String[] c = scan.nextLine().split(" ");
			String[] d = scan.nextLine().split(" ");
			String last1 = lastSyllable(a[a.length - 1]);
			String last2 = lastSyllable(b[b.length - 1]);
			String last3 = lastSyllable(c[c.length - 1]);
			String last4 = lastSyllable(d[d.length - 1]);
			if (last1.equals(last2) && last3.equals(last4)
					&& last1.equals(last3)) {
				rhymes[y] = "perfect";
			} else if (last1.equals(last2) && last3.equals(last4)) {
				rhymes[y] = "even";
			} else if (last1.equals(last3) && last2.equals(last4)) {
				rhymes[y] = "cross";
			} else if (last1.equals(last4) && last3.equals(last2)) {
				rhymes[y] = "shell";
			} else {
				rhymes[y] = "free";
			}
		}
		for (int x = 0; x < rhymes.length; x++) {
			System.out.println(rhymes[x]);
		}
	}

	public static String lastSyllable (String a) {
		int[] lastIndices = {a.lastIndexOf("a"), a.lastIndexOf("e"),
				a.lastIndexOf("i"), a.lastIndexOf("o"), a.lastIndexOf("u")};
		int lowest = 100;
		for (int x = 0; x < lastIndices.length; x++) {
			if (lastIndices[x] < lowest && lastIndices[x] != -1) {
				lowest = lastIndices[x];
			}
		}
		return a.substring(lowest >= 0 ? lowest : 0);
	}
}
