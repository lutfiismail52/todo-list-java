package todo;

import todo.view.TodoView;
import todo.controller.TodoController;

public class Main {
    public static void main(String[] args) {
        TodoView view = new TodoView();
        new TodoController(view);
        view.setVisible(true);
    }
}
