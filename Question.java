public class Question {
    private int id;
    private String title;
    private String description;
    private String topic;

    public Question(int id, String title, String description, String topic) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public void printQuestion() {
        System.out.println("[" + id + "] " + title);
        System.out.println("Topic: " + topic);
        System.out.println("Description: " + description);
    }
}
