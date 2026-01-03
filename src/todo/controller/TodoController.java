package todo.controller;

// Mengimpor class View (GUI)
import todo.view.TodoView;

// Mengimpor Model Todo
import todo.model.Todo;

// Mengimpor DAO untuk akses database
import todo.dao.TodoDAO;

// Digunakan untuk menampung kumpulan Todo
import java.util.List;

// Digunakan untuk dialog input (Edit)
import javax.swing.JOptionPane;

/**
 * Class TodoController
 *
 * Controller berperan sebagai penghubung antara:
 * - View (TodoView)
 * - Model (Todo)
 * - DAO (TodoDAO)
 *
 * Semua event dari user (klik tombol, klik checkbox)
 * ditangani di class ini.
 */
public class TodoController {

    // Referensi ke tampilan GUI
    private TodoView view;

    // DAO untuk operasi database (CRUD)
    private TodoDAO dao = new TodoDAO();

    /**
     * Constructor utama
     *
     * Dipanggil dari Main.java
     * Saat controller dibuat:
     * - Event listener dipasang
     * - Data awal dimuat dari database
     */
    public TodoController(TodoView view) {
        this.view = view;

        // Inisialisasi DAO
        this.dao = new TodoDAO();

        // ================== EVENT TOMBOL ==================

        // Saat tombol Tambah diklik → panggil method tambah()
        view.btnTambah.addActionListener(e -> tambah());

        // Saat tombol Hapus diklik → panggil method hapus()
        view.btnHapus.addActionListener(e -> hapus());

        // Saat tombol Edit diklik → panggil method edit
        view.btnEdit.addActionListener(e -> editMethodUntukView());

        // ================== EVENT MOUSE PADA JLIST ==================
        // Digunakan untuk menangani klik checkbox pada JList
        view.listTodo.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                // 1. Mengambil index item berdasarkan posisi klik mouse
                int index = view.listTodo.locationToIndex(e.getPoint());

                // Jika klik berada pada item yang valid
                if (index != -1) {

                    // 2. Mengambil area (kotak) dari item tersebut
                    java.awt.Rectangle rect = view.listTodo.getCellBounds(index, index);

                    /*
                     * 3. HITBOX CHECKBOX
                     *
                     * Checkbox JCheckBox berada di sisi kiri item
                     * dengan lebar kira-kira 25 pixel.
                     *
                     * Jika posisi X klik berada di area tersebut,
                     * maka dianggap klik checkbox.
                     */
                    if (e.getX() < rect.x + 25) {

                        // Mengambil Todo yang diklik
                        Todo selected = view.listModel.getElementAt(index);

                        // Toggle status completed di database
                        dao.toggleCompleted(
                                selected.getId(),
                                selected.isCompleted());

                        // Refresh data agar UI sinkron dengan database
                        loadDataKeView();

                        // Menjaga item tetap terseleksi
                        view.listTodo.setSelectedIndex(index);
                    }
                }
            }
        });

        // Memuat data todo saat aplikasi pertama kali dibuka
        loadDataKeView();
    }

    /**
     * Constructor kosong
     *
     * Disediakan agar class tidak error jika dipanggil
     * tanpa TodoView (opsional / kompatibilitas).
     */
    public TodoController() {
    }

    // ======================================================
    // ========== METHOD UMUM (AKSES DATA) ==================
    // ======================================================

    // Mengambil seluruh todo dari database
    public List<Todo> getAll() {
        return dao.getAllTodos();
    }

    // Menambahkan todo baru
    public void add(String title) {
        dao.addTodo(title);
    }

    // Menghapus todo berdasarkan id
    public void delete(int id) {
        dao.deleteTodo(id);
    }

    // Mengedit judul todo
    public void edit(int id, String title) {
        dao.updateTitle(id, title);
    }

    // Toggle status completed todo
    public void toggle(int id, boolean status) {
        dao.toggleCompleted(id, status);
    }

    // ======================================================
    // ========== LOGIKA KHUSUS UNTUK TodoView ===============
    // ======================================================

    /**
     * Menangani aksi tombol Tambah
     */
    private void tambah() {

        // Mengambil teks dari input field
        String text = view.txtTodo.getText().trim();

        // Jika kosong, hentikan proses
        if (text.isEmpty())
            return;

        // Simpan todo ke database
        dao.addTodo(text);

        // Kosongkan input setelah ditambahkan
        view.txtTodo.setText("");

        // Refresh tampilan list
        loadDataKeView();
    }

    /**
     * Menangani aksi tombol Hapus
     */
    private void hapus() {

        // Mengambil todo yang sedang dipilih
        Todo selected = view.listTodo.getSelectedValue();

        // Pastikan ada item yang dipilih
        if (selected != null) {

            // Hapus todo dari database
            dao.deleteTodo(selected.getId());

            // Refresh tampilan
            loadDataKeView();
        }
    }

    /**
     * Menangani aksi tombol Edit
     */
    private void editMethodUntukView() {

        // Mengambil todo yang sedang dipilih
        Todo selected = view.listTodo.getSelectedValue();

        if (selected != null) {

            // Menampilkan dialog input untuk edit judul
            String newTitle = JOptionPane.showInputDialog(
                    view,
                    "Edit Tugas:",
                    selected.getTitle());

            // Validasi input:
            // - Tidak cancel
            // - Tidak kosong
            if (newTitle != null && !newTitle.trim().isEmpty()) {

                // Update judul di database
                dao.updateTitle(
                        selected.getId(),
                        newTitle.trim());

                // Refresh tampilan
                loadDataKeView();
            }
        }
    }

    /**
     * Memuat ulang data dari database ke JList
     *
     * Method ini adalah pusat sinkronisasi
     * antara database dan tampilan UI.
     */
    private void loadDataKeView() {

        // Pengaman jika view belum tersedia
        if (view == null)
            return;

        // Mengosongkan data lama di list
        view.listModel.clear();

        // Mengambil semua todo dari database
        // lalu memasukkannya kembali ke JList
        for (Todo t : dao.getAllTodos()) {
            view.listModel.addElement(t);
        }
    }
}
