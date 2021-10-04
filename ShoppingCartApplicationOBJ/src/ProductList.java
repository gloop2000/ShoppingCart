import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductList {
	// Map product to their quantities in Store
	private static Map<Product, Double> productsInStore = new LinkedHashMap<>();
	Map<Integer, Product> productAndIdMap = new HashMap<>();
	
	public ProductList() {
		// TODO Auto-generated constructor stub
		this.initializeProducts();
	}

	// initialize products in store
	void initializeProducts() {
		String[] productNames = { "Apple", "Manoges", "Pear", "Banana", "Grapes" };
		Double[] productPrice = { 100.00d, 120.00d, 140.00d, 74.00d, 80.00d };
		Double[] stock = { 10.0d, 25.0d, 8.0d, 30.0d, 5.0d };
		for (int index = 0; index < productNames.length; index++) {
			Product tempProduct = new Product(index + 1, productNames[index], productPrice[index]);
			productsInStore.put(tempProduct, stock[index]);
			productAndIdMap.put(index+1, tempProduct);
		}
	}
	
	//Find Product By ProductID
	Product findProductByProductID(int productID) {
		return productAndIdMap.get(productID);
	}

	// Check if Product is in Stock
	boolean isProductInStock(Product currentProduct, double quantityRequested) {
		double currentStock = productsInStore.get(currentProduct);
		if (currentStock == 0)
			return false;
		if (currentStock < quantityRequested)
			return false;
		return true;
	}

	// Add quantity to Product Stock
	void addToProductStock(Product currentProduct, double quantityToAdd) {
		double currentStock = productsInStore.get(currentProduct);
		productsInStore.put(currentProduct, currentStock + quantityToAdd);
	}

	// Remove quantity from Product Stock
	void removeFromProductStock(Product currentProduct, double quantityToRemove) {
		double currentStock = productsInStore.get(currentProduct);
		productsInStore.put(currentProduct, currentStock - quantityToRemove);
	}
	
	int getSize() {
		return productsInStore.size();
	}
	
	Map<Product,Double> getAllProductList(){
		return productsInStore;
	}
	
	double getProductStock(Product currentProduct) {
		return productsInStore.get(currentProduct);
	}
}
