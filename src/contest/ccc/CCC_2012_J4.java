package contest.ccc;

import java.util.Scanner;

class CCC_2012_J4 {

	public static Scanner scan = new Scanner(System.in);
	public static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	public static void main (String[] args) {
		int parameter = scan.nextInt();
		String rawCode = scan.next();
		char[] code = rawCode.toCharArray();
		int tempCharPos = 0;
		char[] newCode = new char[code.length];
		for (int x = 0; x < code.length; x++) {
			for (int y = 0; y < alphabet.length; y++) {
				if (code[x] == alphabet[y]) {
					tempCharPos = y;
				}
			}
			tempCharPos -= (3 * (x + 1) + parameter);
			if (tempCharPos < 0)
				tempCharPos += 26;
			newCode[x] = alphabet[tempCharPos];
		}
		for (int z = 0; z < newCode.length; z++) {
			System.out.print(newCode[z]);
		}
	}
}