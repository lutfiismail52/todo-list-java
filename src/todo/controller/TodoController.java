package todo.controller;

import todo.view.TodoView;
import todo.model.Todo;
import todo.dao.TodoDAO;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller: penghubung View dan DAO
 */
public class TodoController {

    private TodoView view;
    private TodoDAO dao;

    public TodoController(TodoView view) {
        this.view = view;
        this.dao = new TodoDAO();

        view.btnTambah.addActionListener(e -> tambah());
        view.btnHapus.addActionListener(e -> hapus());
        view.btnEdit.addActionListener(e -> edit());

        view.listTodo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = view.listTodo.locationToIndex(e.getPoint());
                if (index == -1)
                    return;

                // ⬇️ PAKSA JList memilih item
                view.listTodo.setSelectedIndex(index);

                Todo todo = view.listModel.get(index);
                dao.toggleCompleted(todo.getId(), todo.isCompleted());
                loadData();
            }
        });

        loadData();
    }

    private void loadData() {
        view.listModel.clear();
        for (Todo t : dao.getAllTodos()) {
            view.listModel.addElement(t);
        }
    }

    private void tambah() {
        String text = view.txtTodo.getText().trim();
        if (text.isEmpty())
            return;

        dao.addTodo(text);
        view.txtTodo.setText("");
        loadData();
    }

    private void hapus() {
        Todo selected = view.listTodo.getSelectedValue();
        if (selected == null)
            return;

        dao.deleteTodo(selected.getId());
        loadData();
    }

    private void edit() {
        Todo selected = view.listTodo.getSelectedValue();
        if (selected == null)
            return;

        String baru = JOptionPane.showInputDialog(
                view,
                "Edit Todo:",
                selected.getTitle());

        if (baru != null && !baru.trim().isEmpty()) {
            dao.updateTitle(selected.getId(), baru.trim());
            loadData();
        }
    }
}
