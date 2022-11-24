package datahandling;

import datahandling.Club;
import datasource.FileHandler;
import member.Member;

import java.util.ArrayList;

public class Controller {
    private Club club = new Club();
    private FileHandler fileHandler = new FileHandler();

    public void createMember(){
        club.createMember();
    }
    public ArrayList<Member> searchMember(String searchMemberName){
        return club.searchMember(searchMemberName);
    }

    public String deleteMember(int numberIndex){
        return club.deleteMember(numberIndex);
    }


}
