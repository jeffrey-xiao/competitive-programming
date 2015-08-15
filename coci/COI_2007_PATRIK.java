package coci;

import java.util.Scanner;

public class COI_2007_PATRIK {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int numOfPeople = scan.nextInt();
		int[] people = new int[numOfPeople];
		int pairs = 0;
		main : for (int x = 0; x < people.length; x++) {
			int max = 0;
			people[x] = scan.nextInt();
			for (int y = x - 1; y >= 0; y--) {
				if (max > people[x])
					continue main;
				if (max <= people[y]) {
					max = people[y];
					pairs++;
				}
			}
		}

		System.out.println(pairs);
	}

}
