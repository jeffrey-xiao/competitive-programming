package contest.ccc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CCC_2006_J4 {
	public static Scanner scan = new Scanner(System.in);
	public static Integer[] temp1 = {1, 1, 2, 3, 3};
	public static Integer[] temp2 = {7, 4, 1, 4, 5};
	public static ArrayList<Integer> done = new ArrayList<Integer>();
	public static Integer[] temp3 = {7, 6, 5, 4, 3, 2, 1};
	public static List<Integer> haveToDo = new ArrayList<Integer>(Arrays.asList(temp3));
	public static ArrayList<Integer> before = new ArrayList<Integer>(Arrays.asList(temp1));
	public static ArrayList<Integer> after = new ArrayList<Integer>(Arrays.asList(temp2));

	public static void main (String[] args) {
		int value = scan.nextInt();
		int value2 = scan.nextInt();
		while (value != 0 && value2 != 0) {
			before.add(value);
			after.add(value2);
			value = scan.nextInt();
			value2 = scan.nextInt();
		}
		int r = 0;
		while (done.size() != 7 && r < 7) {
			r++;
			ArrayList<Integer> temporary = new ArrayList<Integer>();
			for (int x = haveToDo.size() - 1; x >= 0; x--) {
				if (checkCanDo(haveToDo.get(x))) {
					temporary.add(haveToDo.get(x));
					haveToDo.remove(x);
					break;
				}
			}
			Collections.sort(temporary);
			done.addAll(temporary);
		}
		if (done.size() == 7) {
			for (Integer i : done) {
				System.out.print(i + " ");
			}
		} else {
			System.out.println("Cannot complete these tasks. Going to bed.");
		}

	}

	public static boolean checkCanDo (int task) {
		for (int x = 0; x < after.size(); x++) {
			if (after.get(x) == task && !done.contains(before.get(x))) {
				return false;
			}
		}
		return true;
	}
}