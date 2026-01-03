package todo.controller;

import todo.view.TodoView;
import todo.model.Todo;
import todo.dao.TodoDAO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controller: penghubung View dan DAO
 */
public class TodoController {
    private TodoView view;
    private TodoDAO dao = new TodoDAO();

    // Constructor untuk TodoView (Tampilan Pink)
    public TodoController(TodoView view) {
        this.view = view;
        this.dao = new TodoDAO();

        // Menghubungkan tombol ke metode
        view.btnTambah.addActionListener(e -> tambah());
        view.btnHapus.addActionListener(e -> hapus());
        view.btnEdit.addActionListener(e -> editMethodUntukView());

        view.listTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // 1. Dapatkan index baris yang diklik
                int index = view.listTodo.locationToIndex(e.getPoint());

                if (index != -1) {
                    // 2. Dapatkan area/kotak pembatas dari baris tersebut
                    java.awt.Rectangle rect = view.listTodo.getCellBounds(index, index);

                    /*
                     * * 3. CEK HITBOX CHECKBOX:
                     * Standarnya, kotak checkbox pada JCheckBox berada di sisi paling kiri
                     * dengan lebar sekitar 25 pixel. Kita cek apakah koordinat X klik
                     * berada di dalam area 25 pixel pertama tersebut.
                     */
                    if (e.getX() < rect.x + 25) {
                        Todo selected = view.listModel.getElementAt(index);

                        // Panggil toggle hanya jika area checkbox yang diklik
                        dao.toggleCompleted(selected.getId(), selected.isCompleted());

                        // Refresh tampilan
                        loadDataKeView();

                        // Pastikan baris tetap terseleksi agar bisa di-Edit/Hapus
                        view.listTodo.setSelectedIndex(index);
                    }
                }
            }
        });

        loadDataKeView();
    }

    // Constructor kosong agar TodoAppGUI tidak error
    public TodoController() {
    }

    // --- METODE UNTUK TodoAppGUI (Database) ---
    public List<Todo> getAll() {
        return dao.getAllTodos();
    }

    public void add(String title) {
        dao.addTodo(title);
    }

    public void delete(int id) {
        dao.deleteTodo(id);
    }

    public void edit(int id, String title) {
        dao.updateTitle(id, title);
    }

    public void toggle(int id, boolean status) {
        dao.toggleCompleted(id, status);
    }

    // --- LOGIKA UNTUK Tampilan Pink (TodoView) ---

    private void tambah() {
        String text = view.txtTodo.getText().trim();
        if (text.isEmpty())
            return;
        dao.addTodo(text);
        view.txtTodo.setText("");
        loadDataKeView();
    }

    // INI METODE YANG TADI HILANG (Penyebab Error)
    private void hapus() {
        Todo selected = view.listTodo.getSelectedValue();
        if (selected != null) {
            dao.deleteTodo(selected.getId());
            loadDataKeView();
        }
    }

    private void editMethodUntukView() {
        Todo selected = view.listTodo.getSelectedValue();
        if (selected != null) {
            String newTitle = JOptionPane.showInputDialog(
                    view,
                    "Edit Tugas:",
                    selected.getTitle());

            if (newTitle != null && !newTitle.trim().isEmpty()) {
                dao.updateTitle(selected.getId(), newTitle.trim());
                loadDataKeView();
            }
        }
    }

    private void loadDataKeView() {
        if (view == null)
            return;
        view.listModel.clear();
        for (Todo t : dao.getAllTodos()) {
            view.listModel.addElement(t);
        }
    }
}
