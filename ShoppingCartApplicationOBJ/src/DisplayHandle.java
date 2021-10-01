import java.util.List;

public class DisplayHandle {

	Cart userCart = new Cart();
	private int choice = 0;
	private int innerChoice;
//	private boolean isLoggedIn = false;
	private InputHandle inputHandler;
	public static final short COLUMN_GAP = -15;

	public DisplayHandle(InputHandle inputHandler) {
		this.inputHandler = inputHandler;
		mainMenu();
	}

	public void startScreen() {
		System.out.printf("%50s", "Welcome To Fruit Vendor App\n");
		System.out.printf("%s", "Items in cart: " + userCart.numberOfItemsInCart());
		System.out.printf("%45s", "Hello, ");
		if (userCart.isUserLoggedIn())
			System.out.printf("%s\n",userCart.currentUser.getUserName());
		else {
			System.out.printf("%s", "Sign in.\n");
		}
	}

	public void startMenu() {
		System.out.printf("%35s", "Main Menu\n");
		if(userCart.isUserLoggedIn()) {
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
		List<Product> products = userCart.products.getProducts();
		for (Product product : products) {
			System.out.printf("|%" + COLUMN_GAP + "s", product.getPid());
			System.out.printf("|%" + COLUMN_GAP + "s", product.getName());
			System.out.printf("|%" + COLUMN_GAP + "s", product.getPrice());
			System.out.printf("|%" + COLUMN_GAP + "s\n", product.getStock());
		}
	}

	public void storeProductsMenu() {
		System.out.printf("%35s", "Store Products Menu\n");
		System.out.printf("1. Add to cart\t 2. Remove from Cart\t 0. Exit\n");
	}

	public void storeLoginMenu() {
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
				if(!userCart.isUserLoggedIn())
					displayLoginInnerChoice();
				else
					//purchase
					if(canPurchaseItems()) {
						printCartItems();
						userCart.writeToBill();
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

	private void displayLoginInnerChoice() {
		// TODO Auto-generated method stub
//		int innerChoice = 0;
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

	public void displayStoreProductInnerChoice() {
//		int innerChoice = 0;
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

	private void addProductToCart() {
		Product tempProduct = inputHandler.getProductInput();
		userCart.addProductToCartByPID(tempProduct.getPid(), tempProduct.getQuantity());
		System.out.println("Item Added");
	}

	private void removeProductFromCart() {
		Product tempProduct = inputHandler.getProductInput();
		userCart.removeProductByPID(tempProduct.getPid(), tempProduct.getQuantity());
		System.out.println("Item Removed");
	}

	private void printCartItems() {
		String[] headings = { "Name", "Quantity", "Price (Rs/kg)" };
		for (String heading : headings) {
			System.out.printf("|%" + COLUMN_GAP + "s", heading);
		}
		System.out.println();
		for (Product currentProduct : userCart.cartItems) {
			System.out.printf("|%" + COLUMN_GAP + "s", currentProduct.getName());
			System.out.printf("|%" + COLUMN_GAP + "s", currentProduct.getQuantity());
			System.out.printf("|%" + COLUMN_GAP + "s\n", (currentProduct.getPrice() * currentProduct.getQuantity()));
		}
		System.out.println();
		System.out.printf("|%" + COLUMN_GAP + "s", "Grand Total");
		System.out.printf(":%" + COLUMN_GAP + "s\n", userCart.getGrandTotal());
	}
	
	private void loginUser() {
		String[] userDetails = inputHandler.getUserInput();
		String userName = userDetails[0];
		String userPassword = userDetails[1];
		if(userCart.assignExistingUserToCart(userName, userPassword))
			System.out.println("Logged In");
		else
			System.out.println("Incorrect User Name or Password");
	}
	
	private void registerUser() {
		String[] userDetails = inputHandler.getUserInput();
		String userName = userDetails[0];
		String userPassword = userDetails[1];
		userCart.assignNewUserToCart(userName, userPassword);
		System.out.println("Registered Successfully");
	}
	
	private boolean canPurchaseItems() {
		if(userCart.numberOfItemsInCart()==0) {
			System.out.println("There are no items in Cart");
			return false;
		}
		else {
			return true;
		}
	}
}
