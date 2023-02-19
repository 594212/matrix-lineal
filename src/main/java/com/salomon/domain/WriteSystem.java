package com.salomon.domain;

import com.salomon.Writer;
import com.salomon.domain.utils.Matrix;
import com.salomon.domain.utils.MatrixUtil;

import static com.salomon.domain.utils.LinealSystemUtil.column;

public class WriteSystem {

    public static String[] convert(int[] mx) {
        int n = mx.length;
        String[] cv = new String[n];
        for (int i = 0; i < n; i++) {
            switch (mx[i]) {
                case 0:
                    cv[i] = "";
                    break;
                case 1:
                    cv[i] = "x_" + (i + 1);
                    break;
                case -1:
                    cv[i] = "-x_" + (i + 1);
                    break;
                default:
                    cv[i] = mx[i] + "x_" + (i + 1);
            }
        }
        return cv;
    }


    public static String toString(int[] arr) {
        String[] cv = convert(arr);
        int n = arr.length;
        StringBuilder sb = new StringBuilder();
        int first = first(arr);
        if (first == -1) sb.append("0");
        else {
            for (int j = first; j < n; j++) {
                if (j != first && arr[j] > 0) sb.append("+");
                sb.append(cv[j]);
            }
        }
        return sb.toString();
    }

    public static int first(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) return i;
        }
        return -1;
    }


    public static StringBuilder generate() {
        int[][] matrix = Matrix
                .identity(3, 3)
                .screwCol()
                .screwRow()
                .getMatrix();
        int[][] column = column(3);
        int[][] term = MatrixUtil.multiply(matrix, column);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb
                    .append(toString(matrix[i]))
                    .append(" = ")
                    .append(term[i][0])
                    .append("\\\\ \n");
        }
        sb = Writer.addBrackets(sb, "matrix");
        sb.insert(0, "\\left\\{ \n");
        return sb.append("\\right. \n");
    }

    public static void main(String[] args) {
        Writer.write(WriteSystem::generate,30,3);
    }
}