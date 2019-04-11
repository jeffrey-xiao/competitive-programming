/*
 * An algorithm to determine if two rooted trees are isomorphic.
 *
 * Time complexity: O(V + E)
 */

package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class RootedTreeIsomorphism {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  // precondition: t1 and t2 represent trees and they are rooted at vertex 0
  public static boolean equal(ArrayList<ArrayList<Integer>> t1, ArrayList<ArrayList<Integer>> t2) {
    if (t1.size() != t2.size())
      return false;
    int n = t1.size();
    Queue<Integer> q = new ArrayDeque<Integer>();
    ArrayList<ArrayList<Integer>> depth1 = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> depth2 = new ArrayList<ArrayList<Integer>>();
    int[] dist1 = new int[n];
    int[] dist2 = new int[n];
    String[] label1 = new String[n];
    String[] label2 = new String[n];
    for (int i = 0; i < n; i++) {
      dist1[i] = dist2[i] = 1 << 30;
      depth1.add(new ArrayList<Integer>());
      depth2.add(new ArrayList<Integer>());
    }
    dist1[0] = dist2[0] = 0;
    q.offer(0);
    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int next : t1.get(curr))
        if (dist1[next] == 1 << 30) {
          dist1[next] = dist1[curr] + 1;
          depth1.get(dist1[next]).add(next);
          q.offer(next);
        }
    }
    q.offer(0);
    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int next : t2.get(curr)) {
        if (dist2[next] == 1 << 30) {
          dist2[next] = dist2[curr] + 1;
          depth2.get(dist2[next]).add(next);
          q.offer(next);
        }
      }
    }
    for (int i = n - 1; i >= 0; i--) {
      if (depth1.get(i).size() != depth2.get(i).size())
        return false;
      HashMap<String, String> hm = new HashMap<String, String>();
      ArrayList<String> level1 = new ArrayList<String>();
      ArrayList<String> level2 = new ArrayList<String>();
      for (int curr : depth1.get(i)) {
        ArrayList<String> child = new ArrayList<String>();
        for (int next : t1.get(curr))
          child.add(label1[next] + " ");
        Collections.sort(child);
        StringBuilder label = new StringBuilder();
        for (String s : child)
          label.append(s + " ");
        if (hm.get(label.toString()) == null)
          hm.put(label.toString(), Integer.toString(hm.size()));
        label1[curr] = hm.get(label.toString());
        level1.add(label1[curr]);
      }
      for (int curr : depth2.get(i)) {
        ArrayList<String> child = new ArrayList<String>();
        for (int next : t2.get(curr))
          child.add(label2[next] + " ");
        Collections.sort(child);
        StringBuilder label = new StringBuilder();
        for (String s : child)
          label.append(s + " ");
        if (hm.get(label.toString()) == null)
          hm.put(label.toString(), Integer.toString(hm.size()));
        label2[curr] = hm.get(label.toString());
        level2.add(label2[curr]);
      }
      Collections.sort(level1);
      Collections.sort(level2);
      for (int j = 0; j < level1.size(); j++)
        if (level1.get(j) != level2.get(j))
          return false;
    }
    return true;
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    ArrayList<ArrayList<Integer>> t1 = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> t2 = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < n; i++) {
      t1.add(new ArrayList<Integer>());
      t2.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < n - 1; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      t1.get(a).add(b);
      t1.get(b).add(a);
    }
    for (int i = 0; i < n - 1; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      t2.get(a).add(b);
      t2.get(b).add(a);
    }
    out.println(equal(t1, t2));
    out.close();
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
