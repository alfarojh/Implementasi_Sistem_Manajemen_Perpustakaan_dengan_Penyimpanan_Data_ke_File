package custom;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    // Menampilkan pesan error dalam warna merah
    public void errorMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    // Mendapatkan input bilangan bulat dengan operator + atau -
    public int getIntegerInputWithOperator(String message) {
        // Menampilkan pesan input dan mengambil baris input dari pengguna
        System.out.print(message);
        String input = scanner.nextLine().trim();

        // Selama input tidak sesuai dengan pola bilangan bulat atau operator + atau -
        while (!input.matches("^[-+]?\\d+")) {
            // Menampilkan pesan error jika input tidak sesuai
            errorMessage("Maaf, harap masukkan input berupa bilangan bulat atau menggunakan operator + atau -.");
            newLine();

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

    // Menunggu input tanpa memprosesnya (untuk memberikan jeda)
    public void delayInput () {
        scanner.nextLine();
    }

    // Mendapatkan input teks yang tidak boleh kosong
    public String getInputText(String message) {
        // Menampilkan pesan input dan mengambil baris input dari pengguna
        System.out.print(message);
        String input = scanner.nextLine().trim();

        // Selama input kosong, tampilkan pesan error dan minta input ulang
        while (input.equals("")) {
            // Menampilkan pesan error jika input kosong
            errorMessage("Maaf, input tidak boleh kosong!");
            newLine();

            // Menampilkan ulang pesan input dan mengambil input baru dari pengguna
            System.out.print(message);
            input = scanner.nextLine().trim();
        }

        // Mengembalikan input yang valid setelah di-trim
        return input;

    }

    // Melakukan pemindahan baris sejumlah tertentu (opsional)
    public void newLine(int... count) {
        int numLines = (count.length > 0) ? count[0] : 1;
        for (int i = 0; i < numLines; i++) {
            System.out.println();
        }
    }


    // Menutup scanner untuk menghindari kebocoran sumber daya
    public void close () {
        scanner.close();
    }
}
