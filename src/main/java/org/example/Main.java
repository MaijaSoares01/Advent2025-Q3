package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        long totalBatteriesValue = 0;
        long totalBatteriesValue2 = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/batteries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //PART 1
                int best = 0;
                // For each digit as the first digit
                for (int i = 0; i < line.length() - 1; i++) {
                    int d1 = line.charAt(i) - '0';

                    // Find best second digit to the right
                    for (int j = i + 1; j < line.length(); j++) {
                        int d2 = line.charAt(j) - '0';
                        int value = d1 * 10 + d2;
                        if (value > best) {
                            best = value;
                        }
                    }
                }
                totalBatteriesValue += best;

                //PART 2
                int K = 12; // number of digits to choose
                Deque<Character> stack = new ArrayDeque<>();
                int toRemove = line.length() - K;
                for (char c : line.toCharArray()) {
                    while (!stack.isEmpty()
                            && stack.peekLast() < c
                            && toRemove > 0) {
                        stack.removeLast();
                        toRemove--;
                    }
                    stack.addLast(c);
                }
                // Build the best 12-digit number
                StringBuilder best12 = new StringBuilder();
                int used = 0;
                for (char c : stack) {
                    if (used == K) break;
                    best12.append(c);
                    used++;
                }
                long value = Long.parseLong(best12.toString());
                totalBatteriesValue2 += value;
                }
            //PART 1
            System.out.println("Total Added up Batteries (2): " + totalBatteriesValue);
            //PART 2
            System.out.println("Total Added up Batteries (12): " + totalBatteriesValue2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
