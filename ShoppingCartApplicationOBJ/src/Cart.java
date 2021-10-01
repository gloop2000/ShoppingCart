import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Cart {
	Set<Product> cartItems = new HashSet<>();
	ProductList<Product> products = new ProductList<>();
	UserMap<String, User> users = new UserMap<>();
	
	
//	public boolean isUserLoggedIn = false;
	User currentUser = null;
	
	public void addProductToCartByPID(int productID, double quantity) {
        Product currentProduct = products.getProductByProductID(productID, quantity);
        currentProduct.removeFromStock(quantity);
        addProductToCart(currentProduct);
    }
	
	private void addProductToCart(Product currentProduct) {
        cartItems.add(currentProduct);
    }
	
	public void removeProductByPID(int productID,double quantity) {
        Product currentProduct = products.getProductByProductID(productID, quantity);
        if(currentProduct != null) {
        	currentProduct.addToStock(quantity);
        	if(currentProduct.getQuantity() == 0)
        		cartItems.remove(currentProduct);
        }
        	
    }
	
	public int numberOfItemsInCart() {
		return cartItems.size();
	}
	
	public double getGrandTotal() {
		double grandTotal = 0;
		for(Product currentProduct: cartItems) {
			grandTotal += currentProduct.getPrice() * currentProduct.getQuantity();
		}
		return grandTotal;
	}
	
	public boolean isUserLoggedIn() {
		return currentUser != null;
	}
	
	public boolean assignExistingUserToCart(String userName,String userPassword){
		if(users.isValidUser(userName, userPassword)) {
			this.currentUser = users.get(userName);
			return true;
		}
		return false;
	}
	
	public void assignNewUserToCart(String userName, String userPassword) {
		this.currentUser = users.addUser(userName, userPassword);
	}
	
	public void writeToBill() {
		String headingString = String.format("%35s\n", "Bill");
		String userDetails = String.format("%s %45s\n", "User Id: "+currentUser.getUserId(), "User Name: " + currentUser.getUserName());
		String fruitHeadings = String.format("|%" + DisplayHandle.COLUMN_GAP + "s" + "|%" + DisplayHandle.COLUMN_GAP + "s"
				+ "|%" + DisplayHandle.COLUMN_GAP + "s\n" , "Name", "Quantity", "Price (Rs/kg)");
		String items = "";
		String thankYouString = "Thank you for the purchase. Have a nice day!";
		
		try(FileWriter billWriter = new FileWriter("Bill.txt")){
			billWriter.write(headingString + userDetails + fruitHeadings);
			for(Product currentProduct: cartItems) {
				items += String.format("|%" + DisplayHandle.COLUMN_GAP + "s"
						+ "|%" + DisplayHandle.COLUMN_GAP+ "s"
						+"|%" + DisplayHandle.COLUMN_GAP + "s\n" 
						, currentProduct.getName() , currentProduct.getQuantity() ,(currentProduct.getPrice() * currentProduct.getQuantity()));
			}
			items += String.format("|%" + DisplayHandle.COLUMN_GAP + "s"
					+ ":%" + DisplayHandle.COLUMN_GAP + "s\n"
					, "Grand Total" , this.getGrandTotal());
			billWriter.write(items);
			billWriter.write(thankYouString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
