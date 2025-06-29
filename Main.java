import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Question> loadQuestionsFromFile(String filename) {
        ArrayList<Question> questions = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",", 4); // Split into 4 parts only

                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String description = parts[2].trim();
                    String topic = parts[3].trim();

                    questions.add(new Question(id, title, description, topic));
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: questions.txt not found.");
        }

        return questions;
    }

    public static void main(String[] args) {
        ArrayList<Question> questions = loadQuestionsFromFile("questions.txt");

        Scanner sc = new Scanner(System.in);

        // Ask for username
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        User user = new User(name);

        int choice;

        while (true) {
            System.out.println("\n--- Mini Coding Platform ---");
            System.out.println("Welcome, " + user.getUsername() + "!");
            System.out.println("1. View All Questions");
            System.out.println("2. Attempt a Question");
            System.out.println("3. View My Progress");
            System.out.println("4. Exit");
            System.out.println("5. View Questions by Topic");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                for (Question q : questions) {
                    q.printQuestion();
                    System.out.println();
                }

            } else if (choice == 2) {
                System.out.print("Enter question ID to attempt: ");
                int id = sc.nextInt();

                boolean found = false;
                for (Question q : questions) {
                    if (q.getId() == id) {
                        q.printQuestion();
                        user.solveQuestion(id);
                        System.out.println("Marked as solved!");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Invalid question ID.");
                }

            } else if (choice == 3) {
                System.out.println(user.getUsername() + ", you have solved " + user.getSolvedCount() + " question(s).");

            } else if (choice == 5) {
                sc.nextLine(); // clear buffer
                System.out.print("Enter topic to filter (e.g., Array, String): ");
                String filterTopic = sc.nextLine();

                boolean found = false;
                for (Question q : questions) {
                    if (q.getTopic().equalsIgnoreCase(filterTopic)) {
                        q.printQuestion();
                        System.out.println();
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("No questions found for topic: " + filterTopic);
                }

            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }
}
