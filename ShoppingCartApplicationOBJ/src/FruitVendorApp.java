import java.sql.SQLException;
import java.util.Scanner;

public class FruitVendorApp {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner inputScanner = new Scanner(System.in);
		InputHandle inputHandler = new InputHandle(inputScanner);
		DisplayHandle displayHandler = new DisplayHandle(inputHandler);
	}
}
