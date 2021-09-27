import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DisplayHandle {
	public static boolean isLoggedIn = false;

	public static int itemsInCart = 0;

	public static final short COLUMN_GAP = -15;
	
	private static SQLHandle sqlHandler;
	
	private static void clearScreen() {
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}
	
	//Display Welcome and Sign in
	public static void appDisplayWelcome() {
//		clearScreen();
		System.out.printf("%50s", "Welcome To Fruit Vendor App\n");
		System.out.printf("%s", "Items in cart: " + itemsInCart);
		System.out.printf("%45s", "Hello, ");
		if (isLoggedIn)
			System.out.print("Sharath");
		else {
			System.out.printf("%s", "Sign in.\n");
		}
		System.out.println();
		String[] headings = { "Product Code", "Name", "In Stock (kg)", "Price (Rs/kg)" };
		for (String heading : headings) {
			System.out.printf("|%" + COLUMN_GAP + "s", heading);
		}
		System.out.println();
	}
	
	//Display Items
	public static void appDisplayItems() throws ClassNotFoundException, SQLException {
		sqlHandler = new SQLHandle();
		ResultSet fruitsData = sqlHandler.retriveData("Fruits");
		while(fruitsData.next()) {
			System.out.printf("|%" + COLUMN_GAP + "s", fruitsData.getString("Id"));
			System.out.printf("|%" + COLUMN_GAP + "s", fruitsData.getString("Name"));
			System.out.printf("|%" + COLUMN_GAP + "s", fruitsData.getString("Stock"));
			System.out.printf("|%" + COLUMN_GAP + "s\n", fruitsData.getString("Price"));
		}
	}
	
	public static void appDisplayMenu(Scanner inputScanner) {
		System.out.printf("\n%20s", "Main Menu\n");
		System.out.printf("%" + COLUMN_GAP + "s - Add Item to cart\n"
				+"%" + COLUMN_GAP + "s - Login\n"
				+"%" + COLUMN_GAP + "s - Purchase\n"
				+"Enter Your Choice: "
				,"Product Code","L","P");
		InputHandle.handleMenuInput(inputScanner);
	}
	
	public static void userHandleDisplay(Scanner inputScanner) {
//		clearScreen();
		System.out.printf("Are you an existing user (y/n): ");
		if(InputHandle.handleExistingUserStatus(inputScanner) == 'L') {
			System.out.printf("%s", "Enter Login Credentials\n");
			InputHandle.handleExistingUserLogin(inputScanner);
		}
		else {
			System.out.printf("%s", "Enter Your Credentials:\n");
			InputHandle.handleNewUserLogin(inputScanner);
			System.out.printf("%s", "User Added!:\n");
		}
	}
}
