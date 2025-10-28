
public abstract class User {
	protected String user;
	protected String password;
	protected String name;
	
	// constructor
	public User(String user, String password, String name) {
		
	}
	
	// get name and username
	// getter for getting name
	public String getName() {
		return name;
	}
	
	// getter for getting user
	public String getUser() {
		return user;
	}
	
	
	// makes sure password inputed matches set pass (used by account manager during log in)
	public boolean checkPassword(String input) {
		
	}
	
	// detects professor or student
	public abstract String getRole();
		
	}

