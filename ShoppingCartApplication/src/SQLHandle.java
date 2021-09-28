import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandle {
	static final String ConnectionURL = "jdbc:derby:C:\\Users\\ssrvk\\MyDB;create=true";
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
	
	public void insertNewUser(String Name, String Password, String Phone) throws SQLException {
		String query = "insert into FruitVendorAppData.Users(U_Name,U_Pass,Phone) values "
				+ "('"+Name+"','"+Password+"','"+Phone+"')";
		sqlStatement.executeUpdate(query);
	}
	
	public boolean hasfoundUser(String Name, String Password) throws SQLException{
		String query = "SELECT U_Name, U_Pass FROM FruitVendorAppData.Users where U_Name = '"+Name+"' AND U_PASS ='"+Password+"'";
		ResultSet queryResult = sqlStatement.executeQuery(query);
		if(queryResult.next())
			return true;
		return false;
	}
	
	public boolean canBuyItem(int productCode, float quantity) throws SQLException{
		String query = "SELECT ID,STOCK,Price FROM FruitVendorAppData.fruits WHERE ID = "+productCode;
		ResultSet queryResult = sqlStatement.executeQuery(query);
		if(queryResult.next()) {
			float currentStock = queryResult.getFloat("Stock");
			if(currentStock >= quantity) {
				float itemPrice = queryResult.getFloat("Price");
				currentStock -= quantity;
				String updateQuery = "UPDATE FruitVendorAppData.fruits SET STOCK="+currentStock+"WHERE ID="+productCode;
				sqlStatement.executeUpdate(updateQuery);
				addOrder(0, productCode, quantity, itemPrice*quantity);
				return true;
			}	
		}
		return false;
	}
	
	public void addOrder(int userID,int productID, float quantity,float price) throws SQLException {
		String query = "insert into FruitVendorAppData.Orders values "
				+ "("+userID+","+productID+","+quantity+","+price+")";
		sqlStatement.executeUpdate(query);
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
