package todo;

import todo.view.TodoAppGUI;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new TodoAppGUI().setVisible(true));
  }
}
