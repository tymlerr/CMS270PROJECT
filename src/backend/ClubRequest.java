package backend;

public class ClubRequest {
    public Student senderStudent;
    public Professor toAdvise;
    public String potentialClubName;

    @Override
    public String toString() {
        return potentialClubName;
    }
}
