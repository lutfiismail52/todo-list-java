# PROYEK APLIKASI TO-DO LIST

---

## Ringkasan Proyek

Proyek ini merupakan implementasi aplikasi To-Do List berbasis Java yang dibangun sebagai tugas besar mata kuliah Pemrograman Berorientasi Objek (PBO). Aplikasi menyajikan antarmuka grafis sederhana untuk menambah, menampilkan, dan mengelola daftar tugas (todo). Tujuan proyek adalah memperagakan konsep OOP, pemisahan tanggung jawab (separation of concerns), dan alur aplikasi berbasis arsitektur sederhana Model–View–Controller (MVC).

---

## Fitur Utama

- Menambah entri tugas baru.
- Menampilkan daftar tugas yang tersimpan selama sesi berjalan.
- Menghapus atau menandai tugas (opsional, tergantung implementasi GUI).
- Struktur kode mengikuti pola MVC untuk memisahkan logika, data, dan tampilan.

---

## Struktur Proyek

Susunan berkas dan direktori pada workspace adalah sebagai berikut:

```
.
├── bin/
├── lib/
│   └── sqlite-jdbc-3.51.1.0.jar
├── README.md
├── src/
│   └── todo/
│       ├── controller
│       │   └── TodoController.java
│       ├── dao
│       │   └── TodoDAO.java
│       ├── Main.java
│       ├── model
│       │   └── Todo.java
│       └── view
│           └── TodoAppGUI.java
└── todo.db
```

Keterangan singkat tiap paket/berkas sumber:

- `src/todo/Main.java`: Titik masuk aplikasi (entry point). Menyiapkan lingkungan dan memanggil antarmuka pengguna.
- `src/todo/model/Todo.java`: Model yang merepresentasikan entitas tugas (atribut: id, deskripsi, status, dsb.).
- `src/todo/dao/TodoDAO.java`: Data Access Object sederhana untuk mengelola koleksi `Todo` (penyimpanan di memori pada implementasi ini).
- `src/todo/controller/TodoController.java`: Pengontrol yang menerima perintah dari view dan memanipulasi model melalui DAO.
- `src/todo/view/TodoAppGUI.java`: Implementasi antarmuka grafis (GUI) untuk interaksi pengguna.

---

## Arsitektur dan Alur Data

Arsitektur mengikuti prinsip MVC:

- Model: `Todo` menyimpan data tugas.
- View: `TodoAppGUI` menampilkan antarmuka dan meneruskan tindakan pengguna (mis. klik tombol tambah) ke controller.
- Controller: `TodoController` memproses permintaan view, memvalidasi input bila perlu, dan memanggil `TodoDAO` untuk operasi CRUD pada model.

Alur sederhana saat menambah tugas:

1. Pengguna memasukkan teks tugas pada GUI lalu menekan tombol "Tambah".
2. `TodoAppGUI` memanggil `TodoController.addTodo(...)` dengan data input.
3. `TodoController` membuat objek `Todo` baru lalu meminta `TodoDAO` untuk menyimpannya.
4. `TodoDAO` menyimpan objek ke struktur data (mis. `List<Todo>`) dan view diperbarui untuk menampilkan daftar terkini.

---

## Petunjuk Build dan Menjalankan

Prasyarat:

- Java Development Kit (JDK) terpasang (versi 8 atau lebih baru direkomendasikan).

Langkah kompilasi dan eksekusi dari root proyek (Linux/macOS/Windows dengan shell):

```bash
# Kompilasi: menempatkan kelas terkompilasi ke folder bin
javac -d bin -sourcepath src $(find src -name "*.java")

# Menjalankan aplikasi
java -cp bin todo.Main
```

Catatan:

- Perintah `find` di atas berguna pada sistem berbasis Unix untuk menemukan semua berkas `.java` di dalam `src`. Pada Windows, Anda dapat menggunakan perintah kompile per-file atau menggunakan IDE.
- Jika menggunakan IDE (mis. IntelliJ IDEA atau Eclipse), impor proyek sebagai proyek Java biasa dan jalankan kelas `todo.Main`.

---

## Penjelasan Kelas dan Fungsinya

- `Main`:
	- Menginisialisasi komponen aplikasi (DAO, Controller, View) dan menghubungkannya.
	- Menyediakan titik masuk aplikasi.

- `Todo` (model):
	- Atribut umum: `id` (opsional), `description` (deskripsi tugas), `status` (mis. selesai/belum).
	- Menyediakan konstruktor, getter, dan setter.

- `TodoDAO`:
	- Menangani operasi penyimpanan data tugas pada memori (mis. `List<Todo>`).
	- Metode umum: `add`, `remove`, `listAll`, `findById`.

- `TodoController`:
	- Menyediakan API logika bisnis untuk view, seperti menambah tugas baru, menghapus tugas, dan mengambil daftar tugas.
	- Memastikan validasi input sederhana sebelum memodifikasi model.

- `TodoAppGUI`:
	- Menyajikan antarmuka grafis, menerima input pengguna, dan menampilkan daftar tugas.
	- Mendelegasikan aksi pengguna ke `TodoController`.

---

## Kontributor

- Lutfi Ismail Aliansah Putra (10224004)
- Nela Noviyanti (10224170)
- Sindy Syafa'atu Zakiyah (10224046)
- Muhamad Saparudin (10224071)
- Lutvi Maulana (10224005)

