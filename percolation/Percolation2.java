package percolation;

import java.util.ArrayList;

public class Percolation2 {
	private WeightedQuickUnionUF qf;
	private boolean[][] open;
	private int[] dirx = {0, 0, -1, 1};
	private int[] diry = {-1, 1, 0, 0};
	private int n;
	private ArrayList<Point> points = new ArrayList<Point>();

	static class Point {
		int x;
		int y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public Percolation2 (int n) {
		this.n = n;
		open = new boolean[n][n];
		qf = new WeightedQuickUnionUF(n * n + 1);

		for (int x = 1; x <= n; x++) {
			for (int y = 1; y <= n; y++)
				points.add(new Point(x, y));
			qf.id[x] = 0;
		}
		qf.sz[0] = n;
	}

	// x and y start at 1
	public void open (int x, int y) {
		open[x - 1][y - 1] = true;
		for (int z = 0; z < 4; z++) {
			int adjX = dirx[z] + x;
			int adjY = diry[z] + y;
			if (isOpen(adjX - 1, adjY - 1)
					&& !qf.connected((x - 1) * n + y, (adjX - 1) * n + adjY)) {
				qf.union((x - 1) * n + y, (adjX - 1) * n + adjY);
			}
		}
	}

	private boolean isOpen (int adjX, int adjY) {
		if (adjX < 0 || adjY < 0 || adjX >= n || adjY >= n)
			return false;
		return open[adjX][adjY];
	}

	private boolean percolates () {
		int length = qf.id.length;
		for (int x = length - n; x < length; x++)
			if (qf.find(x) == 0) {
				return true;
			}
		return false;
	}

	public int getOperations () {
		int counter = 0;

		while (!percolates()) {
			int ranIndex = (int) (Math.random() * points.size());
			int ranX = points.get(ranIndex).x;
			int ranY = points.get(ranIndex).y;
			points.remove(ranIndex);
			/*
			 * int ranX = (int)(Math.random()*(p.n)+1); int ranY =
			 * (int)(Math.random()*(p.n)+1); while(p.isOpen(ranX-1, ranY-1)){
			 * ranX = (int)(Math.random()*(p.n)+1); ranY =
			 * (int)(Math.random()*(p.n)+1); }
			 */
			open(ranX, ranY);
			counter++;
		}
		/*
		 * System.out.println(counter); for(int x = 0; x < n; x++){ for(int y =
		 * 0; y < n; y++){ System.out.print(open[x][y]?"1 ":"0 "); }
		 * System.out.println(); }
		 */
		return counter;
	}

	public static void main (String[] args) {
		Percolation2 p = new Percolation2(10);
		int counter = 0;

		while (!p.percolates()) {
			int ranIndex = (int) (Math.random() * p.points.size());
			int ranX = p.points.get(ranIndex).x;
			int ranY = p.points.get(ranIndex).y;
			p.points.remove(ranIndex);
			/*
			 * int ranX = (int)(Math.random()*(p.n)+1); int ranY =
			 * (int)(Math.random()*(p.n)+1); while(p.isOpen(ranX-1, ranY-1)){
			 * ranX = (int)(Math.random()*(p.n)+1); ranY =
			 * (int)(Math.random()*(p.n)+1); }
			 */

			p.open(ranX, ranY);
			counter++;
		}
		System.out.println(counter);
	}
}
