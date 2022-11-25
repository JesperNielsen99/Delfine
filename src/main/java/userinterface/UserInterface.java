package userinterface;

import datahandling.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in).useLocale(Locale.GERMAN);

    public void start() {
        System.out.println("Velkommen til Delfinen");
        printMainMenu();
        handleMainMenuChoice();

    }

    public void printMainMenu() {
        System.out.println("""
                Du har følgende valgmuligheder, hvad ønsker du at gøre?
                                
                1: Opret medlem 
                2: Søg efter medlem 
                3: Redigere medlem 
                4: Slet medlem """);
    }

    public void handleMainMenuChoice() {
        switch (readInt()) {
            case 1 -> createMember();
            case 2 -> searchMember();
            case 3 -> editMember();
            case 4 -> deleteMember();
            case 5 -> exitProgram();
            default -> System.out.println("invalid option");
        }


    }

    public void printEditMenu() {
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


    public void handleEditMenuChoice() {
    }


    void createMember() {
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
        boolean memberIsActive = readactive();
        boolean memberIsCompetitive = readCompetetive();

        controller.createMember(memberName, memberAddress, memberPhoneNumber, memberMail, memberBirthdate, memberSex, memberIsStudent, memberIsActive,memberIsCompetitive);

    }

    public void searchMember() {

    }

    public void editMember() {
        printEditMenu();
    }

    public void deleteMember() {

    }

    public void exitProgram() {
        System.exit(0);
    }

    public int readInt() {
        while (!scanner.hasNextInt()) {
            String wrongInput = scanner.nextLine();
            System.out.println("Du intastede [" + wrongInput + "] dette er ikke et helt tal.\nPrøv igen.");
        }
        int rightInput = scanner.nextInt();
        scanner.nextLine();
        return rightInput;
    }

    public double readDouble() {
        while (!scanner.hasNextDouble()) {
            String wrongInput = scanner.nextLine();
            System.out.println("Du intastede [" + wrongInput + "] dette er ikke et kommatal.\nPrøv igen.");
        }
        double rightInput = scanner.nextDouble();
        scanner.nextLine();
        return rightInput;

    }

    public String readMail() {
        System.out.println("Indtast mail");
        String input = scanner.nextLine();
        while (!input.contains("@") || input.contains("..")) {
            System.out.println("Ugyldig e-mail prøv igen");
            input = scanner.nextLine();
        }
        return input;
    }

    public LocalDate readBirthday() {
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

    public boolean readSex() {
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

    public boolean readStudent() {
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

    public boolean readactive() {
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

    public boolean readCompetetive() {
        boolean wrongInput = true;

        while (wrongInput) {
        }
        System.out.println("Er medlemmet konkurencesvømmer: ");
        switch (scanner.nextLine().toLowerCase()) {
            case "ja", "j" -> {
                return true;
            }
            case "nej", "n" -> {
                return false;
            }
            default -> System.out.println("Ugyldigt input");
        }
        return true;
    }

}
