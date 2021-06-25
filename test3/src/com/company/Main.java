package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int MAX = 1000001;
    static int factor[];

    // generate all prime factors of numbers from 1 to 1 million
    static void genPrimeFactor()
    {
        factor[1] = 1;

        for (int i = 2; i < MAX; i++)
            factor[i] = i;

        for (int i = 4; i < MAX; i += 2)
            factor[i] = 2;

        for (int i = 3; i * i < MAX; i++) {
            if (factor[i] == i) {
                for (int j = i * i; j < MAX; j += i) {
                    if (factor[j] == j)
                        factor[j] = i;
                }
            }
        }
    }

    static int calcNumsOfFactors(int n)
    {
        if (n == 1)
            return 1;

        int result = 1;
        int smallestPrimeNumberThatDividesN = factor[n];
        int countNumsOfPrimesDividesN = 1;
        int j = n / factor[n];

        while (j != 1) {
            if (factor[j] == smallestPrimeNumberThatDividesN)
                countNumsOfPrimesDividesN += 1;
            else {
                smallestPrimeNumberThatDividesN = factor[j];
                result = result * (countNumsOfPrimesDividesN + 1);
                countNumsOfPrimesDividesN = 1;
            }

            j = j / factor[j];
        }

        result = result * (countNumsOfPrimesDividesN + 1);
        return result;
    }

    private static int getTotCountOfNumberWith6Factor(int input) {
        int totCountOfNumberWith6Factor = 0;
        for (int i=0; i<input; i++) {
            if (calcNumsOfFactors(i+1) == 6) {
                totCountOfNumberWith6Factor++;
            }
        }
        return totCountOfNumberWith6Factor;
    }

    public static void main(String[] args)
    {
        String strN = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            strN = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        factor = new int[MAX];
        factor[0] = 0;
        genPrimeFactor();

        System.out.printf("Total angka yang tidak melebihi n dan dengan tepat memiliki 6 faktor: %d%n", getTotCountOfNumberWith6Factor(Integer.parseInt(strN)));
    }

}