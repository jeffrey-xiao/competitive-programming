package codebook.math;

public class TriangleCenters {
	
	static class Point {
		double x, y;
		Point (double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static double getDistSq (Point A, Point B) {
		return (A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y);
	}
	
	static double getDist (Point A, Point B) {
		return Math.sqrt(getDist(A, B));
	}
	
	static Point getBarycentric (Point A, Point B, Point C, double a, double b, double c) {
		double x = (A.x * a + B.x * b + C.x * c) / (a + b + c);
		double y = (A.y * a + B.y * b + C.y * c) / (a + b + c);
		return new Point(x, y);
	}
	
	// geometric center of mass
	static Point centroid (Point A, Point B, Point C) {
		return getBarycentric(A, B, C, 1, 1, 1);
	}

	// intersection of perpendicular bisectors
	static Point circumcenter (Point A, Point B, Point C) {
		double a = getDistSq(B, C);
		double b = getDistSq(C, A);
		double c = getDistSq(A, B);
		return getBarycentric(A, B, C, a * (b + c - a), b * (a + c - a), c * (a + b - c));
	}
	
	// intersection of internal angle bisectors
	static Point incenter (Point A, Point B, Point C) {
		return getBarycentric(A, B, C, getDist(B, C), getDist(C, A), getDist(A, B));
	}
	
	// intersection of altitudes
	static Point orthocenter (Point A, Point B, Point C) {
		double a = getDistSq(B, C);
		double b = getDistSq(C, A);
		double c = getDistSq(A, B);
		return getBarycentric(A, B, C, (a + b - c) * (c + a - b), (b + c - a) * (a + b - c), (c + a - b) * (b + c - a));
	}
	// intersection of two external angle bisectors
	static Point excenter (Point A, Point B, Point C) {
		double a = getDist(B, C);
		double b = getDist(C, A);
		double c = getDist(A, B);
		return getBarycentric(A, B, C, -a, b, c);
		// note that there are three excenters:
		// getBarycentric(A, B, C, a, -b, c);
		// getBarycentric(A, B, C, a, b, -c);
	}
}

