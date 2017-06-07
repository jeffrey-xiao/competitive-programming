package codebook.math;

public class Matrix {
  int r, c;
  int[][] mat;

  public Matrix (int r, int c) {
    this(r, c, new int[r][c]);
  }

  public Matrix (int r, int c, int[][] mat) {
    this.r = r;
    this.c = c;
    this.mat = mat;
  }

  public Matrix add (Matrix a, Matrix b) throws Exception {
    if (a.r != b.r || a.c != b.c)
      throw new IllegalArgumentException();
    Matrix ret = new Matrix(a.r, a.c);
    for (int i = 0; i < a.r; i++)
      for (int j = 0; j < a.c; j++)
        ret.mat[i][j] = a.mat[i][j] + b.mat[i][j];
    return ret;
  }

  public Matrix subtract (Matrix a, Matrix b) throws Exception {
    if (a.r != b.r || a.c != b.c)
      throw new IllegalArgumentException();
    Matrix ret = new Matrix(a.r, a.c);
    for (int i = 0; i < a.r; i++)
      for (int j = 0; j < a.c; j++)
        ret.mat[i][j] = a.mat[i][j] - b.mat[i][j];
    return ret;
  }

  public Matrix multiply (Matrix a, Matrix b) throws Exception {
    if (a.c != b.r)
      throw new IllegalArgumentException();
    Matrix ret = new Matrix(a.r, b.c);
    for (int i = 0; i < a.r; i++)
      for (int j = 0; j < b.c; j++)
        for (int k = 0; k < a.c; k++)
          ret.mat[i][j] += a.mat[i][k] * b.mat[k][j];
    return ret;
  }
}
