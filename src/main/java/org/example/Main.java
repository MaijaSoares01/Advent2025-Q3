package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        long totalBatteriesValue = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/batteries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
            }
            System.out.println("Total Added up Batteries: " + totalBatteriesValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
