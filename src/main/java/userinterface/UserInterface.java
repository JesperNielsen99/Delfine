package userinterface;

import com.sun.source.tree.ArrayAccessTree;
import datahandling.Controller;
import member.Member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in).useLocale(Locale.GERMAN);

    public void start() {
        controller.loadMembers();
        System.out.println("Velkommen til Delfinen");
        while (true) {
            if (!controller.getMembers().isEmpty()){
                printMainMenu();
                handleMainMenuChoice();
            } else {
                System.out.println("Ingen oprettede medlemmer. Opret nyt medlem nu: ");
                createMember();
            }
        }

    }

    private void printMainMenu() {
        System.out.println("""
                Du har følgende valgmuligheder, hvad ønsker du at gøre?
                                
                1: Opret medlem 
                2: Søg efter medlem 
                3: Redigere medlem
                4: Vis alle medlemmer 
                5: Slet medlem 
                6: Luk program
                """);

    }

    private void handleMainMenuChoice() {
        switch (readInt()) {
            case 1 -> createMember();
            case 2 -> searchMember();
            case 3 -> editMember();
            case 4 -> showAllMembers();
            case 5 -> deleteMember();
            case 6 -> exitProgram();
            default -> System.out.println("invalid option");
        }
    }


    private void showAllMembers() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Member member : controller.getMembers()) {
            stringBuilder.append(member.printMember());
        }
        System.out.println(stringBuilder.toString());

    }

    private void printEditMenu() {
        System.out.println("""
                Hvad ønsker du at redigere?
                                
                1: Navn
                2: Adresse 
                3: Telefon nummer 
                4: Mail
                5: Fødselsdag
                6: Køn
                7: Studie status
                8: Aktivitets status
                9: Medlemskab 
                10: Tilbage til hovedmenu  
                """);
    }


    private void handleEditMenuChoice(Member currentMember) {
        switch (readInt()) {
            case 1 -> editMemberName(currentMember);
            case 2 -> editMemberAddresse(currentMember);
            case 3 -> editMemberPhoneNumber(currentMember);
            case 4 -> editMemberMail(currentMember);
            case 5 -> editMemberBirthdate(currentMember);
            case 6 -> editMemberSex(currentMember);
            case 7 -> editMemberIsStudent(currentMember);
            case 8 -> editMemberIsActive(currentMember);
            case 9 -> editMemberIsCompetitive(currentMember);
            case 10 -> start();
            default -> System.out.println("Ikke en mulig funktion.");

        }

    }

    private void editMemberName(Member currentMember) {
        currentMember.setName(scanner.nextLine());
    }

    private void editMemberAddresse(Member currentMember) {
        currentMember.setAddress(scanner.nextLine());
    }

    private void editMemberPhoneNumber(Member currentMember) {
        currentMember.setPhoneNumber(scanner.nextLine());
    }

    private void editMemberMail(Member currentMember) {
        currentMember.setMail(readMail());
    }

    private void editMemberBirthdate(Member currentMember) {
        currentMember.setBirthday(readBirthday());
    }

    private void editMemberSex(Member currentMember) {
        currentMember.setSex(readSex());
    }

    private void editMemberIsStudent(Member currentMember) {
        currentMember.setIsStudent(readStudent());
    }

    private void editMemberIsActive(Member currentMember) {
        currentMember.setIsActive(readActive());
    }

    private void editMemberIsCompetitive(Member currentMember) {
        currentMember.setIsCompetitive(readCompetetive());
    }


    private void createMember() {
        System.out.println("Indtast navnet på det nye medlem: ");
        String memberName = scanner.nextLine();
        System.out.println("Indtast adressen på det nye medlem: ");
        String memberAddress = scanner.nextLine();
        System.out.println("Indtast telefon nummer på det nye medlem: ");
        String memberPhoneNumber = scanner.nextLine();
        String memberMail = readMail();
        LocalDate memberBirthdate = readBirthday();
        boolean memberSex = readSex();
        boolean memberIsStudent = readStudent();
        boolean memberIsActive = readActive();
        boolean memberIsCompetitive = readCompetetive();

        controller.createMember(memberName, memberAddress, memberPhoneNumber, memberMail, memberBirthdate, memberSex, memberIsStudent, memberIsActive, memberIsCompetitive);

    }

    private void searchMember() {
        if (controller.getMembers().isEmpty()) {
            System.out.print("Indtast navn du vil søge efter: ");
            ArrayList<Member> members = controller.searchMember(scanner.nextLine());
            while (members.isEmpty()) {
                System.out.println("Ingen resultater var fundet med indtastet navn");
                members = controller.searchMember(scanner.nextLine());
            }
            printSearchResult(members);
        } else {
            System.out.println("Der er ingen medlemmer i klubben. Opret nye medlemmer, før du kan søge.");
        }
    }

    private void printSearchResult(ArrayList<Member> searchResult) {
        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println((i + 1) + ")\n" + searchResult.get(i).printMember());
        }
    }

    private void editMember() {
        System.out.print("Indtast navn på medlem du vil redigere: ");
        ArrayList<Member> searchResult = controller.searchMember(scanner.nextLine());
        while (searchResult.isEmpty()) {
            System.out.print("Ingen medlemmer med indtastede navn eksisterer. Prøv igen: " );
            searchResult = controller.searchMember(scanner.nextLine());
        }
        Member currentMember = chooseSearchResult(searchResult);
        printEditMenu();
        handleEditMenuChoice(currentMember);
    }

    private Member chooseSearchResult(ArrayList<Member> members) {
        printSearchResult(members);
        System.out.print("Indtast medlem du gerne vil redigere: ");
        int index = readInt();
        while (index > members.size() || index <= 0) {
            System.out.println("Der findes ikke noget medlem tilsvarende: " + index);
            System.out.println("Indtast et tal mellem 1 og " + members.size());
            index = readInt();
        }
        return members.get(index - 1);
    }

    private void deleteMember() {
        System.out.print("Indtast navn, eller del af navn på medlemmet du ønsker at slette: ");
        ArrayList<Member> searchResult = controller.searchMember(scanner.nextLine());
        Member currentMember = chooseSearchResult(searchResult);
        System.out.println(controller.deleteMember(currentMember));
    }

    private void exitProgram() {
        controller.saveMembers();
        System.exit(0);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            String wrongInput = scanner.nextLine();
            System.out.println("Du intastede [" + wrongInput + "] dette er ikke et helt tal.\nPrøv igen.");
        }
        int rightInput = scanner.nextInt();
        scanner.nextLine();
        return rightInput;
    }

    private double readDouble() {
        while (!scanner.hasNextDouble()) {
            String wrongInput = scanner.nextLine();
            System.out.println("Du intastede [" + wrongInput + "] dette er ikke et kommatal.\nPrøv igen.");
        }
        double rightInput = scanner.nextDouble();
        scanner.nextLine();
        return rightInput;

    }

    private String readMail() {
        System.out.println("Indtast mail");
        String input = scanner.nextLine();
        while (!input.contains("@") || input.contains("..")) {
            System.out.println("Ugyldig e-mail prøv igen");
            input = scanner.nextLine();
        }
        return input;
    }

    private LocalDate readBirthday() {
        DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthday = null;
        String errorMessage = "Ugyldig fødselsdato prøv igen!";

        boolean wrongBirthday = true;
        while (wrongBirthday) {
            System.out.print("Intast fødselsdag (dd-mm-yyyy): ");
            String birthdayInput = scanner.nextLine();
            try {
                birthday = LocalDate.parse(birthdayInput, birthdayFormat);
                if (birthday.getYear() <= LocalDate.now().getYear() && birthday.getYear() > 1900) {
                    wrongBirthday = false;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
        return birthday;
    }

    private boolean readSex() {
        boolean wrongInput = true;

        while (wrongInput) {
            System.out.print("Indtast køn (Mand/Kvinde eller M/K): ");

            switch (scanner.nextLine().toLowerCase()) {
                case "m", "mand" -> {
                    return true;
                }
                case "k", "kvinde" -> {
                    return false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return true;
    }

    private boolean readStudent() {
        boolean wrongInput = true;
        while (wrongInput) {
            System.out.println("Er medlemmet studerende? (ja/nej): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    return true;
                }
                case "nej", "n" -> {
                    return false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return true;
    }

    private boolean readActive() {
        boolean wrongInput = true;
        while (wrongInput) {

            System.out.println("Er medlemmet aktiv? (ja/nej): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    return true;
                }
                case "nej", "n" -> {
                    return false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return true;
    }

    private boolean readCompetetive() {
        boolean wrongInput = true;

        while (wrongInput) {

            System.out.println("Er medlemmet konkurencesvømmer? (ja/nej): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    return true;
                }
                case "nej", "n" -> {
                    return false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return true;
    }

}
