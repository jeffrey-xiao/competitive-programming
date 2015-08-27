package contest.ccc;

import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class CCC_2012_S3 {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		for (int x = scan.nextInt(); x > 0; x--) {
			int key = scan.nextInt();
			tm.put(key, tm.get(key) == null ? 1 : tm.get(key) + 1);
		}
		SortedSet<KeyValuePair> sortedSet = new TreeSet<KeyValuePair>();
		for (Map.Entry<Integer, Integer> entry : tm.entrySet()) {
			sortedSet.add(new CCC_2012_S3().new KeyValuePair(entry.getKey(), entry.getValue()));
		}
		int maxValue = -1;
		int firstValue = -1;
		int secondValue = -1;
		int sValue = 0;
		for (KeyValuePair k : sortedSet) {
			if (firstValue == -1) {
				firstValue = k.key;
				continue;
			}
			if (sValue == 0 || k.value == sValue) {
				sValue = k.value;
				if (Math.abs(firstValue - k.key) > maxValue) {
					maxValue = Math.abs(firstValue - k.key);
					secondValue = k.key;
				}
				continue;
			}
			break;

		}
		System.out.println(Math.abs(firstValue - secondValue));
		scan.close();
	}

	class KeyValuePair implements Comparable<KeyValuePair> {
		int key, value;

		public KeyValuePair (int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo (KeyValuePair o) {
			return value == o.value ? o.key - key : o.value - value;
		}
	}
}
