package custom;

public class DisplayPrint {

    // Fungsi untuk menghasilkan tampilan menu utama.
    public String displayMenu() {
        return """
                |=======================================|
                |                 Menu                  |
                |=======================================|
                |  1.  Manajemen Buku                   |
                |  2.  Manajemem Member                 |
                |  3.  Pinjam Buku                      |
                |  4.  Kembalikan Buku                  |
                |  5.  Tampilkan Record                 |
                |  6.  Simpan Data                      |
                |  7.  Load Data                        |
                |  0.  Keluar                           |
                |=======================================|
                Silahkan masukkan pilihan:\s""";
    }

    // Fungsi untuk menghasilkan tampilan menu manajemen buku.
    public String displayBookController() {
        return """
                |=======================================|
                |           Manajemen Buku              |
                |=======================================|
                |  1.  Tambah                           |
                |  2.  Hapus                            |
                |  3.  Tampilkan Buku yang Dipinjam     |
                |  4.  Tampilkan Buku yang Tersedia     |
                |  5.  Cari Buku                        |
                |  0.  Keluar                           |
                |=======================================|
                Silahkan masukkan pilihan:\s""";
    }

    // Fungsi untuk menghasilkan tampilan menu kelola barang.
    public String displayMemberController() {
        return """
                |=======================================|
                |           Manajemen Anggota           |
                |=======================================|
                |  1.  Tambah                           |
                |  2.  Hapus                            |
                |  3.  Tampilkan                        |
                |  0.  Keluar                           |
                |=======================================|
                Silahkan masukkan pilihan:\s""";
    }

}
