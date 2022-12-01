package datasource;

import datahandling.Subscription;
import member.Member;
import member.MembershipStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private File membersFile = new File("Members.txt");
    private File subscriptionFile = new File("Subscription.txt");
    private File trainerFile = new File("Trainer.txt");

    private String fileNotFoundMessage = "Filen blev ikke fundet!!";

    public FileHandler() {
    }

    public void saveMembers(ArrayList<Member> members) {
        PrintStream output = null;
        try {
            output = new PrintStream(membersFile);

        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);;
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
            stringBuilder.append(member.getIsActive()).append(';');
            stringBuilder.append(member.getHasPaid()).append(';');
            stringBuilder.append(member.getIsCompetitive()).append(';');
            stringBuilder.append(member.getCrawl()).append(';');
            stringBuilder.append(member.getRygCrawl()).append(';');
            stringBuilder.append(member.getBrystSvømning()).append(';');
            stringBuilder.append(member.getButterfly());
            output.println(stringBuilder);
        }
        output.close();
    }

    public ArrayList<Member> loadMembers() {
        ArrayList<Member> members = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(membersFile);
            while (scanner.hasNextLine()) {
                String[] lineSplit = scanner.nextLine().split(";");
                if (lineSplit.length == 9) {
                    members.add(new Member(
                            lineSplit[0],
                            lineSplit[1],
                            lineSplit[2],
                            lineSplit[3],
                            LocalDate.parse(lineSplit[4]),
                            Boolean.parseBoolean(lineSplit[5]),
                            Boolean.parseBoolean(lineSplit[6]),
                            Boolean.parseBoolean(lineSplit[7]),
                            readMemberShipStatus(lineSplit[9]),
                            Boolean.parseBoolean(lineSplit[8])
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
                            readMemberShipStatus(lineSplit[9]),
                            Boolean.parseBoolean(lineSplit[8]),
                            Boolean.parseBoolean(lineSplit[10]),
                            Boolean.parseBoolean(lineSplit[11]),
                            Boolean.parseBoolean(lineSplit[12]),
                            Boolean.parseBoolean(lineSplit[13])
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        return members;
    }

    public void saveSubscription(Subscription subscription) {
        PrintStream output = null;
        try {
            output = new PrintStream(subscriptionFile);

        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        output.println(
                subscription.getPassiv() + ";" +
                        subscription.getJunior() + ";" +
                        subscription.getSenior() + ";" +
                        subscription.getSeniorPlus() + ";" +
                        subscription.getStudent()
        );
        output.close();
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


        } catch (FileNotFoundException e){
            System.out.println(fileNotFoundMessage);
        }
        return subscription;
    }

    public void saveTrainer(String trainer) {
        PrintStream output = null;
        try {
            output = new PrintStream(trainerFile);

        } catch (FileNotFoundException e) {
            System.out.println(fileNotFoundMessage);
        }
        output.println(trainer);
        output.close();
    }

    public String loadTrainer() {
        String trainer = null;
        try {
            Scanner scanner = new Scanner(trainerFile);
            trainer = scanner.nextLine();

        } catch (FileNotFoundException e){
            System.out.println(fileNotFoundMessage);
        }
        return trainer;
    }


    private MembershipStatus readMemberShipStatus(String memberShipStatusString) {
        MembershipStatus membershipStatus = MembershipStatus.NONE;
        switch (memberShipStatusString) {
            case "NONE" -> { membershipStatus = MembershipStatus.NONE; }
            case "HOBBY" -> { membershipStatus = MembershipStatus.HOBBY; }
            case "COMPETITIVE" -> { membershipStatus = MembershipStatus.COMPETITIVE; }
        }
        return membershipStatus;
    }
}
