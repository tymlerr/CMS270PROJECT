package backend;

public class Account {
    //Unique
    private String name;
    //display, not unique
    private String userName;
    private String password;

    public Account(String _name, String _user, String _password)
    {
        name = _name;
        userName = _user;
        password = _password;
    }

    public String getName() 
    {
        return name;
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
