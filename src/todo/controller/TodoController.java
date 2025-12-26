package todo.controller;

import todo.dao.TodoDAO;
import todo.model.Todo;
import java.util.List;

public class TodoController {

    private TodoDAO dao = new TodoDAO();

    public void add(String title) {
        dao.addTodo(title);
    }

    public List<Todo> getAll() {
        return dao.getAllTodos();
    }

    public void complete(int id) {
        dao.markCompleted(id);
    }

    public void edit(int id, String title) {
        dao.updateTitle(id, title);
    }

    public void delete(int id) {
        dao.deleteTodo(id);
    }

    public void toggle(int id, boolean currentStatus) {
        dao.toggleCompleted(id, currentStatus);
    }
}
