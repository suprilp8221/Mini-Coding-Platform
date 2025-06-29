import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class User {
    private String username;
    private HashSet<Integer> solvedQuestions = new HashSet<>();

    public User(String username) {
        this.username = username;
        loadProgress();
    }

    public String getUsername() {
        return username;
    }

    public void solveQuestion(int id) {
        if (!solvedQuestions.contains(id)) {
            solvedQuestions.add(id);
            saveProgress(id);  // Save to file only if not already present
        }
    }

    public int getSolvedCount() {
        return solvedQuestions.size();
    }

    private void saveProgress(int id) {
        try {
            FileWriter writer = new FileWriter(username + ".txt", true); // append mode
            writer.write(id + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving progress.");
        }
    }

    private void loadProgress() {
        File file = new File(username + ".txt");
        if (!file.exists()) return;

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                int id = Integer.parseInt(sc.nextLine().trim());
                solvedQuestions.add(id);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading progress.");
        }
    }
}
