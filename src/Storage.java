import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//testing git

public class Storage {
	
	/**Variable holding value of database we're using.*/
	private static final String database = "budget.db";
	
	private Controller controller = null;
	private Connection connection = null;
	
	//Prepared queries:
	private Statement select = null;
	private Statement insert = null;
	private Statement statement = null;//generic for infrequent uses
	
	public Storage(){
		try{
			//load the sqlite-JDBC driver using the current class loader
			Class.forName("org.sqlite.JDBC");
			
			//initialize prepared queries
			select = connection.createStatement();
			insert = connection.createStatement();
			statement = connection.createStatement();
			
		}
		catch(ClassNotFoundException ex){
			System.err.println("Database Error! Problem connecting to the database: " + ex.getMessage());
			return;
		}
		catch(SQLException ex){
			System.err.println("Database Error! Problem creating prepared queries: " + ex.getMessage());
			return;
		}
		
		connect();
		
	}
	
	/**
	 * Attempt to establish a connection to the database.
	 */
	public void connect(){
		try{ connection = DriverManager.getConnection("jdbc:sqlite:" + database);}
		catch(SQLException ex){System.err.println("Problem connecting to the database: " + ex.getMessage());}
	}
	
	private void select(String query){
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from person");
		}
		catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
	}
	
	public void addListener(Controller controller) {
		this.controller = controller;
	}
	
	public boolean newBudget(String name) {
		//try to create new budget with given name
		//if budget name already exists, return false
		//otherwise, create and return true
		
		//testing git
		return true;
	}

	public String[] retrieveBudgets() {
		//Get budgets and store them in this array
		//return array
		try{
			//get the names of all budgets
			ResultSet rs = statement.executeQuery("select name from budget");
		}
		catch(SQLException ex){System.err.println("Database Error! " + ex.getMessage());}
		
		String[] budgetsAvailable = {"budget 1", "budget 2", "budget 3", "budget 4", "budget 5"};
		
		return budgetsAvailable;
	}
	
	
	public static void main(String[] args){
		Storage storage = new Storage();
	}
}


