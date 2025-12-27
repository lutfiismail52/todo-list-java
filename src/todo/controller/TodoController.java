package todo.controller;

import todo.dao.TodoDAO;
import todo.model.Todo;
import java.util.List;

/**
 * TodoController berfungsi sebagai CONTROLLER dalam pola MVC.
 * 
 * Tugas utama:
 * - Menerima permintaan dari View (GUI)
 * - Meneruskan logika ke DAO (database)
 * - Tidak mengandung kode UI atau SQL langsung
 */
public class TodoController {

    // DAO digunakan untuk berinteraksi langsung dengan database
    private TodoDAO dao = new TodoDAO();

    /**
     * Menambahkan todo baru ke database
     */
    public void add(String title) {
        dao.addTodo(title);
    }

    /**
     * Mengambil semua data todo dari database
     */
    public List<Todo> getAll() {
        return dao.getAllTodos();
    }

    /**
     * Menandai todo sebagai selesai (completed = true)
     */
    public void complete(int id) {
        dao.markCompleted(id);
    }

    /**
     * Mengubah judul todo
     */
    public void edit(int id, String title) {
        dao.updateTitle(id, title);
    }

    /**
     * Menghapus todo berdasarkan id
     */
    public void delete(int id) {
        dao.deleteTodo(id);
    }

    /**
     * Toggle status todo:
     * - Jika selesai → menjadi belum selesai
     * - Jika belum selesai → menjadi selesai
     */
    public void toggle(int id, boolean currentStatus) {
        dao.toggleCompleted(id, currentStatus);
    }
}
