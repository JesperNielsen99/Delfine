package userinterface;
import datahandling.Controller;

public class UserInterface {
    private Controller controller = new Controller();

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
}
