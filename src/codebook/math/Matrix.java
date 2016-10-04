package codebook.math;

import java.util.*;
import java.io.*;

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
}

