
public class Product {
	private Integer productID;
    private String productName;
    private Double productPrice;
    private Double productStock;
    private Double productQuantity;
    
    public Product(Integer productID, String productName, Double productStock , Double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productQuantity = 0d;
    }
    
    public String getName() {
        return productName;
    }
    
    public Double getPrice() {
        return productPrice;
    }
    
    public Double getStock() {
        return productStock;
    }
    
    public void setStock(Double stock) {
        this.productStock = stock;
    }
    
    public Double getQuantity() {
    	return productQuantity;
    }
    
    public void setQuantity(Double quantity) {
    	this.productQuantity += quantity;
    }
    
    public Integer getPid() {
        return productID;
    }
}