package custom;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Mencetak pesan kesalahan dengan menggunakan warna teks merah.
     *
     * @param message Pesan kesalahan yang akan dicetak.
     */
    public void errorMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    /**
     * Meminta input bilangan bulat dengan operator + atau - dari pengguna.
     *
     * @param message Pesan yang akan ditampilkan sebelum meminta input.
     * @return Bilangan bulat hasil input pengguna.
     */
    public int getIntegerInputWithOperator(String message) {
        // Menampilkan pesan input dan mengambil baris input dari pengguna
        System.out.print(message);
        String input = scanner.nextLine().trim();

        // Selama input tidak sesuai dengan pola bilangan bulat atau operator + atau -
        while (!input.matches("^[-+]?\\d+")) {
            // Menampilkan pesan error jika input tidak sesuai
            errorMessage("Maaf, harap masukkan input berupa bilangan bulat atau menggunakan operator + atau -.");
            newLine(); // Metode newLine() tidak diberikan dalam kode ini, tetapi diasumsikan untuk mencetak baris baru.

            // Menampilkan ulang pesan input dan mengambil input baru dari pengguna
            System.out.print(message);
            input = scanner.nextLine().trim();
        }

        // Mengonversi input yang valid menjadi bilangan bulat dan mengembalikannya
        return Integer.parseInt(input);
    }

    // Mendapatkan input bilangan bulat positif
    public int getIntegerInput(String message) {
        // Menampilkan pesan input dan mengambil baris input dari pengguna
        System.out.print(message);
        String input = scanner.nextLine().trim();

        // Selama input tidak sesuai dengan pola bilangan bulat positif
        while (!input.matches("\\d+")) {
            // Menampilkan pesan error jika input tidak sesuai
            errorMessage("Maaf, harap masukkan input berupa bilangan bulat dan positif.");
            newLine();

            // Menampilkan ulang pesan input dan mengambil input baru dari pengguna
            System.out.print(message);
            input = scanner.nextLine().trim();
        }

        // Mengonversi input yang valid menjadi bilangan bulat dan mengembalikannya
        return Integer.parseInt(input);

    }

    /**
     * Memberikan jeda dengan menunggu input tanpa memprosesnya.
     * Berguna untuk memberikan waktu antara tampilan dan aksi pengguna.
     */
    public void delayInput() {
        scanner.nextLine();
    }

    /**
     * Meminta input teks yang tidak boleh kosong dari pengguna.
     *
     * @param message Pesan yang akan ditampilkan sebelum meminta input.
     * @return Teks hasil input pengguna setelah di-trim.
     */
    public String getInputText(String message) {
        // Menampilkan pesan input dan mengambil baris input dari pengguna
        System.out.print(message);
        String input = scanner.nextLine().trim();

        // Selama input kosong, tampilkan pesan error dan minta input ulang
        while (input.equals("")) {
            // Menampilkan pesan error jika input kosong
            errorMessage("Maaf, input tidak boleh kosong!");
            newLine(); // Metode newLine() tidak diberikan dalam kode ini, tetapi diasumsikan untuk mencetak baris baru.

            // Menampilkan ulang pesan input dan mengambil input baru dari pengguna
            System.out.print(message);
            input = scanner.nextLine().trim();
        }

        // Mengembalikan input yang valid setelah di-trim
        return input;
    }

    /**
     * Melakukan pemindahan baris sejumlah tertentu (opsional).
     *
     * @param count Jumlah baris yang akan dipindahkan (opsional).
     */
    public void newLine(int... count) {
        int numLines = (count.length > 0) ? count[0] : 1;
        for (int i = 0; i < numLines; i++) {
            System.out.println();
        }
    }

    /**
     * Menutup scanner untuk menghindari kebocoran sumber daya.
     * Disarankan untuk dipanggil setelah selesai menggunakan objek ini.
     */
    public void close() {
        scanner.close();
    }
}
