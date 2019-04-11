package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CCC_2001_Stage_2_Coke_or_Chocolate_Milk {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int n;
  static TreeMap<String, Integer> toIndex = new TreeMap<String, Integer>();
  static HashMap<Integer, String> toName = new HashMap<Integer, String>();
  static ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>();
  static int[] v;
  static int[] disc;
  static int[] low;
  static int[] com;
  static int[] negcom;
  static int[] idCom;
  static int[] in;
  static int[] sizeCom;
  static Stack<Integer> s = new Stack<Integer>();
  static int cnt = 0, comNum = 0;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    toIndex.clear();
    toName.clear();
    adj.clear();
    v = new int[2000];
    disc = new int[2000];
    low = new int[2000];
    com = new int[2000];
    negcom = new int[2000];
    idCom = new int[2000];
    in = new int[2000];
    sizeCom = new int[2000];
    s.clear();
    cnt = 0;
    comNum = 0;
    n = 0;
    int Q = -1;
  main:
    while ((Q = readInt()) != 0) {
      for (int i = 0; i < 2000; i++) {
        adj.add(new HashSet<Integer>());
        disc[i] = low[i] = com[i] = -1;
      }
      // p U q == -p -> q and -q -> p
      // x * 2 + 1 = complement

      for (int i = 0; i < Q; i++) {
        String[] in = readLine().split(" ");
        if (in.length == 3) {
          addName(in[0]);
          if (in[1].equals("wants"))
            v[toIndex.get(in[0])] = 1;
          else
            v[toIndex.get(in[0])] = -1;
        } else if (in.length == 4) {
          addName(in[0]);
          if (in[1].equals("wants"))
            v[toIndex.get(in[0])] = -1;
          else
            v[toIndex.get(in[0])] = 1;
        } else if (in.length == 5) {
          addName(in[0]);
          addName(in[4]);
          int p = toIndex.get(in[0]);
          int q = toIndex.get(in[4]);
          if (in[2].equals("same")) {
            adj.get(p * 2).add(q * 2);
            adj.get(q * 2).add(p * 2);
            adj.get(p * 2 + 1).add(q * 2 + 1);
            adj.get(q * 2 + 1).add(p * 2 + 1);
          } else {
            adj.get(p * 2 + 1).add(q * 2);
            adj.get(q * 2).add(p * 2 + 1);
            adj.get(p * 2).add(q * 2 + 1);
            adj.get(q * 2 + 1).add(p * 2);
          }
        } else {
          addName(in[0]);
          int p = toIndex.get(in[0]);
          if (in[2].equals("Coke") && in[6].equals("Coke")) {
            addName(in[4]);
            int q = toIndex.get(in[4]);
            adj.get(p * 2 + 1).add(q * 2 + 1);
            adj.get(q * 2).add(p * 2);
          } else if (in[2].equals("Coke") && in[6].equals("chocolate")) {
            addName(in[4]);
            int q = toIndex.get(in[4]);
            adj.get(p * 2 + 1).add(q * 2);
            adj.get(q * 2 + 1).add(p * 2);
          } else if (in[2].equals("chocolate") && in[7].equals("chocolate")) {
            addName(in[5]);
            int q = toIndex.get(in[5]);
            adj.get(p * 2).add(q * 2);
            adj.get(q * 2 + 1).add(p * 2 + 1);
          } else if (in[2].equals("chocolate") && in[7].equals("Coke")) {
            addName(in[5]);
            int q = toIndex.get(in[5]);
            adj.get(p * 2).add(q * 2 + 1);
            adj.get(q * 2).add(p * 2 + 1);
          }
        }
      }
      n = toIndex.size();
      // generate all the connected components
      for (int i = 0; i < 2 * n; i++)
        if (disc[i] == -1)
          dfs(i);
      // checking if assignment is valid
      boolean valid = true;
      for (int i = 0; i < 2 * n; i += 2) {
        if (com[i] == com[i + 1]) {
          valid = false;
        } else {
          negcom[com[i]] = com[i + 1];
          negcom[com[i + 1]] = com[i];
        }
      }
      if (!valid) {
        System.out.println("Everybody gets water");
        continue;
      }
      // assigning values
      for (int i = 0; i < n; i++) {
        if (v[i] != 0) {
          if (idCom[com[i * 2]] == -v[i] || idCom[com[i * 2 + 1]] == v[i]) {
            System.out.println("Everybody gets water");
            continue main;
          }
          idCom[com[i * 2]] = v[i];
          idCom[com[i * 2 + 1]] = -v[i];
        }
      }
      for (int i = 0; i < comNum; i++) {
        if (idCom[i] == 0) {
          if (sizeCom[i] > sizeCom[negcom[i]]) {
            idCom[i] = 1;
            idCom[negcom[i]] = -1;
          } else if (sizeCom[i] < sizeCom[negcom[i]]) {
            idCom[i] = -1;
            idCom[negcom[i]] = 1;
          } else {
          }
        }
      }
      for (Map.Entry<String, Integer> e : toIndex.entrySet()) {
        if (idCom[com[e.getValue() * 2]] == 0) {
          idCom[com[e.getValue() * 2]] = 1;
          idCom[negcom[com[e.getValue() * 2]]] = -1;
        }
        System.out.println(e.getKey() + " gets " + (idCom[com[e.getValue() * 2]] == 1 ? "Coke" : "chocolate milk"));
      }
    }
    pr.close();
  }

  static void dfs(int u) {
    disc[u] = low[u] = cnt++;
    s.push(u);
    for (Integer v : adj.get(u)) {
      if (disc[v] == -1) {
        dfs(v);
        low[u] = Math.min(low[u], low[v]);
      } else if (com[v] == -1) {
        low[u] = Math.min(low[u], disc[v]);
      }
    }
    if (disc[u] == low[u]) {
      while (s.peek() != u) {
        com[s.pop()] = comNum;
        sizeCom[comNum]++;
      }
      sizeCom[comNum]++;
      com[s.pop()] = comNum++;
    }
  }

  // adds name if didn't yet
  static void addName(String name) {
    if (!toIndex.containsKey(name)) {
      toIndex.put(name, n);
      toName.put(n, name);
      n++;
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
}
