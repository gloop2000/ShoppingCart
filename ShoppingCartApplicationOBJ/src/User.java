
public class User {
	private static Integer idCounter=1;
	private Integer userId;
	private String userName;
	private String password;
	
	public User(String userName, String password) {
		this.userId = idCounter++;
		this.userName = userName;
		this.password = password;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getUserPassword() {
		return this.password;
	}
}
