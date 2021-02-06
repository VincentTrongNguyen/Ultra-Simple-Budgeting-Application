import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

//Group: Vincent Nguyen
//OCCC Fall 2020
//Advanced Java
//Final Presentation

public class project {
	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Bolt Budgeting!");
		
		System.out.print("Please input your name: ");
		String name = in.nextLine();		
		File f = new File(name + ".txt");
		
		boolean loop = true;
		while (loop) {
			if (f.exists()) {
				System.out.println("\n------------------------ Loading profiles... ------------------------\n");
				System.out.printf("Use existing data for %s or overwrite? (y or o)", name);
				String loadInput = in.next();
				
				boolean loop2 = true;
				while (loop2) {
					if (loadInput.equals("y")) {
						System.out.println("\n------------------------ Loading profile for " + name + "... ------------------------\n");
						readFile(f);
						loop2 = false;
					}
					else if (loadInput.equals("o")) {
						System.out.println("\n------------------------ Overwriting profile for " + name + "... ------------------------\n");
						newFile(f);
						loop2 = false;
					} else {
						System.out.print("Invalid Input. Please input y or o.");
						loadInput = in.next();
					}
				}	
			} else {
				System.out.println("\n------------------------ Creating profile for " + name + "... ------------------------\n");
				newFile(f);
			} 
			
			System.out.print("Do you wish to go back? (y or n)");
			String loopInput = in.next();
			if (!loopInput.equals("y")) {
				loop = false;
			} 
		}
	}
	
	public static void readFile(File f) throws FileNotFoundException {
		Scanner reader = new Scanner(f);
		
		double income = Double.parseDouble(reader.nextLine());
		System.out.printf("Expected Income: $%s\n", income);
		
		double expenses = Double.parseDouble(reader.nextLine());
  		System.out.printf("Expected Expenses: $%s\n", expenses);
  		
  		double leftover = Double.parseDouble(reader.nextLine());
  		System.out.printf("Money Left Over: $%s\n", leftover);
  		
  		double saveGoal = Double.parseDouble(reader.nextLine());
  		double goalDollar = Double.parseDouble(reader.nextLine());
  		System.out.printf("Save Goal: %s%% - $%s\n", saveGoal, goalDollar);
  		
  		double budget = Double.parseDouble(reader.nextLine());
  		System.out.printf("Budget: $%s\n", budget);
  		
  		double budgetLeft = Double.parseDouble(reader.nextLine());
  		System.out.printf("Left in Budget: $%s\n\n", budgetLeft);
  		
  		reader.close();
	}
	
	public static void newFile(File f) throws IOException {
		Scanner out = new Scanner(System.in);
		FileWriter fw = new FileWriter(f);

		System.out.print("Expected Income: $");
		double income = out.nextDouble();
		fw.write(String.valueOf(income) + "\n");
		
  		System.out.print("Expected Expenses: $");
		double expenses = out.nextDouble();
		fw.write(String.valueOf(expenses) + "\n");
		
		double leftover = income - expenses;
  		System.out.printf("Money Left Over: $%.2f\n", leftover);
  		fw.write(String.valueOf(leftover) + "\n");
  		
  		System.out.print("How much do you want to save(%)? ");
  		double saveGoal = out.nextDouble();
  		fw.write(String.valueOf(saveGoal) + "\n");
  		double goalDollar = leftover * (saveGoal / 100);
  		fw.write(String.valueOf(goalDollar) + "\n");
  		
  		double budget = leftover - goalDollar;
  		System.out.printf("Budget = $%.2f\n", budget);
  		fw.write(String.valueOf(budget) + "\n");
  		
  		System.out.print("Additional Expenses: $");
		double spent = out.nextDouble();
		
		double budgetLeft = budget - spent;
		System.out.printf("Left in Budget: $%.2f\n\n", budgetLeft);
  		fw.write(String.valueOf(budgetLeft) + "\n");
		
  		fw.close();
	}
}