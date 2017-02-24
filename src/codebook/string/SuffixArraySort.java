/*
 * O (N log N log N) construction of a suffix array. The time complexity can be shortened to O (N log N) if radix sort is used.
 * This implementation is very simple to code and it's only handful of lines. Can be used if the time limit isn't very tight.
 *
 * Time complexity: O(N (log N)^2)
 */
package codebook.string;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class SuffixArraySort {
	final SuffixComparator C = new SuffixComparator();

	// attributes of input
	private char[] input;
	private int len;
	private Integer[] res;
	private int[] order;
	private int[] newOrder;
	private int sz;

	SuffixArraySort (String s) {
		input = s.toCharArray();
		initialize();
	}

	public void setString (String s) {
		input = s.toCharArray();
		initialize();
	}

	public String getString () {
		return new String(input);
	}

	private void initialize () {
		len = input.length;
		res = new Integer[len];
		order = new int[len];
		newOrder = new int[len];
		sz = 0;
		computeSuffixArray();
	}

	public Integer[] getSuffixArray () {
		return res;
	}

	private void computeSuffixArray () {
		// initializing suffix array, order and new order
		for (int i = 0; i < len; i++) {
			res[i] = i;
			order[i] = (int)(input[i]);
			newOrder[i] = 0;
		}
		// we sort the suffix array with steps of the powers of 2
		// we can notice that a suffix with length 2^(n+1) can be split into two
		// strings each with length 2^n
		// since we already have the order of the first strings, the order
		// changes only when two first strings are equivalent
		for (sz = 1;; sz <<= 1) {
			Arrays.sort(res, C);
			// checking if two first strings are equivalent
			for (int i = 0; i < len - 1; i++)
				newOrder[i + 1] = newOrder[i] + (C.compare(res[i], res[i + 1]) < 0 ? 1 : 0);
			for (int i = 0; i < len; i++)
				order[res[i]] = newOrder[i];
			if (newOrder[len - 1] == len - 1)
				break;
		}
	}

	// Comparator for suffixes
	class SuffixComparator implements Comparator<Integer> {
		@Override
		public int compare (Integer o1, Integer o2) {
			if (order[o1] != order[o2])
				return order[o1] - order[o2];
			if ((o1 += sz) < len & (o2 += sz) < len)
				return order[o1] - order[o2];
			return o2 - o1;
		}
	}

	public static void main (String[] args) throws IOException {
		SuffixArraySort s = new SuffixArraySort(
				"7656632013158138561652486333406146657287177640441810188112108257455585717541805245206400757522102177522477573137787626350115827710213101412116385315232112775315858157041065400387888682628451834882045610646485805140684845318416426243386148534624541871758640300711358862082668764473624460654026856184680164086878588785370280855001127845414561206835781763527160242055554855825718761273055314325383163665733331246604262287888574842586514167774251627775625756533583000528735356104746533680380704784686056667843475362060321111851675570514420371300070388484134610872743815560371871217238365056360358111852312501862548123818362478617502870426272485712006286343240635026267488207136547211523086787556323814271023640112840080334760860415007212374282754885331218505756866701824246463034330661856032350561640772383451770814525342701843248243670561156613722718215515173306645832650566534818022542234365638228160876212784774416785200747542384244367836201173120211843131635521135433117721766470222163001185383453514");
		Integer[] res = s.getSuffixArray();
		for (int i = 0; i < s.getString().length(); i++)
			System.out.println(s.getString().substring(res[i]));
	}

}
