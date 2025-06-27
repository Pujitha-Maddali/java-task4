import java.io.*;
import java.util.Scanner;

public class NoteTaker {

    private static final String FILE_PATH = "mynotes.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean run = true;

        while (run) {
            showMenu();
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    addNote(input);
                    break;
                case "2":
                    displayNotes();
                    break;
                case "3":
                    run = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        input.close();
    }

    private static void showMenu() {
        System.out.println("\n--- Simple Note Taker ---");
        System.out.println("1. Add New Note");
        System.out.println("2. View Notes");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addNote(Scanner input) {
        System.out.print("Write your note here: ");
        String newNote = input.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(newNote);
            bw.newLine();
            System.out.println("Your Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving note.");
            System.err.println(e.getMessage());
        }
    }

    private static void displayNotes() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No notes available yet.");
            return;
        }

        System.out.println("\n Your Notes:");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int lineNo = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(lineNo++ + ") " + line);
            }
        } catch (IOException e) {
            System.out.println("Could not read notes.");
            System.err.println(e.getMessage());
        }
    }
}
