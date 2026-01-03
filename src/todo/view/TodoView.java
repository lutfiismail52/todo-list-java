package todo.view;

// Import class Todo (Model)
import todo.model.Todo;

// Import komponen Swing
import javax.swing.*;

// Import layout dan warna
import java.awt.*;

/**
 * Class TodoView
 * 
 * Berfungsi sebagai tampilan (View) pada arsitektur MVC.
 * Semua komponen GUI didefinisikan di sini.
 */
public class TodoView extends JFrame {

    // ===== KOMPONEN INPUT =====
    // TextField untuk mengetik tugas baru
    public JTextField txtTodo;

    // Tombol aksi
    public JButton btnTambah, btnEdit, btnHapus;

    // JList untuk menampilkan daftar Todo
    public JList<Todo> listTodo;

    // Model data untuk JList
    // Menyimpan kumpulan objek Todo
    public DefaultListModel<Todo> listModel;

    /**
     * Constructor TodoView
     * Dipanggil saat objek TodoView dibuat
     * Bertugas membangun seluruh tampilan GUI
     */
    public TodoView() {

        // Judul window
        setTitle("My To-Do List");

        // Ukuran window
        setSize(500, 550);

        // Window muncul di tengah layar
        setLocationRelativeTo(null);

        // Program berhenti saat window ditutup
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Font default yang digunakan
        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // ===== PANEL ATAS (JUDUL) =====
        JLabel title = new JLabel("My To-Do List", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(244, 143, 177));

        // Memberi jarak di sekitar judul
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Menambahkan judul ke bagian atas JFrame
        add(title, BorderLayout.NORTH);

        // ===== PANEL INPUT =====
        // Panel untuk input todo dan tombol tambah
        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // TextField untuk mengetik todo
        txtTodo = new JTextField();
        txtTodo.setFont(font);

        // Tombol tambah todo
        btnTambah = new JButton("Tambah");
        btnTambah.setFont(font);

        // Menempatkan TextField di tengah panel
        inputPanel.add(txtTodo, BorderLayout.CENTER);

        // Menempatkan tombol tambah di kanan
        inputPanel.add(btnTambah, BorderLayout.EAST);

        // ===== LIST TODO =====
        // Model data list (menyimpan objek Todo)
        listModel = new DefaultListModel<>();

        // JList menggunakan listModel sebagai sumber data
        listTodo = new JList<>(listModel);

        // Mengatur tampilan item list menggunakan renderer custom
        listTodo.setCellRenderer(new TodoRenderer());

        // Hanya boleh memilih satu item
        listTodo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Membungkus JList agar bisa di-scroll
        JScrollPane scroll = new JScrollPane(listTodo);

        // Panel tengah (input + list)
        JPanel center = new JPanel(new BorderLayout());
        center.add(inputPanel, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        // Menambahkan panel tengah ke JFrame
        add(center, BorderLayout.CENTER);

        // ===== PANEL BAWAH (TOMBOL AKSI) =====
        JPanel bottom = new JPanel(new FlowLayout());

        // Tombol edit todo
        btnEdit = new JButton("Edit");

        // Tombol hapus todo
        btnHapus = new JButton("Hapus");

        btnEdit.setFont(font);
        btnHapus.setFont(font);

        bottom.add(btnEdit);
        bottom.add(btnHapus);

        // Menambahkan panel bawah ke JFrame
        add(bottom, BorderLayout.SOUTH);
    }
}
