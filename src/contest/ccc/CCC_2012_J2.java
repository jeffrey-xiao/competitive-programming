package contest.ccc;

import java.util.Scanner;

class CCC_2012_J2 {
	public static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		String reading = "";
		int[] depthsRecorded = new int[4];
		for (int x = 0; x < 4; x++) {
			depthsRecorded[x] = scan.nextInt();
			if (x == 0)
				continue;

			if (depthsRecorded[x] > depthsRecorded[x - 1] && (reading.equals("Fish Rising") || reading.equals(""))) {
				reading = "Fish Rising";

			} else if (depthsRecorded[x] < depthsRecorded[x - 1] && (reading.equals("Fish Diving") || reading.equals(""))) {
				reading = "Fish Diving";
			} else if (depthsRecorded[x] == depthsRecorded[x - 1] && (reading.equals("Fish At Constant Depth") || reading.equals(""))) {
				reading = "Fish At Constant Depth";
			} else {
				reading = "No Fish";
			}
		}
		System.out.println(reading);
	}
}