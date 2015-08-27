package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class IOI_1998_Party_Lamps {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfLights = readInt();
		int numOfButtons = readInt();
		ArrayList<Byte> on = new ArrayList<Byte>();
		ArrayList<Byte> off = new ArrayList<Byte>();
		boolean[] lights = new boolean[numOfLights];
		for (int x = 0; x < lights.length; x++)
			lights[x] = true;
		byte next = Byte.parseByte(next());
		while (next != -1) {
			on.add(next);
			next = Byte.parseByte(next());
		}
		next = Byte.parseByte(next());
		while (next != -1) {
			off.add(next);
			next = Byte.parseByte(next());
		}
		Set<String> s = new HashSet<String>();
		boolean possible = false;
		for (int b1 = 0; b1 < 2; b1++) {
			if (b1 == 1)
				flip(1, lights);
			for (int b2 = 0; b2 < 2; b2++) {
				if (b2 == 1)
					flip(2, lights);
				for (int b3 = 0; b3 < 2; b3++) {
					if (b3 == 1)
						flip(3, lights);
					for (int b4 = 0; b4 < 2; b4++) {
						if (b4 == 1)
							flip(4, lights);
						// print(lights);
						// System.out.println(b1+"" +b2+"" +b3+"" +b4);
						if (b1 + b2 + b3 + b4 <= numOfButtons && check(lights, on, off)) {
							s.add(print(lights));
							// System.out.println(print(lights));
							// System.out.println(b1+"" +b2+"" +b3+"" +b4);
							possible = true;
						}

					}
					flip(4, lights);
				}
				flip(3, lights);
			}
			flip(2, lights);
		}
		if (!possible)
			System.out.println("IMPOSSIBLE");
		else {
			for (String s1 : s)
				System.out.println(s1);
		}
	}

	private static String print (boolean[] lights) {
		String s = "";
		for (boolean b : lights)
			s += (b ? "1" : "0");
		return s;
	}

	private static boolean check (boolean[] lights, ArrayList<Byte> on, ArrayList<Byte> off) {
		for (byte b : on)
			if (!lights[b - 1])
				return false;
		for (byte b : off)
			if (lights[b - 1])
				return false;
		return true;
	}

	private static void flip (int i, boolean[] lights) {
		if (i == 1)
			for (int x = 0; x < lights.length; x++)
				lights[x] = !lights[x];
		else if (i == 2)
			for (int x = 0; x < lights.length; x++) {
				if ((x + 1) % 2 == 0)
					lights[x] = !lights[x];
			}
		else if (i == 3)
			for (int x = 0; x < lights.length; x++) {
				if (x % 2 == 0)
					lights[x] = !lights[x];
			}
		else if (i == 4)
			for (int x = 0; x < lights.length; x++)
				if (x % 3 == 0)
					lights[x] = !lights[x];
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
