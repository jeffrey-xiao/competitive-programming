package contest.coci;

import java.util.Scanner;

public class COCI_2008_PTICE {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    char[] a = {'A', 'B', 'C'};
    char[] b = {'B', 'A', 'B', 'C'};
    char[] c = {'C', 'C', 'A', 'A', 'B', 'B'};
    int a1 = 0;
    int b1 = 0;
    int c1 = 0;
    int max = 0;
    int num = scan.nextInt();
    scan.nextLine();
    char[] seq = scan.nextLine().toCharArray();
    for (int x = 0; x < num; x++) {
      if (seq[x] == a[x % a.length])
        a1++;
      if (seq[x] == b[x % b.length])
        b1++;
      if (seq[x] == c[x % c.length])
        c1++;
    }
    max = Math.max(Math.max(a1, b1), c1);
    System.out.println(max);
    if (a1 == max)
      System.out.println("Adrian");
    if (b1 == max)
      System.out.println("Bruno");
    if (c1 == max)
      System.out.println("Goran");

  }
}
