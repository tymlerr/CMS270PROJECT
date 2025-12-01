package backend;

import java.util.ArrayList;

public class Club {
    private ArrayList<Student> clubMembers;
    private Professor clubAdvisor;
    private String clubName;

    public Club(String name, Professor advisor)
    {
        clubName = name;
        clubAdvisor = advisor;
        clubMembers = new ArrayList<Student>();
        advisor.setAdvisingClub(this);
    }

    public String getClubName()
    {
        return clubName;
    }

    public Professor getAdvisor()
    {
        return clubAdvisor;
    }

    public Student[] getClubMembers()
    {
        return clubMembers.toArray(new Student[clubMembers.size()]);
    }

    public void addMember(Student student)
    {
        if(clubMembers.contains(student))
            return;
        clubMembers.add(student);
        student.addToClub(this);
    }

    public void removeMember(Student student)
    {
        clubMembers.remove(student);
        student.removeFromClub(this);
    }

    @Override
    public String toString() {
        return clubName;
    }
}
