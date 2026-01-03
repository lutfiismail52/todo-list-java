package todo.view;

// Import model Todo
import todo.model.Todo;

// Import Swing dan AWT
import javax.swing.*;
import java.awt.*;

/**
 * Class TodoRenderer
 * 
 * Digunakan untuk mengatur tampilan setiap item di dalam JList<Todo>.
 * Menggunakan JCheckBox agar setiap todo bisa ditandai selesai/belum.
 */
public class TodoRenderer extends JCheckBox implements ListCellRenderer<Todo> {

    /**
     * Constructor TodoRenderer
     * Mengatur style dasar checkbox
     */
    public TodoRenderer() {
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBackground(Color.WHITE);
    }

    /**
     * Method ini dipanggil otomatis oleh JList
     * untuk setiap item yang akan ditampilkan
     */
    @Override
    public Component getListCellRendererComponent(
            JList<? extends Todo> list, // JList yang memanggil renderer
            Todo value, // Data Todo pada index tertentu
            int index, // Posisi item
            boolean isSelected, // Apakah item sedang dipilih
            boolean cellHasFocus) {

        // Menampilkan judul todo sebagai teks checkbox
        setText(value.getTitle());

        // Status checkbox sesuai dengan status todo
        setSelected(value.isCompleted());

        // Warna background saat item dipilih
        if (isSelected) {
            setBackground(new Color(252, 228, 236));
        } else {
            setBackground(Color.WHITE);
        }

        // Mengembalikan komponen checkbox sebagai tampilan item
        return this;
    }
}
