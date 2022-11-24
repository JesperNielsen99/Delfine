package userinterface;

import datahandling.Controller;

import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in).useLocale(Locale.GERMAN);

    public void start() {
        printMainMenu();
    }

    public void printMainMenu() {
        System.out.println("""
                1: Opret medlem 
                2: Redigere Medlemmer
                3: Søg efter medlemmer 
                4: Slet medlem """);
    }

    public void handleMainMenuChoice() {
       

    }

    public void printEditMenu() {
        System.out.println("""
                1:""");

    }

    public void handleEditMenuChoice() {

    }

    public void createMember() {

    }

    public void searchMember() {

    }

    public void editMember() {

    }

    public void deleteMember() {

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
}
