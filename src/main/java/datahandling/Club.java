package datahandling;

import java.time.LocalDate;
import java.util.ArrayList;

import member.Member;

public class Club {
    private ArrayList<Member> members = new ArrayList<>();
    private Member currentMember;

    public Club() {

    }

    public void createMember() {
        currentMember = new Member();
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

    public String deleteMember(int memberIndex) {
        if (members.size() >= memberIndex) {
            String deletedMemberName = members.get(memberIndex).getName();
            members.remove(memberIndex);
            return deletedMemberName;
        }
        else {
            return null;
        }
    }

    public void setCurrentMemberName(String newMemberName){
        currentMember.setName(newMemberName);
    }
    public void setCurrentMemberAddress(String newMemberAddress){
        currentMember.setAddress(newMemberAddress);
    }
    public void setCurrentMemberPhoneNumber(String newMemberPhoneNumber){
        currentMember.setPhoneNumber(newMemberPhoneNumber);
    }
    public void setCurrentMemberMail(String newMemberMail){
        currentMember.setMail(newMemberMail);
    }
    public void setCurrentMemberBirthday(LocalDate newMemberBirthday){
        currentMember.setBirthday(newMemberBirthday);
    }
    public void setCurrentMemberSex(String newMemberSex){
        currentMember.setSex(newMemberSex);
    }
    public void setCurrentMemberIsStudent(Boolean newMemberIsStudent){
        currentMember.setStudent(newMemberIsStudent);
    }
    public void setCurrentMemberIsActive(Boolean newMemberIsActive){
        currentMember.setActive(newMemberIsActive);
    }
    public void setCurrentMemberCompetitive(Boolean newMemberIsCompetitive){
        currentMember.setCompetitive(newMemberIsCompetitive);
    }

}
