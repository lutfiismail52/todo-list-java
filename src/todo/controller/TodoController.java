package todo.controller;

import todo.view.TodoView;
import todo.model.Todo;
import todo.dao.TodoDAO;
import java.util.List;
import javax.swing.JOptionPane;

public class TodoController {

    private TodoView view;
    private TodoDAO dao = new TodoDAO();

    public TodoController(TodoView view) {
        this.view = view;
        this.dao = new TodoDAO();

        view.btnTambah.addActionListener(e -> tambah());
        view.btnHapus.addActionListener(e -> hapus());
        view.btnEdit.addActionListener(e -> editMethodUntukView());

        view.listTodo.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int index = view.listTodo.locationToIndex(e.getPoint());
                if (index != -1) {
                    java.awt.Rectangle rect = view.listTodo.getCellBounds(index, index);
                    if (e.getX() < rect.x + 25) {
                        Todo selected = view.listModel.getElementAt(index);
                        dao.toggleCompleted(
                                selected.getId(),
                                selected.isCompleted());
                        loadDataKeView();
                        view.listTodo.setSelectedIndex(index);
                    }
                }
            }
        });

        loadDataKeView();
    }

    public TodoController() {
    }

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

    private void tambah() {
        String text = view.txtTodo.getText().trim();
        if (text.isEmpty())
            return;
        dao.addTodo(text);
        view.txtTodo.setText("");
        loadDataKeView();
    }

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
                dao.updateTitle(
                        selected.getId(),
                        newTitle.trim());
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
