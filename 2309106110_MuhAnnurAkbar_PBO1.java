import java.util.ArrayList;
import java.util.Scanner;

class Peminjaman {
    private int id;
    private String namaUMKM;
    private double jumlahPinjaman;
    private String tanggalPinjaman;

    public Peminjaman(int id, String namaUMKM, double jumlahPinjaman, String tanggalPinjaman) {
        this.id = id;
        this.namaUMKM = namaUMKM;
        this.jumlahPinjaman = jumlahPinjaman;
        this.tanggalPinjaman = tanggalPinjaman;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaUMKM() {
        return namaUMKM;
    }

    public void setNamaUMKM(String namaUMKM) {
        this.namaUMKM = namaUMKM;
    }

    public double getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(double jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }

    public String getTanggalPinjaman() {
        return tanggalPinjaman;
    }

    public void setTanggalPinjaman(String tanggalPinjaman) {
        this.tanggalPinjaman = tanggalPinjaman;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama UMKM: " + namaUMKM + ", Jumlah Pinjaman: " + jumlahPinjaman + ", Tanggal Pinjaman: " + tanggalPinjaman;
    }
}

public class SistemPeminjamanUMKM {
    private static ArrayList<Peminjaman> daftarPeminjaman = new ArrayList<>();
    private static int nextId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Sistem Peminjaman Uang untuk UMKM ===");
            System.out.println("1. Tambah Peminjaman");
            System.out.println("2. Lihat Daftar Peminjaman");
            System.out.println("3. Update Peminjaman");
            System.out.println("4. Hapus Peminjaman");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahPeminjaman();
                    break;
                case 2:
                    lihatDaftarPeminjaman();
                    break;
                case 3:
                    updatePeminjaman();
                    break;
                case 4:
                    hapusPeminjaman();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem peminjaman uang untuk UMKM.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void tambahPeminjaman() {
        System.out.print("Masukkan Nama UMKM: ");
        String namaUMKM = scanner.nextLine();
        System.out.print("Masukkan Jumlah Pinjaman: ");
        double jumlahPinjaman = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Masukkan Tanggal Pinjaman (dd/mm/yyyy): ");
        String tanggalPinjaman = scanner.nextLine();

        Peminjaman peminjaman = new Peminjaman(nextId++, namaUMKM, jumlahPinjaman, tanggalPinjaman);
        daftarPeminjaman.add(peminjaman);
        System.out.println("Peminjaman berhasil ditambahkan.");
    }

    private static void lihatDaftarPeminjaman() {
        if (daftarPeminjaman.isEmpty()) {
            System.out.println("Tidak ada data peminjaman.");
        } else {
            for (Peminjaman peminjaman : daftarPeminjaman) {
                System.out.println(peminjaman);
            }
        }
    }

    private static void updatePeminjaman() {
        System.out.print("Masukkan ID Peminjaman yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Peminjaman peminjaman = cariPeminjamanById(id);
        if (peminjaman == null) {
            System.out.println("Peminjaman dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan Nama UMKM baru: ");
        String namaUMKM = scanner.nextLine();
        System.out.print("Masukkan Jumlah Pinjaman baru: ");
        double jumlahPinjaman = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Masukkan Tanggal Pinjaman baru (dd/mm/yyyy): ");
        String tanggalPinjaman = scanner.nextLine();

        peminjaman.setNamaUMKM(namaUMKM);
        peminjaman.setJumlahPinjaman(jumlahPinjaman);
        peminjaman.setTanggalPinjaman(tanggalPinjaman);
        System.out.println("Peminjaman berhasil diupdate.");
    }

    private static void hapusPeminjaman() {
        System.out.print("Masukkan ID Peminjaman yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Peminjaman peminjaman = cariPeminjamanById(id);
        if (peminjaman == null) {
            System.out.println("Peminjaman dengan ID " + id + " tidak ditemukan.");
            return;
        }

        daftarPeminjaman.remove(peminjaman);
        System.out.println("Peminjaman berhasil dihapus.");
    }

    private static Peminjaman cariPeminjamanById(int id) {
        for (Peminjaman peminjaman : daftarPeminjaman) {
            if (peminjaman.getId() == id) {
                return peminjaman;
            }
        }
        return null;
    }
}