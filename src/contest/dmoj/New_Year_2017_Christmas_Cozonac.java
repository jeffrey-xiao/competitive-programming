package contest.dmoj;

import java.util.*;
import java.io.*;

public class New_Year_2017_Christmas_Cozonac {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		HashMap<ArrayList<String>, String> recipes = new HashMap<ArrayList<String>, String>();
		
		for (int i = 0; i < N; i++) {
			String in = readLine().replaceAll("[()\\s]", "");
			ArrayList<String> tokens = tokenize(in, '=');
			String result = tokens.get(1);
			ArrayList<String> ingredients = tokenize(tokens.get(0), '+');
			Collections.sort(ingredients);
			recipes.put(ingredients, result);
		}
		
		int M = readInt();
		for (int i = 0; i < M; i++) {
			ArrayList<String> in = tokenize(readLine().replaceAll("\\+", " "), ' ');
			Stack<String> s = new Stack<String>();
			
			for (int j = 0; j < in.size(); j++) {
				if (in.get(j).equals(""))
					continue;
				if (!in.get(j).equals(")"))
					s.push(in.get(j));
				else {
					ArrayList<String> recipe = new ArrayList<String>();
					while (!s.peek().equals("("))
						recipe.add(s.pop());
					s.pop();
					Collections.sort(recipe);
					s.push(recipes.get(recipe));
				}
			}
			out.println(s.peek());
		}
		out.close();
	}

	static ArrayList<String> tokenize (String s, char delimiter) {
		ArrayList<String> list = new ArrayList<String>();
        int pos = 0, end;
        while ((end = s.indexOf(delimiter, pos)) >= 0) {
            list.add(s.substring(pos, end));
            pos = end + 1;
        }
        if (pos < s.length()) {
        	list.add(s.substring(pos, s.length()));
        }
        return list;
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

