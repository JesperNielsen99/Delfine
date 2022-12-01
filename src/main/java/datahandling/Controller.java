package datahandling;

import datasource.FileHandler;
import member.Member;
import member.MembershipStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private Subscription subscription = new Subscription();
    private Club club = new Club();
    private FileHandler fileHandler = new FileHandler();

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, Boolean isActive, MembershipStatus isCompetitive, boolean hasPaid){
        club.createMember(name, address, number, mail, birthdate, sex, isStudent, isActive, isCompetitive, hasPaid);
    }

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent, Boolean isActive, MembershipStatus isCompetitive,
                             boolean hasPaid, boolean crawl, boolean rygCrawl, boolean brystSvømning, boolean butterfly){
        club.createMember(name, address, number, mail, birthdate, sex, isStudent, isActive, isCompetitive, hasPaid, crawl, rygCrawl, brystSvømning, butterfly);
    }

    public void searchMember(String searchMemberName){ club.searchMember(searchMemberName); }

    public String deleteMember(Member currentMember){
        return club.deleteMember(currentMember);
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
    public ArrayList<Member> getSearchResult() { return club.getSearchResult(); }

    public double calculateMemberSubscription(Member member) {
        return subscription.calculateMemberSubscription(member);
    }

    public double getExpectedTotalIncome() {
        return subscription.expectedTotalIncome(club.getMembers());
    }

    public ArrayList<Member> getMembersInDebt(){
        return club.membersInDebt();
    }

    public double getpassiv(){
       return subscription.getPassiv();
    }
    public double getJunior(){
        return subscription.getJunior();
    }
    public double getSenior(){
        return subscription.getSenior();
    }
    public double getSeniorPlus(){
        return subscription.getSeniorPlus();
    }
    public double getStudent(){
        return subscription.getStudent();
    }


    public void setPassiv(double passiv){
        subscription.setPassiv(passiv);
    }

    public void setJunior (double junior){
        subscription.setJunior(junior);
    }

    public void setSenior(double senior){
        subscription.setSenior(senior);
    }

    public void setSeniorPlus(double seniorPlus){
        subscription.setSenior(seniorPlus);
    }

    public void setStudent(double student){
        subscription.setStudent(student);
    }

}
