package datasource;

import datahandling.Subscription;
import member.Member;
import member.MembershipStatus;
import member.SwimDiscipline;
import member.Time;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final File membersFile = new File("Members.txt");
    private final File subscriptionFile = new File("Subscription.txt");
    private final File trainerFile = new File("Trainer.txt");

    private final String fileNotFoundMessage = "Filen blev ikke fundet!!";

    //*-----------------------------------------------Constructor----------------------------------------------------*\\
    public FileHandler() {
    }

    //*--------------------------------------------------Save--------------------------------------------------------*\\
    public void saveMembers(ArrayList<Member> members) {
        PrintStream output = null;
        try {
            output = new PrintStream(membersFile);

        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        for (Member member : members) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(member.getName()).append(';');
            stringBuilder.append(member.getAddress()).append(';');
            stringBuilder.append(member.getPhoneNumber()).append(';');
            stringBuilder.append(member.getMail()).append(';');
            stringBuilder.append(member.getBirthdate()).append(';');
            stringBuilder.append(member.getSex()).append(';');
            stringBuilder.append(member.getIsStudent()).append(';');
            stringBuilder.append(member.getHasPaid()).append(';');
            stringBuilder.append(member.getActivity()).append(';');
            if (member.getCrawl() || member.getBackCrawl() || member.getBreastStroke() || member.getButterfly()) {
                stringBuilder.append(member.getCrawl()).append(';');
                stringBuilder.append(member.getBackCrawl()).append(';');
                stringBuilder.append(member.getBreastStroke()).append(';');
                stringBuilder.append(member.getButterfly()).append(';');
            }
            stringBuilder.append(member.getCurrentMember()).append(';');
            stringBuilder.append(readTimeToString(member));
            if (output != null) {
                output.println(stringBuilder);
            }
        }
        output.close();
    }

    public void saveSubscription(Subscription subscription) {
        PrintStream output = null;
        try {
            output = new PrintStream(subscriptionFile);

        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        if (output != null) {
            output.println(
                    subscription.getPassive() + ";" +
                            subscription.getJunior() + ";" +
                            subscription.getSenior() + ";" +
                            subscription.getSeniorPlus() + ";" +
                            subscription.getStudent()
            );
        }
        output.close();
    }

    public void saveTrainer(String[] trainers) {
        PrintStream output = null;
        try {
            output = new PrintStream(trainerFile);

        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        if (output != null) {
            output.println(
                    trainers[0] + ";" + trainers[1]);
        }
        output.close();
    }

    //*--------------------------------------------------Load--------------------------------------------------------*\\
    public ArrayList<Member> loadMembers(String fileName) {
        ArrayList<Member> members = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String[] lineSplit = scanner.nextLine().split(";");
                if (lineSplit.length == 10) {
                    members.add(new Member(
                            lineSplit[0],
                            lineSplit[1],
                            lineSplit[2],
                            lineSplit[3],
                            LocalDate.parse(lineSplit[4]),
                            Boolean.parseBoolean(lineSplit[5]),
                            Boolean.parseBoolean(lineSplit[6]),
                            Boolean.parseBoolean(lineSplit[7]),
                            readMemberShipStatus(lineSplit[8]),
                            Boolean.parseBoolean(lineSplit[9])
                    ));
                } else {
                    members.add(new Member(
                            lineSplit[0],
                            lineSplit[1],
                            lineSplit[2],
                            lineSplit[3],
                            LocalDate.parse(lineSplit[4]),
                            Boolean.parseBoolean(lineSplit[5]),
                            Boolean.parseBoolean(lineSplit[6]),
                            Boolean.parseBoolean(lineSplit[7]),
                            readMemberShipStatus(lineSplit[8]),
                            Boolean.parseBoolean(lineSplit[9]),
                            Boolean.parseBoolean(lineSplit[10]),
                            Boolean.parseBoolean(lineSplit[11]),
                            Boolean.parseBoolean(lineSplit[12]),
                            Boolean.parseBoolean(lineSplit[13]),
                            readStringToTime(lineSplit[14])
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        return members;
    }

    public ArrayList<Member> loadMembers() {
        ArrayList<Member> members = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(membersFile);
            while (scanner.hasNextLine()) {
                String[] lineSplit = scanner.nextLine().split(";");
                if (lineSplit.length == 10) {
                    members.add(new Member(
                            lineSplit[0],
                            lineSplit[1],
                            lineSplit[2],
                            lineSplit[3],
                            LocalDate.parse(lineSplit[4]),
                            Boolean.parseBoolean(lineSplit[5]),
                            Boolean.parseBoolean(lineSplit[6]),
                            Boolean.parseBoolean(lineSplit[7]),
                            readMemberShipStatus(lineSplit[8]),
                            Boolean.parseBoolean(lineSplit[9])
                    ));
                } else {
                    members.add(new Member(
                            lineSplit[0],
                            lineSplit[1],
                            lineSplit[2],
                            lineSplit[3],
                            LocalDate.parse(lineSplit[4]),
                            Boolean.parseBoolean(lineSplit[5]),
                            Boolean.parseBoolean(lineSplit[6]),
                            Boolean.parseBoolean(lineSplit[7]),
                            readMemberShipStatus(lineSplit[8]),
                            Boolean.parseBoolean(lineSplit[9]),
                            Boolean.parseBoolean(lineSplit[10]),
                            Boolean.parseBoolean(lineSplit[11]),
                            Boolean.parseBoolean(lineSplit[12]),
                            Boolean.parseBoolean(lineSplit[13]),
                            readStringToTime(lineSplit[14])
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        return members;
    }

    public Subscription loadSubscription() {
        Subscription subscription = null;
        try {
            Scanner scanner = new Scanner(subscriptionFile);

            String[] lineSplit = scanner.nextLine().split(";");
            subscription = new Subscription(
                    Double.parseDouble(lineSplit[0]),
                    Double.parseDouble(lineSplit[1]),
                    Double.parseDouble(lineSplit[2]),
                    Double.parseDouble(lineSplit[3]),
                    Double.parseDouble(lineSplit[4])
            );


        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        return subscription;
    }

    public String[] loadTrainers() {
        String[] trainers = null;
        try {
            Scanner scanner = new Scanner(trainerFile);
            String trainer = scanner.nextLine();
            trainers = trainer.split(";");

        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        return trainers;
    }

    //*-------------------------------------------------MISC--------------------------------------------------------*\\
    private MembershipStatus readMemberShipStatus(String memberShipStatusString) {
        MembershipStatus membershipStatus = MembershipStatus.NONE;
        switch (memberShipStatusString) {
            case "NONE" -> membershipStatus = MembershipStatus.NONE;
            case "HOBBY" -> membershipStatus = MembershipStatus.HOBBY;
            case "COMPETITIVE" -> membershipStatus = MembershipStatus.COMPETITIVE;
        }
        return membershipStatus;
    }

    private SwimDiscipline readSwimDisciplin(String swimDisciplinString) {
        SwimDiscipline swimDiscipline = SwimDiscipline.CRAWL;
        switch (swimDisciplinString) {
            case "CRAWL" -> swimDiscipline = SwimDiscipline.CRAWL;
            case "BACKCRAWL" -> swimDiscipline = SwimDiscipline.BACKCRAWL;
            case "BREASTSTROKE" -> swimDiscipline = SwimDiscipline.BREASTSTROKE;
            case "BUTTERFLY" -> swimDiscipline = SwimDiscipline.BUTTERFLY;
        }
        return swimDiscipline;
    }

    private String readTimeToString(Member member) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Time time : member.getSwimTimes()) {
            stringBuilder.append(time.getName()).append(':');
            stringBuilder.append(time.getTime()).append(':');
            stringBuilder.append(time.getDate()).append(':');
            stringBuilder.append(time.getSwimDisciplin()).append('/');
        }
        return stringBuilder.toString();
    }

    private ArrayList<Time> readStringToTime(String input) {
        ArrayList<Time> times = new ArrayList<>();
        if (input != null) {
            String[] timeArray = input.split("/");
            for (String timeString : timeArray) {
                String[] singleTime = timeString.split(":");
                times.add(new Time(singleTime[0], Double.parseDouble(singleTime[1]),
                        LocalDate.parse(singleTime[2]), readSwimDisciplin(singleTime[3])));
            }
        }
        return times;
    }
}
