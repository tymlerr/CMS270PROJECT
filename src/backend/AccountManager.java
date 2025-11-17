package backend;

import java.util.ArrayList;

public class AccountManager {
    public static ArrayList<Account> accounts = new ArrayList<Account>();

    // returns null if none is found
    public static Account getAccountByName(String name)
    {
        for (Account account : accounts) {
            if(account.getName().equals(name))
                return account;
        }

        return null;
    }

    public static Professor addProfessor(String _name, String _user, String _password)
    {
        if(getAccountByName(_name) != null) return null;

        Professor newProf = new Professor(_name, _user, _password);
        accounts.add(newProf);
        return newProf;
    }

    public static Student addStudent(String _name, String _user, String _password)
    {
        if(getAccountByName(_name) != null) return null;

        Student newStudent = new Student(_name, _user, _password);
        accounts.add(newStudent);
        return newStudent;
    }

    public static void removeAccount(Account account)
    {
        accounts.remove(account);
    }
}
