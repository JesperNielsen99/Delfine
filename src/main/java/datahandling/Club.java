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
            String deleteedMemberName = members.get(memberIndex).getName();
            members.remove(memberIndex);
            return deleteedMemberName;
        }
        else {
            return null;
        }
    }
}
