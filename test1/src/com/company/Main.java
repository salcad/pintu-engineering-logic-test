package com.company;

import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] pricesStr;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            pricesStr = br.readLine().split(" ");
        }
        int[] priceArr;
        priceArr = Arrays.stream(pricesStr).mapToInt(Integer::parseInt).toArray();
        System.out.printf("The maximum profit Jacky can make is: %d%n", findMaximumProfit(priceArr));
    }

    public static int findMaximumProfit(int[] prices) {
        int maxProfit = 0;
        int minPriceIdx = 0;
        int buyAtIdx;

        for (int i = 1; i < prices.length; i++) {
            if ((prices[i] - prices[minPriceIdx]) > maxProfit) {
                buyAtIdx = minPriceIdx;
                maxProfit = prices[i] - prices[buyAtIdx];
            }
            if (prices[i] < prices[minPriceIdx]) {
                minPriceIdx = i;
            }
        }
        return maxProfit;
    }
}
