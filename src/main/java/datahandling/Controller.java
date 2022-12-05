package datahandling;

import datasource.FileHandler;
import member.Member;
import member.MembershipStatus;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private Subscription subscription = new Subscription();
    private final Club club = new Club();
    private final FileHandler fileHandler = new FileHandler();

    //*--------------------------------------------------Create------------------------------------------------------*\\
    public void createMember(String name, String address, String number, String mail, LocalDate birthdate,
                             boolean sex, Boolean isStudent, MembershipStatus activity, boolean hasPaid, boolean currentMember){
        club.createMember(name, address, number, mail, birthdate, sex, isStudent, activity, hasPaid, currentMember);
    }

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex, Boolean isStudent,
                             MembershipStatus activity, boolean hasPaid, boolean crawl, boolean backCrawl, boolean breastStroke,
                             boolean butterfly, boolean currentMember){
        club.createMember(name, address, number, mail, birthdate, sex, isStudent, activity, hasPaid, crawl, backCrawl, breastStroke, butterfly, currentMember);
    }

    //*--------------------------------------------------Search------------------------------------------------------*\\
    public void searchMember(String searchMemberName, MembershipStatus membershipStatus) {
        club.searchMember(searchMemberName, membershipStatus);
    }

    public void searchMember(String searchMemberName) {
        club.searchMember(searchMemberName);
    }

    //*-------------------------------------------------Delete-------------------------------------------------------*\\
    public String deleteMember(Member currentMember){
        return club.deleteMember(currentMember);
    }

    //*--------------------------------------------------Save--------------------------------------------------------*\\

    public void saveMembers(){
        fileHandler.saveMembers(club.getCurrentMembers());
    }

    public void saveSubscription(){
        fileHandler.saveSubscription(subscription);
    }

    public void saveTrainers(){
        fileHandler.saveTrainer(club.getTrainers());
    }

    //*--------------------------------------------------Load--------------------------------------------------------*\\

    public void loadMembers(){
        club.setMembers(fileHandler.loadMembers());
    }

    public void loadSubscription(){
        Subscription loadedSubscription = fileHandler.loadSubscription();
        if (loadedSubscription != null) {
            subscription = loadedSubscription;
        }
    }

    public void loadTrainers(){
        club.setTrainers(fileHandler.loadTrainers());
    }


    //*--------------------------------------------------Getter------------------------------------------------------*\\
    public ArrayList<Member> getMembers() {
        return club.getCurrentMembers();
    }

    public ArrayList<Member> getSearchResult() { return club.getSearchResult(); }

    public double getExpectedTotalIncome() {
        return subscription.expectedTotalIncome(club.getCurrentMembers());
    }

    public ArrayList<Member> getMembersInDebt(){
        return club.membersInDebt();
    }

    public double getPassive(){
       return subscription.getPassive();
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

    public ArrayList<Member> getTeamJuniorMembers(){
        return club.getTeamJuniorMembers();
    }

    public ArrayList<Member> getTeamSeniorMembers(){
        return club.getTeamSeniorMembers();
    }

    public double calculateMemberSubscription(Member member) {
        return subscription.calculateMemberSubscription(member);
    }

    public Team getTeamJunior() {
       return club.getTeamJunior();
    }

    public Team getTeamSenior() {
        return club.getTeamSenior();
    }

    //*--------------------------------------------------Setter------------------------------------------------------*\\
    public void setPassive(double passive){
        subscription.setPassive(passive);
    }

    public void setJunior (double junior){
        subscription.setJunior(junior);
    }

    public void setSenior(double senior){
        subscription.setSenior(senior);
    }

    public void setSeniorPlus(double seniorPlus){
        subscription.setSeniorPlus(seniorPlus);
    }

    public void setStudent(double student){
        subscription.setStudent(student);
    }

}
