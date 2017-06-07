package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TLE_Power_Ranking {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, P;
  static User[] users;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    P = readInt();

    users = new User[N];

    for (int i = 0; i < N; i++)
      users[i] = new User(readLine(), 0);

    for (int i = 0; i < P; i++)
      for (int j = 0; j < N; j++)
        users[j].points += readInt();

    Arrays.sort(users);

    for (int i = 0; i < N; i++)
      out.printf("%d. %s\n", i + 3, users[i].name);

    out.close();
  }

  static class User implements Comparable<User> {
    String name;
    int points;

    User (String name, int points) {
      this.name = name;
      this.points = points;
    }

    @Override
    public int compareTo (User o) {
      return o.points - points;
    }
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
