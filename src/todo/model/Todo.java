package todo.model;

/**
 * Model Todo
 * Merepresentasikan satu data todo yang tersimpan di database
 */
public class Todo {

    private int id;
    private String title;
    private boolean completed;

    public Todo(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Digunakan oleh JList jika tidak memakai custom renderer
     */
    @Override
    public String toString() {
        return (completed ? "[✔] " : "[ ] ") + title;
    }
}
