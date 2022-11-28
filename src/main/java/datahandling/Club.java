package datahandling;

import java.time.LocalDate;
import java.util.ArrayList;

import member.Member;

public class Club {
    private ArrayList<Member> members = new ArrayList<>();

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, Boolean isActive, Boolean isCompetitive) {
        members.add(new Member(name, address, number, mail, birthdate, sex, isStudent, isActive, isCompetitive));
    }

    public ArrayList<Member> searchMember(String searchMemberName) {
        ArrayList<Member> searchResult = new ArrayList<>();
        if (!members.isEmpty()) {
            for (Member member : members) {
                if (member.getName().toLowerCase().contains(searchMemberName.toLowerCase())) {
                    searchResult.add(member);
                }
            }
        }
        return searchResult;
    }

    public String deleteMember(Member currentMember) {
        String currentMemberName = currentMember.getName();
        members.remove(currentMember);
        return String.format("%s er blevet slettet fra medlemslisten.", currentMemberName);
    }


    public int getSizeOfMembers(){
        return members.size();
    }

    public ArrayList<Member> getMembers(){
        return members;
    }

}
