package library.models;

public class Member {
    // Menggunakan encapsulation pada id, name dan status
    private final String id;         // ID anggota
    private final String name;       // Nama anggota
    private String status;     // Status anggota (aktif/tidak aktif)
    private int borrowedBookCount;

    // Konstruktor untuk inisialisasi objek anggota
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = "aktif";   // Status awal anggota adalah aktif
        this.borrowedBookCount = 0;
    }

    public Member(String id, String name, int borrowedBookCount, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.borrowedBookCount = borrowedBookCount;
    }

    // Mengembalikan ID anggota
    public String getId() {
        return id;
    }

    // Mengembalikan nama anggota
    public String getName() {
        return name;
    }

    // Mengembalikan status anggota (aktif/tidak aktif)
    public String getStatus() {
        return status;
    }

    // Mengembalikan jumlah buku yang sedang dipinjam
    public int getBorrowedBookCount() {
        return borrowedBookCount;
    }

    // Menonaktifkan anggota dengan mengubah status menjadi "tidak aktif"
    public void deactivated() {
        this.status = "tidak aktif";
    }

    // Menambahkan jumlah buku yang dipinjam
    public void borrowBook() {
        borrowedBookCount += 1;
    }

    // Mengurangi jumlah buku yang dipinjam
    public void returnBook() {
        borrowedBookCount -= 1;
    }
}
