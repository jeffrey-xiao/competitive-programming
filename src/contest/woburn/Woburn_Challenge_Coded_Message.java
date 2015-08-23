package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_Coded_Message {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int t = 0; t < 5; t++) {
			String input = scan.next();
			scan.nextLine();
			String msg = scan.nextLine();
			char[][] code = new char[(int) Math.ceil(msg.length() / 5.0)][5];
			if (input.equals("D")) {
				int counter = 0;
				for (int x = 0; x < code[0].length; x++) {
					for (int y = 0; y < code.length; y++) {
						if (counter >= msg.length())
							code[y][x] = ' ';
						else
							code[y][x] = msg.charAt(counter);
						counter++;
					}
				}
				for (int x = 0; x < code.length; x++) {
					for (int y = 0; y < code[0].length; y++) {
						System.out.print(code[x][y]);
					}
				}
				System.out.println();
			} else {
				int counter = 0;
				for (int x = 0; x < code.length; x++) {
					for (int y = 0; y < code[0].length; y++) {
						if (counter >= msg.length())
							code[x][y] = ' ';
						else
							code[x][y] = msg.charAt(counter);
						counter++;
					}
				}
				for (int x = 0; x < code[0].length; x++) {
					for (int y = 0; y < code.length; y++) {
						System.out.print(code[y][x]);
					}
				}
				System.out.println();
			}
		}
	}
}
