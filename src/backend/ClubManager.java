package backend;

import java.util.ArrayList;

public class ClubManager {
    public ArrayList<Club> clubs = new ArrayList<Club>();
    // potential clubs
    private ArrayList<ClubRequest> requests = new ArrayList<>();

    public Club getClubByName(String name)
    {
        for (Club club : clubs) {
            if(club.getClubName().equals(name))
                return club;
        }

        return null;
    }

    public Club[] getUnjoinedClubs(Student stu)
    {
        ArrayList<Club> found = new ArrayList<>();
        for (Club club : clubs) {
            boolean contains = false;
            for (Student student : club.getClubMembers()) {
                if(student == stu)
                    contains = true;
            }

            if(!contains)
                found.add(club);
        }

        return found.toArray(new Club[0]);
    }

    public void clearClubRequestsToProfessor(Professor prof)
    {
        for (int i = requests.size() - 1; i >= 0; i--) {
            if(requests.get(i).toAdvise == prof)
                requests.remove(i);
        }
    }

    public ClubRequest[] getRequestsByProfessor(Professor prof)
    {
        ArrayList<ClubRequest> clubRequest = new ArrayList<>();
        for (ClubRequest req : requests) {
            if(req.toAdvise == prof)
                clubRequest.add(req);
        }

        return clubRequest.toArray(new ClubRequest[0]);
    }

    public void submitClubRequest(Professor toAdvise, Student sender, String clubName)
    {
        ClubRequest req = new ClubRequest();
        req.potentialClubName = clubName;
        req.senderStudent = sender;
        req.toAdvise = toAdvise;

        requests.add(req);
    }

    public boolean acceptRequest(ClubRequest req)
    {
        if(req.toAdvise.getAdvisingClub() != null) 
            return false;

        if(getClubByName(req.potentialClubName) != null)
            return false;

        Club newClub = new Club(req.potentialClubName, req.toAdvise);
        newClub.addMember(req.senderStudent);
        clubs.add(newClub);
        clearClubRequestsToProfessor(req.toAdvise);
        return true;
    }
}
