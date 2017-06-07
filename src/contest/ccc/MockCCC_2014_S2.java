package contest.ccc;

import java.util.Scanner;

public class MockCCC_2014_S2 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int maxSequence = 0;
    int stage = -2; // -1 going down, 0 is stay, 1 is going up
    int currSeqLength = 1;
    int length = scan.nextInt();
    int prevNum = scan.nextInt();
    for (int x = length - 1; x > 0; x--) {
      int currNum = scan.nextInt();
      if (currNum < prevNum && stage != 1 && stage != 0) {
        stage = -1;
        currSeqLength++;
      } else if (currNum == prevNum && (stage == 0 || stage == -1)) {
        stage = 0;
        currSeqLength++;
      } else if (currNum > prevNum && (stage != -2)) {
        stage = 1;
        currSeqLength++;
      } else if (stage != 0 && stage != -2) {
        stage = -1;
        currSeqLength = 2;
      }
      if (currSeqLength > maxSequence && currSeqLength >= 3 && stage == 1)
        maxSequence = currSeqLength;
      prevNum = currNum;
    }
    System.out.println(maxSequence);
  }
}
