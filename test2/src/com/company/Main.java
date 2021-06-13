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
        for (char inpChar: input.toCharArray()) {
            if (!occurrenceArr[inpChar - 'a']) {
                result.append(inpChar);
                occurrenceArr[inpChar - 'a'] = true;
            }
        }
        return result.toString();
    }

    public static String smallestLexicographicalOrder(String input) {
        int[] alphabetArr = new int[26];
        boolean[] occurrenceArr = new boolean[26];
        char[] inpCharArr = new char[input.length()];
        int idx = 0;

        for (char inpChar: input.toCharArray()) {
            alphabetArr[inpChar - 'a']++;
        }

        for (char inpChar: input.toCharArray()) {
            alphabetArr[inpChar - 'a']--;

            if (!occurrenceArr[inpChar - 'a']) {
                while ((idx > 0) &&
                        (inpChar < inpCharArr[idx - 1]) &&
                        (alphabetArr[inpCharArr[idx - 1] - 'a'] > 0)) {
                    occurrenceArr[inpCharArr[--idx] - 'a'] = false;
                }
                inpCharArr[idx++] = inpChar;
                occurrenceArr[inpChar - 'a'] = true;
            }
        }
        return new String(inpCharArr, 0, idx);
    }
}
