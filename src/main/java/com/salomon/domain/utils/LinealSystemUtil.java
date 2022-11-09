package com.salomon.domain.utils;


import java.util.Arrays;
import java.util.Random;

public class LinealSystemUtil {

    static int lengthPow2(int[] array) {
        int s = 0;
        for (int i : array) {
            s += i * i;
        }
        return s;
    }

    static int scalarMultiply(int[] array1, int[] array2) {
        int s = 0;
        for (int i = 0; i < array1.length; i++) {
            s += array1[i] * array2[i];
        }
        return s;
    }

    static int[] terms(int m) {
        Random random = new Random();
        int[] temp = new int[m];
        for (int i = 0; i < m; i++) {
            temp[i] = random.nextInt(25) - 12;
        }
        return temp;
    }

    static int[][] steptal(int[][] matrix) {

        int[][] temp = matrix.clone();
        int n = matrix.length;
        int m = matrix[0].length;


        for (int col = 0; col < n; col++) {
            for (int row = col + 1; row < n; row++) {
                if (temp[col][col] == 0) {
                    int[] cash = temp[row].clone();
                    System.arraycopy(temp[col], 0, temp[row], 0, n);
                    System.arraycopy(cash, 0, temp[col], 0, n);
                } else if (temp[row][col] != 0) {
                    temp[row] = substract(temp[row], temp[col], col);
                }

            }
        }

        return temp;
    }

    static int[][] rotateRow(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] temp = new int[n][m];
        for (int row = 0; row < n; row++) {
            temp[row] = matrix[n - row - 1];
        }
        return temp;
    }

    private static int[] substract(int[] row1, int[] row2, int index) {
        int coef1 = row1[index];
        int coef2 = row2[index];
        int n = row1.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = coef2 * row1[i] - coef1 * row2[i];
        }

        return temp;
    }


    static int[][] addTerm(int[] term, int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] temp = new int[n][m + 1];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                temp[i][j] = matrix[i][j];
            }
            temp[j][m] = term[j];
        }
        return temp;

    }

    public static void main(String[] args) {
        int[][] matrix = Matrix.triangle(3, 3).screwCol().screwRow().matrix;
        System.out.println(MatrixUtil.toString(matrix));
        int[] term = terms(3);
        System.out.println(Arrays.toString(term));
        int[][] problem = addTerm(term, matrix);
        System.out.println(MatrixUtil.toString(problem));
        int[][] solve = steptal(problem);
        System.out.println(MatrixUtil.toString(solve));
//        System.out.println(Arrays.toString(simplifyRow(new int[]{0, 0, 10, 770})));;
        int[][] simpl = simplify(solve);
        System.out.println(MatrixUtil.toString(simpl));

    }

    private static int[] simplifyRow(int[] row) {
        int gcd = Arrays.stream(row).max().getAsInt();
        int n = row.length;
        for (int el : row) {
            int gcd1 = gcdByEuclidsAlgorithm(gcd, el);
            if (gcd1 < gcd) gcd = gcd1;
        }

        int[] newRow = new int[n];
        for (int i = 0; i < n; i++) {
            newRow[i] = row[i] / gcd;
        }
        return newRow;
    }

    static int[][] simplify(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp[i] = simplifyRow(matrix[i]);
        }
        return temp;
    }


    static int gcdBySteinsAlgorithm(int n1, int n2) {
        if (n1 == 0) {
            return n2;
        }

        if (n2 == 0) {
            return n1;
        }

        int n;
        for (n = 0; ((n1 | n2) & 1) == 0; n++) {
            n1 >>= 1;
            n2 >>= 1;
        }

        while ((n1 & 1) == 0) {
            n1 >>= 1;
        }

        do {
            while ((n2 & 1) == 0) {
                n2 >>= 1;
            }

            if (n1 > n2) {
                int temp = n1;
                n1 = n2;
                n2 = temp;
            }
            n2 = (n2 - n1);
        } while (n2 != 0);
        return n1 << n;
    }

    static int gcdByBruteForce(int n1, int n2) {
        int gcd = 1;
        for (int i = 1; i <= n1 && i <= n2; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    static int gcdByEuclidsAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcdByEuclidsAlgorithm(n2, n1 % n2);
    }

}
