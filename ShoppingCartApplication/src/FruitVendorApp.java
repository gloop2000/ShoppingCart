import java.sql.SQLException;
import java.util.Scanner;

public class FruitVendorApp {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//Welcome
		Scanner inputScanner = new Scanner(System.in);
		//Main Screen
		DisplayHandle.appDisplayWelcome(); 
		DisplayHandle.appDisplayItems();
		DisplayHandle.appDisplayMenu(inputScanner);
		
		//Display Screen
//		DisplayHandle.userHandleDisplay(inputScanner);
		inputScanner.close();
	}
}
