
public class Product {
	private Integer productID;
    private String productName;
    private Double productPrice;
    
    public Product(Integer productID, String productName, Double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }
    
    public String getName() {
        return productName;
    }
    
    public Double getPrice() {
        return productPrice;
    }
    
    public Integer getPid() {
        return productID;
    }
}
