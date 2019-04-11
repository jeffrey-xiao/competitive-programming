package contest.acm;

import java.util.Arrays;
import java.util.Scanner;

public class ACM_A_Subtle_Surf {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    for (int t = scan.nextInt(); t > 0; t--) {
      int numOfChannels = scan.nextInt();
      int maxGap = scan.nextInt();
      int time = scan.nextInt();
      int count = 0;
      int[] channels = new int[numOfChannels];
      for (int x = 0; x < channels.length; x++) {
        channels[x] = scan.nextInt();
      }
      int startChannel = channels[0];
      Arrays.sort(channels);
      int c = -1;
      int y;
      for (int x = 0; x < channels.length && c == -1; x++)
        if (startChannel == channels[x])
          c = x;
      while (c < channels.length) {
        for (y = c; y + 1 < channels.length && channels[y + 1] - maxGap <= channels[c]; y++)
          ;
        if (y == c || channels[y] == channels[c])
          break;
        count++;
        c = y;
      }
      System.out.println(channels[c] + " " + (time * count));
    }
  }
}
