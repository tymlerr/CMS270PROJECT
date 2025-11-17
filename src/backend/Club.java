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
        clubMembers.add(student);
    }
}
