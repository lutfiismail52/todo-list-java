package todo.view;

import todo.controller.TodoController;
import todo.model.Todo;

import javax.swing.*;
import java.awt.*;

/**
 * TodoAppGUI adalah VIEW.
 * Bertugas menampilkan antarmuka dan menerima input user.
 */
public class TodoAppGUI extends JFrame {

    // Controller sebagai penghubung ke logika & database
    private TodoController todoList = new TodoController();

    // Model list untuk JList
    private DefaultListModel<Todo> listModel = new DefaultListModel<>();
    private JList<Todo> list = new JList<>(listModel);

    // Input teks untuk menambah todo
    private JTextField input = new JTextField();

    /**
     * Constructor GUI
     */
    public TodoAppGUI() {
        setTitle("To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        loadData();
    }

    /**
     * Inisialisasi semua komponen UI
     */
    private void initUI() {
        JButton addBtn = new JButton("Tambah");
        JButton toggleBtn = new JButton("Check / Uncheck");
        JButton delBtn = new JButton("Hapus");
        JButton editBtn = new JButton("Edit");

        // Panel atas (input + tombol tambah)
        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.add(input, BorderLayout.CENTER);
        top.add(addBtn, BorderLayout.EAST);

        // Panel bawah (aksi)
        JPanel bottom = new JPanel(new GridLayout(1, 3, 5, 5));
        bottom.add(toggleBtn);
        bottom.add(editBtn);
        bottom.add(delBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // Event listener tombol
        addBtn.addActionListener(e -> addTodo());
        toggleBtn.addActionListener(e -> toggleTodo());
        delBtn.addActionListener(e -> deleteTodo());
        editBtn.addActionListener(e -> editTodo());
    }

    /**
     * Memuat ulang data dari database ke JList
     */
    private void loadData() {
        listModel.clear();
        for (Todo t : todoList.getAll()) {
            listModel.addElement(t);
        }
    }

    /**
     * Menambah todo baru
     */
    private void addTodo() {
        String text = input.getText().trim();
        if (!text.isEmpty()) {
            todoList.add(text);
            input.setText("");
            loadData();
        }
    }

    /**
     * Menghapus todo terpilih
     */
    private void deleteTodo() {
        Todo selected = list.getSelectedValue();
        if (selected != null) {
            todoList.delete(selected.getId());
            loadData();
        }
    }

    /**
     * Mengedit judul todo
     */
    private void editTodo() {
        Todo selected = list.getSelectedValue();
        if (selected != null) {
            String newTitle = JOptionPane.showInputDialog(
                    this,
                    "Edit tugas:",
                    selected.getTitle());

            if (newTitle != null && !newTitle.trim().isEmpty()) {
                todoList.edit(selected.getId(), newTitle.trim());
                loadData();
            }
        }
    }

    /**
     * Toggle status selesai / belum selesai
     */
    private void toggleTodo() {
        Todo selected = list.getSelectedValue();
        if (selected != null) {
            todoList.toggle(selected.getId(), selected.isCompleted());
            loadData();
        }
    }

    /**
     * Main alternatif (opsional)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoAppGUI().setVisible(true));
    }
}
