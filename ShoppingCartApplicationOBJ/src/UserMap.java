import java.util.HashMap;
import java.util.Map;

public class UserMap<K, V> extends HashMap<K, V>{
	
	private static final long serialVersionUID = 1L;
	private Map<String,User> users = new HashMap<>();
	
	public UserMap(){
		this.initUsers();
	}

	private void initUsers() {
		// TODO Auto-generated method stub
		String[] userNames = {"sharath","vinay"};
		String[] userPasswords = {"123","321"};
		for(int index=0; index < userNames.length; index++) {
			this.users.put(userNames[index], new  User(userNames[index], userPasswords[index]));
		}
	}
	
	public User addUser(String userName, String userPassword) {
		this.users.put(userName,new User(userName,userPassword));
		return users.get(userName);
	}
	
	public boolean isValidUser(String userName,String userPassword) {
		User currentUser = users.get(userName);
		if(currentUser != null) {
			String currentPassword = currentUser.getUserPassword();
			if(currentPassword.equals(userPassword))
				return true;
		}
		return false;
	}
	
	public User get(String userName) {
		return users.get(userName);
	}
}
