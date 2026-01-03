package todo.dao;

import todo.model.Todo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object)
 * Menangani seluruh operasi database SQLite
 */
public class TodoDAO {

  private static final String DB_URL = "jdbc:sqlite:todo.db";

  public TodoDAO() {
    createTable();
  }

  private void createTable() {
    String sql = """
            CREATE TABLE IF NOT EXISTS todo (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                completed INTEGER NOT NULL
            )
        """;

    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      stmt.execute(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // CREATE
  public void addTodo(String title) {
    String sql = "INSERT INTO todo (title, completed) VALUES (?, 0)";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, title);
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // READ
  public List<Todo> getAllTodos() {
    List<Todo> list = new ArrayList<>();
    String sql = "SELECT * FROM todo";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        list.add(new Todo(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getInt("completed") == 1));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  // UPDATE title
  public void updateTitle(int id, String title) {
    String sql = "UPDATE todo SET title = ? WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, title);
      ps.setInt(2, id);
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // DELETE
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

  // TOGGLE completed
  public void toggleCompleted(int id, boolean currentStatus) {
    String sql = "UPDATE todo SET completed = ? WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, currentStatus ? 0 : 1);
      ps.setInt(2, id);
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
