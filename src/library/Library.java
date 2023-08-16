package library;

import custom.InputHandler;
import custom.TableGenerate;
import library.controller.BookController;
import library.controller.MemberController;
import library.models.Book;
import library.models.LibraryRecord;
import library.models.Member;
import manager.FileManager;

import java.util.ArrayList;

public class Library {
    private ArrayList<LibraryRecord> libraryRecords = new ArrayList<>();
    private final BookController bookController = new BookController();
    private final MemberController memberController = new MemberController();
    private final InputHandler inputHandler = new InputHandler();
    private final FileManager fileManager = new FileManager();

    //=================================== Book Section ======================================

    // Menambahkan buku baru ke dalam koleksi buku
    public void addBook() {
        // Validasi dan konversi ISBN
        String ISBN = validateISBN();

        // Jika pengguna memilih keluar, kembali ke menu utama
        if (ISBN.equalsIgnoreCase("keluar")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }
        // Jika ISBN sudah ada, tampilkan pesan error
        else if (bookController.isISBN_Exist(convertISBN(ISBN))) {
            inputHandler.errorMessage("ISBN sudah ada, kembali ke menu.");
            inputHandler.newLine();
            return;
        }

        // Meminta input judul dan pengarang buku
        String title = inputHandler.getInputText("Masukkan judul buku: ");
        String author = inputHandler.getInputText("Masukkan nama pengarang: ");

        // Menambahkan buku dan tampilkan pesan sesuai hasil
        if (bookController.addBook(ISBN, title, author)) {
            System.out.println("Buku berhasil ditambahkan.");
        } else {
            inputHandler.errorMessage("Buku gagal ditambahkan.");
        }
        inputHandler.newLine();
    }

    // Menghapus buku dari koleksi buku
    public void removeBook() {
        // Validasi ISBN dan konfirmasi penghapusan
        String ISBN = validateISBN();

        // Jika pengguna memilih keluar, kembali ke menu utama
        if (ISBN.equalsIgnoreCase("keluar")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }

        String choice = inputHandler.getInputText("Apakah anda yakin ingin menghapus (Y/n)? ");
        // Menampilkan pesan dan kembali ke menu utama jika pengguna tidak yakin
        if (!choice.equals("Y")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }
        // Jika ISBN ditemukan, proses penghapusan buku
        else if (bookController.isISBN_Exist(ISBN)) {
            if (bookController.removeBook(ISBN)) {
                System.out.println("Buku "
                        + bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getTitle()
                        + " berhasil dihapus.");
            } else {
                inputHandler.errorMessage("Buku "
                        + bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getTitle()
                        + " gagal dihapus.");
            }
        }
        // Jika ISBN tidak ditemukan, tampilkan pesan error
        else {
            inputHandler.errorMessage("ISBN tidak ditemukan, kembali ke menu.");
            inputHandler.newLine();
        }
        inputHandler.newLine();
    }

    // Mengonversi ISBN ke format yang sesuai
    private String convertISBN(String ISBN) {
        // Proses konversi ISBN
        String input = ISBN.replace("-", "");

        try {
            input = input.substring(0, 3) + "-" + input.substring(3, 6) + "-"
                    + input.substring(6, 9) + "-" + input.substring(9, 12) + "-"
                    + input.charAt(12);
            return input;
        } catch (Exception e) {
            return "";
        }
    }

    // Validasi input ISBN dan konversi ke format yang sesuai
    private String validateISBN() {
        // Perulangan validasi hingga input ISBN valid
        while (true) {
            boolean error = false;
            System.out.println("Ketik 'keluar' untuk kembali.");
            String ISBN = inputHandler.getInputText("Masukkan ISBN: ").replace("-", "");

            // Jika pengguna memilih keluar, kembali ke menu utama
            if (ISBN.equalsIgnoreCase("keluar")) {
                return ISBN;
            }
            // Validasi bahwa input harus berupa bilangan bulat positif
            if (!ISBN.matches("\\d+")) {
                inputHandler.errorMessage("Harus berupa bilangan bulat positif.");
                error = true;
            }
            // Validasi bahwa ISBN harus memiliki 13 digit
            if (ISBN.length() != 13) {
                inputHandler.errorMessage("ISBN harus mengandung 13 digit.");
                error = true;
            }

            // Jika terdapat error, tampilkan pesan dan perulangan validasi
            if (error) {
                inputHandler.newLine();
                continue;
            }
            // Jika tidak ada error, konversi dan kembalikan ISBN yang valid
            return convertISBN(ISBN);
        }
    }

    // Menampilkan daftar buku berdasarkan judul atau pengarang
    public void findBook() {
        System.out.println("Ketik '.' untuk menampilkan semua list buku.");
        String inputBook = inputHandler.getInputText("Masukkan judul / author: ");
        inputHandler.newLine();

        // Jika input adalah '.', tampilkan semua buku
        if (inputBook.equals(".")) {
            bookController.showAllBooks();
        }
        // Jika input adalah judul atau pengarang, tampilkan buku yang sesuai
        else {
            bookController.showBooksByInput(inputBook);
        }
        inputHandler.delayInput();
    }

    // Menampilkan daftar buku yang tersedia di perpustakaan
    public void showBooksAvailable() {
        inputHandler.newLine();
        bookController.showBooksAvailable();
    }

    // Menampilkan daftar buku yang sedang dipinjam
    public void showBooksNonAvailable() {
        inputHandler.newLine();
        bookController.showBooksNonAvailable();
    }

    //=================================== Book Section ======================================

    //================================= Member Section ======================================

    // Menambahkan anggota baru ke dalam daftar anggota
    public void addMember() {
        System.out.println("Ketik 'keluar' untuk kembali.");
        String memberName = inputHandler.getInputText("Masukkan nama anggota baru: ");

        // Jika pengguna memilih keluar, kembali ke menu utama
        if (memberName.equalsIgnoreCase("keluar")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }

        // Menambahkan anggota baru dan tampilkan pesan berhasil
        memberController.addMember(memberName);
        System.out.println("Penambahan member berhasil.");
        inputHandler.newLine();
    }

    // Menghapus anggota dari daftar anggota
    public void removeMember() {
        System.out.println("Ketik 'keluar' untuk kembali.");
        String memberID = String.valueOf(inputHandler.getIntegerInput("Masukkan ID member yang ingin dihapus: "));

        // Jika pengguna memilih keluar, kembali ke menu utama
        if (memberID.equalsIgnoreCase("keluar")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }

        String choice = inputHandler.getInputText("Apakah anda yakin (Y/n)? ");
        // Jika pengguna tidak yakin, kembali ke menu utama
        if (!choice.equals("Y")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }
        // Jika ID anggota ditemukan, proses penghapusan
        else if (memberController.isMemberExistByInput(memberID)) {
            if (memberController.removeMember(memberID)) {
                System.out.println("Member "
                        + memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)).getName()
                        + " berhasil dihapus");
            } else {
                inputHandler.errorMessage("Member "
                        + memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)).getName()
                        + " gagal dihapus");
            }
        }
        // Jika ID anggota tidak ditemukan, tampilkan pesan error
        else {
            inputHandler.errorMessage("ID member tidak ditemukan");
        }
        inputHandler.newLine();
    }

    // Menampilkan daftar anggota berdasarkan ID atau nama
    public void findMember() {
        System.out.println("Ketik '.' untuk menampilkan semua list member.");
        String inputMember = inputHandler.getInputText("Masukkan id / nama member: ");
        inputHandler.newLine();

        // Jika input adalah '.', tampilkan semua anggota
        if (inputMember.equals(".")) {
            memberController.showMembers();
        }
        // Jika input adalah ID atau nama anggota, tampilkan anggota yang sesuai
        else {
            memberController.showMembersByInput(inputMember);
        }
        inputHandler.delayInput();
    }

    //================================= Member Section ======================================


    //================================= Library Section =====================================


    // Meminjam buku dari perpustakaan
    public void borrowBook() {
        System.out.println("Ketik 'keluar' untuk kembali.");
        String memberID = String.valueOf(inputHandler.getIntegerInput("Masukkan ID member yang ingin meminjam buku: "));

        // Jika pengguna memilih keluar, kembali ke menu utama
        if (memberID.equalsIgnoreCase("keluar")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }
        // Jika anggota tidak ditemukan, tampilkan pesan error
        else if (!memberController.isMemberExistByInput(memberID)) {
            inputHandler.errorMessage("Member tidak tersedia, kembali ke menu utama.");
            inputHandler.newLine();
            return;
        }

        String ISBN = validateISBN();

        // Jika pengguna memilih keluar, kembali ke menu utama
        if (ISBN.equalsIgnoreCase("keluar")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }
        // Jika jumlah buku habis, tampilkan pesan
        else if (bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getAmount() == 0) {
            System.out.println("Buku sedang dipinjam, kembali ke menu.");
            inputHandler.newLine();
            return;
        }

        // Meminjam buku dan menambahkan catatan peminjaman
        bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN))
                .setMember(memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)));
        bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).borrowBook();
        memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)).borrowBook();
        libraryRecords.add(new LibraryRecord(bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN))
                , memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID))));
        System.out.println("Buku "
                + bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getTitle()
                + " berhasil dipinjam oleh "
                + memberController.getMemberByIndex(memberController.getIndexMemberByInput(memberID)).getName()
                + ".");
        inputHandler.newLine();
    }

    // Mengembalikan buku yang telah dipinjam
    public void returnBorrowBook() {
        String ISBN = validateISBN();

        // Jika pengguna memilih keluar, kembali ke menu utama
        if (ISBN.equalsIgnoreCase("keluar")) {
            System.out.println("Kembali ke menu utama");
            inputHandler.newLine();
            return;
        }

        Member member = null;
        // Memeriksa status buku dan mencari anggota yang meminjam
        if (bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getStatus().equalsIgnoreCase("tidak aktif")) {
            System.out.println("Buku tidak tersedia.");
            inputHandler.newLine();
            return;
        } else if (bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getAmount() == 1) {
            System.out.println("Buku masih ada di perpustakaan.");
            inputHandler.newLine();
            return;
        } else {
            for (LibraryRecord libraryRecord : libraryRecords) {
                if (libraryRecord.getBook().getISBN().equals(ISBN)) {
                    member = libraryRecord.getMember();
                    break;
                }
            }
        }

        // Jika anggota tidak ditemukan, tampilkan pesan error
        if (member == null) {
            inputHandler.errorMessage("Gagal mengembalikan buku, kembali ke menu.");
            inputHandler.newLine();
            return;
        }

        // Mengembalikan buku dan menambahkan catatan peminjaman
        bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).setMember(null);
        bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).returnBook();
        memberController.getMemberByIndex(memberController.getIndexMemberByInput(member.getId())).returnBook();
        for (LibraryRecord libraryRecord: libraryRecords) {
            if (libraryRecord.getBook().equals
                    (bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)))) {
                libraryRecord.returnBorrowedBook();
                break;
            }
        }
        System.out.println("Buku "
                + bookController.getBookByIndex(bookController.getIndexBookByInput(ISBN)).getTitle()
                + " berhasil dikembalikan oleh "
                + member.getName() + ".");
        inputHandler.newLine();
    }

    // Menampilkan catatan peminjaman
    public void showRecord() {
        // Jika tidak ada catatan, tampilkan pesan kosong
        if (libraryRecords.size() == 0) {
            System.out.println("Belum ada catatan peminjaman. \n");
            inputHandler.newLine();
            return;
        }
        inputHandler.newLine();

        // Menampilkan catatan peminjaman dalam bentuk tabel
        TableGenerate tableGenerate = new TableGenerate(
                "Catatan Peminjaman",
                new String[]{"Waktu Peminjaman", "Nama Member", "Judul Buku", "Status", "Waktu Pengembalian"},
                new char[]{'c', 'l', 'l', 'l', 'c'},
                new int[]{19, 50, 50, 15, 19});
        tableGenerate.printSubTitle();
        for (int indexRecord = 0; indexRecord < libraryRecords.size(); indexRecord++) {
            tableGenerate.printBody(indexRecord, new String[]{
                    libraryRecords.get(indexRecord).getTimestampBorrow(),
                    libraryRecords.get(indexRecord).getMember().getName(),
                    libraryRecords.get(indexRecord).getBook().getTitle(),
                    libraryRecords.get(indexRecord).getStatusBorrow(),
                    String.valueOf(libraryRecords.get(indexRecord).getTimestampReturn())
            });
        }
        tableGenerate.line();
        inputHandler.delayInput();
    }

    //================================= Library Section =====================================

    // Menyimpan data ke file
    public void saveToFile() {
        fileManager.saveMembersToFile(memberController.getMembers());
        fileManager.saveBooksToFile(bookController.getBooks(), memberController.getMembers());
        fileManager.saveRecordToFile(libraryRecords, bookController.getBooks(), memberController.getMembers());
        System.out.println("Data berhasil di save.");
        inputHandler.delayInput();
    }

    // Memuat data dari file
    public void loadFromFile() {
        memberController.setMembers(fileManager.loadMembersFromFile());
        bookController.setBooks(fileManager.loadBookFromFile(memberController.getMembers()));
        libraryRecords = fileManager.loadRecordFromFile(bookController.getBooks(), memberController.getMembers());
        System.out.println("Data berhasil di load.");
        inputHandler.delayInput();
    }
}
