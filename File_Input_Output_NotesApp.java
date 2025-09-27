package Elevate_Labs;

import java.io.*;
import java.util.Scanner;
public class File_Input_Output_NotesApp {
        private static final String FILE_NAME = "notes.txt";


        public static void addNote() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your note: ");
            String note = sc.nextLine();

            try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // append mode
                writer.write(note + "\n");
                System.out.println(" Note saved successfully.");
            } catch (IOException e) {
                System.out.println("Error while saving note: " + e.getMessage());
            }
        }


        public static void readNotes() {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                System.out.println("\n Your Notes:");
                while ((line = reader.readLine()) != null) {
                    System.out.println("- " + line);
                }
            } catch (FileNotFoundException e) {
                System.out.println(" No notes found. Please add a note first.");
            } catch (IOException e) {
                System.out.println(" Error while reading notes: " + e.getMessage());
            }
        }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Add Note");
            System.out.println("2. Read Notes");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println(" Exiting Notes App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);

        sc.close();
    }
}
