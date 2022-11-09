package com.salomon.domain.utils;

public class Square extends Matrix {

    public Square(int[][] matrix) {
        super(matrix);
        if (matrix[0].length != matrix.length) throw new IllegalArgumentException("matrix are not square");
    }


    public int det() {
        return MatrixUtil.Square.determinant(matrix);
    }

    public static Square triangle(int n) {
        return new Square(MatrixUtil.triangle(n, n));
    }

    public static Square identity(int n) {
        return new Square(MatrixUtil.identity(n, n));
    }

    public static Square diagonal(int n) {
        return new Square(MatrixUtil.diagonal(n, n));
    }

    public void algebraic() {
        this.matrix = MatrixUtil.Square.algebraic(matrix);
    }

}
