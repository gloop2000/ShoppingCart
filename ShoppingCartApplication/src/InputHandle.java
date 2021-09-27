import java.sql.SQLException;
import java.util.Scanner;

public class InputHandle {

	private static SQLHandle sqlHandler;

	public static void handleMenuInput(Scanner inputScanner) {
		String inputString = inputScanner.next();
		char firstCharacter = inputString.charAt(0);
		if (isCharacter(firstCharacter)) {
			// handle login or Purchase
			if(firstCharacter == 'L')
				handleExistingUserStatus(inputScanner);
		} else {
			// handleItemAddition
			handleItemAddition(Integer.parseInt(inputString), inputScanner);
		}
	}

	public static char handleExistingUserStatus(Scanner inputScanner) {
		String inputString = inputScanner.next();
		char firstCharacter = inputString.charAt(0);
		if (firstCharacter == 'Y' || firstCharacter == 'y') {
			// Login the user
			return 'L';
		} else {
			// New user registration
			return 'R';
		}

	}

	public static void handleNewUserLogin(Scanner inputScanner) {
		String userName, password, phoneNumber;
		System.out.print("Enter User Name: ");
		userName = inputScanner.next();
		System.out.print("Enter Password: ");
		password = inputScanner.next();
		System.out.print("Enter Phone Number: ");
		phoneNumber = inputScanner.next();
		try {
			sqlHandler = new SQLHandle();
			sqlHandler.insertNewUser(userName, password, phoneNumber);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void handleExistingUserLogin(Scanner inputScanner) {
		String userName,Password;
		System.out.print("Enter User Name: ");
		userName = inputScanner.next();
		System.out.print("Enter Password: ");
		Password = inputScanner.next();
		try {
			sqlHandler = new SQLHandle();
			if(sqlHandler.hasfoundUser(userName, Password))
				System.out.print("Login Successfull");
			else
				System.out.println("Login failed");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static boolean isCharacter(char inputCharacter) {
		if ((inputCharacter >= 65 && inputCharacter <= 90) || (inputCharacter >= 97 && inputCharacter <= 122))
			return true;
		return false;
	}

	private static void handleItemAddition(int productCode, Scanner inputScanner) {
		float quantity = 0f;
		System.out.println("Enter the quantity of item you wish to buy: ");
		quantity = inputScanner.nextFloat();
		try {
			sqlHandler = new SQLHandle();
			if(sqlHandler.canBuyItem(productCode, quantity))
				System.out.println("Added to Cart");
			else
				System.out.println("Could not add");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}
}
