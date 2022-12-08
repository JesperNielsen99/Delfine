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
    private boolean hasPaid;
    private MembershipStatus activity;
    private boolean crawl;
    private boolean backCrawl;
    private boolean breastStroke;
    private boolean butterfly;
    private boolean currentMember;

    private final ArrayList<Time> times;
    private final DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //*-----------------------------------------------Constructor----------------------------------------------------*\\
    public Member(String name, String address, String phoneNumber, String mail, LocalDate birthdate,
                  boolean sex, boolean isStudent, boolean hasPaid, MembershipStatus activity,
                  boolean currentMember) {
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
        times = new ArrayList<>();
    }

    public Member(String name, String address, String phoneNumber, String mail, LocalDate birthdate,
                  boolean sex, boolean isStudent, boolean hasPaid, MembershipStatus activity, boolean crawl,
                  boolean backCrawl, boolean breastStroke, boolean butterfly, boolean currentMember, ArrayList<Time> times) {
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
        if (times != null) {
            this.times = times;
        } else {
            this.times = new ArrayList<>();
        }
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
        stringBuilder.append("Aktive svømmediscipliner: ").append(activeSwimDisciplins()).append('\n');
        stringBuilder.append("Svømmetider: ").append(readSwimTimes());

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

    public MembershipStatus getActivity() {
        return activity;
    }

    public boolean getHasPaid() {
        return hasPaid;
    }

    public int getAge() {
        return LocalDate.now().compareTo(birthdate);
    }

    public boolean getCrawl() {
        return crawl;
    }

    public boolean getBackCrawl() {
        return backCrawl;
    }

    public boolean getBreastStroke() {
        return breastStroke;
    }

    public boolean getButterfly() {
        return butterfly;
    }

    public boolean getCurrentMember() {
        return currentMember;
    }

    public ArrayList<Time> getSwimTimes() {
        return times;
    }

    public double getBestTime(SwimDiscipline swimDiscipline) {
        double bestTime = Double.MAX_VALUE;
        for (Time time : times) {
            if (swimDiscipline == time.getSwimDisciplin()) {
                if (time.getTime() < bestTime) {
                    bestTime = time.getTime();
                }
            }
        }
        if (bestTime == Double.MAX_VALUE) {
            return 0;
        } else {
            return bestTime;
        }
    }

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

    public void addSwimTime(String name, double swimTime, LocalDate date, SwimDiscipline swimDiscipline) {
        times.add(new Time(name, swimTime, date, swimDiscipline));
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

    public String readSwimTime(Time time) {
        int minutes = (int) (time.getTime() / 60 % 60);
        double seconds = time.getTime() % 60;
        String secondsInString = String.format("%.2f", seconds);
        if (seconds < 10) {
            secondsInString = String.format("0%.2f", seconds);
        }
        return String.format("%s:%s", minutes, secondsInString).replace(',', '.');
    }

    public String readSwimTimes() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Time time : times) {
            String swimDisciplin = "";
            switch (time.getSwimDisciplin()) {
                case CRAWL -> swimDisciplin = "Crawl: ";
                case BACKCRAWL -> swimDisciplin = "Ryg crawl: ";
                case BREASTSTROKE -> swimDisciplin = "Bryst svømning: ";
                case BUTTERFLY -> swimDisciplin = "Butterfly: ";
            }
            stringBuilder.append(swimDisciplin);
            stringBuilder.append(readSwimTime(time)).append(" | ");
        }
        return stringBuilder.toString();
    }
}

