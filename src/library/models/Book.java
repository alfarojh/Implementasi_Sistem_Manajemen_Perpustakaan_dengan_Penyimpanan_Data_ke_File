package library.models;

public class Book {
    // Menggunakan encapsulation pada ISBN, title, author, amount dan status
    private final String ISBN;         // ISBN buku
    private final String title;        // Judul buku
    private final String author;       // Penulis buku
    private int amount;          // Jumlah buku yang tersedia
    private String status;       // Status buku (aktif/tidak aktif)

    private Member member;

    // Konstruktor untuk inisialisasi objek buku
    public Book(String ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.amount = 1;           // Awalnya hanya ada satu salinan buku
        this.status = "aktif";     // Status awal buku adalah aktif
        this.member = null;
    }

    public Book(String ISBN, String title, String author, int amount, Member member, String status) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.amount = amount;
        this.status = status;
        this.member = member;
    }

    // Mengembalikan ISBN buku
    public String getISBN() {
        return ISBN;
    }

    // Mengembalikan judul buku
    public String getTitle() {
        return title;
    }

    // Mengembalikan nama penulis buku
    public String getAuthor() {
        return author;
    }

    // Mengembalikan jumlah salinan buku yang tersedia
    public int getAmount() {
        return amount;
    }

    // Mengembalikan status buku (aktif/tidak aktif)
    public String getStatus() {
        return status;
    }

    // Mengembalikan objek Member
    public Member getMember() {
        return member;
    }

    // Mengatur objek Member yang terkait dengan instance saat ini
    public void setMember(Member member) {
        this.member = member;
    }

    // Menonaktifkan buku dengan mengubah status menjadi "tidak aktif"
    public void deactivated() {
        this.status = "tidak aktif";
    }

    // Fungsi untuk meminjam buku, mengurangi jumlah salinan yang tersedia menjadi 0
    public void borrowBook() {
        amount = 0;
    }

    // Fungsi untuk mengembalikan buku setelah dipinjam, mengembalikan jumlah salinan menjadi 1
    public void returnBook() {
        amount = 1;
    }
}
