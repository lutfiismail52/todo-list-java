package todo.dao;

// Import model Todo
import todo.model.Todo;

// Import JDBC
import java.sql.*;

// Import List dan ArrayList
import java.util.*;

/**
 * Class TodoDAO
 * 
 * DAO (Data Access Object) bertugas sebagai penghubung
 * antara aplikasi dan database.
 * 
 * Tanggung jawab utama:
 * - Mengelola koneksi database
 * - Menjalankan query SQL
 * - Mengubah hasil query menjadi object Todo
 * 
 * Class ini:
 * ❌ Tidak tahu GUI
 * ❌ Tidak tahu Controller
 * ✅ Fokus ke database saja
 */
public class TodoDAO {

  // URL koneksi ke database SQLite
  // File todo.db akan otomatis dibuat jika belum ada
  private static final String DB_URL = "jdbc:sqlite:todo.db";

  /**
   * Constructor
   * 
   * Dipanggil saat object TodoDAO dibuat.
   * Secara otomatis memastikan tabel sudah tersedia.
   */
  public TodoDAO() {
    createTable();
  }

  /**
   * Membuat tabel todo jika belum ada
   */
  private void createTable() {
    String sql = """
            CREATE TABLE IF NOT EXISTS todo (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                completed INTEGER NOT NULL
            )
        """;

    /*
     * try-with-resources:
     * - Connection otomatis ditutup
     * - Statement otomatis ditutup
     * - Aman dari memory leak
     */
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      stmt.execute(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * CREATE
   * Menambahkan todo baru ke database
   */
  public void addTodo(String title) {
    String sql = "INSERT INTO todo (title, completed) VALUES (?, 0)";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      // Mengisi parameter ?
      ps.setString(1, title);

      // Menjalankan INSERT
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * READ
   * Mengambil seluruh data todo dari database
   */
  public List<Todo> getAllTodos() {
    List<Todo> todos = new ArrayList<>();
    String sql = "SELECT * FROM todo";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      // Membaca setiap baris hasil query
      while (rs.next()) {

        todos.add(new Todo(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getInt("completed") == 1));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return todos;
  }

  /**
   * UPDATE
   * Menandai todo sebagai selesai
   */
  public void markCompleted(int id) {
    String sql = "UPDATE todo SET completed = 1 WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, id);
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * UPDATE
   * Mengubah judul todo
   */
  public void updateTitle(int id, String newTitle) {
    String sql = "UPDATE todo SET title = ? WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, newTitle);
      ps.setInt(2, id);
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * DELETE
   * Menghapus todo berdasarkan id
   */
  public void deleteTodo(int id) {
    String sql = "DELETE FROM todo WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, id);
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * TOGGLE
   * Mengubah status completed:
   * true → false
   * false → true
   */
  public void toggleCompleted(int id, boolean currentStatus) {
    String sql = "UPDATE todo SET completed = ? WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      // Jika sekarang true → simpan 0
      // Jika sekarang false → simpan 1
      ps.setInt(1, currentStatus ? 0 : 1);
      ps.setInt(2, id);
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
