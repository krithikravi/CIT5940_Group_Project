package edu.upenn.cit594.datamanagement;

import java.util.Scanner;

//Code from 5910 Starter Code
public class Input {
	private static Scanner scanner;          // Scanner for user input

	/**
     * Gets valid integer input from the user. Re-prompts the user if the input is not a valid integer.
     *
     * @return A valid integer input
     */
    private static int getValidIntInput() {
        while (true) { // Infinite loop to keep asking for input until it's valid
            try {
                return Integer.parseInt(scanner.nextLine().trim()); // Try to parse the input as an integer
            } catch (NumberFormatException e) { // Catch invalid integer input
                System.out.print("Please enter a valid number: "); // Re-prompt the user for a valid number
            }
        }
    }

    /**
     * Gets valid string input from the user. Re-prompts if the input is empty.
     *
     * @param prompt The message to display when requesting input
     * @return A valid non-empty string
     */
    public static String getValidStringInput(String prompt) {
        System.out.print(prompt); // Display the prompt message
        String input = scanner.nextLine().trim(); // Get user input and trim any extra spaces
        while (input.isEmpty()) { // Loop if the input is empty
            System.out.print("Input cannot be empty. " + prompt); // Re-prompt the user
            input = scanner.nextLine().trim(); // Get new input from the user
        }
        return input; // Return the valid non-empty string
    }
}
