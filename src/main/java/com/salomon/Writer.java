package com.salomon;

import com.salomon.domain.utils.Matrix;

import java.io.FileOutputStream;
import java.util.function.Supplier;

public class Writer {
    public static void main(String[] args) {
        write(Writer::buildMxTex,30,4);
    }

    public static StringBuilder addBrackets(StringBuilder sb, String type) {
        String temp = "\\begin{" + type + "}\n";
        return sb
                .insert(0, temp)
                .append("\\end{")
                .append(type)
                .append("} \n");
    }

    public static StringBuilder addNumber(StringBuilder sb, String number) {
        return sb.insert(0, number + " \\; \n");
    }

    public static void write(Supplier<StringBuilder> func, int raw, int col) {
        try (FileOutputStream out = new FileOutputStream("file.tex")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < raw; i++) {
                sb.append("\\item ");
                StringBuilder oneline = new StringBuilder();

                for (int j = 0; j < col; j++) {
                    StringBuilder temp;

                    temp = func.get();
                    temp = addNumber(temp, "A_{" + (j + 1) + "} \\: :");
                    oneline
                            .append(temp)
                            .append(" & ");
                }
                oneline.append("\\\\ \n");
                oneline = addBrackets(oneline, "matrix");
                sb.append(oneline);
            }
            sb = addBrackets(sb, "enumerate");
            out.write(sb
                    .toString()
                    .getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder buildMxTex() {
        Matrix mx = Matrix
                .identity(3, 3)
                .screwCol()
                .screwRow();
        StringBuilder sb = new StringBuilder();
        int[][] temp = mx.getMatrix();
        for (int[] ints : temp) {
            for (int anInt : ints) {
                sb
                        .append(anInt)
                        .append(" & ");
            }
            sb.append("\\\\ \n");
        }
        sb = addBrackets(sb, "bmatrix");
        return sb;
    }

}
