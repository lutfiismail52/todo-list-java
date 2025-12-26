package todo.view;

import todo.controller.TodoController;
import todo.model.Todo;

import javax.swing.*;
import java.awt.*;

public class TodoAppGUI extends JFrame {

    private TodoController todoList = new TodoController();
    private DefaultListModel<Todo> listModel = new DefaultListModel<>();
    private JList<Todo> list = new JList<>(listModel);
    private JTextField input = new JTextField();

    public TodoAppGUI() {
        setTitle("To-Do List (SQLite)");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        loadData();
    }

    private void initUI() {
        JButton addBtn = new JButton("Tambah");
        JButton toggleBtn = new JButton("Check / Uncheck");
        JButton delBtn = new JButton("Hapus");
        JButton editBtn = new JButton("Edit");

        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.add(input, BorderLayout.CENTER);
        top.add(addBtn, BorderLayout.EAST);

        JPanel bottom = new JPanel(new GridLayout(1, 3, 5, 5));
        bottom.add(toggleBtn);
        bottom.add(editBtn);
        bottom.add(delBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addTodo());
        toggleBtn.addActionListener(e -> toggleTodo());
        delBtn.addActionListener(e -> deleteTodo());
        editBtn.addActionListener(e -> editTodo());
    }

    private void loadData() {
        listModel.clear();
        for (Todo t : todoList.getAll()) {
            listModel.addElement(t);
        }
    }

    private void addTodo() {
        String text = input.getText().trim();
        if (!text.isEmpty()) {
            todoList.add(text);
            input.setText("");
            loadData();
        }
    }

    private void deleteTodo() {
        Todo selected = list.getSelectedValue();
        if (selected != null) {
            todoList.delete(selected.getId());
            loadData();
        }
    }

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

    private void toggleTodo() {
        Todo selected = list.getSelectedValue();
        if (selected != null) {
            todoList.toggle(selected.getId(), selected.isCompleted());
            loadData();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoAppGUI().setVisible(true));
    }
}
