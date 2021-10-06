import java.util.Scanner;

public class FruitVendorApp {
	
	public static void main(String[] args){
		Scanner inputScanner = new Scanner(System.in);
		
		OnlineStore myFruitStore = new OnlineStore();
		
		InputHandle inputHandler = new InputHandle(inputScanner);
		DisplayHandle displayHandler = new DisplayHandle(inputHandler, myFruitStore);
		displayHandler.mainMenu();
		
		inputScanner.close();
	}
}
