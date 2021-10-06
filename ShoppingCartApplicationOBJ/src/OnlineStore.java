import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class OnlineStore {
	
	private ProductList myStoreProducts = new ProductList();
	private UserMap myStoreUsers = new UserMap();
	
	//Map User to Cart
	private static Map<User, Cart> cartUsers = new HashMap<>();
	
	Cart userCart = null;
	User currentUser = null;
	
	//Cart methods start
	Map<Product, Double> getStoreProductDetails() {
		return myStoreProducts.getAllProductList();
	}
	
	Product getProductByID(int productId) {
		return myStoreProducts.findProductByProductID(productId);
	}
	
	Map<Product, Double> getCartProductDetails() {
		return userCart.getAllProductsInCart();
	}
	
	int getNumberOfItemsInCart() {
		if(userCart == null)
			return 0;
		return userCart.getNumberOfItemsInCart();
	}
	
	boolean canPurchaseItems() {
		if(userCart == null)
			return false;
		return getNumberOfItemsInCart() != 0;
	}
	
	boolean isProductInStock(Product currentProduct, double productQuantity) {
		if(myStoreProducts.isProductInStock(currentProduct, productQuantity))
			return true;
		return false;
	}
	
	void addProductToUserCart(Product currentProduct, double productQuantity) {
		if(userCart==null)
			userCart = new Cart();
		myStoreProducts.removeFromProductStock(currentProduct, productQuantity);
		userCart.addProductToCart(currentProduct, productQuantity);
	}
	
	boolean hasRemovedProductFromUserCart(Product currentProduct, double productQuantity) {
		if(userCart == null)
			return false;
		myStoreProducts.addToProductStock(currentProduct, productQuantity);
		userCart.removeProductFromCart(currentProduct, productQuantity);
		return true;
	}
	
	double getRequestedProductStock(Product currentProduct) {
		return myStoreProducts.getProductStock(currentProduct);
	}
	//Cart Method End
	
	//User Start
	boolean isValidUser(String userName, String userPassword) {
		if(myStoreUsers.isUserValid(userName, userPassword)) {
			currentUser = myStoreUsers.getUserCredentials(userName);
			if(hasUserAlreadyLoggedIn(currentUser))
				userCart = cartUsers.get(currentUser);
			else
				cartUsers.put(currentUser, userCart);
			return true;
		}
		return false;
	}
	
	boolean hasUserAlreadyLoggedIn(User currentUser) {
		return cartUsers.containsKey(currentUser);
	}
	
	boolean hasUserLoggedIn() {
		return currentUser != null;
	}
	
	void addNewUserToUserMap(String userName, String userPassword) {
		currentUser = myStoreUsers.addNewUser(userName, userPassword);
	}
	//User End
	
	public void writeToBill() {
		String headingString = String.format("%35s\n", "Bill");
		String userDetails = String.format("%s %45s\n", "User Id: "+currentUser.getUserId(), "User Name: " + currentUser.getUserName());
		String fruitHeadings = String.format("|%" + DisplayHandle.COLUMN_GAP + "s" + "|%" + DisplayHandle.COLUMN_GAP + "s"
				+ "|%" + DisplayHandle.COLUMN_GAP + "s\n" , "Name", "Quantity", "Price (Rs/kg)");
		String items = "";
		String thankYouString = "Thank you for the purchase. Have a nice day!";
		
		try(FileWriter billWriter = new FileWriter("Bill.txt")){
			billWriter.write(headingString + userDetails + fruitHeadings);
			for(Entry<Product, Double> productMap: userCart.getAllProductsInCart().entrySet()) {
				Product currentProduct = productMap.getKey();
				double productQuantity = productMap.getValue();
				items += String.format("|%" + DisplayHandle.COLUMN_GAP + "s"
						+ "|%" + DisplayHandle.COLUMN_GAP+ "s"
						+"|%" + DisplayHandle.COLUMN_GAP + "s\n" 
						, currentProduct.getName() , productQuantity ,(currentProduct.getPrice() * productQuantity));
			}
			items += String.format("|%" + DisplayHandle.COLUMN_GAP + "s"
					+ ":%" + DisplayHandle.COLUMN_GAP + "s\n"
					, "Grand Total" , userCart.getGrandTotal());
			billWriter.write(items);
			billWriter.write(thankYouString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
