package datasource;

import datahandling.Club;
import member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    FileHandler fileHandler;
    Club club;

    //*--------------------------------------------------Setup------------------------------------------------------*\\
    @BeforeEach
    public void setup(){
        fileHandler = new FileHandler();
        club = new Club();
    }

    //*---------------------------------------------------Test-------------------------------------------------------*\\
    @Test
    void loadMembers() {
        ArrayList<Member> members = fileHandler.loadMembers("test file.txt");
        int expectedValue = 3;
        assertEquals(expectedValue,members.size());

        assertTrue(members.get(0).getSex());
        assertEquals(members.get(1).getName(), "test2");
        assertEquals(members.get(2).getMail(), "test3@");
    }
}