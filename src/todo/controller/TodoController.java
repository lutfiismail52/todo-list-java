package todo.controller;

import todo.view.TodoView;
import todo.model.TodoItem;

import javax.swing.*;

public class TodoController {

    private TodoView view;

    public TodoController(TodoView view) {
        this.view = view;

        view.btnTambah.addActionListener(e -> tambah());
        view.btnHapus.addActionListener(e -> hapus());
        view.btnEdit.addActionListener(e -> edit());

        // ⬇️ INI YANG BIKIN CHECKBOX BISA DIKLIK
        view.listTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int index = view.listTodo.locationToIndex(e.getPoint());
                if (index != -1) {
                    TodoItem item = view.listModel.get(index);
                    item.setDone(!item.isDone());
                    view.listTodo.repaint();
                }
            }
        });
    }

    private void tambah() {
        String text = view.txtTodo.getText().trim();
        if (text.isEmpty()) return;

        view.listModel.addElement(new TodoItem(text));
        view.txtTodo.setText("");
    }

    private void hapus() {
        int index = view.listTodo.getSelectedIndex();
        if (index != -1) {
            view.listModel.remove(index);
        }
    }

    private void edit() {
        int index = view.listTodo.getSelectedIndex();
        if (index == -1) return;

        TodoItem item = view.listModel.get(index);
        String baru = JOptionPane.showInputDialog(view, "Edit Todo:", item.getText());

        if (baru != null && !baru.trim().isEmpty()) {
            item = new TodoItem(baru);
            view.listModel.set(index, item);
        }
    }
}
