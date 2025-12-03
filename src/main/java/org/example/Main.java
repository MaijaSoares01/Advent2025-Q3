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
                int toRemove = line.length() - K;// Number of digits we can remove (drop) to end with exactly K digits
                for (char c : line.toCharArray()) { // Iterate over each digit in the line
                    // While we can remove digits,
                    // and the last digit in the stack is smaller than the current one,
                    // pop it from the stack to try to make a bigger number
                    while (!stack.isEmpty() && stack.peekLast() < c && toRemove > 0) {
                        stack.removeLast();// remove smaller digit
                        toRemove--;// used one removal
                    }
                    stack.addLast(c);// Push current digit to stack
                }
                // Build the best 12-digit number
                // After processing all digits,
                // the stack may be longer than K because we stopped removing when allowed.
                // So, take only the first K digits for the final number.
                StringBuilder best12 = new StringBuilder();
                int used = 0;
                for (char c : stack) {
                    if (used == K) break;
                    best12.append(c);
                    used++;
                }
                long value = Long.parseLong(best12.toString());// Convert chosen digits to a long number
                totalBatteriesValue2 += value; // Add to total sum of battery joltage
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
