package custom;

import java.util.Arrays;

public class TableGenerate {
    private final String title;         // Judul tabel
    private final String[] subTitle;    // Judul tiap kolom pada tabel
    private char[] alignment;           // Posisi align pada text
    private int[] textLength;           // Panjang kolom
    private final int widthTabel;       // Total panjang tabel

    /**
     * Konstruktor untuk menginisialisasi objek TableGenerate.
     *
     * @param title Judul tabel.
     * @param subTitle Array berisi judul-judul kolom.
     * @param alignment Array berisi penjajaran teks untuk setiap kolom ('l' untuk kiri, 'c' untuk tengah).
     * @param textLength Array berisi panjang teks maksimal untuk setiap kolom.
     */
    public TableGenerate(String title, String[] subTitle, char[] alignment, int[] textLength) {
        this.title = title;
        this.subTitle = subTitle;
        setAlignment(alignment);
        setTextLength(textLength);
        widthTabel = Arrays.stream(textLength).sum() + (subTitle.length * 3) - 1;
    }

    /**
     * Konstruktor untuk menginisialisasi objek TableGenerate.
     *
     * @param title Judul tabel.
     * @param subTitle Array berisi judul-judul kolom.
     */
    public TableGenerate(String title, String[] subTitle) {
        this.title = title;
        this.subTitle = subTitle;
        setAlignment(new char[0]);
        setTextLength(new int[0]);
        widthTabel = Arrays.stream(textLength).sum() + (subTitle.length * 3) - 1;
    }

    /**
     * Mengatur penjajaran teks untuk setiap kolom tabel berdasarkan array penjajaran yang diberikan.
     * Jika panjang array penjajaran kurang dari jumlah judul kolom, penjajaran default ('l' untuk kiri) akan digunakan
     * untuk kolom yang tersisa.
     *
     * @param alignment Array penjajaran teks untuk setiap kolom.
     */
    private void setAlignment(char[] alignment) {
        if (subTitle.length > alignment.length) {
            // Jika panjang array penjajaran lebih pendek dari jumlah judul kolom,
            // tambahkan penjajaran default ('l') untuk kolom yang tersisa.
            char[] temp = new char[subTitle.length];
            System.arraycopy(alignment, 0, temp, 0, alignment.length);
            for (int indexAlignment = alignment.length; indexAlignment < temp.length; indexAlignment++) {
                temp[indexAlignment] = 'l';
            }
            this.alignment = temp;
        } else if (alignment.length > subTitle.length) {
            // Jika panjang array penjajaran lebih panjang dari jumlah judul kolom,
            // gunakan hanya sebagian awalnya sesuai jumlah judul kolom.
            char[] temp = new char[subTitle.length];
            System.arraycopy(alignment, 0, temp, 0, temp.length);
            this.alignment = temp;
        } else {
            System.out.println("hai");
            // Jika panjang array penjajaran cocok dengan jumlah judul kolom, gunakan seperti adanya.
            this.alignment = alignment;
        }
    }

    /**
     * Mengatur panjang teks maksimal untuk setiap kolom tabel berdasarkan array panjang teks yang diberikan.
     * Jika panjang array panjang teks kurang dari jumlah judul kolom, panjang teks judul kolom akan digunakan
     * sebagai panjang teks maksimal.
     *
     * @param textLength Array panjang teks maksimal untuk setiap kolom.
     */
    private void setTextLength(int[] textLength) {
        if (subTitle.length > textLength.length) {
            // Jika panjang array panjang teks lebih pendek dari jumlah judul kolom,
            // gunakan panjang teks judul kolom sebagai panjang teks maksimal.
            int[] temp = new int[subTitle.length];
            System.arraycopy(textLength, 0, temp, 0, textLength.length);
            for (int indexTextLength = 0; indexTextLength < textLength.length; indexTextLength++) {
                if (subTitle[indexTextLength].length() > temp[indexTextLength]) {
                    temp[indexTextLength] = subTitle[indexTextLength].length();
                }
            }
            for (int indexTextLength = textLength.length; indexTextLength < temp.length; indexTextLength++) {
                temp[indexTextLength] = subTitle[indexTextLength].length();
            }
            this.textLength = temp;
        } else if (textLength.length > subTitle.length) {
            // Jika panjang array panjang teks lebih panjang dari jumlah judul kolom,
            // gunakan panjang teks judul kolom sebagai panjang teks maksimal untuk kolom yang sesuai.
            int[] temp = new int[subTitle.length];
            System.arraycopy(textLength, 0, temp, 0, temp.length);
            for (int indexTemp = 0; indexTemp < temp.length; indexTemp++) {
                if (subTitle[indexTemp].length() > temp[indexTemp]) {
                    temp[indexTemp] = subTitle[indexTemp].length();
                }
            }
            this.textLength = temp;
        } else {
            // Jika panjang array panjang teks cocok dengan jumlah judul kolom,
            // gunakan panjang teks judul kolom jika lebih besar dari panjang teks maksimal yang diberikan.
            for (int index = 0; index < textLength.length; index++) {
                if (subTitle[index].length() > textLength[index]) {
                    textLength[index] = subTitle[index].length();
                }
            }
            this.textLength = textLength;
        }
    }

    //============================== Function Pembuatan Tabel ===================================

    /**
     * Mencetak judul tabel beserta baris pemisah atasnya.
     */
    public void printTitle() {
        line(); // Mencetak baris pemisah atas tabel.

        // Mencetak judul tabel di tengah dengan ruang kosong di sisi kanan dan kiri.
        System.out.println("|  " + title + " ".repeat(widthTabel - title.length() - 4) + "  |");

        printSubTitle(); // Melanjutkan dengan pencetakan judul-judul kolom.
    }

    /**
     * Mencetak judul-judul kolom pada tabel dan baris pemisah di bawahnya.
     */
    public void printSubTitle() {
        line(); // Mencetak baris pemisah pada bawah judul tabel.
        System.out.print("| ");

        for (int indexSubTitle = 0; indexSubTitle < textLength.length; indexSubTitle++) {
            int length = (textLength[indexSubTitle] - subTitle[indexSubTitle].length()) / 2;

            // Menambahkan spasi di awal untuk menjaga penjajaran berdasarkan panjang teks maksimal.
            System.out.print(" ".repeat(length) + subTitle[indexSubTitle]);

            if ((textLength[indexSubTitle] - subTitle[indexSubTitle].length()) % 2 == 0) {
                System.out.print(" ".repeat(length));
            } else {
                System.out.print(" ".repeat(length + 1));
            }

            System.out.print(" | "); // Mencetak pemisah antara kolom.
        }

        System.out.println(); // Pindah baris setelah selesai mencetak judul-judul kolom.
        line(); // Mencetak baris pemisah di bawah judul-judul kolom.
    }

    /**
     * Mencetak isi konten untuk baris tubuh tabel pada indeks tertentu.
     *
     * @param index Indeks baris tubuh tabel yang akan dicetak.
     * @param content Array berisi konten untuk setiap kolom pada baris tersebut.
     */
    public void printBody(int index, String[] content) {
        String[] temp = new String[content.length];
        Arrays.fill(temp, "");
        boolean isTextMore = false;

        // Mengecek apakah konten lebih panjang dari panjang teks maksimal dan
        // membagi konten yang lebih panjang ke dalam array temp.
        for (int indexContent = 0; indexContent < content.length; indexContent++) {
            if (content[indexContent].length() > textLength[indexContent]) {
                temp[indexContent] = content[indexContent]
                        .substring(textLength[indexContent]);
                content[indexContent] = content[indexContent].substring(0, textLength[indexContent]);
                isTextMore = true;
            }
        }

        // Mengatur warna teks berdasarkan indeks baris (indeks genap: biru).
        if (index % 2 == 0) {
            System.out.print("\033[34m");
        }

        System.out.print("| ");

        for (int indexSubTitle = 0; indexSubTitle < content.length; indexSubTitle++) {
            if (alignment[indexSubTitle] == 'c' || alignment[indexSubTitle] == 'C') {
                int length = (textLength[indexSubTitle] - content[indexSubTitle].length()) / 2;
                System.out.print(" ".repeat(length) + content[indexSubTitle]);
                if ((textLength[indexSubTitle] - content[indexSubTitle].length()) % 2 == 0) {
                    System.out.print(" ".repeat(length));
                } else {
                    System.out.print(" ".repeat(length + 1));
                }
                System.out.print(" | ");
            } else {
                System.out.print(content[indexSubTitle] + " ".repeat(textLength[indexSubTitle] - content[indexSubTitle].length()) + " | ");
            }
        }

        System.out.println("\u001B[0m"); // Mengakhiri penggunaan warna teks.

        // Jika ada konten yang lebih panjang, lanjutkan mencetak dengan menggunakan array temp.
        if (isTextMore) {
            printBody(index, temp);
        }
    }

    /**
     * Mencetak pesan pada baris dengan lebar tabel.
     *
     * @param message Pesan yang akan dicetak.
     */
    public void printMessage(String message) {
        System.out.print("| ");

        // Menghitung jumlah spasi yang diperlukan untuk menjaga penjajaran pesan.
        int length = widthTabel - message.length() - 2;

        // Menambahkan spasi di awal pesan untuk menjaga penjajaran.
        System.out.print(" ".repeat(length / 2) + message);

        if (length % 2 == 0) {
            System.out.print(" ".repeat(length / 2));
        } else {
            System.out.print(" ".repeat(length / 2 + 1));
        }

        System.out.print(" | \n");
    }

    /**
     * Mencetak baris pemisah tabel dengan panjang tabel .
     */
    public void line() {
        System.out.println("|" + "=".repeat(widthTabel) + "|");
    }


    //============================== Function Pembuatan Tabel ===================================
}
