package codebook.datastructures;
public class DisjointSetUnion {
	private int[] id;

	public DisjointSetUnion (int n) {
		id = new int[n];
		for (int x = 0; x < n; x++)
			id[x] = x;
	}

	public boolean find (int x, int y) {
		return id[x] == id[y];
	}

	public void union (int x, int y) {
		int idx = id[x];
		int idy = id[y];
		for (int z = 0; z < id.length; z++)
			if (id[z] == idx)
				id[z] = idy;
	}

	public void print () {
		for (int i : id)
			System.out.print(i + " ");
		System.out.println();
	}
}
