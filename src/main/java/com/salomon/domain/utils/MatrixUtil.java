package com.salomon.domain.utils;

import java.util.Random;

public class MatrixUtil {

    private final static Random random = new Random();

    static int[][] minor(int[][] mtrx, int row, int col) {
        int n = mtrx.length;
        int m = mtrx[0].length;

        int[][] mx = new int[n - 1][m - 1];

        for (int i = 0; i < row - 1; i++) {
            System.arraycopy(mtrx[i], 0, mx[i], 0, col - 1);
            System.arraycopy(mtrx[i], col, mx[i], col - 1, m - col);
        }

        for (int i = row; i < n; i++) {
            System.arraycopy(mtrx[i], 0, mx[i - 1], 0, col - 1);
            System.arraycopy(mtrx[i], col, mx[i - 1], col - 1, m - col);
        }
        return mx;
    }

    static int[][] identity(int n, int m) {
        int[][] mtrx = new int[n][m];
        int min = Math.min(n, m);
        for (int i = 0; i < min; i++) {
            mtrx[i][i] = 1;
        }
        return mtrx;
    }

    static int[][] diagonal(int n, int m) {
        int[][] mtrx = new int[n][m];
        int min = Math.min(n, m);
        for (int i = 0; i < min; i++) {
            mtrx[i][i] = r1() + 3;
            if (random.nextBoolean()) mtrx[i][i] = -mtrx[i][i];
        }
        return mtrx;
    }

    static int[][] triangle(int n, int m) {
        int[][] mtrx = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < m; j++)
                mtrx[i][j] = r1();
        }

        return mtrx;
    }

    static int[][] screwRow(int[][] mtrx) {
        int n = mtrx.length;
        int m = mtrx[0].length;

        for (int row = 0; row < n; row++) {
            for (int rowb = 0; rowb < row; rowb++) {
                int r = r();
                for (int col = 0; col < m; col++) {
                    mtrx[row][col] += r * mtrx[rowb][col];
                }
            }

            for (int rowb = row + 1; rowb < n; rowb++) {
                int r = r();
                for (int col = 0; col < m; col++) {
                    mtrx[row][col] += r * mtrx[rowb][col];
                }
            }

        }
        return mtrx;
    }

    static int[][] screwCol(int[][] mtrx) {
        int n = mtrx.length;
        int m = mtrx[0].length;

        for (int col = 0; col < m; col++) {
            for (int colb = 0; colb < col; colb++) {
                int r = r();
                for (int row = 0; row < n; row++) {
                    mtrx[row][col] += r * mtrx[row][colb];
                }
            }

            for (int colb = col + 1; colb < m; colb++) {
                int r = r();
                for (int row = 0; row < n; row++) {
                    mtrx[row][col] += r * mtrx[row][colb];
                }
            }

        }
        return mtrx;
    }

    static int[][] zeroRow(int[][] mx, int raw) {

        int n = mx.length;
        int m = mx[0].length;

        for (int i = 0; i < m; i++) {
            mx[raw - 1][i] = 0;
        }

        return mx;
    }

    public static int[][] multiply(int[][] mx, int[][] mx2) {
        int[][] result = new int[mx.length][mx2[0].length];
        for (int raw = 0; raw < mx.length; raw++) {
            for (int col=0; col < mx2[0].length; col++) {
                for (int el =0; el< mx[0].length; el++) {
                    result[raw][col] += mx[raw][el]*mx2[el][col];
                }
            }
        }
        return result;
    }

    public static String toString(int[][] mtrx) {
        int n = mtrx.length;
        int m = mtrx[0].length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(mtrx[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static int r() {
        return random.nextInt(3) - 2;
    }

    private static int r1() {
        int temp = random.nextInt(4) - 2;
        if (temp == 0) return r1();
        else return temp;
    }

    static class Square {

        //recursive
        static int determinant(int[][] mtrx) {

            int n = mtrx.length;
            if (n == 2) {
                return mtrx[0][0] * mtrx[1][1] - mtrx[0][1] * mtrx[1][0];
            }

            int sum = 0;
            int p = -1;
            for (int row = 0; row < n; row++) {
                p = p * (-1);
                sum += p * mtrx[row][0] * determinant(minor(mtrx, row + 1, 1));
            }
            return sum;
        }


        static int[][] algebraic(int[][] mtrx) {

            int n = mtrx.length;

            int[][] mx = new int[n][n];
            int p = -1;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    p = p * (-1);
                    mx[col][row] = p * determinant(minor(mtrx, row + 1, col + 1));
                }
            }

            return mx;
        }

    }


}
