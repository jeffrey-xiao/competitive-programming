package contest.coci;

import java.util.HashSet;
import java.util.Scanner;

public class COCI_2006_MODULO {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    HashSet<Integer> set = new HashSet<Integer>();
    for (int x = 0; x < 10; x++)
      set.add(scan.nextInt() % 42);
    System.out.println(set.size());
  }
}
