package datasource;

import datahandling.Subscription;
import member.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private File membersFile = new File("Members.txt");
    private File subscriptionFile = new File("Subscription.txt");

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
            output.println(
                    member.getName() + ";" +
                            member.getAddress() + ";" +
                            member.getPhoneNumber() + ";" +
                            member.getMail() + ";" +
                            member.getBirthdate() + ";" +
                            member.getSex() + ";" +
                            member.getIsStudent() + ";" +
                            member.getIsActive() + ";" +
                            member.getIsCompetitive() + ";" +
                            member.getHasPaid()
            );
        }
        output.close();
    }

    public ArrayList<Member> loadMembers() {
        ArrayList<Member> members = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(membersFile);
            while (scanner.hasNextLine()) {
                String[] lineSplit = scanner.nextLine().split(";");
                members.add(new Member(
                        lineSplit[0],
                        lineSplit[1],
                        lineSplit[2],
                        lineSplit[3],
                        LocalDate.parse(lineSplit[4]),
                        Boolean.parseBoolean(lineSplit[5]),
                        Boolean.parseBoolean(lineSplit[6]),
                        Boolean.parseBoolean(lineSplit[7]),
                        Boolean.parseBoolean(lineSplit[8]),
                        Boolean.parseBoolean(lineSplit[9])
                ));
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

}
