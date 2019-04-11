package contest.bloomberg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Bloomberg_Codecon_2017_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static Pokemon[] opp, team;
  static int max = 0;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    opp = new Pokemon[N];
    team = new Pokemon[N];

    for (int i = 0; i < N; i++) {
      next();
      team[i] = new Pokemon(readDouble(), readDouble(), readDouble());
    }
    for (int i = 0; i < N; i++) {
      next();
      opp[i] = new Pokemon(readDouble(), readDouble(), readDouble());
    }
    permute(0);
    out.println(max);
    out.close();
  }

  static int simulate() {
    int i = 0;
    int j = 0;
    double hp1 = opp[i].health;
    double hp2 = team[j].health;
    while (i < N && j < N) {
      while (hp1 > 0 && hp2 > 0) {
        hp1 -= (1 - opp[i].defence) * team[j].attack;
        hp2 -= (1 - team[j].defence) * opp[i].attack;
      }
      if (hp1 <= 0.0) {
        i++;
        if (i < N) {
          hp1 = opp[i].health;
        }
      }
      if (hp2 <= 0.0) {
        j++;
        if (j < N)
          hp2 = team[j].health;
      }
    }
    return N - j;
  }

  static void permute(int i) {
    if (i == N) {
      max = Math.max(max, simulate());
      return;
    }
    for (int j = i; j < N; j++) {
      swap(i, j);
      permute(i + 1);
      swap(i, j);
    }
  }

  static void swap(int i, int j) {
    Pokemon temp = team[i];
    team[i] = team[j];
    team[j] = temp;
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

  static class Pokemon {
    double health, attack, defence;

    Pokemon(double health, double attack, double defence) {
      this.health = health;
      this.attack = attack;
      this.defence = defence;
    }
  }
}
