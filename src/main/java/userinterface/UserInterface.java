package userinterface;

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
                4: Slet medlem 
                9: Luk program
                """);
    }

    private void handleMainMenuChoice() {
        switch (readInt()) {
            case 1 ->  createMember();
            case 2 ->  searchMember();
            case 3 ->  editMember();
            case 4 ->  deleteMember();
            case 8 ->  showAllMembers();
            case 9 ->  exitProgram();
            default -> { System.out.println("invalid option"); }
        }
    }


    private void showAllMembers() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Member member : controller.getMembers()) {
            stringBuilder.append(member.printMember());
        }
        System.out.println(stringBuilder);

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
        System.out.print("Indtast nyt navn: ");
        currentMember.setName(scanner.nextLine());
        while (currentMember.getName().isEmpty()){
            currentMember.setName(scanner.nextLine());}
    }

    private void editMemberAddresse(Member currentMember) {
        System.out.print("Indtast ny adresse: ");
        currentMember.setAddress(scanner.nextLine());
        while (currentMember.getAddress().isEmpty()){
            currentMember.setAddress(scanner.nextLine());}
    }

    private void editMemberPhoneNumber(Member currentMember) {
        System.out.print("Indtast ny telefon nummer: ");
        currentMember.setPhoneNumber(scanner.nextLine());
        while (currentMember.getPhoneNumber().isEmpty()){
            currentMember.setPhoneNumber(scanner.nextLine());}
    }

    private void editMemberMail(Member currentMember) {
        currentMember.setMail(readMail());
        while (currentMember.getMail().isEmpty()){
            currentMember.setMail(scanner.nextLine());}
    }

    private void editMemberBirthdate(Member currentMember) {
        currentMember.setBirthdate(readBirthday());
        while (currentMember.getMail().isEmpty()){
            currentMember.setMail(scanner.nextLine());}
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
        while (!memberName.isEmpty()) {
            System.out.println("Dit navn kan ikke være tomt.");
            memberName = scanner.nextLine();
        }
        System.out.println("Indtast adressen på det nye medlem: ");
        String memberAddress = scanner.nextLine();
        while (!memberAddress.isEmpty()) {
            System.out.println("Din addresse kan ikke være tomt.");
            memberAddress = scanner.nextLine();
        }
        System.out.println("Indtast telefon nummer på det nye medlem: ");
        String memberPhoneNumber = scanner.nextLine();
        while (!memberPhoneNumber.isEmpty()) {
            System.out.println("Dit telefon nummer kan ikke være tomt.");
            memberPhoneNumber = scanner.nextLine();
        }
        String memberMail = readMail();
        LocalDate memberBirthdate = readBirthday();
        boolean memberSex = readSex();
        boolean memberIsStudent = readStudent();
        boolean memberIsActive = readActive();
        boolean memberIsCompetitive = readCompetetive();

        controller.createMember(memberName, memberAddress, memberPhoneNumber, memberMail, memberBirthdate, memberSex, memberIsStudent, memberIsActive, memberIsCompetitive);

    }

    private void searchMember() {
        System.out.print("Indtast navn på medlem: ");
        controller.searchMember(scanner.nextLine());
        if (!controller.getSearchResult().isEmpty()) {
            printSearchResult(controller.getSearchResult());
        } else {
            System.out.println("Ingen medlemmer blev fundet med dette navn.");
        }
    }

    private void printSearchResult(ArrayList<Member> searchResult) {
        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println((i + 1) + ")\n" + searchResult.get(i).printMember() +
                    "Kontingent:  " + controller.calculateMemberSubscription(searchResult.get(i)) + '\n');
        }
    }

    private void editMember() {
        searchMember();
        if (!controller.getSearchResult().isEmpty()) {
            Member currentMember = chooseSearchResult(controller.getSearchResult());
            printEditMenu();
            handleEditMenuChoice(currentMember);
        }
    }

    private Member chooseSearchResult(ArrayList<Member> members) {
        System.out.print("Indtast nummeret på medlemmet: ");
        int index = readInt();
        while (index > members.size() || index <= 0) {
            System.out.println("Der findes ikke noget medlem tilsvarende: " + index);
            System.out.println("Indtast et tal mellem 1 og " + members.size());
            index = readInt();
        }
        return members.get(index - 1);
    }

    private void deleteMember() {
        searchMember();
        if (!controller.getSearchResult().isEmpty()) {
            Member currentMember = chooseSearchResult(controller.getSearchResult());
            System.out.println(controller.deleteMember(currentMember));
        }
    }

    public void membersInDebtMessage(ArrayList<Member> members) {
        for (Member member: members) {
            System.out.printf("Hej %s, du har desværre ikke betalt dit kontingent på %s kroner og bedes derfor betale snarest muligt.", member.getName(), controller.calculateMemberSubscription(member));
        }
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
        String emailValidation = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
                                 + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        while (!input.matches(emailValidation)) {
            System.out.print("Ugyldig e-mail prøv igen: ");
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
            System.out.print("Er medlemmet studerende? (ja/nej): ");
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

            System.out.print("Er medlemmet aktiv? (ja/nej): ");
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

            System.out.print("Er medlemmet konkurencesvømmer? (ja/nej): ");
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
