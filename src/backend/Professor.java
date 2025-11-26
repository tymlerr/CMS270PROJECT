package backend;

public class Professor extends Account {
    private Club advisingClub;

    public Professor(String _displayName, String _userName, String _password) 
    {
        super(_displayName, _userName, _password);
    }

    public void setAdvisingClub(Club _advisingClub) 
    {
        advisingClub = _advisingClub;
    }

    public Club getAdvisingClub() 
    {
        return advisingClub;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
