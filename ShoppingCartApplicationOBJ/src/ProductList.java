import java.util.List;
import java.util.ArrayList;

public class ProductList<E> extends ArrayList<E> {
	
	private static final long serialVersionUID = 1L;
	private List<Product> products = new ArrayList<Product>();
	
	public ProductList () {
        this.initStoreItems();
    }
	
	public void initStoreItems() {
        String [] productNames = {"Apple", "Manoges", "Pear", "Banana", "Grapes"};
        Double [] productPrice = {100.00d, 120.00d, 140.00d, 74.00d, 80.00d};
        Double [] stock = {10.0d, 25.0d, 8.0d, 30.0d, 5.0d};
        
        for (int i=0; i < productNames.length; i++) {
            this.products.add(new Product(i+1, productNames[i], stock[i], productPrice[i]));
        }
    }
	
	public List<Product> getProducts() {
        return products;
    }
	
	public Product getProductByProductID(int pid, double quantity) {
        Product product = null;
        for (Product currentProduct: products) {
            if (currentProduct.getPid() == pid) {
            	if(isInStock(currentProduct, quantity)) {
            		product = currentProduct;
                break;
            	}
            }
        }
        return product;
    }
	
	private boolean isInStock(Product currentProduct, double quantity) {
		double currentStock = currentProduct.getStock();
		if( currentStock > quantity) {
			currentProduct.setQuantity(quantity);
			currentProduct.setStock(currentStock - quantity);
			return true;
		}
		return false;
	}
	
//	public List<Product> updateProduct(int pid)
}
