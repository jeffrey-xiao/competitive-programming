package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2001_J5_S3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		boolean[][] vertices;
		ArrayList<String> s = new ArrayList<String>();

		String road = next();
		while (!road.equals("**")) {
			s.add(road);
			road = next();
		}
		int count = 0;
		for (int t = 0; t < s.size(); t++) {
			vertices = new boolean[26][26];
			for (int k = 0; k < s.size(); k++) {
				if (k != t) {
					int a = s.get(k).charAt(0) - 65;
					int b = s.get(k).charAt(1) - 65;
					vertices[a][b] = true;
					vertices[b][a] = true;
				}

			}
			for (int x = 0; x < 26; x++) {
				for (int y = 0; y < 26; y++) {
					for (int z = 0; z < 26; z++) {
						vertices[y][z] = vertices[y][z]
								|| (vertices[x][z] && vertices[y][x]);
					}
				}
			}
			if (!vertices[0][1]) {
				System.out.println(s.get(t));
				count++;
			}
		}
		System.out.printf("There are %d disconnecting roads.", count);
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
