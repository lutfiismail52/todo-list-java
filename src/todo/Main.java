package todo;

// Import class View (tampilan GUI)
import todo.view.TodoView;

// Import class Controller (penghubung View dan Model)
import todo.controller.TodoController;

/**
 * Class Main
 * 
 * Berfungsi sebagai titik awal (entry point) aplikasi Java.
 * Method main() akan dijalankan pertama kali oleh JVM.
 */
public class Main {

    /**
     * Method main adalah method utama yang dijalankan saat aplikasi dimulai
     */
    public static void main(String[] args) {

        // Membuat objek TodoView
        // Ini adalah tampilan GUI (window) aplikasi To-Do List
        TodoView view = new TodoView();

        // Membuat objek TodoController
        // Controller menerima view sebagai parameter
        // Controller bertugas:
        // - Menangani event (klik tombol, pilih item)
        // - Mengatur alur data antara View dan Model
        new TodoController(view);

        // Menampilkan window aplikasi ke layar
        view.setVisible(true);
    }
}
