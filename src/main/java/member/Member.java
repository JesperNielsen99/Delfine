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

    public boolean isStudent() {
        return isStudent;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isCompetitive() {
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

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCompetitive(boolean competitive) {
        isCompetitive = competitive;
    }

    public String printMember() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name).append(" ");
        stringBuilder.append(address).append(" ");
        stringBuilder.append(phoneNumber).append(" ");
        stringBuilder.append(mail).append(" ");
        stringBuilder.append(birthday).append(" ");
        stringBuilder.append(sex).append(" ");
        stringBuilder.append(isStudent).append(" ");
        stringBuilder.append(isActive).append(" ");
        stringBuilder.append(isCompetitive).append(" ");
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

}