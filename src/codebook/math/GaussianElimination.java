package codebook.math;

import java.util.*;

public class GaussianElimination {

	private static final double EPS = 1e-12;

	/*
	 * Reduces the matrix A where Ax = B into a RREF and returns a unique solution
	 * Input: A[][]: 	Mx(N + 1) matrix that represents M equations with N unknowns where the last column is the column vector B
	 *
	 * Returns: A valid unique solution or null if there is no unique solution (I.E. no solution
	 * 			or multiple solutions).
	 */
	public static double[] solve (double[][] A) {
		int M = A.length;
		int N = A[0].length - 1;

		if (N > M)
			return null;

		// forward elimination
		for (int i = 0; i < N; i++) {
			// finding the max value in column i and swapping that row to index i
			int maxIndex = i;
			for (int j = i + 1; j < M; j++)
				if (Math.abs(A[j][i]) > Math.abs(A[maxIndex][i]))
					maxIndex = j;

			if (Math.abs(A[maxIndex][i]) < EPS)
				return null;

			double[] tempA = A[i];
			A[i] = A[maxIndex];
			A[maxIndex] = tempA;

			// ensuring that it's positive
			if (A[i][i] < 0) {
				for (int j = i; j <= N; j++)
					A[i][j] *= -1;
			}

			// eliminating zeroes in rows bigger than i
			for (int j = i + 1; j < N; j++) {
				double factor = A[j][i] / A[i][i];
				for (int k = i; k <= N; k++) 
					A[j][k] -= A[i][k] * factor;
			}
		}

		for (int i = N; i < M; i++)
			if (Math.abs(A[i][N]) > EPS)
				return null;

		// backward elimination
		for (int i = N - 1; i >= 0; i--) {
			if (Math.abs(A[i][i]) < EPS)
				return null;

			// eliminating zeroes in rows smaller than i
			for (int j = i - 1; j >= 0; j--) {
				double factor = A[j][i] / A[i][i];
				for (int k = i; k <= N; k++)
					A[j][k] -= A[i][k] * factor;
			}
		}

		// dividing to create leading ones
		for (int i = 0; i < N; i++) {
			A[i][N] /= A[i][i];
			A[i][i] /= A[i][i];
		}

		double[] ret = new double[M];
		
		for (int i = 0; i < M; i++)
			ret[i] = A[i][N];
		
		return ret;
	}
	
	public static Double determinant (double[][] A) {
		int M = A.length;
		int N = A[0].length;
		double det = 1;
		
		// forward elimination
		for (int i = 0; i < N; i++) {
			// finding the max value in column i and swapping that row to index i
			int maxIndex = i;
			for (int j = i + 1; j < M; j++)
				if (Math.abs(A[j][i]) > Math.abs(A[maxIndex][i]))
					maxIndex = j;

			if (Math.abs(A[maxIndex][i]) < EPS)
				return null;

			double[] tempA = A[i];
			A[i] = A[maxIndex];
			A[maxIndex] = tempA;

			if (maxIndex != i)
				det *= -1;
			
			// ensuring that it's positive
			if (A[i][i] < 0) {
				for (int j = i; j < N; j++)
					A[i][j] *= -1;
				det *= -1;
			}

			// eliminating zeroes in rows bigger than i
			for (int j = i + 1; j < N; j++) {
				double factor = A[j][i] / A[i][i];
				for (int k = i; k < N; k++) 
					A[j][k] -= A[i][k] * factor;
			}
		}

		// backward elimination
		for (int i = N - 1; i >= 0; i--) {
			if (Math.abs(A[i][i]) < EPS)
				return null;

			// eliminating zeroes in rows smaller than ix	
			for (int j = i - 1; j >= 0; j--) {
				double factor = A[j][i] / A[i][i];
				for (int k = i; k < N; k++)
					A[j][k] -= A[i][k] * factor;
			}
		}

		// dividing to create leading ones
		for (int i = 0; i < N; i++) {
			det *= A[i][i];
			A[i][i] = 1;
		}

		return det;
	}

	public static void main (String[] args) {
		double[][] A;

		A = new double[][] {
				{25, 5, 1, 106.8},
				{64, 8, 1, 177.2},
				{144, 12, 1, 279.2}
		};

		// [0.29047619047619005, 19.690476190476193, 1.0857142857142679]
		System.out.println(Arrays.toString(solve(A)));

		A = new double[][] {
				{3, 2, 2, 15},
				{0, 4, 1, 6},
				{1, 1, 3, 19}
		};

		// [1.0, 0.0, 6.0]
		System.out.println(Arrays.toString(solve(A)));
		
		// 8
		System.out.println(determinant(new double[][]{
			{-1, 3},
			{-5, 7}
		}));
		
		// 2457
		System.out.println(determinant(new double[][]{
			{1, 3, 5, 2, 4, 6},
			{2, 5, 4, 3, 1, 6},
			{6, 1, 2, 3, 4, 5},
			{2, 5, 1, 3, 6, 4},
			{4, 5, 1, 2, 3, 6},
			{5, 4, 3, 6, 1, 2}
		}));
	}
}

