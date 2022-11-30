package datahandling;

import member.Member;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;


class ClubTest {

    Club club;

    @BeforeEach
    public void setup(){
        club = new Club();
        club.createMember("Test testen testesten","Himmelbjerget 69","10101010", "test@gmail.com", LocalDate.now(), true, true, true, true,true);
    }

    @org.junit.jupiter.api.Test
    void searchMember() {
    //arrange
        String searchTest = "Test";
        ArrayList<Member> testArray = club.searchMember(searchTest);

        assertTrue(testArray.size() != 0);








    }

    @org.junit.jupiter.api.Test
    void deleteMember() {
        //Arrange
        assertTrue(club.getSizeOfMembers() > 0);

        //Act
        club.deleteMember(club.getMembers().get(0));
        int expectedSize = 0;

        //Assert (true)
        assertEquals(expectedSize,club.getSizeOfMembers());

    }
}