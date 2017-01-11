package contest.hackercup;

import java.util.*;
import java.io.*;

public class Diff {

	static BufferedReader br1, br2;

	public static void main (String[] args) throws IOException {
		br1 = new BufferedReader(new FileReader("out.txt"));
		br2 = new BufferedReader(new FileReader("out1.txt"));

		while (br1.ready()) {
			String s1 = br1.readLine().trim(), s2 = br2.readLine().trim();
			
			if (!s1.equals(s2))
				System.out.printf("%s %s\n", s1, s2);
		}
		
	}
}

