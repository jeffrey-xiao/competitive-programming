/*
 * Online construction of a suffix automaton
 * 
 * Time complexity:
 *  - O(N)
 */

package codebook.string;

import java.util.ArrayList;
import java.util.HashMap;

public class SuffixAutomaton {

	// constants
	static final int MAXN = (int)(1e6);

	// attributes of the input data
	private String input;

	// transitions
	private ArrayList<HashMap<Character, Integer>> to;
	
	// suffix links
	private int[] link;
	
	// length of largest string accepted by state
	private int[] len;
	
	// final states
	private boolean[] finalState;
	
	// last state corresponding to entire string
	private int last;
	
	// current amount of states
	private int sz;

	SuffixAutomaton (String input) {
		this.input = input;
		initialize();
	}

	public void setString (String string) {
		this.input = string;
		initialize();
	}

	public String getString () {
		return input;
	}

	private void initialize () {
		link = new int[MAXN];
		len = new int[MAXN];
		finalState = new boolean[MAXN];
		
		last = 0;
		sz = 1;
		
		to = new ArrayList<HashMap<Character, Integer>>();
		
		for (int i = 0; i < MAXN; i++)
			to.add(new HashMap<Character, Integer>());
		
		for (int i = 0; i < input.length(); i++)
			addCharacter(input.charAt(i), i == input.length() - 1);
	}

	private void addCharacter (char c, boolean mark) {
		// state of string s
		int prev = last;
		
		// state of string sc
		int curr = (last = sz++);
		
		if (mark)
			finalState[curr] = true;
		
		len[curr] = len[prev] + 1;
		for (; to.get(prev).get(c) == null; prev = link[prev])
			to.get(prev).put(c, curr);
		
		// first occurrence of c and it is at the root
		if (to.get(prev).get(c) == curr) {
			link[curr] = 0;
			return;
		}
		
		// if the next state accepts all suffixes with length  <= len[prev] + 1,
		// then just add the suffix link from the new state to the next state
		int next = to.get(prev).get(c);
		if (len[next] == len[prev] + 1) {
			link[curr] = next;
			return;
		}
		
		// else, we split next into next and next'
		int next2 = sz++;
		
		if (mark)
			finalState[next2] = true;
		
		to.get(next2).putAll(to.get(next));
		
		link[next2] = link[next];
		len[next2] = len[curr] + 1;
		
		link[curr] = link[next] = next2;
		
		// redirect transitions where needed
		for (; to.get(prev).get(c) == next; prev = link[prev])
			to.get(prev).put(c, next2);
		
	}
	
	public boolean isAccepted (String s) {
		int currState = 0;
		for (int i = 0; i < s.length(); i++) {
			if (to.get(currState).get(s.charAt(i)) == null)
				return false;
			currState = to.get(currState).get(s.charAt(i));
		}
		return finalState[currState];
	}
	
	
	public static void main (String[] args) {
		String s1 = "abcabxabcd";
		SuffixAutomaton st = new SuffixAutomaton(s1);
		
		for (int i = 0; i < s1.length(); i++)
			assert(st.isAccepted(s1.substring(i)));
		
		assert(!st.isAccepted("xabc"));
		assert(!st.isAccepted("c"));
		
	}
}
