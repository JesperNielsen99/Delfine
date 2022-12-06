package userinterface;

import datahandling.Controller;
import member.Member;
import member.MembershipStatus;
import member.SwimDisciplin;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller = new Controller();
    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.GERMAN);

    //*----------------------------------------------StartProgram----------------------------------------------------*\\
    public void runProgram() {
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

    public void start() {
        loadData();
        System.out.println("Velkommen til Delfinen");
        runProgram();
    }

    //*----------------------------------------------PrintMenus----------------------------------------------------*\\
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
                8: Vis Konkurrence hold.
                9: Vis alle medlemmer.
                10: Luk program.
                """);
    }

    private void printEditCompetitiveMenu() {
        System.out.println("""
                Hvad ønsker du at redigere?
                                
                1:  Navn
                2:  Adresse
                3:  Telefon nummer
                4:  Mail
                5:  Fødselsdag
                6:  Køn
                7:  Studie status
                8:  Aktivitetsform
                9:  Betalingssatus
                10: Aktive Svømmediscipliner
                11: Tilbage til hovedmenu
                """);
    }

    private void printEditNonCompetitiveMenu() {
        System.out.println("""
                Hvad ønsker du at redigere?
                                
                1:  Navn
                2:  Adresse
                3:  Telefon nummer
                4:  Mail
                5:  Fødselsdag
                6:  Køn
                7:  Studie status
                8:  Aktivitetsform
                9:  Betalingssatus
                10: Tilbage til hovedmenu
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

    private void printSwimDisciplinMenu() {
        System.out.println("""
                Hvilken disciplin vil du ændre?
                                
                1: Crawl
                2: Ryg Crawl
                3: Bryst Svømning
                4: Butterfly
                """);
    }

    private void printSwimTeamMenu() {
        System.out.println("""
                Hvilken mulighed vil du vælge?
                                
                1: Vis junior team
                2: Vis senior team
                3: Skift junior træner
                4: Skift senior træner
                """);
    }

    //*---------------------------------------------HandelUserinput--------------------------------------------------*\\
    private void handleMainMenuChoice() {
        switch (readInt()) {
            case 1 -> createMember();
            case 2 -> searchMember();
            case 3 -> editMember();
            case 4 -> deleteMember();
            case 5 -> printMembersInDebt();
            case 6 -> printExpetedTotalIncome();
            case 7 -> editSubscription();
            case 8 -> printSwimTeam();
            case 9 -> printAllMembers();
            case 10 -> exitProgram();
            default -> System.out.println("invalid option");
        }
    }

    private void handleCompetitiveEditMenuChoice(Member currentMember) {
        switch (readInt()) {
            case 1 -> editName(currentMember);
            case 2 -> editAddresse(currentMember);
            case 3 -> editPhoneNumber(currentMember);
            case 4 -> editMail(currentMember);
            case 5 -> editBirthdate(currentMember);
            case 6 -> editSex(currentMember);
            case 7 -> editIsStudent(currentMember);
            case 8 -> editActivity(currentMember);
            case 9 -> editHasPaid(currentMember);
            case 10 -> editSwimDisciplins(currentMember);
            case 11 -> runProgram();
            default -> System.out.println("Ikke en mulig funktion.");

        }
    }

    private void handleNonCompetitiveEditMenuChoice(Member currentMember) {
        switch (readInt()) {
            case 1 -> editName(currentMember);
            case 2 -> editAddresse(currentMember);
            case 3 -> editPhoneNumber(currentMember);
            case 4 -> editMail(currentMember);
            case 5 -> editBirthdate(currentMember);
            case 6 -> editSex(currentMember);
            case 7 -> editIsStudent(currentMember);
            case 8 -> editActivity(currentMember);
            case 9 -> editHasPaid(currentMember);
            case 10 -> runProgram();
            default -> System.out.println("Ikke en mulig funktion.");

        }
    }

    private void handleSwimTeamMenuChoice() {
        switch (readInt()) {
            case 1 -> {
                System.out.println("Hold træner: " + controller.getTeamJunior().getTrainerName() + '\n');
                printMemberArray(controller.getTeamJuniorMembers());
            }
            case 2 -> {
                System.out.println("Hold træner: " + controller.getTeamSenior().getTrainerName() + '\n');
                printMemberArray(controller.getTeamSeniorMembers());
            }
            case 3 -> {
                System.out.print("Indtast den nye junior træner's navn: ");
                controller.getTeamJunior().setTrainerName(scanner.nextLine());
            }
            case 4 -> {
                System.out.print("Indtast den nye senior træner's navn: ");
                controller.getTeamSenior().setTrainerName(scanner.nextLine());
            }
        }
    }

    //*---------------------------------------------PrintMemberInfo--------------------------------------------------*\\
    private void printSwimTeam() {
        printSwimTeamMenu();
        handleSwimTeamMenuChoice();
    }

    private void printMemberArray(ArrayList<Member> members) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            stringBuilder.append(i + 1).append(") \n").append(member.printMember()).append("\nKontigent: ").append(controller.calculateMemberSubscription(member)).append(" kr.").append("\n\n\n");
        }
        System.out.println(stringBuilder);
    }

    private void printMembersInDebt() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Member member : controller.getMembersInDebt()) {
            stringBuilder.append(member.printMember()).append("\nManglende betaling: ").append(controller.calculateMemberSubscription(member)).append(" kr.").append("\n");
            stringBuilder.append("\n\nAutogenereret besked til medlem: \nHej ").append(member.getName()).append(". Du mangler at betale: ").append(controller.calculateMemberSubscription(member)).append(" kr.\nMed venlig hilsen. Delfinens Kasserer\n\n\n");
        }
        System.out.println(stringBuilder);
    }

    private void printAllMembers() {
        printMemberArray(controller.getMembers());
    }

    private void printExpetedTotalIncome() {
        System.out.println("Forventede årlige inkomst er: " + controller.getExpectedTotalIncome() + "\n");
    }

    private void printTop5Svimmers(ArrayList<ArrayList<Member>> members) {
        ArrayList<Member> bestCrawlTimes = members.get(0);
        ArrayList<Member> bestBackCrawlTimes = members.get(1);
        ArrayList<Member> bestBreastStrokeTimes = members.get(2);
        ArrayList<Member> bestButterflyTimes = members.get(3);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nTop 5 crawl svømmere:\n");
        if (bestCrawlTimes.size() > 0) {
            for (Member member : bestCrawlTimes) {
                stringBuilder.append(member.getName()).append(": ").append(member.getBestTime(SwimDisciplin.CRAWL)).append("\n");
            }
        } else {
            stringBuilder.append("Der er ingen svømmere som har en tid i crawl");
        }

        stringBuilder.append("\nTop 5 ryg crawl svømmere:\n");
        if (bestBackCrawlTimes.size() > 0) {
            for (Member member : bestBackCrawlTimes) {
                stringBuilder.append(member.getName()).append(": ").append(member.getBestTime(SwimDisciplin.BACKCRAWL)).append("\n");
            }
        } else {
            stringBuilder.append("Der er ingen svømmere som har en tid i ryg crawl");
        }

        stringBuilder.append("\nTop 5 bryst svømning svømmere:\n");
        if (bestBreastStrokeTimes.size() > 0) {
            for (Member member : bestBreastStrokeTimes) {
                stringBuilder.append(member.getName()).append(": ").append(member.getBestTime(SwimDisciplin.BREASTSTROKE)).append("\n");
            }
        } else {
            stringBuilder.append("Der er ingen svømmere som har en tid i brystsvømning");
        }

        stringBuilder.append("\nTop 5 butterfly svømmere:\n");
        if (bestButterflyTimes.size() > 0) {
            for (Member member : bestButterflyTimes) {
                stringBuilder.append(member.getName()).append(": ").append(member.getBestTime(SwimDisciplin.BUTTERFLY)).append("\n");
            }
        } else {
            stringBuilder.append("Der er ingen svømmere som har en tid i butterfly");
        }

        System.out.println(stringBuilder);
    }

    //*--------------------------------------------------Create------------------------------------------------------*\\

    private void createMember() {
        System.out.print("Indtast navnet på det nye medlem: ");
        String memberName = scanner.nextLine();
        System.out.print("Indtast adressen på det nye medlem: ");
        String memberAddress = scanner.nextLine();
        System.out.print("Indtast telefon nummer på det nye medlem: ");
        String memberPhoneNumber = scanner.nextLine();
        String memberMail = readMail();
        LocalDate memberBirthdate = readBirthday();
        boolean memberSex = readSex();
        boolean memberIsStudent = readStudent();
        boolean memberHasPaid = readHasPaid();
        MembershipStatus memberIsCompetitive;
        memberIsCompetitive = readCompetitive();
        if (memberIsCompetitive.equals(MembershipStatus.COMPETITIVE)) {
            boolean crawl = readSwimDisciplin("crawl");
            boolean backCrawl = readSwimDisciplin("ryg crawl");
            boolean breastStroke = readSwimDisciplin("bryst svømning");
            boolean butterfly = readSwimDisciplin("butterfly");
            controller.createMember(memberName, memberAddress, memberPhoneNumber, memberMail, memberBirthdate, memberSex,
                    memberIsStudent, memberIsCompetitive, memberHasPaid, crawl, backCrawl, breastStroke, butterfly, false, null);
        } else {
            controller.createMember(memberName, memberAddress, memberPhoneNumber, memberMail, memberBirthdate, memberSex,
                    memberIsStudent, memberIsCompetitive, memberHasPaid, false);
        }
        System.out.println("\n");


    }

    //*--------------------------------------------------Search------------------------------------------------------*\\
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

    //*---------------------------------------------------Edits------------------------------------------------------*\\
    private void editMember() {
        searchMember();
        if (!controller.getSearchResult().isEmpty()) {
            Member currentMember = chooseSearchResult(controller.getSearchResult());
            if (currentMember.getActivity().equals(MembershipStatus.COMPETITIVE)) {
                printEditCompetitiveMenu();
                handleCompetitiveEditMenuChoice(currentMember);
            } else {
                printEditNonCompetitiveMenu();
                handleNonCompetitiveEditMenuChoice(currentMember);
            }
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

    private void editActivity(Member currentMember) {
        currentMember.setActivity(readCompetitive());
    }

    private void editHasPaid(Member currenMember) {
        currenMember.setHasPaid(readHasPaid());
    }

    private void editSubscription() {
        printSubscriptionMenu();

        boolean wrongUserChoice = true;
        while (wrongUserChoice) {
            switch (readInt()) {
                case 1 -> {
                    System.out.println("Nuværende kontigent for passive medlemmer er: " + controller.getPassive() + " kr.");
                    controller.setPassive(readSubscription());
                    wrongUserChoice = false;
                }
                case 2 -> {
                    System.out.println("Nuværende kontigent for junior medlemmer er: " + controller.getJunior() + " kr.");
                    controller.setJunior(readSubscription());
                    wrongUserChoice = false;
                }
                case 3 -> {
                    System.out.println("Nuværende kontigent for senior medlemmer er: " + controller.getSenior() + " kr.");
                    controller.setSenior(readSubscription());
                    wrongUserChoice = false;
                }
                case 4 -> {
                    System.out.println("Nuværende rabat for senior+ medlemmer er: " + controller.getSeniorPlus() * 100 + " procent.");
                    controller.setSeniorPlus(readSubscriptionDiscount());
                    wrongUserChoice = false;
                }
                case 5 -> {
                    System.out.println("Nuværende rabat for studerende er: " + controller.getStudent() * 100 + " procent.");
                    controller.setStudent(readSubscriptionDiscount());
                    wrongUserChoice = false;
                }
                default -> System.out.println("Ugyldigt input. Prøv igen!");
            }
        }
    }

    private void editSwimDisciplins(Member member) {
        printSwimDisciplinMenu();

        boolean wrongUserchoice = true;
        while (wrongUserchoice) {
            switch (readInt()) {
                case 1 -> {
                    member.setCrawl(readSwimDisciplin("crawl"));
                    wrongUserchoice = false;
                }
                case 2 -> {
                    member.setBackCrawl(readSwimDisciplin("ryg crawl"));
                    wrongUserchoice = false;
                }
                case 3 -> {
                    member.setBreastStroke(readSwimDisciplin("bryst svømning"));
                    wrongUserchoice = false;
                }
                case 4 -> {
                    member.setButterfly(readSwimDisciplin("butterfly"));
                    wrongUserchoice = false;
                }
                default -> System.out.println("Ugyldigt input. Prøv igen!");
            }
        }
    }

    //*-------------------------------------------------Delete-------------------------------------------------------*\\
    private void deleteMember() {
        searchMember();
        if (!controller.getSearchResult().isEmpty()) {
            Member currentMember = chooseSearchResult(controller.getSearchResult());
            System.out.println(controller.deleteMember(currentMember));
        }
    }

    //*------------------------------------------------ExitProgram---------------------------------------------------*\\
    public void loadData() {
        controller.loadMembers();
        controller.loadSubscription();
        controller.loadTrainers();
    }

    private void saveData() {
        controller.saveMembers();
        controller.saveSubscription();
        controller.saveTrainers();
    }

    private void exitProgram() {
        saveData();
        loadData();
        System.exit(0);
    }

    //*-------------------------------------------------READS--------------------------------------------------------*\\
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
        System.out.print("Indtast mail: ");
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

    private MembershipStatus readCompetitive() {
        boolean wrongInput = true;
        MembershipStatus membershipStatus = MembershipStatus.NONE;
        while (wrongInput) {
            System.out.print("Medlemmets aktivitetsform (passiv/motionist/konkurrence): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "passiv", "pas", "p" -> {
                    membershipStatus = MembershipStatus.NONE;
                    wrongInput = false;
                }
                case "konkurrence", "kon", "k" -> {
                    membershipStatus = MembershipStatus.COMPETITIVE;
                    wrongInput = false;
                }
                case "motionist", "mot", "m" -> {
                    membershipStatus = MembershipStatus.HOBBY;
                    wrongInput = false;
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
                    hasPaid = true;
                    wrongInput = false;
                }
                case "nej", "n" -> {
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
                    hasDisciplin = true;
                    wrongInput = false;
                }
                case "nej", "n" -> {
                    hasDisciplin = false;
                    wrongInput = false;
                }
                default -> System.out.println("Ugyldigt input");
            }
        }

        return hasDisciplin;
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

    private double readSubscriptionDiscount() {
        System.out.print("Indtast ny rabat i procent som et tal mellem 0 og 100: ");
        double input = readDouble();
        while (input < 0 || input > 100) {
            System.out.println("Rabat skal være et tal mellem 0 og 100. Prøv igen: ");
            input = readDouble();
        }
        return input / 100;
    }

    private double readSwimTime() {
        System.out.print("Indtast ny svømmetid med formattet (m:s.ms): ");
        String input = scanner.nextLine();
        return calculateSwimTime(input);
    }


    // TODO: 05/12/2022 Fix format and test. 
    public double calculateSwimTime(String input) {
        String[] timeArray = input.split(":");
        double time = 0;
        try {
            time += Integer.parseInt(timeArray[0]) * 60;
            time += Double.parseDouble(timeArray[1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return time;
    }

    private LocalDate readSwimDate(Member member) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate swimDate = null;
        String errorMessage = "Ugyldig svømme dato prøv igen!";

        boolean wrongSwimdate = true;
        while (wrongSwimdate) {
            System.out.print("Intast Dagen der blev svømmet på (dd-mm-yyyy): ");
            String swimTimeInput = scanner.nextLine();
            try {
                swimDate = LocalDate.parse(swimTimeInput, dateFormat);
                if (swimDate.getYear() <= LocalDate.now().getYear() && swimDate.getYear() > member.getBirthdate().getYear()) {
                    wrongSwimdate = false;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
        return swimDate;
    }

    private SwimDisciplin chooseSwimDisciplin(Member member) {
        boolean wrongInput = true;
        SwimDisciplin swimDisciplin = SwimDisciplin.CRAWL;
        printChooseSwimDisciplin();
        while (wrongInput) {
            switch (readInt()) {
                case 1 -> {
                    if (member.getCrawl()) {
                        swimDisciplin = SwimDisciplin.CRAWL;
                        wrongInput = false;
                    } else {
                        System.out.println(member.getName() + " har ikke crawl");
                        wrongInput = false;
                    }
                }
                case 2 -> {
                    if (member.getBackCrawl()) {
                        swimDisciplin = SwimDisciplin.BACKCRAWL;
                        wrongInput = false;
                    } else {
                        System.out.println(member.getName() + " har ikke ryg crawl");
                        wrongInput = false;
                    }
                }
                case 3 -> {
                    if (member.getBreastStroke()) {
                        swimDisciplin = SwimDisciplin.BREASTSTROKE;
                        wrongInput = false;
                    } else {
                        System.out.println(member.getName() + " har ikke bryst svømning");
                        wrongInput = false;
                    }
                }
                case 4 -> {
                    if (member.getButterfly()) {
                        swimDisciplin = SwimDisciplin.BUTTERFLY;
                        wrongInput = false;
                    } else {
                        System.out.println(member.getName() + " har ikke butterfly");
                        wrongInput = false;
                    }
                }
                default -> System.out.println("Ugyldigt input");
            }
        }
        return swimDisciplin;
    }


}
