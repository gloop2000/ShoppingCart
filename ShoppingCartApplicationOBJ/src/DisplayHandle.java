import java.io.IOException;
import java.util.Map;

public class DisplayHandle {
	
	private int choice = 0;
	private int innerChoice;
	private InputHandle inputHandler;
	private OnlineStore myFruitStore;
	public static final short COLUMN_GAP = -15;

	public DisplayHandle(InputHandle inputHandler, OnlineStore myFruitStore) {
		this.inputHandler = inputHandler;
		this.myFruitStore = myFruitStore;
	}
	
	public void clearScreen() {  
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	public void startScreen() {
		clearScreen();
		System.out.printf("%50s", "Welcome To Fruit Vendor App\n");
		System.out.printf("%s", "Items in cart: " + myFruitStore.getNumberOfItemsInCart());
		System.out.printf("%45s", "Hello, ");
		if (myFruitStore.hasUserLoggedIn())
			System.out.printf("%s\n",myFruitStore.currentUser.getUserName());
		else {
			System.out.printf("%s", "Sign in.\n");
		}
	}

	public void startMenu() {
		System.out.printf("%35s", "Main Menu\n");
		if(myFruitStore.hasUserLoggedIn()) {
			System.out.printf("1. Display Store Products\t 2. Display Cart\t 3. Purchase\t 0. Exit\n");
		}
		else {
			System.out.printf("%40s", "(Login to Purchase)\n");
			System.out.printf("1. Display Store Products\t 2. Display Cart\t 3. Login\t 0. Exit\n");
		}
			
		
	}

	public void displayStoreProducts() {
		String[] headings = { "Product Code", "Name", "Price (Rs/kg)", "In Stock (kg)" };
		for (String heading : headings) {
			System.out.printf("|%" + COLUMN_GAP + "s", heading);
		}
		System.out.println();
		Map<Product, Double> products = myFruitStore.getStoreProductDetails(); 
		for (Map.Entry<Product, Double> productMap : products.entrySet()) {
			Product currentProduct = productMap.getKey();
			double productStock = productMap.getValue();
			System.out.printf("|%" + COLUMN_GAP + "s", currentProduct.getPid());
			System.out.printf("|%" + COLUMN_GAP + "s", currentProduct.getName());
			System.out.printf("|%" + COLUMN_GAP + "s", currentProduct.getPrice());
			System.out.printf("|%" + COLUMN_GAP + "s\n", productStock);
		}
	}

	public void storeProductsMenu() {
		System.out.printf("%35s", "Store Products Menu\n");
		System.out.printf("1. Add to cart\t 2. Remove from Cart\t 0. Exit\n");
	}

	public void storeLoginMenu() {
		clearScreen();
		System.out.printf("%35s", "Login Menu\n");
		System.out.printf("1. Login\t 2. New User Registration\t 0. Exit\n");
	}

	public void mainMenu() {
		do {
			startScreen();
			startMenu();
			choice = inputHandler.getMenuInput();

			switch (choice) {
			case 1:
				displayStoreProductInnerChoice();
				break;
			case 2:
				printCartItems();
				break;
			case 3:
				if(!myFruitStore.hasUserLoggedIn())
					displayLoginInnerChoice();
				else
					//purchase
					if(myFruitStore.canPurchaseItems()) {
						printCartItems();
						myFruitStore.writeToBill();
						System.exit(0);
					}
				break;
			case 0:
				System.exit(0);
				break;
			default:
				break;
			}
		} while (choice != 0);
	}

	private void printCartItems() {
		// TODO Auto-generated method stub
		String[] headings = {"Name", "Quantity", "Price (Rs/kg)", "Total"};
		for (String heading : headings) {
			System.out.printf("|%" + COLUMN_GAP + "s", heading);
		}
		System.out.println();
		Map<Product,Double> productsInCart = myFruitStore.getCartProductDetails();
		for(Map.Entry<Product,Double> productMap: productsInCart.entrySet()) {
			Product currentProduct = productMap.getKey();
			double productQuantity = productMap.getValue();
			System.out.printf("|%" + COLUMN_GAP + "s", currentProduct.getName());
			System.out.printf("|%" + COLUMN_GAP + "s", productQuantity);
			System.out.printf("|%" + COLUMN_GAP + "s", currentProduct.getPrice());
			System.out.printf("|%" + COLUMN_GAP + "s", currentProduct.getPrice() * productQuantity);
		}
	}

	private void displayLoginInnerChoice() {
		// TODO Auto-generated method stub
		storeLoginMenu();
		innerChoice = inputHandler.getMenuInput();
		switch (innerChoice) {
		case 1:
			// login
			loginUser();
			break;
		case 2:
			// New user
			registerUser();
			break;
		default:
			break;
		}
	}

	private void registerUser() {
		// TODO Auto-generated method stub
		String[] userCredentials = inputHandler.getUserInput();
		String userName = userCredentials[0];
		String userPassword = userCredentials[1];
		myFruitStore.addNewUserToUserMap(userName, userPassword);
	}

	private void loginUser() {
		// TODO Auto-generated method stub
		String[] userCredentials = inputHandler.getUserInput();
		String userName = userCredentials[0];
		String userPassword = userCredentials[1];
		if(myFruitStore.isValidUser(userName, userPassword)) {
			System.out.println("Logged In");
		}
		else
			System.out.println("UserName or Password is wrong");
	}
	
	public void displayStoreProductInnerChoice() {
		displayStoreProducts();
		do {
			storeProductsMenu();
			innerChoice = inputHandler.getMenuInput();

			switch (innerChoice) {
			case 1:
				addProductToCart();
				break;
			case 2:
				removeProductFromCart();
				break;
			default:
				break;
			}
		} while (innerChoice != 0);
	}

	private void removeProductFromCart() {
		// TODO Auto-generated method stub
		Object[] productInput = inputHandler.getProductInput();
		int productID = (int)productInput[0];
		double productQuantity = (double)productInput[1];
		Product currentProduct = myFruitStore.getProductByID(productID);
		myFruitStore.myStoreProducts.addToProductStock(currentProduct, productQuantity);
		myFruitStore.userCart.removeProductFromCart(currentProduct, productQuantity);
	}

	private void addProductToCart() {
		// TODO Auto-generated method stub
		Object[] productInput = inputHandler.getProductInput();
		int productID = (int)productInput[0];
		double productQuantity = (double)productInput[1];
		Product currentProduct = myFruitStore.getProductByID(productID);
		if(myFruitStore.myStoreProducts.isProductInStock(currentProduct, productQuantity)) {
			myFruitStore.myStoreProducts.removeFromProductStock(currentProduct, productQuantity);
			myFruitStore.userCart.addProductToCart(currentProduct, productQuantity);
			System.out.println("Product Added to Cart");
		}
		else
			System.out.println("Could not add product to Cart, Please reduce quantity." + myFruitStore.myStoreProducts.getProductStock(currentProduct));
	}
}
