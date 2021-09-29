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
	
	public Product getProductInput() {
		System.out.print("Enter product code: ");
		int productId = inputScanner.nextInt();
		System.out.print("Enter Quantity: ");
		double quantity = inputScanner.nextDouble();
		Product tempProduct = new Product(productId, quantity);
		return tempProduct;
	}
}
