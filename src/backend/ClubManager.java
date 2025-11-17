package backend;

import java.util.ArrayList;

public class ClubManager {
    public static ArrayList<Club> clubs = new ArrayList<Club>();

    // tries to create club, if it works it adds it to clubs
    public static Boolean createClub(String name, Professor advisor)
    {
        //add check to make sure teacher is not already advising another club
        clubs.add(new Club(name, advisor));
        return true;
    }
}
