package backend;

import java.util.ArrayList;

public class AccountManager {
    public static ArrayList<Account> accounts = new ArrayList<Account>();

    // returns null if none is found
    public static Account getAccountByName(String name)
    {
        for (Account account : accounts) {
            if(account.getUserName().equals(name))
                return account;
        }

        return null;
    }

    public static Professor addProfessor(String _displayName, String _userName, String _password)
    {
        if(getAccountByName(_userName) != null) return null;

        Professor newProf = new Professor(_displayName, _userName, _password);
        accounts.add(newProf);
        return newProf;
    }

    public static Student addStudent(String _displayName, String _userName, String _password)
    {
        if(getAccountByName(_userName) != null) return null;

        Student newStudent = new Student(_displayName, _userName, _password);
        accounts.add(newStudent);
        return newStudent;
    }

    public static void removeAccount(Account account)
    {
        accounts.remove(account);
    }
}
