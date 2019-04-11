package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class WOC_29_G_Suboptimal {

  static final int TOTAL_TIME = 3800;
  static final int OFFSET = 1000;
  static final boolean DEBUG = true;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, K;
  static int[][] adj;
  static int[] parameter;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    if (K >= N)
      out.println(1);
    else if (K == 1)
      out.println(N - 1);
    else if (K == 2 && N <= 400)
      solve2();
    else if (N <= 40)
      solveSmall();
    else
      simulatedAnnealing();

    out.close();
  }

  static void solve2() {
    parameter = new int[]{1, 2};

    int min = 1 << 30;
    int[][] bestAdj = new int[N][K];

    for (int a = 1; a <= N; a++)
      for (int b = a + 1; b <= N; b++) {
        parameter[0] = a;
        parameter[1] = b;

        adj = new int[N][K];

        for (int j = 0; j < N; j++) {
          for (int i = 0; i < K; i++) {
            adj[j][i] = (j + parameter[i]) % N;
          }
        }

        int diameter = getLongestDistance(0);

        if (diameter < min) {
          min = diameter;
          bestAdj = adj;
        }
      }

    // stack two circulant graphs
    if (N % 2 == 0) {
      for (int a = 1; a <= N; a++)
        for (int b = a + 1; b <= N; b++) {
          adj = new int[N][K];
          for (int i = 0; i < N / 2; i++) {
            adj[i][0] = (i + a) % (N / 2);
            adj[i][1] = i + N / 2;

            adj[i + N / 2][0] = (i + b) % (N / 2) + N / 2;
            adj[i + N / 2][1] = i;
          }
          int diameter = getLongestDistance(0);
          if (diameter < min) {
            min = diameter;
            bestAdj = adj;
          }
        }

    }
    out.println(min);
    if (!DEBUG)
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < K; j++)
          out.print(bestAdj[i][j] + " ");
        out.println();
      }
  }

  static void solveSmall() {
    parameter = new int[]{1, 0, 0, 0, 0};

    int min = 1 << 30;
    int[][] bestAdj = new int[N][K];

    for (int a = 2; a <= N; a++)
      for (int b = a; b <= N; b++)
        for (int c = a; c <= N; c++)
          for (int d = a; d <= N; d++) {
            parameter[1] = a;
            parameter[2] = b;
            parameter[3] = c;
            parameter[4] = d;

            adj = new int[N][K];

            for (int j = 0; j < N; j++) {
              for (int i = 0; i < K; i++) {
                adj[j][i] = (j + parameter[i]) % N;
              }
            }

            int diameter = getLongestDistance(0);

            if (diameter < min) {
              min = diameter;
              bestAdj = adj;
            }
          }

    out.println(min);
    if (!DEBUG)
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < K; j++)
          out.print(bestAdj[i][j] + " ");
        out.println();
      }
  }

  static void simulatedAnnealing() {
    int[] curr = new int[K], best = new int[K];
    for (int i = 1; i <= K; i++)
      curr[i - 1] = best[i - 1] = i;

    int bestValue = getCost(best);
    int currValue = getCost(curr);
    long startTime = System.currentTimeMillis(), currTime;
    while ((currTime = System.currentTimeMillis()) - startTime <= TOTAL_TIME) {
      int[] next = getRandomNeighbour(curr);

      long timeLeft = TOTAL_TIME - (currTime - startTime) + OFFSET;
      double temp = 1.0 * timeLeft / (TOTAL_TIME + OFFSET);

      int nextValue = getCost(next);

      if (nextValue < bestValue) {
        bestValue = nextValue;
        best = next;
      }

      if (nextValue < currValue) {
        currValue = nextValue;
        curr = next;
      } else {
        double factor = (1.0 * currValue / nextValue) * temp;
        if (factor >= Math.random()) {
          currValue = nextValue;
          curr = next;
        }
      }
    }

    out.println(bestValue);
    if (!DEBUG)
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < K; j++) {
          out.print((i + best[j]) % N + " ");
        }
        out.println();
      }
  }

  static int[] getRandomNeighbour(int[] parameters) {
    int gap = N - K;
    int index = (int) (Math.random() * gap + 1);
    int[] ret = Arrays.copyOf(parameters, K);
    if (index < ret[0]) {
      ret[0] = index;
      return ret;
    }
    index -= ret[0] - 1;
    for (int i = 0; i < K; i++) {
      if (i < K - 1) {
        if (ret[i + 1] - ret[i] - 1 >= index) {
          if (Math.random() < 0.5)
            ret[i] += index;
          else
            ret[i + 1] -= index;
          return ret;
        }
        index -= ret[i + 1] - ret[i] - 1;
      } else {
        ret[i] += index;
        return ret;
      }
    }
    assert false;
    return null;
  }

  static int getCost(int[] parameters) {
    adj = new int[N][K];

    for (int i = 0; i < N; i++)
      for (int j = 0; j < K; j++)
        adj[i][j] = (i + parameters[j]) % N;

    return getLongestDistance(0);
  }

  static int getLongestDistance(int u) {
    Queue<Integer> q = new ArrayDeque<Integer>();
    int[] dist = new int[N];
    Arrays.fill(dist, 1 << 30);
    dist[u] = 0;
    q.offer(u);

    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int j = 0; j < adj[curr].length; j++) {
        int next = adj[curr][j];
        if (dist[next] != 1 << 30)
          continue;
        dist[next] = dist[curr] + 1;
        q.offer(next);
      }
    }
    int ret = 0;
    for (int i = 0; i < N; i++)
      ret = Math.max(ret, dist[i]);
    return ret;
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
