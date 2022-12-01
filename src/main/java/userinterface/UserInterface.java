package userinterface;

import datahandling.Controller;
import member.Member;
import member.MembershipStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller = new Controller();
    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.GERMAN);

    public void start() {
        controller.loadMembers();
        System.out.println("Velkommen til Delfinen");
        while (true) {
            if (!controller.getMembers().isEmpty()) {
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
                                
                1: Opret medlem.
                2: Søg efter medlem.
                3: Redigere medlem.
                4: Slet medlem.
                5: Print restanceliste.
                6: Vis forventede indkomst.
                7: Rediger kontigent.
                8: Vis alle medlemmer.
                9: Luk program.
                """);
    }

    private void printEditMenu() {
        System.out.println("""
                Hvad ønsker du at redigere?
                                
                1:  Navn
                2:  Adresse
                3:  Telefon nummer
                4:  Mail
                5:  Fødselsdag
                6:  Køn
                7:  Studie status
                8:  Aktivitets status
                9:  Medlemskab
                10: Betalingssatus
                11: Tilbage til hovedmenu
                """);
    }

    private void printSubscriptionMenu() {
        System.out.println("""
                Hvilket kontigent ønsker du at ændre?
                                
                1: Passiv
                2: Junior
                3: Senior
                4: Senior+
                5: Student
                """);
    }

    private void handleMainMenuChoice() {
        switch (readInt()) {
            case 1 -> createMember();
            case 2 -> searchMember();
            case 3 -> editMember();
            case 4 -> deleteMember();
            case 5 -> printMembersInDebt();
            case 6 -> expetedTotalIncome();
            case 7 -> editSupscription();
            case 8 -> printAllMembers();
            case 9 -> exitProgram();
            default -> System.out.println("invalid option");
        }
    }


    private void handleEditMenuChoice(Member currentMember) {
        switch (readInt()) {
            case 1 -> editName(currentMember);
            case 2 -> editAddresse(currentMember);
            case 3 -> editPhoneNumber(currentMember);
            case 4 -> editMail(currentMember);
            case 5 -> editBirthdate(currentMember);
            case 6 -> editSex(currentMember);
            case 7 -> editIsStudent(currentMember);
            case 8 -> editIsActive(currentMember);
            case 9 -> editIsCompetitive(currentMember);
            case 10 -> editHasPaid(currentMember);
            case 11 -> start();
            default -> System.out.println("Ikke en mulig funktion.");

        }
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
        boolean memberHasPaid = readHasPaid();
        MembershipStatus memberIsCompetitive = null;
        if (memberIsActive) {
            memberIsCompetitive = readCompetitive();
            boolean crawl = readSwimDisciplin("crawl");
            boolean rygCrawl = readSwimDisciplin("ryg crawl");
            boolean brystSvømning = readSwimDisciplin("bryst svømning");
            boolean butterfly = readSwimDisciplin("butterfly");
            controller.createMember(memberName, memberAddress, memberPhoneNumber, memberMail, memberBirthdate, memberSex,
                    memberIsStudent, false, memberIsCompetitive, memberHasPaid, crawl, rygCrawl, brystSvømning, butterfly);

        } else {
            memberIsCompetitive = MembershipStatus.NONE;
            controller.createMember(memberName, memberAddress, memberPhoneNumber, memberMail, memberBirthdate, memberSex,
                    memberIsStudent, false, memberIsCompetitive, memberHasPaid);
        }
        System.out.println("\n");


    }

    private void searchMember() {
        System.out.print("Indtast navn på medlem: ");
        controller.searchMember(scanner.nextLine());
        if (!controller.getSearchResult().isEmpty()) {
            printMemberArray(controller.getSearchResult());
        } else {
            System.out.println("Ingen medlemmer blev fundet med dette navn.");
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


    private void editMember() {
        searchMember();
        if (!controller.getSearchResult().isEmpty()) {
            Member currentMember = chooseSearchResult(controller.getSearchResult());
            printEditMenu();
            handleEditMenuChoice(currentMember);
        }
    }

    private void editName(Member currentMember) {
        System.out.print("Indtast nyt navn: ");
        currentMember.setName(scanner.nextLine());
        while (currentMember.getName().isEmpty()) {
            currentMember.setName(scanner.nextLine());
        }
    }

    private void editAddresse(Member currentMember) {
        System.out.print("Indtast ny adresse: ");
        currentMember.setAddress(scanner.nextLine());
        while (currentMember.getAddress().isEmpty()) {
            currentMember.setAddress(scanner.nextLine());
        }
    }

    private void editPhoneNumber(Member currentMember) {
        System.out.print("Indtast ny telefon nummer: ");
        currentMember.setPhoneNumber(scanner.nextLine());
        while (currentMember.getPhoneNumber().isEmpty()) {
            currentMember.setPhoneNumber(scanner.nextLine());
        }
    }

    private void editMail(Member currentMember) {
        currentMember.setMail(readMail());
        while (currentMember.getMail().isEmpty()) {
            currentMember.setMail(scanner.nextLine());
        }
    }

    private void editBirthdate(Member currentMember) {
        currentMember.setBirthdate(readBirthday());
        while (currentMember.getMail().isEmpty()) {
            currentMember.setMail(scanner.nextLine());
        }
    }

    private void editSex(Member currentMember) {
        currentMember.setSex(readSex());
    }

    private void editIsStudent(Member currentMember) {
        currentMember.setIsStudent(readStudent());
    }

    private void editIsActive(Member currentMember) {
        currentMember.setIsActive(readActive());
    }

    private void editIsCompetitive(Member currentMember) {
        currentMember.setIsCompetitive(readCompetitive());
    }

    private void editHasPaid(Member currenMember) {
        currenMember.setHasPaid(readHasPaid());
    }

    private void editSupscription() {
        printSubscriptionMenu();

        boolean wrongUserchoice = true;
        while(wrongUserchoice) {
            switch (readInt()) {
                case 1 -> {
                    System.out.println("Nuværende kontigent for passive medlemmer er: " + controller.getpassiv() + " kr.");
                    controller.setPassiv(readSubscription());
                    wrongUserchoice = false;
                }
                case 2 -> {
                    System.out.println("Nuværende kontigent for junior medlemmer er: " + controller.getJunior() + " kr.");
                    controller.setJunior(readSubscription());
                    wrongUserchoice = false;
                }
                case 3 -> {
                    System.out.println("Nuværende kontigent for senior medlemmer er: " + controller.getSenior() + " kr.");
                    controller.setSenior(readSubscription());
                    wrongUserchoice = false;
                }
                case 4 -> {
                    System.out.println("Nuværende kontigent for senior+ medlemmer er: " + controller.getSeniorPlus() + " kr.");
                    controller.setSeniorPlus(readSubscription());
                    wrongUserchoice = false;
                }
                case 5 -> {
                    System.out.println("Nuværende kontigent for studerende er: " + controller.getStudent() + " kr.");
                    controller.setStudent(readSubscription());
                    wrongUserchoice = false;
                }
                default -> System.out.println("Ugyldigt input. Prøv igen!");
            }
        }
    }

    private double readSubscription() {
        System.out.print("Indtast nyt kontingent: ");
        double input = readDouble();
        while (input < 0) {
            System.out.println("Kontingentet kan ikke være negativt. Prøv igen: ");
            input = readDouble();
        }
        return input;
    }

    private void deleteMember() {
        searchMember();
        if (!controller.getSearchResult().isEmpty()) {
            Member currentMember = chooseSearchResult(controller.getSearchResult());
            System.out.println(controller.deleteMember(currentMember));
        }
    }

    private void printMemberArray(ArrayList<Member> members) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            stringBuilder.append((i + 1) + ") \n" + member.printMember()).append("\nKontigent: ").append(controller.calculateMemberSubscription(member)).append(" kr.").append("\n\n\n");
        }
        System.out.println(stringBuilder);
    }

    private void printMembersInDebt() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Member member : controller.getMembersInDebt()) {
            stringBuilder.append(member.printMember()).append("\nManglende betaling: ").append(controller.calculateMemberSubscription(member)).append(" kr.").append("\n");
            stringBuilder.append("\n\nAutogenereret besked til medlem: \nHej ").append(member.getName()).append(". Du mangler at betale: ").append(controller.calculateMemberSubscription(member)).append(" kr.\n\n\n");
        }
        System.out.println(stringBuilder);
    }

    private void expetedTotalIncome() {
        System.out.println("Forventede årlige inkomst er: " + controller.getExpectedTotalIncome() + "\n");
    }

    private void printAllMembers() {
        printMemberArray(controller.getMembers());
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
        boolean sex = true;
        while (wrongInput) {
            System.out.print("Indtast køn (Mand/Kvinde eller M/K): ");

            switch (scanner.nextLine().toLowerCase()) {
                case "m", "mand" -> {
                    sex = true;
                    wrongInput = false;
                }
                case "k", "kvinde" -> {
                    sex = false;
                    wrongInput = false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return sex;
    }

    private boolean readStudent() {
        boolean wrongInput = true;
        boolean isStudent = true;
        while (wrongInput) {
            System.out.print("Er medlemmet studerende? (ja/nej): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    isStudent = true;
                    wrongInput = false;
                }
                case "nej", "n" -> {
                    isStudent = false;
                    wrongInput = false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return isStudent;
    }

    private boolean readActive() {
        boolean wrongInput = true;
        boolean isActive = true;
        while (wrongInput) {
            System.out.print("Er medlemmet aktiv? (ja/nej): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    isActive = true;
                    wrongInput = false;
                }
                case "nej", "n" -> {
                    isActive = false;
                    wrongInput = false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return isActive;
    }

    private MembershipStatus readCompetitive() {
        boolean wrongInput = true;
        MembershipStatus membershipStatus = MembershipStatus.NONE;
        while (wrongInput) {
            System.out.print("Er medlemmet konkurencesvømmer? (ja/nej): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    membershipStatus = MembershipStatus.COMPETITIVE;
                }
                case "nej", "n" -> {
                    membershipStatus = MembershipStatus.HOBBY;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return membershipStatus;
    }

    private boolean readHasPaid() {
        boolean wrongInput = true;
        boolean hasPaid = true;
        while (wrongInput) {
            System.out.print("Har medlemmet betalt? (ja/nej): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    System.out.println("\n");
                    hasPaid = true;
                    wrongInput = false;
                }
                case "nej", "n" -> {
                    System.out.println("\n");
                    hasPaid = false;
                    wrongInput = false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }

        return hasPaid;
    }

    private boolean readSwimDisciplin(String disciplin) {
        boolean wrongInput = true;
        boolean hasDisciplin = true;
        while (wrongInput) {
            System.out.print("Deltager medlemmet i " + disciplin + "? (ja/nej): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    System.out.println("\n");
                    hasDisciplin = true;
                    wrongInput = false;
                }
                case "nej", "n" -> {
                    System.out.println("\n");
                    hasDisciplin = false;
                    wrongInput = false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }

        return hasDisciplin;
    }

}
