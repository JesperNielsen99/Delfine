package datahandling;

import datahandling.Club;
import datasource.FileHandler;
import member.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private Club club = new Club();
    private FileHandler fileHandler = new FileHandler();

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, Boolean isActive, Boolean isCompetitive){
        club.createMember(name, address, number, mail, birthdate, sex, isStudent, isActive, isCompetitive);
    }
    public ArrayList<Member> searchMember(String searchMemberName){
        return club.searchMember(searchMemberName);
    }

    public String deleteMember(int numberIndex){
        return club.deleteMember(numberIndex);
    }


    public ArrayList<Member> getMembers() {
        return club.getMembers();
    }

    public void loadMembers(){
        club.setMembers(fileHandler.loadMembers());
    }

    public void saveMembers(){
        fileHandler.saveMembers(club.getMembers());
    }
}
