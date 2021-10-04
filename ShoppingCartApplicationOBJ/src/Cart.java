import java.util.HashMap;
import java.util.Map;

public class Cart {
	//Map product to their quantities in cart
	private Map<Product,Double> userCart = new HashMap<>();
	double grandTotal =0;
	
	void addProductToCart(Product currentProduct, Double requestedQuantity) {
		if(userCart.containsKey(currentProduct)) {
			double currentQuantity = userCart.get(currentProduct);
			userCart.put(currentProduct, currentQuantity + requestedQuantity);
		}
		else {
			userCart.put(currentProduct, requestedQuantity);
		}
		grandTotal += currentProduct.getPrice() * requestedQuantity;
	}
	
	void removeProductFromCart(Product currentProduct, Double requestedQuantity) {
		if(userCart.containsKey(currentProduct)) {
			double currentQuantity = userCart.get(currentProduct);
			if(currentQuantity > requestedQuantity)
				userCart.put(currentProduct, currentQuantity - requestedQuantity);
			else
				userCart.remove(currentProduct);
			grandTotal -= currentProduct.getPrice() * requestedQuantity;
		}
	}
	
	Map<Product,Double> getAllProductsInCart() {
		return userCart;
	}
	
	int getNumberOfItemsInCart() {
		return userCart.size();
	}
	
	double getGrandTotal() {
		return grandTotal;
	}
}
