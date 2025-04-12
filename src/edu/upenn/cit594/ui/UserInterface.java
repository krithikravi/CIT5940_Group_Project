package edu.upenn.cit594.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.CSVReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.processor.AllZipCodes;

public class UserInterface {
	
	int input = Integer.MAX_VALUE;
	Scanner scanner = new Scanner(System.in);
	String[] fileName = new String[2];
	
	public void runProgram(String[] args) {
		while(input!=0) {
			this.menuContent();
			System.out.println("> ");
			System.out.flush();
			input=scanner.nextInt();
			switch(input) {
			case 0: 
				return;
//				break;
			case 1:
				break;
			case 2:
				for (int i = 0; i<args.length; i++) {
					if (args[i].startsWith("--population=")) {
						fileName = args[i].split("=");
//						and then here send data to processing where you would get the total population
						try {
//							See how to use util package classes and functions
							Reader reader = Reader.getReader(fileName[1], new HashMap<>());
							AllZipCodes allZipCodes = new AllZipCodes(reader.read());
							allZipCodes.totalPopulation(); // want to print this to fileName
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case 3:
				System.out.println("Please enter either \"partial\" or \"full\" >");
				String input3 = scanner.next();
				while (!(input3.equals("partial") || input3.equals("full"))){
					System.out.print("Please enter a valid option: partial or full>");
					input3 = scanner.next();
 
				}
				System.out.print("Please enter a date in the format: YYY-MM-DD");
				String input2 = scanner.next();
				String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
			    Pattern pattern = Pattern.compile(regex);
				while (!(pattern.matcher(input2).find())){
					System.out.print("Please enter a valid date: YYY-MM-DD  >");
					input2 = scanner.next();
 
				}
				//rest of 3.3
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
