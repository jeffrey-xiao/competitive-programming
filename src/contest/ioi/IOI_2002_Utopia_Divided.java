package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class IOI_2002_Utopia_Divided {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[] codes1, codes2, ans1, ans2, regions;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    codes1 = new int[N];
    codes2 = new int[N];
    ans1 = new int[N];
    ans2 = new int[N];
    regions = new int[N];

    for (int i = 0; i < N; i++)
      codes1[i] = readInt();

    for (int i = 0; i < N; i++)
      codes2[i] = readInt();

    Arrays.sort(codes1);
    Arrays.sort(codes2);

    for (int i = 0; i < N; i++)
      regions[i] = readInt();

    Deque<Integer> horIndex = new ArrayDeque<Integer>();
    Deque<Integer> verIndex = new ArrayDeque<Integer>();

    horIndex.add(0);
    verIndex.add(0);
    for (int i = 1; i < N; i++) {
      if (isHorPos(regions[i]) == isHorPos(regions[i - 1]))
        horIndex.addFirst(i);
      else
        horIndex.addLast(i);

      if (isVerPos(regions[i]) == isVerPos(regions[i - 1]))
        verIndex.addFirst(i);
      else
        verIndex.addLast(i);
    }

    boolean horNeg = false, verNeg = false;

    int index = 0;
    for (int i : horIndex) {
      if (i == 0)
        horNeg = (index % 2 == 0) != isHorPos(regions[0]);
      ans1[i] = index++;
    }

    index = 0;
    for (int i : verIndex) {
      if (i == 0)
        verNeg = (index % 2 == 0) != isVerPos(regions[0]);
      ans2[i] = index++;
    }

    int currHor = 0, currVer = 0;
    for (int i = 0; i < N; i++) {
      out.printf("%c%d ", horNeg == (ans1[i] % 2 == 0) ? '-' : '+', codes1[ans1[i]]);
      out.printf("%c%d%n", verNeg == (ans2[i] % 2 == 0) ? '-' : '+', codes2[ans2[i]]);
      currHor += horNeg == (ans1[i] % 2 == 0) ? -codes1[ans1[i]] : codes1[ans1[i]];
      currVer += verNeg == (ans2[i] % 2 == 0) ? -codes2[ans2[i]] : codes2[ans2[i]];
      assert currHor > 0 == isHorPos(regions[i]) && currVer > 0 == isVerPos(regions[i]);
    }
    out.close();
  }

  static boolean isHorPos(int region) {
    if (region == 1 || region == 4)
      return true;
    return false;
  }

  static boolean isVerPos(int region) {
    if (region == 1 || region == 2)
      return true;
    return false;
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
