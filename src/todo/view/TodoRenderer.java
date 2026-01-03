package todo.view;

import todo.model.TodoItem;
import javax.swing.*;
import java.awt.*;

public class TodoRenderer extends JCheckBox implements ListCellRenderer<TodoItem> {

    public TodoRenderer() {
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBackground(Color.WHITE);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends TodoItem> list,
            TodoItem value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        setText(value.getText());
        setSelected(value.isDone());

        if (isSelected) {
            setBackground(new Color(252, 228, 236));
        } else {
            setBackground(Color.WHITE);
        }

        return this;
    }
}
