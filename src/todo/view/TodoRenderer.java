package todo.view;

import todo.model.Todo; // Gunakan Todo
import javax.swing.*;
import java.awt.*;

public class TodoRenderer extends JCheckBox implements ListCellRenderer<Todo> {

    public TodoRenderer() {
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setOpaque(true); // ⬅️ WAJIB
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Todo> list,
            Todo value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        // Menggunakan method dari class Todo
        setText(value.getTitle()); 
        setSelected(value.isCompleted());

        if (isSelected) {
            setBackground(new Color(252, 228, 236));
        } else {
            setBackground(list.getBackground()); // ⬅️ PAKAI WARNA ASLI
        }

        return this;
    }
}