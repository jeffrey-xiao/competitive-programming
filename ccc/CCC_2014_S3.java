package ccc;

import java.util.Scanner;
import java.util.Stack;

public class CCC_2014_S3 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int x = scan.nextInt(); x > 0; x--) {
			int size = scan.nextInt();
			Stack<Integer> start = new Stack<Integer>();
			Stack<Integer> side = new Stack<Integer>();
			start.push(0);
			side.push(0);
			int counter = 1;
			for (int y = 0; y < size; y++)
				start.push(scan.nextInt());
			boolean yum = true;
			while (counter <= size) {
				if (start.peek() == counter) {
					start.pop();
					counter++;
				} else if (side.peek() == counter) {
					side.pop();
					counter++;
				} else {
					if (start.peek() == 0) {
						yum = false;
						break;
					}
					side.push(start.pop());
				}
			}
			System.out.println(yum ? "Y" : "N");
		}
	}
}
