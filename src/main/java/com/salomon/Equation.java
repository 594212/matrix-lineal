package com.salomon;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Equation {

    public static void main(String[] args) {

        write(30);

    }

    public static StringBuilder amount() {
        StringBuilder temp = new StringBuilder("x");
        Random random = new Random();
        temp = addCoefficient(temp);
        temp = types(temp);
        int chance = random.nextInt(100);
        if (chance > 70) {
            temp = addCoefficient(temp);
            temp = types(temp);
        }
        return temp;
    }

    public static StringBuilder types(StringBuilder sb) {

        Random random = new Random();
        int x = random.nextInt(115);
        if (x < 18) {
            return sb
                    .insert(0, "\\sin{(")
                    .append(")}");
        } else if (x < 36) {
            return sb
                    .insert(0, "\\cos{(")
                    .append(")}");
        } else if (x < 54) {
            return sb
                    .insert(0, "\\tan{(")
                    .append(")}");
        } else if (x < 72) {
            return sb
                    .insert(0, "\\cot{(")
                    .append(")}");
        } else if (x < 90) {
            return sb
                    .insert(0, "\\sqrt{")
                    .append("}");
        } else if (x < 108) {
            return sb
                    .insert(0, "{(")
                    .append(")}^" + (random.nextInt(2) + 2));
        } else if (x < 110) {
            return sb
                    .insert(0, "\\arcsin{(")
                    .append(")}");
        } else if (x < 112) {
            return sb
                    .insert(0, "\\arccos{(")
                    .append(")}");
        } else if (x < 114) {
            return sb
                    .insert(0, "\\arctan{(")
                    .append(")}");
        } else {
            return sb;
        }
    }

    public static StringBuilder addCoefficient(StringBuilder func) {
        Random random = new Random();
        int coeff = random.nextInt(12) - 3;
        String str = "";
        switch (coeff) {
            case 0:
            case 1:
                return func;
            case -1:
                return func.insert(0, "-");

            default:
                return func.insert(0, coeff);

        }
    }

    public static StringBuilder createSum() {
        Random random = new Random();
        int k = random.nextInt(3);

        StringBuilder sum = new StringBuilder();
        sum.append(amount());
        for (int i = 0; i < k; i++) {
            StringBuilder temp = amount();
            sum
                    .append(" + ")
                    .append(temp);
        }
        return sum;
    }

    public static StringBuilder createMultiplication() {
        Random random = new Random();
        StringBuilder multi = new StringBuilder();
        multi.append(amount());
        for (int i = 0; i < 1; i++) {
            StringBuilder temp = amount();
            multi
                    .append(" \\cdot ")
                    .append(temp);
        }
        return multi;
    }

    public static void write(int number) {
        try (FileOutputStream out = new FileOutputStream("file.tex")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < number; i++) {
                sb.append("\\item ");
                {
                    StringBuilder temp;
                    temp = new StringBuilder();
                    temp = temp
                            .append("\\item $")
                            .append(createSum())
                            .append("$\n")
                            .append("\\item $")
                            .append(createSum())
                            .append("$\n")
                            .append("\\item $")
                            .append(createMultiplication())
                            .append("$\n")
                            .append("\\item $")
                            .append(createSum())
                            .append("$\n")
                            .append("\\item $")
                            .append(createMultiplication())
                            .append("$\n");
                    Writer.addBrackets(temp, "itemize");
                    sb.append(temp);
                }
            }
            sb = Writer.addBrackets(sb, "enumerate");
            out.write(sb
                    .toString()
                    .getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}