package backend;

public class Professor extends Account {

    private Club advisingClub;

    public Professor(String _name, String _user, String _password) 
    {
        super(_name, _user, _password);
    }

    public void setAdvisingClub(Club _advisingClub) 
    {
        advisingClub = _advisingClub;
    }

    public Club getAdvisingClub() 
    {
        return advisingClub;
    }

}
