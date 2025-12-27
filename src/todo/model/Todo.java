package todo.model;

/**
 * Class Todo adalah MODEL.
 * Mewakili satu data todo di aplikasi.
 */
public class Todo {

    private int id;
    private String title;
    private boolean completed;

    /**
     * Constructor untuk membuat object Todo
     */
    public Todo(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // Getter id
    public int getId() {
        return id;
    }

    // Getter title
    public String getTitle() {
        return title;
    }

    // Getter status selesai
    public boolean isCompleted() {
        return completed;
    }

    // Setter status selesai
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Method ini menentukan tampilan Todo di JList.
     * 
     * Contoh:
     * [ ] Belajar Java
     * [✔] Kerjakan tugas
     */
    @Override
    public String toString() {
        return (completed ? "[✔] " : "[ ] ") + title;
    }
}
