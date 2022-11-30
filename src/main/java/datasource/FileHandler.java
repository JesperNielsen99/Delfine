package datasource;

import member.Member;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public FileHandler() {
    }

    public void saveMembers(ArrayList<Member> members) {
        PrintStream output = null;
        try {
            output = new PrintStream(new File("Members.txt"));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        for (Member member : members) {
            output.println(
                    member.getName() + ";" +
                            member.getAddress() + ";" +
                            member.getPhoneNumber() + ";" +
                            member.getMail() + ";" +
                            member.getBirthday() + ";" +
                            member.getSex() + ";" +
                            member.getIsStudent() + ";" +
                            member.getIsActive() + ";" +
                            member.getIsCompetitive()
            );
        }
        output.close();
    }

    public ArrayList<Member> loadMembers(String fileName) {
        ArrayList<Member> members = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
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
                        Boolean.parseBoolean(lineSplit[8])
                ));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }
}
