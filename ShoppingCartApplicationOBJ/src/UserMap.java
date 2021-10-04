import java.util.HashMap;
import java.util.Map;

public class UserMap {
	// Map names to User object
	private static Map<String, User> usersOfStore = new HashMap<>();
	
	public UserMap() {
		// TODO Auto-generated constructor stub
		this.initializeUsers();
	}
	
	// initialize users of store
	void initializeUsers() {
		String[] userNames = { "sharath", "vinay" };
		String[] userPasswords = { "123", "321" };
		for (int index = 0; index < userNames.length; index++) {
			usersOfStore.put(userNames[index], new User(userNames[index], userPasswords[index]));
		}
	}

	// Check if user is valid
	boolean isUserValid(String userName, String password) {
		User currentUser = usersOfStore.get(userName);
		String currentUserPassword = currentUser.getUserPassword();
		if (password.equals(currentUserPassword))
			return true;
		return false;
	}
	
	//Add user to Map
	User addNewUser(String userName, String password) {
		User newUser = new User(userName,password);
		usersOfStore.put(userName, newUser);
		return newUser;
	}
	
	User getUserCredentials(String userName) {
		return usersOfStore.get(userName);
	}
}
