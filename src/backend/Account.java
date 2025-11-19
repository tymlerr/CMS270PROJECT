package backend;

public class Account {
    //Unique
    private String userName;
    //display, not unique
    private String displayName;
    private String password;

    public Account(String _displayName, String _userName, String _password)
    {
        displayName = _displayName;
        userName = _userName;
        password = _password;
    }

    public String getDisplayName() 
    {
        return displayName;
    }

    public String getPassword() 
    {
        return password;
    }

    public String getUserName() 
    {
        return userName;
    }
}
