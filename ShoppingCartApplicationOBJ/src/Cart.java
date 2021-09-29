import java.util.Set;
import java.util.HashSet;

public class Cart {
	Set<Product> cartItems = new HashSet<>();
	ProductList<Product> products = new ProductList<>();
	
	public void addProductToCartByPID(int productID, double quantity) {
        Product currentProduct = products.getProductByProductID(productID, quantity);
        currentProduct.removeFromStock(quantity);
        addToCart(currentProduct);
    }
	
	private void addToCart(Product currentProduct) {
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
}
