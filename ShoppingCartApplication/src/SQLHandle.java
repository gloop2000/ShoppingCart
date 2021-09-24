import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandle {
	static final String ConnectionURL = "jdbc:derby:C:\\Users\\saragh\\MyDB;create=true";
	
	private Connection dbConnection;
	private Statement sqlStatement;
	
	public SQLHandle() throws SQLException, ClassNotFoundException {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		dbConnection = DriverManager.getConnection(ConnectionURL);
		sqlStatement = dbConnection.createStatement();
	}
	
	public ResultSet retriveData(String table) throws SQLException {
		String query = "SELECT * FROM FRUITVENDORAPPDATA."+table;
		ResultSet queryResult = sqlStatement.executeQuery(query);
		return queryResult;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SQLHandle test = new SQLHandle();
		ResultSet testdata = test.retriveData("FRUITS");
		while(testdata.next()) {
	         System.out.println("Id: "+testdata.getString("Id"));
	         System.out.println("Name: "+testdata.getString("Name"));
	         System.out.println("Stock: "+testdata.getString("Stock"));
	         System.out.println("Price: "+testdata.getString("Price"));
	         System.out.println(" ");
	      }
	}
}
