package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CROC_2016_Qualification_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();

    HashMap<String, ArrayList<String>> sites = new HashMap<String, ArrayList<String>>();

    for (int i = 0; i < n; i++) {
      String s = readLine();
      int hostEnd = 7;

      while (hostEnd < s.length() && s.charAt(hostEnd) != '/')
        ++hostEnd;

      String host = s.substring(0, hostEnd);
      String query = s.substring(hostEnd);

      if (!sites.containsKey(host))
        sites.put(host, new ArrayList<String>());
      sites.get(host).add(query);
    }

    HashMap<ArrayList<String>, ArrayList<String>> pathHosts = new HashMap<ArrayList<String>, ArrayList<String>>();
    for (Map.Entry<String, ArrayList<String>> e : sites.entrySet()) {
      ArrayList<String> paths = e.getValue();
      Collections.sort(paths);

      ArrayList<String> res = new ArrayList<String>();
      for (int i = 0; i < paths.size(); i++) {
        if (i == 0 || !(paths.get(i).equals(paths.get(i - 1)))) {
          res.add(paths.get(i));
        }
      }

      if (pathHosts.get(res) == null)
        pathHosts.put(res, new ArrayList<String>());
      pathHosts.get(res).add(e.getKey());

    }
    ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();

    for (ArrayList<String> hosts : pathHosts.values()) {
      if (hosts.size() > 1) {
        ans.add(hosts);
      }
    }

    out.println(ans.size());
    for (ArrayList<String> hosts : ans) {
      for (String s : hosts)
        out.print(s + " ");
      out.println();
    }
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

  static class State implements Comparable<State> {
    int index;
    long hash1, hash2;

    State(int index, long hash1, long hash2) {
      this.index = index;
      this.hash1 = hash1;
      this.hash2 = hash2;
    }

    @Override
    public int compareTo(State o) {
      int cmp1 = new Long(hash1).compareTo(new Long(o.hash1));
      int cmp2 = new Long(hash2).compareTo(new Long(o.hash2));
      if (cmp1 != 0)
        return cmp1;
      return cmp2;
    }
  }
}
