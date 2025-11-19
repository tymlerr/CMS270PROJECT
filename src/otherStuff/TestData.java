package otherStuff;

import backend.*;

public class TestData {

    public static void populate() {
        // Professors
        Professor p1 = AccountManager.addProfessor("Alice", "alice", "pass123");
        Professor p2 = AccountManager.addProfessor("Bob", "bob", "password");

        // Students
        Student s1 = AccountManager.addStudent("Charlie", "charlie", "1234");
        Student s2 = AccountManager.addStudent("Dana", "dana", "abcd");

        // Clubs
        Club chessClub = new Club("Chess Club", p1);
        Club mathClub = new Club("Math Club", p2);

        ClubManager.clubs.add(chessClub);
        ClubManager.clubs.add(mathClub);

        // Add students to clubs
        s1.addToClub(chessClub);
        s2.addToClub(chessClub);
        s2.addToClub(mathClub);

        // Assign professors to advise clubs
        p1.setAdvisingClub(chessClub);
        p2.setAdvisingClub(mathClub);
    }
}
