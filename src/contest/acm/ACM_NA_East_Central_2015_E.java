package contest.acm;
import java.util.*;
import java.io.*;

public class ACM_NA_East_Central_2015_E {

    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;

    public static void main (String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        //br = new BufferedReader(new FileReader("in.txt"));
        //out = new PrintWriter(new FileWriter("out.txt"));

        int N = readInt();
        int M = readInt();
        int S = readInt();
        int T = readInt();
        long[] val = new long[N];
        val[S] = 1L;
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        
        for (int i = 0; i < N; i++)
            adj.add(new ArrayList<Integer>());
        
        for (int i = 0; i < M; i++) {
            int a = readInt();
            int b = readInt();
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        
        long ans = 0;
        for (int i = 0; i < T; i++) {
            long[] nextVal = new long[N];
            for (int j = 0; j < N; j++) {
                for (int next : adj.get(j)) {
                    if (i == T - 1)
                        ans += val[j];
                    nextVal[next] += val[j];
                }
            }
            val = nextVal;
        }
        out.println(ans);
        out.close();
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
