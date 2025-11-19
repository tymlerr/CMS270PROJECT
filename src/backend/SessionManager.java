package backend;

public class SessionManager {
    public static Account currentlySignedInAccount;

    public static Account attemptLogin(String user, String pass) {
        for (Account a : AccountManager.accounts) {
            if (a.getUserName().equals(user) && a.getPassword().equals(pass)) {
                currentlySignedInAccount = a;
                return a;
            }
        }
        return null;
    }

    public static void logOut()
    {
        currentlySignedInAccount = null;
    }
}
