package todo.view;

import todo.model.TodoItem;
import javax.swing.*;
import java.awt.*;

public class TodoView extends JFrame {

    public JTextField txtTodo;
    public JButton btnTambah, btnEdit, btnHapus;
    public JList<TodoItem> listTodo;
    public DefaultListModel<TodoItem> listModel;

    public TodoView() {
        setTitle("My To-Do List");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // ===== PANEL ATAS =====
        JLabel title = new JLabel("🌷 My To-Do List", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(244, 143, 177));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        // ===== INPUT =====
        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        txtTodo = new JTextField();
        txtTodo.setFont(font);

        btnTambah = new JButton("➕ Tambah");
        btnTambah.setFont(font);

        inputPanel.add(txtTodo, BorderLayout.CENTER);
        inputPanel.add(btnTambah, BorderLayout.EAST);

        // ===== LIST =====
        listModel = new DefaultListModel<>();
        listTodo = new JList<>(listModel);
        listTodo.setCellRenderer(new TodoRenderer());
        listTodo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(listTodo);

        JPanel center = new JPanel(new BorderLayout());
        center.add(inputPanel, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        // ===== BUTTON BAWAH =====
        JPanel bottom = new JPanel(new FlowLayout());

        btnEdit = new JButton("✏ Edit");
        btnHapus = new JButton("🗑 Hapus");

        btnEdit.setFont(font);
        btnHapus.setFont(font);

        bottom.add(btnEdit);
        bottom.add(btnHapus);

        add(bottom, BorderLayout.SOUTH);
    }
}
