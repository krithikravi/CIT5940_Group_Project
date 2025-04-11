package edu.upenn.cit594.ui;

import java.util.Scanner;

public class UserInterface {
	
	int input;
	Scanner scanner = new Scanner(System.in);
	
	public void runProgram() {
		while((input=scanner.nextInt())!=0) {
			this.menuContent();
			System.out.println("> ");
			System.out.flush();
			switch(input) {
			case 0: 
				return;
//				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			default:
				System.err.println("Invalid input. Please try again");
				continue;
				


			}
		}
	}

	public void menuContent() {
		System.out.println("0. Exit the program.");
		System.out.println("1. Show the available actions.");
		System.out.println("2. Show the total population for all ZIP Codes.");
		System.out.println("3. Show the total vaccinations per capita for each ZIP Code for the specified date.");
		System.out.println("4. Show the average market value for properties in a specified ZIP Code.");
		System.out.println("5. Show the average total livable area for properties in a specified ZIP Code.");
		System.out.println("6. Show the total market value of properties, per capita for a specified ZIP Code.");
		System.out.println("7. Show the results of your custom feature.");
	}

}
