package edu.upenn.cit594.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.CSVReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.processor.AllZipCodes;
import edu.upenn.cit594.processor.SingleZipCode;
import edu.upenn.cit594.util.Area;

public class UserInterface {
	HashMap<Integer, Area> hashMap;
	SingleZipCode singleZipCode;
	AllZipCodes allZipCodes;
	
	public UserInterface(HashMap<Integer, Area> inputHashMap) {
		this.hashMap = inputHashMap;
	}
	
	int input = Integer.MAX_VALUE;
	Scanner scanner = new Scanner(System.in);
	String[] fileName = new String[2];
	
	public void runProgram() {
		while(input!=0) {
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//where/how is the output being logged?
			this.menuContent();
			System.out.println();
			System.out.print("> ");
			System.out.flush();
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//need to use input class and method, no scanner allowed here
			input=scanner.nextInt();
			switch(input) {
			case 0: 
				return;
//				break;
			case 1:
				break;
			case 2:
//				// here ask about how to print to a file instead of console
//				for (int i = 0; i<args.length; i++) {
//					if (args[i].startsWith("--population=")) {
//						fileName = args[i].split("=");
////						and then here send data to processing where you would get the total population
//						try {
////							See how to use util package classes and functions
//							Reader reader = Reader.getReader(fileName[1], new HashMap<>());
//							AllZipCodes allZipCodes = new AllZipCodes(reader.read());
//							allZipCodes.totalPopulation(); // want to print this to fileName
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
				
				System.out.println("BEGIN OUTPUT");
				allZipCodes = new AllZipCodes(this.hashMap);
				allZipCodes.totalPopulation();
				System.out.println("END OUTPUT");
				break;
			case 3:
//				// This seems to already run in AllZipCodes function
//				System.out.println("Please enter either \"partial\" or \"full\" >");
//				String input3 = scanner.next();
//				while (!(input3.equals("partial") || input3.equals("full"))){
//					System.out.print("Please enter a valid option: partial or full > ");
//					input3 = scanner.next();
// 
//				}
//				System.out.print("Please enter a date in the format: YYYY-MM-DD > ");
//				String input2 = scanner.next();
//				String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
//			    Pattern pattern = Pattern.compile(regex);
//				while (!(pattern.matcher(input2).find())){
//					System.out.print("Please enter a valid date: YYYY-MM-DD  >");
//					input2 = scanner.next();
// 
//				}
				
//				for (int i = 0; i<args.length; i++) {
//					if (args[i].startsWith("--covid=")) {
//						fileName = args[i].split("=");
////						and then here send data to processing where you would get the total population
//						try {
////							See how to use util package classes and functions
//							Reader reader = Reader.getReader(fileName[1], new HashMap<>());
//							AllZipCodes allZipCodes = new AllZipCodes(reader.read());
//							allZipCodes.totalVaccinations();// want to print this to fileName
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
				//rest of 3.3
				
//				System.out.println("BEGIN OUTPUT");
				allZipCodes = new AllZipCodes(this.hashMap);
				allZipCodes.totalVaccinations();
//				System.out.println("END OUTPUT");
				break;
			case 4:
//				System.out.println("Please enter a 5 digit zip code >");
//				String input4 = scanner.next();
//				String regex2 = "^\\d{5}$";
//			    Pattern pattern2 = Pattern.compile(regex2);
//				while (!(pattern2.matcher(input4).find())){
//					System.out.print("Please enter a valid 5 digit zip code  > ");
//					input4 = scanner.next();
// 
//				}
				
//				for (int i = 0; i<args.length; i++) {
//					if (args[i].startsWith("--properties=")) {
//						fileName = args[i].split("=");
////						and then here send data to processing where you would get the total population
//						try {
////							See how to use util package classes and functions
//							Reader reader = Reader.getReader(fileName[1], new HashMap<>());
//							SingleZipCode singleZipCode = new SingleZipCode(reader.read());
//							singleZipCode.averageMarketValue(null);// not sure what input should be to averageMarketValue function
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//				System.out.println("BEGIN OUTPUT");
				this.singleZipCode = new SingleZipCode(this.hashMap);
				this.singleZipCode.runOperation(4);
//				System.out.println("END OUTPUT");
				break;
			case 5:
//				// Ask about what part of code you want to call in util vs processor
//				System.out.println("Please enter a 5 digit zip code >");
//				String input5 = scanner.next();
//				String regex3 = "^\\d{5}$";
//			    Pattern pattern3 = Pattern.compile(regex3);
//				while (!(pattern3.matcher(input5).find())){
//					System.out.print("Please enter a valid 5 digit zip code  > ");
//					input5 = scanner.next();
//				}
//				Area area = new Area(Integer.parseInt(input5));
//				System.out.println("BEGIN OUTPUT");
//				System.out.println(area.getTotalLivableArea()/area.getPopulation());
//				System.out.println("END OUTPUT");
				
//				System.out.println("BEGIN OUTPUT");
				this.singleZipCode = new SingleZipCode(this.hashMap);
				this.singleZipCode.runOperation(5);
//				System.out.println("BEGIN OUTPUT");
				break;
			case 6:
//				System.out.println("Please enter a 5 digit zip code >");
//				String input6 = scanner.next();
//				String regex4 = "^\\d{5}$";
//			    Pattern pattern4 = Pattern.compile(regex4);
//				while (!(pattern4.matcher(input6).find())){
//					System.out.print("Please enter a valid 5 digit zip code  > ");
//					input6 = scanner.next();
//				}
//				Area area2 = new Area(Integer.parseInt(input6));
//				System.out.println("BEGIN OUTPUT");
//				System.out.println(area2.getTotalMarketValue()/area2.getPopulation());
//				System.out.println("END OUTPUT");
				
//				System.out.println("BEGIN OUTPUT");
				this.singleZipCode = new SingleZipCode(this.hashMap);
				this.singleZipCode.runOperation(6);
//				System.out.println("END OUTPUT");
				break;
//				break;
			case 7:
//				System.out.println("BEGIN OUTPUT");
				this.singleZipCode = new SingleZipCode(this.hashMap);
				this.singleZipCode.runOperation(7);
//				System.out.println("END OUTPUT");
				break;
//				break;
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
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//need to change this to showing the total market value per capita divided by the number of full vaccinations for a specific zip code 
		System.out.println("7. Show the results of your custom feature.");
	}

}
