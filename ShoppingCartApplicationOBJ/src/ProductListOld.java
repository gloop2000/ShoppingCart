import java.util.List;
import java.util.ArrayList;

public class ProductListOld {
	private List<Product> products = new ArrayList<Product>();
	
	public ProductListOld () {
        this.initStoreItems();
    }
	
	public List<Product> getProducts() {
        return products;
    }
	
	public void initStoreItems() {
        String [] productNames = {"Apple", "Manoges", "Pear", "Banana", "Grapes"};
        Double [] productPrice = {100.00d, 120.00d, 140.00d, 74.00d, 80.00d};
        Double [] stock = {10.0d, 25.0d, 8.0d, 30.0d, 5.0d};
        
        for (int i=0; i < productNames.length; i++) {
            this.products.add(new Product(i+1, productNames[i], productPrice[i], stock[i]));
        }
    }
}
