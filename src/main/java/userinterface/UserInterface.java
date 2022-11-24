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
        /*switch (readInt()){*/
        /*    case 1 ->      */
        /*    case 2 ->*/
        /*    case 3 ->*/
        /*    case 4 ->*/
        /*    case 5 ->*/
        /*    case 6 ->*/
        /*    case 7 ->*/
        /*    case 8 ->*/
        /*    case 9 ->*/
        /*    case 10 ->*/
        /*    default -> System.out.println("Invald option");  */
        //}
    }


    void createMember() {

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
        String input = scanner.nextLine();
        while (!input.contains("@") || input.contains("..")) {
            System.out.println("Ugyldig e-mail prøv igen");
            input = scanner.nextLine();
        }
        return input;
    }

    public LocalDate readBirthday() {
        DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.print("Intast fødselsdag: ");
        String birthdayInput = scanner.nextLine();
        LocalDate birthday = null;

        boolean wrongBirthday = true;
        while (wrongBirthday) {
            try {
                birthday = LocalDate.parse(birthdayInput,birthdayFormat);
                wrongBirthday = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return birthday;
    }
}