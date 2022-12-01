package datahandling;

import java.time.LocalDate;
import java.util.ArrayList;
import member.Member;
import member.MembershipStatus;

public class Club {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Member> searchResult = new ArrayList<>();

    private Team teamJunior = new Team("Junior træner");
    private Team teamSenior = new Team("Senior træner");


    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, Boolean isActive, MembershipStatus isCompetitive, boolean hasPaid) {
        members.add(new Member(name, address, number, mail, birthdate, sex, isStudent, isActive, hasPaid, isCompetitive));
    }

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, Boolean isActive, MembershipStatus isCompetitive, boolean hasPaid, boolean crawl, boolean rygCrawl, boolean brystSvømning, boolean butterfly) {
        members.add(new Member(name, address, number, mail, birthdate, sex, isStudent, isActive, hasPaid, isCompetitive, crawl, rygCrawl, brystSvømning, butterfly));
    }

    public void searchMember(String searchMemberName, MembershipStatus membershipStatus) {
        searchResult.clear();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(searchMemberName.toLowerCase())) {
                if (member.getIsCompetitive() == membershipStatus) {
                    searchResult.add(member);
                }
            }
        }
    }

    public void searchMember(String searchMemberName) {
        searchResult.clear();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(searchMemberName.toLowerCase())) {
                searchResult.add(member);
            }
        }
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

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Member> getSearchResult() {
        return searchResult;
    }

    public ArrayList<Member> membersInDebt() {
        ArrayList<Member> membersInDebt = new ArrayList<>();
        for (Member member : members) {
            if (!member.getHasPaid()) {
                membersInDebt.add(member);
            }
        }
        return membersInDebt;
    }

    public void addMemberToTeam(Member member){
        if (member.getAge() < 18){
            teamJunior.addMemberToTeam(member);
        }
        else {
            teamSenior.addMemberToTeam(member);
        }
    }

    private void addMembersToTeam(ArrayList<Member> members){
        for (Member member : members){
            addMemberToTeam(member);
        }
    }

    public ArrayList<Member> getTeamJunior(){
        return teamJunior.getTeamMembers();
    }

    public ArrayList<Member> getTeamSenior(){
        return teamJunior.getTeamMembers();
    }
}
