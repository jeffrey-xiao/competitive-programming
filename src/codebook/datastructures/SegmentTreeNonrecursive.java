/*
 * A segment tree is a tree data structure for storing intervals. Lazy propagation is used to ensure a O(log N) query and update time.
 * This implementation can update and query the sum of elements.
 *
 * Time complexity:
 *  - Build: O(N)
 *  - Update: O(log N)
 *  - Query: O(log N)
 */

package codebook.datastructures;

public class SegmentTreeNonrecursive {

  private int size;
  private int[] tree;
  private int[] lazy;
  private int[] sz;

  SegmentTreeNonrecursive(int size) {
    this(size, new int[size]);
  }

  SegmentTreeNonrecursive(int size, int[] val) {
    this.size = size;
    this.tree = new int[2 * size];
    this.lazy = new int[2 * size];
    this.sz = new int[2 * size];
    for (int i = 0; i < size; i++) {
      tree[size + i] = val[i];
      sz[size + i] = 1;
    }
    build();
  }

  // zero indexed
  public static void main(String[] args) {
    SegmentTreeNonrecursive t = new SegmentTreeNonrecursive(10);
    t.update(1, 5, 5);
    System.out.println(t.query(2, 5));
    System.out.println(t.query(3, 5));
    System.out.println(t.query(4, 5));
    System.out.println(t.query(5, 5));
    t.update(2, 3, 3);
    System.out.println(t.query(1, 5));
    System.out.println(t.query(1, 2));
  }

  /*
  public void update (int i, int val) {
  	tree[size + i] += val;
  	for (; i > 1; i >>= 1)
  		tree[i >> 1] = tree[i] + tree[i^1];
  }
  */

  private void build() {
    // building the tree from the initial values and initializing the size of each segment
    for (int i = 2 * size - 1; i > 1; i -= 2) {
      tree[i >> 1] = tree[i] + tree[i ^ 1];
      sz[i >> 1] = sz[i] + sz[i ^ 1];
    }
  }

  public void update(int lo, int hi, int val) {
    // directly modifying the segments
    // note that a left child will have an even index while a right child will have an odd index
    for (int l = lo + size, r = hi + size; l <= r; l = (l + 1) >> 1, r = (r - 1) >> 1) {
      // if the left child is even, it is contained in a segment higher above, so we can ignore it
      if ((l & 1) > 0) {
        tree[l] += val * sz[l];
        lazy[l] += val;
      }
      // if the right child is odd, it is contained in a segment higher above, so we can ignore it
      if ((r & 1) == 0) {
        tree[r] += val * sz[r];
        lazy[r] += val;
      }
    }
    // updating the segments that contain the updated segment
    pushUp(lo + size);
    pushUp(hi + size);
  }

  public void pushUp(int i) {
    // we add the values of the child segments, but we also have the add the lazy value for the entire segment length
    for (; i > 1; i >>= 1)
      tree[i >> 1] = tree[i] + tree[i ^ 1] + lazy[i >> 1] * sz[i >> 1];
  }

  public int query(int lo, int hi) {
    int sum = 0;
    for (lo += size, hi += size; lo <= hi; lo = (lo + 1) >> 1, hi = (hi - 1) >> 1) {
      // if the left child is even, it is contained in a segment higher above, so we can ignore it
      if ((lo & 1) > 0) {
        sum += getValue(lo);
      }
      // if the right child is odd, it is contained in a segment higher above, so we can ignore it
      if ((hi & 1) == 0) {
        sum += getValue(hi);
      }
    }
    return sum;
  }

  public int getValue(int i) {
    // a lower segment might be modified by a upper segment, so we add the lazy values of the upper segments but traversing up the tree
    int res = tree[i];
    for (int j = i >> 1; j > 0; j >>= 1)
      res += lazy[j] * sz[i];
    return res;
  }
}
