package edu.upenn.cit594;

//import edu.upenn.cit5940.datamanagement.Analyzer;
//import edu.upenn.cit594.ui.UserInterface;

public class Main {

	public static void main(String[] args) {
//		UserInterface ui = new UserInterface(); 
		// TODO Auto-generated method stub
		if (args.length == 0) {
            System.out.println("Please specify the name of the input file");
            return;
        }
		if (args.length != 3) {
			System.out.println("Please include all 3 arguments: tweets file, states file, log file");
            return;
		}
        String filename = args[0];
//        ui.runProgram(filename, args[1], args[2]);

	}

}
