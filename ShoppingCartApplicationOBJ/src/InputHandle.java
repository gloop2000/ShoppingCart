import java.util.Scanner;

public class InputHandle {
	private Scanner inputScanner;
	
	public InputHandle(Scanner inputScanner) {
		this.inputScanner = inputScanner;
	}
	
	public int getMenuInput() {
		System.out.print("Enter your choice: ");
        int choice = inputScanner.nextInt();
        return choice;
    }
	
	public int[] getProductInput() {
		System.out.print("Enter product code: ");
		int productId = inputScanner.nextInt();
		System.out.print("Enter Quantity: ");
		int quantity = inputScanner.nextInt();
		int[] addToCartValues = {productId,quantity};
		return addToCartValues;
	}
}
