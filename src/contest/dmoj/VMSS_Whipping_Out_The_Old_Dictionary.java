package contest.dmoj;

import java.util.ArrayList;
import java.util.Scanner;

public class VMSS_Whipping_Out_The_Old_Dictionary {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int t = scan.nextInt();
		scan.nextLine();
		for (; t > 0; t--) {
			ArrayList<String[]> input = new ArrayList<String[]>();
			String in = scan.nextLine();
			while (!in.isEmpty()) {
				input.add(in.split(" "));
				in = scan.nextLine();
			}
			ArrayList<String> dictionary = new ArrayList<String>();
			for (int x = 0; x < input.size(); x++) {
				for (int y = 0; y < input.get(x).length; y++) {
					String s = input.get(x)[y];
					if (dictionary.indexOf(s) == -1) {
						dictionary.add(s);
						System.out.print(s + " ");
					} else {
						System.out.print(dictionary.indexOf(s) + 1 + " ");
					}
				}
				System.out.println();
			}

		}
	}
}