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


}
