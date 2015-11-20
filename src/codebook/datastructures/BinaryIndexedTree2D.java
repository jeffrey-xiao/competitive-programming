/*
 * A 2D implementation of a binary indexed tree.
 *
 * Time complexity:
 *  - Update: O((log N)^2)
 *  - Query: O((log N)^2)
 */

package codebook.datastructures;

public class BinaryIndexedTree2D {
	private int[][] tree1, tree2, tree3, tree4;
	private int rows, columns;

	BinaryIndexedTree2D (int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.tree1 = new int[rows][columns];
		this.tree2 = new int[rows][columns];
		this.tree3 = new int[rows][columns];
		this.tree4 = new int[rows][columns];
	}

	// point update and range query
	/*
	public void update (int idx, int idy, int val) { 
		for (int x = idx; x < rows; x += (x & -x)) 
			for (int y = idy; y < columns; y += (y & -y))
				tree1[x][y] += val; 
	} 
	public int query (int x1, int y1, int x2, int y2) {
		return query(x2, y2) + query(x1-1, y1-1) - query(x2, y1-1) - query(x1-1, y2); 
	} 
	public int query (int idx, int idy) { 
		int sum = 0; 
		for (int x = idx; x > 0; x -= (x & -x)) 
			for (int y = idy; y > 0; y -= (y & -y)) 
				sum += tree1[x][y]; 
		return sum; 
	}
	*/
	// range update and range query

	private void update (int[][] tree, int idx, int idy, int val) {
		for (int x = idx; x < rows; x += (x & -x))
			for (int y = idy; y < columns; y += (y & -y))
				tree[x][y] += val;
	}

	private void auxUpdate (int idx, int idy, int val) {
		update(tree1, 1, 1, val);
		update(tree1, 1, idy + 1, -val);
		update(tree2, 1, idy + 1, val * idy);
		update(tree1, idx + 1, 1, -val);
		update(tree3, idx + 1, 1, val * idx);
		update(tree1, idx + 1, idy + 1, val);
		update(tree2, idx + 1, idy + 1, -val * idy);
		update(tree3, idx + 1, idy + 1, -val * idx);
		update(tree4, idx + 1, idy + 1, val * idx * idy);
	}

	public void update (int x1, int y1, int x2, int y2, int val) {
		auxUpdate(x2 + 1, y2 + 1, val);
		auxUpdate(x1, y2 + 1, -val);
		auxUpdate(x2 + 1, y1, -val);
		auxUpdate(x1, y1, val);
	}

	public void update (int x, int y, int val) {
		update(x, y, x, y, val);
	}

	public int query (int idx, int idy) {
		idx++;
		idy++;
		int s1 = 0, s2 = 0, s3 = 0, s4 = 0;
		for (int x = idx; x > 0; x -= (x & -x)) {
			for (int y = idy; y > 0; y -= (y & -y)) {
				s1 += tree1[x][y];
				s2 += tree2[x][y];
				s3 += tree3[x][y];
				s4 += tree4[x][y];
			}
		}
		return s1 * idx * idy + s2 * idx + s3 * idy + s4;
	}

}
