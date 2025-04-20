package edu.upenn.cit594.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.CSVReader;
import edu.upenn.cit594.datamanagement.Input;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.AllZipCodes;
import edu.upenn.cit594.processor.SingleZipCode;
import edu.upenn.cit594.util.Area;

public class UserInterface {
	HashMap<Integer, Area> hashMap;
	SingleZipCode singleZipCode;
	AllZipCodes allZipCodes;
	
	Logger logger;
	Set argsTraversed;
	Input inputHelper;
	
	boolean printRegularMenu = true;
	
	public UserInterface(HashMap<Integer, Area> inputHashMap, Logger logger, Set argsTraversed, Input inputHelper) {
		this.hashMap = inputHashMap;
		this.logger = logger;
		this.argsTraversed = argsTraversed;
		this.inputHelper=inputHelper;
		singleZipCode = new SingleZipCode(this.hashMap, this.logger, this.inputHelper);
		allZipCodes = new AllZipCodes(this.hashMap, this.logger,this.inputHelper);
		
	}
	
	int input = Integer.MAX_VALUE;
	String[] fileName = new String[2];
	
	public void runProgram() {
		while(input!=0) {
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//where/how is the output being logged?
			if (printRegularMenu) {
				this.menuContent();
			}
			printRegularMenu = true;
//			this.menuContent();
			System.out.println();
			System.out.print("> ");
			System.out.flush();
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//need to use input class and method, scanner here violated N-tier architecture
			input = inputHelper.getValidIntInput();
			this.logger.log(Integer.toString(input));
			switch(input) {
			case 0:
				inputHelper.getScanner().close();
				break;
			case 1:
				this.menuContentFromInputs(argsTraversed);
				this.printRegularMenu = false;
				break;
			case 2:

				
				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//can just instantiate one allzipcodes and singlezipcode object at the start and call, no need to create one for each call
//				allZipCodes = new AllZipCodes(this.hashMap);
				allZipCodes.totalPopulation();
				
				break;
			case 3:

				allZipCodes.totalVaccinations();
				break;
			case 4:

				this.singleZipCode.runOperation(4);
				break;
			case 5:

				this.singleZipCode.runOperation(5);
				break;
			case 6:

				this.singleZipCode.runOperation(6);
				break;
			case 7:

				this.singleZipCode.runOperation(7);
				break;
//				break;
			default:
				System.err.println("Invalid input. Please try again");
				continue;
				


			}
		}
		return;
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
		System.out.println("7. Show the total market value per capita divided by the number of full vaccinations for a specific zip code.");
	}
	
	public void menuContentFromInputs(Set argsTraversed) {
		System.out.println();
		System.out.println("BEGIN OUTPUT");
		if (argsTraversed.contains("--population")) {
			System.out.println("0. Exit the program.");
			System.out.println("1. Show the available actions.");
			System.out.println("2. Show the total population for all ZIP Codes.");
		} 
		if(argsTraversed.contains("--population") && argsTraversed.contains("--covid")) {

			System.out.println("3. Show the total vaccinations per capita for each ZIP Code for the specified date.");
		} 
		if(argsTraversed.contains("--population") && argsTraversed.contains("--covid") && argsTraversed.contains("--properties")) {

			System.out.println("4. Show the average market value for properties in a specified ZIP Code.");
			System.out.println("5. Show the average total livable area for properties in a specified ZIP Code.");
			System.out.println("6. Show the total market value of properties, per capita for a specified ZIP Code.");
			System.out.println("7. Show the total market value per capita divided by the number of full vaccinations for a specific zip code.");
		} 
		
		if (!(argsTraversed.contains("--population") || argsTraversed.contains("--covid") || argsTraversed.contains("--properties"))) {
			System.out.println("0. Exit the program.");
			System.out.println("1. Show the available actions.");
		}
		System.out.println("END OUTPUT");

	}

}
