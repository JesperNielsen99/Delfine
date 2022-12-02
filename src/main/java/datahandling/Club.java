package datahandling;

import java.time.LocalDate;
import java.util.ArrayList;
import member.Member;
import member.MembershipStatus;

public class Club {
    private ArrayList<Member> members = new ArrayList<>();
    private final ArrayList<Member> searchResult = new ArrayList<>();

    private final Team teamJunior = new Team("Junior træner");
    private final Team teamSenior = new Team("Senior træner");

    //*--------------------------------------------------Create------------------------------------------------------*\\
    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, MembershipStatus activity, boolean hasPaid) {
        Member member = new Member(name, address, number, mail, birthdate, sex, isStudent, hasPaid, activity);
        members.add(member);
        addMemberToTeam(member);
    }

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, MembershipStatus isCompetitive, boolean hasPaid, boolean crawl, boolean BackCrawl, boolean breastStroke, boolean butterfly) {
        Member member = new Member(name, address, number, mail, birthdate, sex, isStudent, hasPaid, isCompetitive, crawl, BackCrawl, breastStroke, butterfly);
        members.add(member);
        addMemberToTeam(member);
    }

    //*--------------------------------------------------Search------------------------------------------------------*\\
    public void searchMember(String searchMemberName, MembershipStatus membershipStatus) {
        searchResult.clear();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(searchMemberName.toLowerCase())) {
                if (member.getActivity() == membershipStatus) {
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

    //*---------------------------------------------------Add--------------------------------------------------------*\\

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
        if (member.getActivity().equals(MembershipStatus.COMPETITIVE)) {
            if (member.getAge() < 18) {
                teamJunior.addMemberToTeam(member);
            } else {
                teamSenior.addMemberToTeam(member);
            }
        }
    }

    private void addMembersToTeam(ArrayList<Member> members){
        for (Member member : members){
            addMemberToTeam(member);
        }
    }

    //*-------------------------------------------------Delete-------------------------------------------------------*\\
    public String deleteMember(Member currentMember) {
        String currentMemberName = currentMember.getName();
        members.remove(currentMember);
        return String.format("%s er blevet slettet fra medlemslisten.", currentMemberName);
    }

    //*--------------------------------------------------Getter------------------------------------------------------*\\
    public int getSizeOfMembers(){
        return members.size();
    }

    public ArrayList<Member> getMembers(){
        return members;
    }

    public ArrayList<Member> getSearchResult() {
        return searchResult;
    }

    public ArrayList<Member> getTeamJunior(){
        return teamJunior.getTeamMembers();
    }

    public ArrayList<Member> getTeamSenior(){
        return teamSenior.getTeamMembers();
    }

    public String[] getTrainers(){
        return new String[]{teamJunior.getTrainerName(),teamSenior.getTrainerName()};
    }

    //*--------------------------------------------------Setter------------------------------------------------------*\\
    public void setTrainers(String[] trainers){
        if(trainers != null) {
            teamJunior.setTrainerName(trainers[0]);
            teamSenior.setTrainerName(trainers[1]);
        }
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
        addMembersToTeam(this.members);
    }
}
