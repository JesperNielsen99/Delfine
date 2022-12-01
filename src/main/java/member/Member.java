package member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Member {
    private String name;
    private String address;
    private String phoneNumber;
    private String mail;
    private LocalDate birthdate;
    private boolean sex;
    private boolean isStudent;
    private boolean isActive;
    private MembershipStatus isCompetitive;
    private boolean hasPaid;
    private boolean crawl;
    private boolean rygCrawl;
    private boolean brystSvømning;
    private boolean butterfly;

    private final DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Member(String name, String address, String phoneNumber, String mail, LocalDate birthdate,
                  boolean sex, boolean isStudent, boolean isActive, boolean hasPaid, MembershipStatus isCompetitive) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.birthdate = birthdate;
        this.sex = sex;
        this.isStudent = isStudent;
        this.isActive = isActive;
        this.isCompetitive = isCompetitive;
        this.hasPaid = hasPaid;
        crawl = false;
        rygCrawl = false;
        brystSvømning = false;
        butterfly = false;
    }

    public Member(String name, String address, String phoneNumber, String mail, LocalDate birthdate,
                  boolean sex, boolean isStudent, boolean isActive, boolean hasPaid, MembershipStatus isCompetitive,
                  boolean crawl, boolean rygCrawl, boolean brystSvømning, boolean butterfly) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.birthdate = birthdate;
        this.sex = sex;
        this.isStudent = isStudent;
        this.isActive = isActive;
        this.isCompetitive = isCompetitive;
        this.hasPaid = hasPaid;
        this.crawl = crawl;
        this.rygCrawl = rygCrawl;
        this.brystSvømning = brystSvømning;
        this.butterfly = butterfly;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public boolean getSex() {
        return sex;
    }

    public boolean getIsStudent() {
        return isStudent;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public MembershipStatus getIsCompetitive() {
        return isCompetitive;
    }

    public boolean getHasPaid() {
        return hasPaid;
    }

    public int getAge() {
        return LocalDate.now().compareTo(birthdate);
    }

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

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public void setIsCompetitive(MembershipStatus competitive) {
        isCompetitive = competitive;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public boolean getCrawl() {
        return crawl;
    }

    public void setCrawl(boolean crawl) {
        this.crawl = crawl;
    }

    public boolean getRygCrawl() {
        return rygCrawl;
    }

    public void setRygCrawl(boolean rygCrawl) {
        this.rygCrawl = rygCrawl;
    }

    public boolean getBrystSvømning() {
        return brystSvømning;
    }

    public void setBrystSvømning(boolean brystSvømning) {
        this.brystSvømning = brystSvømning;
    }

    public boolean getButterfly() {
        return butterfly;
    }

    public void setButterfly(boolean butterfly) {
        this.butterfly = butterfly;
    }

    public String readSex() {
        return sex ? "M" : "K";
    }

    public String readIsStudent() {
        return isStudent ? "Ja" : "Nej";
    }

    public String readIsActive() {
        return isActive ? "Ja" : "Nej";
    }

    public String readIsCompetitive() {
        String competitive = "";
        switch (isCompetitive) {
            case COMPETITIVE -> {
                competitive = "Ja";
            }
            case HOBBY -> {
                competitive = "Nej";
            }
            case NONE -> {
                competitive = "Er ikke aktiv";
            }
        }
        return competitive;
    }

    public String readHasPaid() {
        return hasPaid ? "Nej" : "Ja";
    }

    private String activeSwimDisciplins() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(crawl ? "Crawl, " : "");
        stringBuilder.append(rygCrawl ? "Rygcrawl, " : "");
        stringBuilder.append(brystSvømning ? "Bryst svømning, " : "");
        stringBuilder.append(butterfly ? "Butterfly" : "");
        return stringBuilder.toString();
    }

    public String printMember() {
        if (isCompetitive == MembershipStatus.COMPETITIVE) {
            return printCompetitiveMember();
        } else {
            return printNonCompetitiveMember();
        }
    }

    private String printNonCompetitiveMember() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Navn: " + name).append('\n');
        stringBuilder.append("Adresse: " + address).append('\n');
        stringBuilder.append("Tlf.nr.: " + phoneNumber).append('\n');
        stringBuilder.append("E-mail: " + mail).append('\n');
        stringBuilder.append("Fødselsdato: " + birthdate.format(birthdayFormat)).append('\n');
        stringBuilder.append("Alder: " + getAge()).append('\n');
        stringBuilder.append("Køn: " + readSex()).append('\n');
        stringBuilder.append("Studerende: " + readIsStudent()).append('\n');
        stringBuilder.append("Aktivitetsform: " + readIsActive()).append('\n');
        stringBuilder.append("Restance: " + readHasPaid());

        return stringBuilder.toString();
    }

    private String printCompetitiveMember() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Navn: " + name).append('\n');
        stringBuilder.append("Adresse: " + address).append('\n');
        stringBuilder.append("Tlf.nr.: " + phoneNumber).append('\n');
        stringBuilder.append("E-mail: " + mail).append('\n');
        stringBuilder.append("Fødselsdato: " + birthdate.format(birthdayFormat)).append('\n');
        stringBuilder.append("Alder: " + getAge()).append('\n');
        stringBuilder.append("Køn: " + readSex()).append('\n');
        stringBuilder.append("Studerende: " + readIsStudent()).append('\n');
        stringBuilder.append("Aktivitetsform: " + readIsActive()).append('\n');
        stringBuilder.append("Restance: " + readHasPaid()).append('\n');
        stringBuilder.append("Aktive svømmediscipliner: " + activeSwimDisciplins());

        return stringBuilder.toString();
    }
}

