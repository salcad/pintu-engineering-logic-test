package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("firstOccurrenceFromLeftToRight: %s%n", firstOccurrenceFromLeftToRight(str));
        System.out.printf("smallestLexicographicalOrder: %s%n", smallestLexicographicalOrder(str));
    }

    public static String firstOccurrenceFromLeftToRight(String input) {
        StringBuilder result = new StringBuilder();
        boolean[] occurrenceArr = new boolean[26];
        for (char inputChar: input.toCharArray()) {
            if (!occurrenceArr[inputChar - 'a']) {
                result.append(inputChar);
                occurrenceArr[inputChar - 'a'] = true;
            }
        }
        return result.toString();
    }

    public static String smallestLexicographicalOrder(String input) {
        int[] alphabetArr = new int[26];
        boolean[] occurrenceArr = new boolean[26];
        char[] resultCharArr = new char[input.length()];
        int idx = 0;

        for (char inputChar: input.toCharArray()) {
            alphabetArr[inputChar - 'a']++;
        }

        for (char inputChar: input.toCharArray()) {
            alphabetArr[inputChar - 'a']--;

            if (!occurrenceArr[inputChar - 'a']) {
                while ((idx > 0) &&
                        (inputChar < resultCharArr[idx - 1]) &&
                        (alphabetArr[resultCharArr[idx - 1] - 'a'] > 0)) {
                    occurrenceArr[resultCharArr[--idx] - 'a'] = false;
                }
                resultCharArr[idx++] = inputChar;
                occurrenceArr[inputChar - 'a'] = true;
            }
        }
        return new String(resultCharArr, 0, idx);
    }
}
