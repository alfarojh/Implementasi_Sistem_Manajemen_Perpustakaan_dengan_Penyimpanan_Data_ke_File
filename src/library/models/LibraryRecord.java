package library.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LibraryRecord {
    // Menggunakan encapsulation pada timestamp, book, member dan statusBorrow
    private final String timestampBorrow;    // Waktu dan tanggal catatan
    private final Book book;           // Objek buku terkait dengan catatan
    private final Member member;       // Objek anggota terkait dengan catatan
    private String statusBorrow; // Status peminjaman (Dipinjam/Dikembalikan)
    private String timestampReturn;    // Waktu dan tanggal catatan

    // Konstruktor untuk membuat catatan peminjaman atau pengembalian buku
    public LibraryRecord(Book book, Member member) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.timestampBorrow = format.format(timestamp);
        this.book = book;
        this.member = member;
        this.timestampReturn = null;
        statusBorrow = "Dipinjam";
    }

    public LibraryRecord(String timestampBorrow, Book book, Member member, String statusBorrow, String timestampReturn) {
        this.timestampBorrow = timestampBorrow;
        this.book = book;
        this.member = member;
        this.statusBorrow = statusBorrow;
        this.timestampReturn = timestampReturn;
    }

    // Mengembalikan waktu peminjaman
    public String getTimestampBorrow() {
        return timestampBorrow;
    }

    // Mengembalikan objek buku terkait dengan catatan
    public Book getBook() {
        return book;
    }

    // Mengembalikan objek anggota terkait dengan catatan
    public Member getMember() {
        return member;
    }

    // Mengembalikan status peminjaman (Dipinjam/Dikembalikan)
    public String getStatusBorrow() {
        return statusBorrow;
    }

    // Mengembalikan waktu pengembalian
    public String getTimestampReturn() {
        return timestampReturn;
    }

    // Fungsi untuk menandai pengembalian buku dengan status "Dikembalikan"
    public void returnBorrowedBook() {
        statusBorrow = "Dikembalikan";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.timestampReturn = format.format(timestamp);
    }

}
