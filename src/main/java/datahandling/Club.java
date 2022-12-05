package datahandling;

import java.time.LocalDate;
import java.util.ArrayList;
import member.Member;
import member.MembershipStatus;

public class Club {
    private final ArrayList<Member> currentMembers = new ArrayList<>();
    private final ArrayList<Member> formerMembers = new ArrayList<>();
    private final ArrayList<Member> searchResult = new ArrayList<>();


    private final Team teamJunior = new Team("Junior træner");
    private final Team teamSenior = new Team("Senior træner");

    //*--------------------------------------------------Create------------------------------------------------------*\\
    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, MembershipStatus activity, boolean hasPaid) {
        Member member = new Member(name, address, number, mail, birthdate, sex, isStudent, hasPaid, activity);
        currentMembers.add(member);
        addMemberToTeam(member);
    }

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, MembershipStatus isCompetitive, boolean hasPaid, boolean crawl, boolean BackCrawl, boolean breastStroke, boolean butterfly) {
        Member member = new Member(name, address, number, mail, birthdate, sex, isStudent, hasPaid, isCompetitive, crawl, BackCrawl, breastStroke, butterfly);
        currentMembers.add(member);
        addMemberToTeam(member);
    }

    //*--------------------------------------------------Search------------------------------------------------------*\\
    public void searchMember(String searchMemberName, MembershipStatus membershipStatus) {
        searchResult.clear();
        for (Member member : currentMembers) {
            if (member.getName().toLowerCase().contains(searchMemberName.toLowerCase())) {
                if (member.getActivity() == membershipStatus) {
                    searchResult.add(member);
                }
            }
        }
    }

    public void searchMember(String searchMemberName) {
        searchResult.clear();
        for (Member member : currentMembers) {
            if (member.getName().toLowerCase().contains(searchMemberName.toLowerCase())) {
                searchResult.add(member);
            }
        }
    }

    //*---------------------------------------------------Add--------------------------------------------------------*\\

    public ArrayList<Member> membersInDebt() {
        ArrayList<Member> membersInDebt = new ArrayList<>();
        for (Member member : currentMembers) {
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
        currentMembers.remove(currentMember);
        return String.format("%s er blevet slettet fra medlemslisten.", currentMemberName);
    }

    //*--------------------------------------------------Getter------------------------------------------------------*\\
    public int getSizeOfMembers(){
        return currentMembers.size();
    }

    public ArrayList<Member> getCurrentMembers(){
        return currentMembers;
    }

    public ArrayList<Member> getFormerMembers() {
        return formerMembers;
    }

    public ArrayList<Member> getSearchResult() {
        return searchResult;
    }

    public ArrayList<Member> getTeamJuniorMembers(){
        return teamJunior.getTeamMembers();
    }

    public ArrayList<Member> getTeamSeniorMembers(){
        return teamSenior.getTeamMembers();
    }

    public String[] getTrainers(){
        return new String[]{teamJunior.getTrainerName(),teamSenior.getTrainerName()};
    }

    public Team getTeamJunior() {
        return teamJunior;
    }

    public Team getTeamSenior() {
        return teamSenior;
    }


    //*--------------------------------------------------Setter------------------------------------------------------*\\
    public void setTrainers(String[] trainers){
        if(trainers != null) {
            teamJunior.setTrainerName(trainers[0]);
            teamSenior.setTrainerName(trainers[1]);
        }
    }

    public void setMembers(ArrayList<Member> members) {

        for (Member member : members) {
            if (member.getCurrentMember()){
                currentMembers.add(member);
            } else {
                formerMembers.add(member);
            }
        }
        addMembersToTeam(currentMembers);
    }


}
