package member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Member {
    private String name;
    private String address;
    private String phoneNumber;
    private String mail;
    private LocalDate birthdate;
    private boolean sex;
    private boolean isStudent;
    private MembershipStatus activity;
    private boolean hasPaid;
    private boolean crawl;
    private boolean backCrawl;
    private boolean breastStroke;
    private boolean butterfly;
    private boolean currentMember;
    private final ArrayList<Time> times = new ArrayList<>();

    private final DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //*-----------------------------------------------Constructor----------------------------------------------------*\\
    public Member(String name, String address, String phoneNumber, String mail, LocalDate birthdate,
                  boolean sex, boolean isStudent, boolean hasPaid, MembershipStatus activity, boolean currentMember) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.birthdate = birthdate;
        this.sex = sex;
        this.isStudent = isStudent;
        this.activity = activity;
        this.hasPaid = hasPaid;
        crawl = false;
        backCrawl = false;
        breastStroke = false;
        butterfly = false;
        this.currentMember = currentMember;
    }

    public Member(String name, String address, String phoneNumber, String mail, LocalDate birthdate,
                  boolean sex, boolean isStudent, boolean hasPaid, MembershipStatus activity,
                  boolean crawl, boolean backCrawl, boolean breastStroke, boolean butterfly, boolean currentMember) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.birthdate = birthdate;
        this.sex = sex;
        this.isStudent = isStudent;
        this.activity = activity;
        this.hasPaid = hasPaid;
        this.crawl = crawl;
        this.backCrawl = backCrawl;
        this.breastStroke = breastStroke;
        this.butterfly = butterfly;
        this.currentMember = currentMember;
    }

    //*---------------------------------------------PrintMemberInfo--------------------------------------------------*\\
    public String printMember() {
        if (activity == MembershipStatus.COMPETITIVE) {
            return printCompetitiveMember();
        } else {
            return printNonCompetitiveMember();
        }
    }

    private String printNonCompetitiveMember() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Navn: ").append(name).append('\n');
        stringBuilder.append("Adresse: ").append(address).append('\n');
        stringBuilder.append("Tlf.nr.: ").append(phoneNumber).append('\n');
        stringBuilder.append("E-mail: ").append(mail).append('\n');
        stringBuilder.append("Fødselsdato: ").append(birthdate.format(birthdayFormat)).append('\n');
        stringBuilder.append("Alder: ").append(getAge()).append('\n');
        stringBuilder.append("Køn: ").append(readSex()).append('\n');
        stringBuilder.append("Studerende: ").append(readIsStudent()).append('\n');
        stringBuilder.append("Aktivitetsform: ").append(readActivity()).append('\n');
        stringBuilder.append("Restance: ").append(readHasPaid());

        return stringBuilder.toString();
    }

    private String printCompetitiveMember() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(printNonCompetitiveMember()).append('\n');
        stringBuilder.append("Aktive svømmediscipliner: ").append(activeSwimDisciplins());

        return stringBuilder.toString();
    }

    private String activeSwimDisciplins() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(crawl ? "| Crawl |" : "");
        stringBuilder.append(backCrawl ? "| Rygcrawl |" : "");
        stringBuilder.append(breastStroke ? "| Bryst svømning |" : "");
        stringBuilder.append(butterfly ? "| Butterfly |" : "");
        return stringBuilder.toString();
    }

    //*--------------------------------------------------Getter------------------------------------------------------*\\
    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getMail() { return mail; }

    public LocalDate getBirthdate() { return birthdate; }

    public boolean getSex() { return sex; }

    public boolean getIsStudent() { return isStudent; }

    public MembershipStatus getActivity() { return activity; }

    public boolean getHasPaid() { return hasPaid; }

    public int getAge() { return LocalDate.now().compareTo(birthdate); }

    public boolean getCrawl() { return crawl; }

    public boolean getBackCrawl() { return backCrawl; }

    public boolean getBreastStroke() { return breastStroke; }

    public boolean getButterfly() { return butterfly; }

    public boolean getCurrentMember() { return currentMember; }

    public ArrayList<Time> getTimes() { return times; }

    //*--------------------------------------------------Setter------------------------------------------------------*\\

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBirthdate(LocalDate birthday) {
        this.birthdate = birthday;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setIsStudent(boolean student) {
        isStudent = student;
    }

    public void setActivity(MembershipStatus competitive) {
        activity = competitive;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public void setCrawl(boolean crawl) {
        this.crawl = crawl;
    }

    public void setBackCrawl(boolean backCrawl) {
        this.backCrawl = backCrawl;
    }

    public void setBreastStroke(boolean breastStroke) {
        this.breastStroke = breastStroke;
    }

    public void setButterfly(boolean butterfly) {
        this.butterfly = butterfly;
    }

    public void setCurrentMember(boolean currentMember) {
       this.currentMember = currentMember;
    }

    //*-------------------------------------------------READS--------------------------------------------------------*\\
    public String readSex() {
        return sex ? "M" : "K";
    }

    public String readIsStudent() {
        return isStudent ? "Ja" : "Nej";
    }

    public String readActivity() {
        String competitive = "";
        switch (activity) {
            case COMPETITIVE -> competitive = "Konkurrence Svømmer";
            case HOBBY -> competitive = "Motionist";
            case NONE -> competitive = "Er ikke aktiv";
        }
        return competitive;
    }

    public String readHasPaid() {
        return hasPaid ? "Nej" : "Ja";
    }
}

