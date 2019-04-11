package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_2011_Dancing_Elephants {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, L, M;
  static int[] pos, bucket;

  static int S, bucketCnt;
  static ArrayList<ArrayList<State>> b;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    L = readInt();
    M = readInt();

    S = (int)(Math.sqrt(N));
    bucketCnt = (N - 1) / S + 1;
    b = new ArrayList<ArrayList<State>>();

    for (int i = 0; i < bucketCnt; i++) {
      b.add(new ArrayList<State>());
    }

    pos = new int[N];
    bucket = new int[N];

    for (int i = 0; i < N; i++) {
      pos[i] = readInt();
      bucket[i] = i / S;
      b.get(i / S).add(new State(i, 0, 0));
    }

    rebuild();

    int rebuildCount = 0;
    for (int i = 0; i < M; i++) {
      int index = readInt();
      int newPos = readInt();
      pos[index] = newPos;

      // removing from bucket
      boolean removed = false;

      for (int j = 0; j < b.get(bucket[index]).size(); j++) {
        if (b.get(bucket[index]).get(j).index == index) {
          b.get(bucket[index]).remove(j);
          removed = true;
          break;
        }
      }
      assert removed;

      // adding to bucket
      int oldBucket = bucket[index];
      int newBucket = 0;

      for (; newBucket < bucketCnt; newBucket++) {
        if (b.get(newBucket).isEmpty())
          continue;
        if (pos[b.get(newBucket).get(b.get(newBucket).size() - 1).index] >= newPos)
          break;
      }

      if (newBucket == bucketCnt)
        newBucket--;

      bucket[index] = newBucket;
      boolean added = false;
      for (int j = 0; j < b.get(newBucket).size(); j++) {
        if (pos[b.get(newBucket).get(j).index] >= newPos) {
          b.get(newBucket).add(j, new State(index, 0, 0));
          added = true;
          break;
        }
      }

      if (!added)
        b.get(newBucket).add(new State(index, 0, 0));

      // rebuilding removed bucket and new bucket
      rebuildBucket(oldBucket);
      rebuildBucket(newBucket);

      int ans = 0;
      for (int j = 0, currPos = -1; j < bucketCnt; j++) {
        if (b.get(j).isEmpty())
          continue;
        if (currPos == -1) {
          ans += b.get(j).get(0).camerasNeeded;
          currPos = b.get(j).get(0).highestPosition;
        } else {
          int lo = 0;
          int hi = b.get(j).size() - 1;

          while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (pos[b.get(j).get(mid).index] <= currPos)
              lo = mid + 1;
            else
              hi = mid - 1;
          }
          if (lo < b.get(j).size()) {
            ans += b.get(j).get(lo).camerasNeeded;
            currPos = b.get(j).get(lo).highestPosition;
          }
        }
      }
      if (rebuildCount == bucketCnt) {
        rebuildCount = 0;
        remake();
      }

      rebuildCount++;
      out.println(ans);
    }

    out.close();
  }

  static void remake() {
    ArrayList<Integer> curr = new ArrayList<Integer>();
    for (int i = 0; i < bucketCnt; i++)
      for (int j = 0; j < b.get(i).size(); j++)
        curr.add(b.get(i).get(j).index);

    for (int i = 0; i < bucketCnt; i++)
      b.get(i).clear();

    for (int i = 0; i < curr.size(); i++) {
      b.get(i / S).add(new State(curr.get(i), 0, 0));
      bucket[curr.get(i)] = i / S;
    }

    rebuild();
  }

  static void rebuild() {
    for (int i = 0; i < bucketCnt; i++) {
      rebuildBucket(i);
    }
  }

  static void rebuildBucket(int i) {
    int index = b.get(i).size();
    for (int j = b.get(i).size() - 1; j >= 0; j--) {
      while (index - 1 > j && pos[b.get(i).get(index - 1).index] - pos[b.get(i).get(j).index] > L)
        index--;
      if (index == b.get(i).size()) {
        b.get(i).get(j).camerasNeeded = 1;
        b.get(i).get(j).highestPosition = pos[b.get(i).get(j).index] + L;
      } else {
        b.get(i).get(j).camerasNeeded = 1 + b.get(i).get(index).camerasNeeded;
        b.get(i).get(j).highestPosition = b.get(i).get(index).highestPosition;
      }
    }
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

  static class State {
    int index, camerasNeeded, highestPosition;

    State(int index, int camerasNeeded, int highestPosition) {
      this.index = index;
      this.camerasNeeded = camerasNeeded;
      this.highestPosition = highestPosition;
    }

    public String toString() {
      return String.format("(%d, %d, %d)", pos[index], camerasNeeded, highestPosition);
    }
  }
}
