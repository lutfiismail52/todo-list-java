package todo.model;

/**
 * Class Todo
 * 
 * Berperan sebagai MODEL dalam arsitektur MVC.
 * Class ini merepresentasikan satu data todo.
 * 
 * Satu objek Todo = satu baris data pada tabel todo di database.
 */
public class Todo {

    // ===== ATRIBUT =====

    // ID unik todo (primary key di database)
    private int id;

    // Judul atau isi todo
    private String title;

    // Status todo:
    // true = selesai
    // false = belum selesai
    private boolean completed;

    /**
     * Constructor
     * 
     * Dipanggil saat objek Todo dibuat,
     * biasanya saat data dibaca dari database.
     */
    public Todo(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    /**
     * Getter untuk id
     * Digunakan controller atau DAO untuk mengetahui id todo
     */
    public int getId() {
        return id;
    }

    /**
     * Getter untuk title
     * Digunakan View (renderer) untuk menampilkan teks todo
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter status completed
     * Digunakan untuk:
     * - Menentukan checkbox dicentang atau tidak
     * - Menentukan logika toggle
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Setter status completed
     * Digunakan jika status todo diubah
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Method toString()
     * 
     * Dipanggil otomatis oleh Java ketika:
     * - Object ditampilkan sebagai String
     * - Digunakan di JList TANPA custom renderer
     * 
     * Pada program ini:
     * - Renderer custom digunakan
     * - Method ini tetap disediakan sebagai fallback
     */
    @Override
    public String toString() {
        return (completed ? "[✔] " : "[ ] ") + title;
    }
}
