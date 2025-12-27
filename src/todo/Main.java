package todo;

import todo.view.TodoAppGUI;
import javax.swing.SwingUtilities;

/**
 * Class Main adalah titik awal (entry point) aplikasi.
 * Aplikasi dijalankan dari method main().
 */
public class Main {
  public static void main(String[] args) {

    /*
     * SwingUtilities.invokeLater digunakan agar pembuatan
     * dan penampilan GUI dilakukan di Event Dispatch Thread (EDT).
     * 
     * Ini adalah praktik WAJIB dan BENAR saat membuat aplikasi GUI Swing,
     * agar tidak terjadi error atau perilaku UI yang tidak stabil.
     */
    SwingUtilities.invokeLater(() -> new TodoAppGUI().setVisible(true));
  }
}
