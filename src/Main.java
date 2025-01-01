import java.io.*;
import java.util.Scanner;

public class Main {

    private static String fileName;
    private static String text = "";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        displayMenu();

        String choice;
        do {
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createNewFile();
                    break;
                case "2":
                    openFile();
                    break;
                case "3":
                    saveFile();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    displayMenu();
            }
        } while (!choice.equals("4"));

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Create a new file");
        System.out.println("2. Open an existing file");
        System.out.println("3. Save the file");
        System.out.println("4. Exit");
    }

    private static void createNewFile() {
        System.out.print("Enter file name: ");
        fileName = scanner.nextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            System.out.println("Enter text:");
            text = scanner.nextLine();
            writer.write(text);
            System.out.println("File created successfully!");
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    private static void openFile() {
        System.out.print("Enter file name: ");
        fileName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            text = sb.toString();
            System.out.println("File content:\n" + text);
        } catch (IOException e) {
            System.err.println("Error opening file: " + e.getMessage());
        }
    }

    private static void saveFile() {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Please create or open a file first.");
            return;
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(text);
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}