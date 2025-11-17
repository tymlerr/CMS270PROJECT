package backend;

import java.util.ArrayList;

public class Student extends Account{

    private ArrayList<Club> clubs;

    public Student(String _name, String _user, String _password) 
    {
        super(_name, _user, _password);
        clubs = new ArrayList<Club>();
    }

    public void addToClub(Club newClub)
    {
        if(clubs.contains(newClub)) return;
        clubs.add(newClub);
    }

    public void removeFromClub(Club club)
    {
        clubs.remove(club);
    }

}
