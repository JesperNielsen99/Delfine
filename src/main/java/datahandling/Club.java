package datahandling;

import java.util.ArrayList;

import member.Member;

public class Club {
    private ArrayList<Member> members = new ArrayList<>();
    private Member currentMember;

    public Club() {

    }

    public void createMember() {

    }

    public String searchMember() {
        return null;
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


}
