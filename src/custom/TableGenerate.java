package custom;

import java.util.Arrays;

public class TableGenerate {
    private final String title;       // Judul tabel
    private final String[] subTitle;  // Array subjudul tabel
    private final char[] alignment;   // Penjajaran teks untuk setiap kolom (c - tengah, l - kiri)
    private final int[] textLength;   // Panjang teks untuk setiap kolom
    private final int widthTabel;     // Lebar total tabel

    // Konstruktor untuk inisialisasi objek tabel dengan berbagai parameter
    public TableGenerate(String title, String[] subTitle, char[] alignment, int[] textLength) {
        this.title = title;
        this.subTitle = subTitle;
        this.alignment = alignment;
        this.textLength = textLength;
        widthTabel = Arrays.stream(textLength).sum() + (subTitle.length * 3) - 1;
    }

    // Metode untuk mencetak judul tabel
    public void printTitle() {
        line();
        System.out.println("|  " + title + " ".repeat(widthTabel - title.length() - 4) + "  |");
        printSubTitle();  // Cetak subjudul tabel.
    }

    // Metode untuk mencetak subjudul tabel
    public void printSubTitle() {
        line(); // Mencetak garis pemisah sebelum baris subjudul
        System.out.print("| "); // Mencetak karakter pipa pertama pada setiap baris subjudul

        // Iterasi melalui setiap kolom subjudul untuk penataan teks
        for (int indexSubTitle = 0; indexSubTitle < textLength.length; indexSubTitle++) {
            // Menghitung jumlah spasi yang diperlukan untuk memusatkan teks subjudul
            int length = (textLength[indexSubTitle] - subTitle[indexSubTitle].length()) / 2;
            System.out.print(" ".repeat(length) + subTitle[indexSubTitle]); // Mencetak spasi sebelum teks subjudul

            // Menambahkan spasi setelah teks subjudul sesuai kebutuhan
            if ((textLength[indexSubTitle] - subTitle[indexSubTitle].length()) % 2 == 0) {
                System.out.print(" ".repeat(length));
            } else {
                System.out.print(" ".repeat(length + 1));
            }
            // Mencetak karakter pipa terakhir setiap kolom subjudul
            System.out.print(" | ");
        }

        System.out.println(); // Pindah baris setelah mencetak seluruh baris subjudul
        line(); // Mencetak garis pemisah setelah baris subjudul

    }

    // Metode untuk mencetak isi tabel
    public void printBody(int index, String[] content) {
        // Jika indeks baris genap, gunakan warna biru
        if (index % 2 == 0) {
            System.out.print("\033[34m");
        }

        System.out.print("| "); // Mencetak karakter pipa pertama pada baris konten tabel

        // Iterasi melalui setiap kolom konten
        for (int indexSubTitle = 0; indexSubTitle < content.length; indexSubTitle++) {
            // Jika penjajaran adalah tengah (c) atau Tengah (C)
            if (alignment[indexSubTitle] == 'c' || alignment[indexSubTitle] == 'C') {
                int length = (textLength[indexSubTitle] - content[indexSubTitle].length()) / 2;
                System.out.print(" ".repeat(length) + content[indexSubTitle]); // Mencetak spasi sebelum teks konten

                // Menambahkan spasi setelah teks konten sesuai kebutuhan
                if ((textLength[indexSubTitle] - content[indexSubTitle].length()) % 2 == 0) {
                    System.out.print(" ".repeat(length));
                } else {
                    System.out.print(" ".repeat(length + 1));
                }

                System.out.print(" | "); // Mencetak karakter pipa terakhir setiap kolom konten
            } else {
                // Mencetak teks konten tanpa perubahan jika penjajaran bukan tengah
                System.out.print(content[indexSubTitle] + " ".repeat(textLength[indexSubTitle] - content[indexSubTitle].length()) + " | ");
            }
        }

        System.out.println("\u001B[0m"); // Kembalikan warna teks ke normal setelah mencetak isi tabel

    }

    // Metode untuk mencetak pesan pada tabel
    public void printMessage(String message) {
        System.out.print("| "); // Mencetak karakter pipa pertama pada baris pesan khusus
        int length = widthTabel - message.length() - 2; // Menghitung jumlah spasi yang diperlukan untuk memusatkan pesan
        System.out.print(" ".repeat(length / 2) + message); // Mencetak spasi sebelum pesan untuk memusatkan teks

        // Menambahkan spasi setelah pesan sesuai kebutuhan
        if (length % 2 == 0) {
            System.out.print(" ".repeat(length / 2));
        } else {
            System.out.print(" ".repeat(length / 2 + 1));
        }

        System.out.print(" | \n"); // Mencetak karakter pipa terakhir dan pindah baris setelah pesan

    }

    // Metode untuk mencetak baris "keluar" pada tabel
    public void messageExit(int index) {
        // Jika indeks baris genap, gunakan warna biru
        if (index % 2 == 0) {
            System.out.print("\033[34m");

            // Mencetak karakter pipa pertama dan spasi pada baris "keluar"
            System.out.print("|" + " ".repeat(textLength[0]/2)
                    + "0" + " ".repeat(textLength[0]/2) + "| ");
        } else {
            // Mencetak karakter pipa pertama dan spasi pada baris "keluar" (baris ganjil)
            System.out.print("|" + " ".repeat(textLength[0]/2)
                    + "0" + " ".repeat(textLength[0]/2+ 1) + "| ");
        }

        // Mencetak teks "keluar" dan spasi untuk memusatkan ke sisi kanan
        System.out.print("keluar" + " ".repeat(widthTabel - textLength[0] - 10) + "|");

        System.out.println("\u001B[0m"); // Mengembalikan warna teks ke normal setelah mencetak baris "keluar"

    }

    // Metode untuk mencetak garis pemisah tabel
    public void line() {
        System.out.println("|" + "=".repeat(widthTabel) + "|");
    }
}
