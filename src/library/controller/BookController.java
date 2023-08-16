package library.controller;

import custom.InputHandler;
import custom.TableGenerate;
import library.models.Book;

import java.util.ArrayList;

public class BookController {
    private ArrayList<Book> books;

    // Konstruktor untuk inisialisasi ArrayList books
    public BookController() {
        books = new ArrayList<>();
    }

    // Mendaftarkan daftar objek Buku
    public ArrayList<Book> getBooks() {
        return books;
    }

    // Mengatur daftar objek Buku
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    // Memeriksa apakah buku ada berdasarkan input ISBN, judul, atau penulis
    public boolean isBookExistByInput(String input) {
        for (Book book: books) {
            if (book.getISBN().equals(input) || book.getTitle().equalsIgnoreCase(input) || book.getAuthor().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    // Memeriksa apakah ISBN buku sudah ada
    public boolean isISBN_Exist(String ISBN) {
        for (Book book: books) {
            if (book.getISBN().equals(ISBN)) {
                return true;
            }
        }
        return false;
    }

    // Mendapatkan indeks buku berdasarkan input ISBN, judul, atau penulis
    public int getIndexBookByInput(String input) {
        for (int indexBook = 0; indexBook < books.size(); indexBook++) {
            if (books.get(indexBook).getISBN().equals(input) || books.get(indexBook).getTitle().equalsIgnoreCase(input) || books.get(indexBook).getAuthor().equalsIgnoreCase(input)) {
                return indexBook;
            }
        }
        return -1;
    }

    // Mendapatkan total jumlah buku tersedia berdasarkan judul
    public int getAmountAvailableByTitle(String title) {
        int sum = 0;
        for (Book book: books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                sum += book.getAmount();
            }
        }
        return sum;
    }

    // Mendapatkan objek Book berdasarkan indeks
    public Book getBookByIndex(int index) {
        return books.get(index);
    }

    // Menambahkan buku baru ke dalam ArrayList
    public boolean addBook(String ISBN, String title, String author) {
        if (isBookExistByInput(ISBN)) return false;
        books.add(new Book(ISBN, title, author));
        return true;
    }

    // Menonaktifkan buku berdasarkan input ISBN, judul, atau penulis
    public boolean removeBook(String input) {
        if (isBookExistByInput(input)) {
            if (books.get(getIndexBookByInput(input)).getAmount() == 0) {
                String choice = new InputHandler().getInputText("Buku masih dipinjam, apakah anda yakin (Y/n)? ");
                if (choice.equals("Y")) {
                    books.get(getIndexBookByInput(input)).deactivated();
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    // Menampilkan semua buku yang aktif
    public void showAllBooks() {
        showBooksByInput("");
    }

    // Menampilkan buku yang sesuai dengan input judul atau penulis
    public void showBooksByInput(String input) {
        boolean bookIsNotExist = true;

        // Inisialisasi objek TableGenerate untuk mencetak tabel
        TableGenerate tableGenerate = new TableGenerate("",
                new String[]{"ISBN", "Judul", "Penulis", "Jumlah"},
                new char[]{'c','l','l','c'},
                new int[]{17, 70, 40, 6});
        tableGenerate.printSubTitle();

        // Iterasi melalui daftar buku untuk mencetak tabel
        for (int indexBook = 0; indexBook < books.size(); indexBook++) {
            if (books.get(indexBook).getStatus().equalsIgnoreCase("aktif")) {
                if (books.get(indexBook).getTitle().toLowerCase().contains(input.toLowerCase()) ||
                        books.get(indexBook).getAuthor().toLowerCase().contains(input.toLowerCase())) {
                    tableGenerate.printBody(indexBook, new String[]{
                            books.get(indexBook).getISBN(),
                            books.get(indexBook).getTitle(),
                            books.get(indexBook).getAuthor(),
                            String.valueOf(books.get(indexBook).getAmount())
                    });
                    bookIsNotExist = false;
                }
            }
        }

        // Mencetak pesan jika buku tidak tersedia
        if (bookIsNotExist) {
            tableGenerate.printMessage("Buku tidak tersedia.");
        }
        tableGenerate.line();
    }

    // Menampilkan daftar buku yang tersedia di perpustakaan
    public void showBooksAvailable() {
        // Inisialisasi objek TableGenerate untuk mencetak tabel
        TableGenerate tableGenerate = new TableGenerate("",
                new String[]{"Judul", "Penulis", "Jumlah"},
                new char[]{'l','l', 'c'},
                new int[]{70, 40, 10});
        tableGenerate.printSubTitle();

        ArrayList<Book> uniqueTitle = new ArrayList<>();

        // Mengiterasi melalui daftar buku dan memeriksa apakah buku memiliki judul unik, status "aktif", dan jumlah 1.
        // Jika kriteria ini terpenuhi, buku akan ditambahkan ke daftar uniqueTitle.
        for (Book book: books) {
            boolean unique = true;
            for (Book book1: uniqueTitle) {
                if (book.getTitle().equalsIgnoreCase(book1.getTitle()) &&
                        book.getStatus().equalsIgnoreCase("aktif") &&
                        book.getAmount() == 1) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                uniqueTitle.add(book);
            }
        }

        // Iterasi melalui daftar buku untuk mencetak tabel
        for (int indexBook = 0; indexBook < uniqueTitle.size(); indexBook++) {
            tableGenerate.printBody(indexBook, new String[]{
                    uniqueTitle.get(indexBook).getTitle(),
                    uniqueTitle.get(indexBook).getAuthor(),
                    String.valueOf(getAmountAvailableByTitle(uniqueTitle.get(indexBook).getTitle()))
            });
        }

        // Mencetak pesan jika buku tidak tersedia
        if (uniqueTitle.size() == 0) {
            tableGenerate.printMessage("Tidak ada buku di perpustakaan.");
        }
        tableGenerate.line();
    }

    // Menampilkan daftar buku yang sedang dipinjam
    public void showBooksNonAvailable() {
        boolean bookIsNotExist = true;

        // Inisialisasi objek TableGenerate untuk mencetak tabel
        TableGenerate tableGenerate = new TableGenerate("",
                new String[]{"ISBN", "Judul", "Penulis", "ID", "Nama Peminjam"},
                new char[]{'c','l','l', 'c','l'},
                new int[]{17, 70, 30 , 6, 30});
        tableGenerate.printSubTitle();

        // Iterasi melalui daftar buku untuk mencetak tabel
        for (int indexBook = 0; indexBook < books.size(); indexBook++) {
            if (books.get(indexBook).getStatus().equalsIgnoreCase("aktif") &&
                    books.get(indexBook).getAmount() == 0) {
                tableGenerate.printBody(indexBook, new String[]{
                        books.get(indexBook).getISBN(),
                        books.get(indexBook).getTitle(),
                        books.get(indexBook).getAuthor(),
                        books.get(indexBook).getMember().getId(),
                        books.get(indexBook).getMember().getName()
                });
                bookIsNotExist = false;
            }
        }

        // Mencetak pesan jika buku tidak tersedia
        if (bookIsNotExist) {
            tableGenerate.printMessage("Tidak ada buku yang dipinjam.");
        }
        tableGenerate.line();
    }
}
