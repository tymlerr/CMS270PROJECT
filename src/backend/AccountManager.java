package backend;

import java.util.ArrayList;

public class AccountManager {
    public ArrayList<Account> accounts = new ArrayList<Account>();
    public Account currentlySignedInAccount;

    // returns null if none is found
    public Account getAccountByUsername(String name)
    {
        for (Account account : accounts) {
            if(account.getUserName().equals(name))
                return account;
        }

        return null;
    }

    public Account addAccount(String _displayName, String _userName, String _password, String accountType)
    {
        if(getAccountByUsername(_userName) != null) return null;

        if(accountType == "Professor")
        {
            Professor newProf = new Professor(_displayName, _userName, _password);
            accounts.add(newProf);
            return newProf;
        } else {
            Student newStudent = new Student(_displayName, _userName, _password);
            accounts.add(newStudent);
            return newStudent;
        }
    }

    public void removeAccount(Account account)
    {
        accounts.remove(account);
    }

    public Account attemptLogin(String user, String pass) {
        for (Account a : accounts) {

            if (a.getUserName().equals(user) && a.getPassword().equals(pass)) {
                currentlySignedInAccount = a;
                return a;
            }
        }
        return null;
    }

    public void logOut()
    {
        currentlySignedInAccount = null;
    }
}
