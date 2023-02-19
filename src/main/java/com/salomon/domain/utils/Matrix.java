package com.salomon.domain.utils;

public class Matrix {
    protected int[][] matrix;

    protected Matrix(int[][] matrix) {
        int temp = matrix[0].length;
        for (int[] col : matrix) {
            if (col.length != temp) throw new IllegalArgumentException("matrix size don't match");
        }
        this.matrix = matrix;
    }

    public static Matrix triangle(int n, int m) {
        return new Matrix(MatrixUtil.triangle(n, m));
    }

    public static Matrix identity(int n, int m) {
        return new Matrix(MatrixUtil.identity(n, m));
    }

    public static Matrix diagonal(int n, int m) {
        return new Matrix(MatrixUtil.diagonal(n, m));
    }

    public Matrix screwRow() {
        this.matrix = MatrixUtil.screwRow(matrix);
        return this;
    }

    public Matrix screwCol() {
        this.matrix = MatrixUtil.screwCol(matrix);
        return this;
    }

    public void zeroRow(int row) {
        this.matrix = MatrixUtil.zeroRow(matrix, row);
    }

    public void minor(int row, int col) {
        this.matrix = MatrixUtil.minor(matrix, row, col);
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "matrix=\n" + MatrixUtil.toString(matrix) +
                '}';
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
