package ccc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

class CCC_2009_J5 {
	public static TreeMap<String, ArrayList<String>> friends = new TreeMap<String, ArrayList<String>>();
	public static ArrayList<Integer> degrees = new ArrayList<Integer>();
	public static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		// INSERTING EXISTING FRIENDSHIPS
		insertFriendship("6", "1");
		insertFriendship("6", "2");
		insertFriendship("6", "7");
		insertFriendship("6", "5");
		insertFriendship("6", "4");
		insertFriendship("6", "3");
		insertFriendship("3", "4");
		insertFriendship("3", "5");
		insertFriendship("3", "15");
		insertFriendship("4", "5");
		insertFriendship("7", "8");
		insertFriendship("8", "9");
		insertFriendship("9", "12");
		insertFriendship("9", "10");
		insertFriendship("10", "11");
		insertFriendship("11", "12");
		insertFriendship("12", "13");
		insertFriendship("13", "15");
		insertFriendship("13", "14");
		insertFriendship("16", "17");
		insertFriendship("16", "18");
		insertFriendship("18", "17");

		// GETTING INPUT
		String input = scan.next();
		while (!input.equals("q")) {
			if (input.equals("i")) {
				insertFriendship(scan.next(), scan.next());
			} else if (input.equals("n")) {
				printFriends(scan.next());
			} else if (input.equals("f")) {
				ArrayList<String> hasAlready = new ArrayList<String>();
				printFriendsOfFriends(scan.next(), hasAlready);
			} else if (input.equals("d")) {
				deleteFriendship(scan.next(), scan.next());
			} else if (input.equals("s")) {
				String temp = scan.next();
				ArrayList<String> visited = new ArrayList<String>();
				visited.add(temp);

				printSeperation(temp, scan.next(), visited, 0);
				Collections.sort(degrees);
				if (degrees.size() == 0)
					System.out.println("Not connected");
				else
					System.out.println(degrees.get(0));
				degrees.clear();
				/*
				 * for(int x = 0; x < degrees.size(); x++){
				 * System.out.println(degrees.get(x)); }
				 */
			}
			input = scan.next();
		}
	}

	public static void insertFriendship (String friend1, String friend2) {
		if (friends.containsKey(friend1)) {
			friends.get(friend1).add(friend2);
		} else {
			friends.put(friend1, new ArrayList<String>());
			friends.get(friend1).add(friend2);
		}
		if (friends.containsKey(friend2)) {
			friends.get(friend2).add(friend1);
		} else {
			friends.put(friend2, new ArrayList<String>());
			friends.get(friend2).add(friend1);
		}
	}

	public static void printFriends (String person) {
		System.out.println(friends.get(person).size());
	}

	public static void printFriendsOfFriends (String person,
			ArrayList<String> hasAlready) {
		int count = 0;
		for (int x = 0; x < friends.get(person).size(); x++) {
			for (int y = 0; y < friends.get(friends.get(person).get(x)).size(); y++) {
				if (!friends.get(person).contains(
						friends.get(friends.get(person).get(x)).get(y))
						&& !person.equals(friends.get(
								friends.get(person).get(x)).get(y))
						&& !hasAlready.contains(friends.get(
								friends.get(person).get(x)).get(y))) {
					count++;
					hasAlready.add(friends.get(friends.get(person).get(x)).get(
							y));
				}
			}
		}
		System.out.println(count);
	}

	public static void deleteFriendship (String friend1, String friend2) {
		friends.get(friend1).remove(friend2);
		friends.get(friend2).remove(friend1);
	}

	public static void printSeperation (String person, String target,
			ArrayList<String> visited, int degree) {
		degree += 1;
		for (int x = 0; x < friends.get(person).size(); x++) {

			if (friends.get(person).get(x).equals(target)) {
				// System.out.println("PERSON " + person + " TARGET " + target +
				// " DEGREE " + degree);
				degrees.add(degree);
				break;
			}

			if (!visited.contains(friends.get(person).get(x))) {
				visited.add(friends.get(person).get(x));
				ArrayList<String> temp = new ArrayList<String>();
				temp.addAll(visited);
				printSeperation(friends.get(person).get(x), target, temp,
						degree);
			}
		}
	}
}
