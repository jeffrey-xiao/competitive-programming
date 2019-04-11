package contest.ccc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CCC_2004_J4 {
  public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    char[] encodeKey = scan.nextLine().toCharArray();
    char[] temp = scan.nextLine().toCharArray();
    List<Character> preEncode = new ArrayList<Character>();
    List<Character> postEncode = new ArrayList<Character>();
    for (char a : temp) {
      if (alphabet.indexOf(a) != -1) {
        preEncode.add(a);
      }
    }
    int counter = 0;
    for (int x = 0; x < preEncode.size(); x++) {
      int indexPreEncode = alphabet.indexOf(preEncode.get(x));
      int moveLength = alphabet.indexOf(encodeKey[counter]);

      postEncode.add((alphabet.charAt((indexPreEncode + moveLength) % 26)));
      counter = (counter + 1) % encodeKey.length;
    }
    for (char a : postEncode) {
      System.out.print(a);
    }
  }
}
