import java.sql.SQLException;

public class FruitVendorApp {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//Welcome
//		FruitVendorApp myApp = new FruitVendorApp();
		DisplayHandle.appDisplayWelcome(); 
		DisplayHandle.appDisplayItems();
		DisplayHandle.loginDisplay();
	}
}
