import java.util.Date;

public class Member {
    private String name;
    private String address;
    private String phoneNumber;
    private String mail;
    private Date birthday;
    private String sex;
    private boolean isStudent;
    private boolean isActive;
    private boolean isCompetitive;

    public Member(String name, String address, String phoneNumber, String mail, Date birthday, String sex, Boolean isStudent, Boolean isActive, Boolean isCompetitive) {
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

    public String toString() {
        return null;
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

    public Date getBirthday() {
        return birthday;
    }

    public String getSex() {
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

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setSex(String sex) {
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
}