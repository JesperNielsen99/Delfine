package member;

import java.time.LocalDate;

public class Member {
    private String name;
    private String address;
    private String phoneNumber;
    private String mail;
    private LocalDate birthday;
    private boolean sex;
    private boolean isStudent;
    private boolean isActive;
    private boolean isCompetitive;

    public Member(String name, String address, String phoneNumber, String mail, LocalDate birthday,
                  boolean sex, boolean isStudent, boolean isActive, boolean isCompetitive) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.birthday = birthday;
        this.sex = sex;
        this.isStudent = isStudent;
        this.isActive = isActive;
        this.isCompetitive = isCompetitive;
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

    public LocalDate getBirthday() {
        return birthday;
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

    public boolean getIsCompetitive() {
        return isCompetitive;
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

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public void setIsCompetitive(boolean competitive) {
        isCompetitive = competitive;
    }

    public String readSex(){
        return sex? "M" : "K";
    }

    public String readIsStudent(){
        return isStudent? "Ja": "Nej";
    }

    public String readIsActive(){
        return isActive? "Ja" : "Nej";
    }

    public String readIsCompetitive(){
        return isCompetitive? "Ja" : "Nej";
    }


    public String printMember() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Navn: " + name).append('\n');
        stringBuilder.append("Adresse: " + address).append('\n');
        stringBuilder.append("Tlf.nr.: " + phoneNumber).append('\n');
        stringBuilder.append("E-mail: " + mail).append('\n');
        stringBuilder.append("Fødselsdato: " + birthday).append('\n');
        stringBuilder.append("Køn: " + readSex()).append('\n');
        stringBuilder.append("Studerende: " + readIsStudent()).append('\n');
        stringBuilder.append("Aktivitetsform: " + readIsActive()).append('\n');
        stringBuilder.append("Konkurrencesvømmer: " + readIsCompetitive()).append('\n');
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

}