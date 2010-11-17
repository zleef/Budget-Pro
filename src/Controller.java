import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

	private static Reports reports = new Reports();
	private static Entries entries = new Entries();
	private static Tags tags = new Tags();
	private static Storage storage = new Storage();
	public static String state = null;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public Controller() throws IOException {
		reports.addListener(this, storage);
		entries.addListener(this, storage);
		tags.addListener(this, storage);
		storage.addListener(this);
		start();
	}
	
	public static void main(String[] args) throws IOException {
		new Controller();
		System.out.println("Welcome to Budget Starter Pro\n");
	}
	
	public void start() throws IOException {
		while(true) {
			System.out.println("Please choose from the following options:");
			System.out.println("  (1) : Create new budget\n  (2) : Load existing budget\n  (3) : Exit application");
			String input = br.readLine();
			if(input.equals("1")) {
				newBudget();
			}
			else if(input.equals("2")) {
				System.out.println("--Choose existing budget--");
				String[] budgets = storage.retrieveBudgets();
				for(int i = 0; i<budgets.length; i++) {
					System.out.println("  (" + (i+1) + ") : " + budgets[i]);
				}
				System.out.println("  (00) : Return to main menu");
				String choice = br.readLine();
				if(choice.equals("00")) {
					//they don't want to choose a budget, do nothing/continue while loop
				}
				else {
					
/*
 * 
 * NEED TO DEAL WITH IF THE INPUT IS NOT AN INTEGER SO THE PROGRAM DOESN'T CRASH HERE
 * 
 */
					int intChoice = Integer.parseInt(choice);
					state = budgets[intChoice-1];
					budgetOptions();
				}
			}
			else if(input.equals("3")) {
				System.out.println("Thank you for using Budget Starter Pro. - Goodbye :)");
				System.exit(0);
			}
		}
	}
	
	public String newBudget() throws IOException {
		System.out.println("--Creating new budget--");
		System.out.print("Name new budget: " );
		String name = br.readLine();
		if(storage.newBudget(name)) {
			System.out.println("Budget successfully created.");
			state = name;
			return budgetOptions();
		}
		else {
			System.out.println("Budget with this name already exists.");
			return "0";
		}
	}
	
	public String budgetOptions() throws IOException {
		while(true) {
			System.out.println("--In Budget " + state + "--");
			System.out.println("  (1) : Add/edit tags (and/or create budgeting limits on tags)\n  (2) : Add or modify entries (expenses/income)\n  (3) : View reports\n  (00) : Return to main menu (exit current budget)");
			String input = br.readLine();
			String temp;
			if(input.equals("1")) {
				temp = tags.start();
				//if it returns 00, they want up to the main menu
				if(temp.equals("00"))
					return temp;
			}
			else if(input.equals("2")) {
				temp = entries.start();
				//if it returns 00, they want up to the main menu
				if(temp.equals("00"))
					return temp;
			}
			else if(input.equals("3")) {
				temp = reports.start();
				//if it returns 00, they want up to the main menu
				if(temp.equals("00"))
					return temp;
			}
			else if(input.equals("00")) {
				return input;
			}
			else
				System.out.println("Not a valid choice. Please enter 1, 2, 3, or 00");
		}
	}
	public void reset() throws IOException {
		reports = new Reports();
		entries = new Entries();
		tags = new Tags();
		storage = new Storage();
		new Controller();
		start();
	}
}
