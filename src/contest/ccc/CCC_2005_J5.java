package contest.ccc;

import java.util.Scanner;

public class CCC_2005_J5 {
  private static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    // input words
    while (true) {
      String inputWords = scan.next();
      if ("X".equals(inputWords))
        System.exit(0);
      System.out.println(isMonkeyLang(inputWords) ? "YES" : "NO");
    }
  }

  public static boolean isMonkeyLang(String words) {
    if (words.length() > 0) {
      int indxN = words.indexOf("N");
      if (indxN >= 0) {
        // check if N is optional letter followed by monkey language
        boolean result = false;
        for (int i = 0; i < words.length(); i++) {
          if (words.charAt(i) == 'N') {
            result = result || (isA_Word(words.substring(0, i)) && isMonkeyLang(words.substring(i + 1)));
            if (result)
              break;
          }
        }
        // check N is part of A-Word
        if (!result)
          return isA_Word(words);
        return result;
      } else
        return isA_Word(words);
    }
    return false;
  }

  public static boolean isA_Word(String words) {
    if (words.length() > 0) {
      int indxS = words.lastIndexOf("S");
      if (words.equals("A"))
        return true;
      if (words.startsWith("B") && words.endsWith("S"))
        return isMonkeyLang(words.substring(1, indxS));
    }
    return false;
  }
}