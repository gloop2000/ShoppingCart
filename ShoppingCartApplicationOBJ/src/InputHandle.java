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
	
	public Object[] getProductInput() {
		System.out.print("Enter product code: ");
		int productId = inputScanner.nextInt();
		System.out.print("Enter Quantity: ");
		double quantity = inputScanner.nextDouble();
		Object[] productInput = new Object[2];
		productInput[0] = productId;
		productInput[1] = quantity;
		return productInput;
	}
	
	public String[] getUserInput() {
		System.out.println("Enter User Name: ");
		String userName = inputScanner.next();
		System.out.println("Enter User Password");
		String userPassword = inputScanner.next();
		String[] userDetails = {userName, userPassword};
		return userDetails;
	}
}
