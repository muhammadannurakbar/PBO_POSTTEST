import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

abstract class Peminjaman {
    private final int id;  
    private String namaUMKM;
    private long jumlahPinjaman;
    private String tanggalPinjaman;
    protected String statusPinjaman;
    protected double bungaPinjaman;

    public Peminjaman(int id, String namaUMKM, long jumlahPinjaman, String tanggalPinjaman) {
        this.id = id;
        this.namaUMKM = namaUMKM;
        this.jumlahPinjaman = jumlahPinjaman;
        this.tanggalPinjaman = tanggalPinjaman;
        this.statusPinjaman = "Aktif";
        this.bungaPinjaman = 0.05; 
    }

    public Peminjaman(int id, String namaUMKM, long jumlahPinjaman, String tanggalPinjaman, double bungaPinjaman) {
        this.id = id;
        this.namaUMKM = namaUMKM;
        this.jumlahPinjaman = jumlahPinjaman;
        this.tanggalPinjaman = tanggalPinjaman;
        this.statusPinjaman = "Aktif";
        this.bungaPinjaman = bungaPinjaman;
    }

    public final int getId() {  
        return id;
    }

    public String getNamaUMKM() {
        return namaUMKM;
    }

    public void setNamaUMKM(String namaUMKM) {
        this.namaUMKM = namaUMKM;
    }

    public long getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(long jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }

    public String getTanggalPinjaman() {
        return tanggalPinjaman;
    }

    public void setTanggalPinjaman(String tanggalPinjaman) {
        this.tanggalPinjaman = tanggalPinjaman;
    }

    public String getStatusPinjaman() {
        return statusPinjaman;
    }

    public void setStatusPinjaman(String statusPinjaman) {
        this.statusPinjaman = statusPinjaman;
    }
    
    public double getBungaPinjaman() {
        return bungaPinjaman;
    }
    
    public void setBungaPinjaman(double bungaPinjaman) {
        this.bungaPinjaman = bungaPinjaman;
    }

    public abstract long hitungTotalPembayaran();
    
    public abstract long hitungTotalPembayaran(int periodeWaktu);

    void displayInfo() {
        System.out.println("Informasi Peminjaman: " + namaUMKM + " - " + formatRupiah(jumlahPinjaman));
    }
    
    void displayDetailInfo() {
        System.out.println("Detail Peminjaman:");
        System.out.println("ID: " + id);
        System.out.println("Nama UMKM: " + namaUMKM);
        System.out.println("Jumlah Pinjaman: " + formatRupiah(jumlahPinjaman));
        System.out.println("Tanggal Pinjaman: " + tanggalPinjaman);
        System.out.println("Status: " + statusPinjaman);
        System.out.println("Bunga: " + (bungaPinjaman * 100) + "%");
        System.out.println("Total Pembayaran: " + formatRupiah(hitungTotalPembayaran()));
    }

    protected final String formatRupiah(long amount) {  
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return rupiah.format(amount);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama UMKM: " + namaUMKM + ", Jumlah Pinjaman: " + formatRupiah(jumlahPinjaman)
                + ", Tanggal Pinjaman: " + tanggalPinjaman + ", Status: " + statusPinjaman 
                + ", Bunga: " + (bungaPinjaman * 100) + "%";
    }
}

final class PeminjamanModalKerja extends Peminjaman {
    private String kebutuhanModal;

    public PeminjamanModalKerja(int id, String namaUMKM, long jumlahPinjaman, String tanggalPinjaman, String kebutuhanModal) {
        super(id, namaUMKM, jumlahPinjaman, tanggalPinjaman);
        this.kebutuhanModal = kebutuhanModal;
        setBungaPinjaman(0.03);
    }
    
    public PeminjamanModalKerja(int id, String namaUMKM, long jumlahPinjaman, String tanggalPinjaman, String kebutuhanModal, double bungaPinjaman) {
        super(id, namaUMKM, jumlahPinjaman, tanggalPinjaman, bungaPinjaman);
        this.kebutuhanModal = kebutuhanModal;
    }

    public String getKebutuhanModal() {
        return kebutuhanModal;
    }

    public void setKebutuhanModal(String kebutuhanModal) {
        this.kebutuhanModal = kebutuhanModal;
    }
    
    @Override
    public long hitungTotalPembayaran() {
        return super.getJumlahPinjaman() + (long)(super.getJumlahPinjaman() * getBungaPinjaman() * 1.1);
    }
    
    @Override
    public long hitungTotalPembayaran(int periodeWaktu) {
        return super.getJumlahPinjaman() + (long)(super.getJumlahPinjaman() * getBungaPinjaman() * periodeWaktu);
    }
    
    @Override
    void displayDetailInfo() {
        super.displayDetailInfo();
        System.out.println("Jenis Peminjaman: Modal Kerja");
        System.out.println("Kebutuhan Modal: " + kebutuhanModal);
        System.out.println("Catatan: Bunga yang dikenakan lebih rendah untuk mendukung kegiatan operasional.");
    }

    @Override
    public String toString() {
        return super.toString() + ", Jenis: Modal Kerja, Kebutuhan: " + kebutuhanModal;
    }
}

final class PeminjamanInvestasi extends Peminjaman {
    private String jenisInvestasi;
    private int durasiInvestasi;

    public PeminjamanInvestasi(int id, String namaUMKM, long jumlahPinjaman, String tanggalPinjaman, String jenisInvestasi, int durasiInvestasi) {
        super(id, namaUMKM, jumlahPinjaman, tanggalPinjaman);
        this.jenisInvestasi = jenisInvestasi;
        this.durasiInvestasi = durasiInvestasi;
        setBungaPinjaman(0.07);
    }
    
    public PeminjamanInvestasi(int id, String namaUMKM, long jumlahPinjaman, String tanggalPinjaman, String jenisInvestasi, int durasiInvestasi, double bungaPinjaman) {
        super(id, namaUMKM, jumlahPinjaman, tanggalPinjaman, bungaPinjaman);
        this.jenisInvestasi = jenisInvestasi;
        this.durasiInvestasi = durasiInvestasi;
    }

    public String getJenisInvestasi() {
        return jenisInvestasi;
    }

    public void setJenisInvestasi(String jenisInvestasi) {
        this.jenisInvestasi = jenisInvestasi;
    }
    
    public int getDurasiInvestasi() {
        return durasiInvestasi;
    }
    
    public void setDurasiInvestasi(int durasiInvestasi) {
        this.durasiInvestasi = durasiInvestasi;
    }
    
    @Override
    public long hitungTotalPembayaran() {
        return super.getJumlahPinjaman() + (long)(super.getJumlahPinjaman() * getBungaPinjaman() * durasiInvestasi);
    }
    
    @Override
    public long hitungTotalPembayaran(int periodeWaktu) {
        return super.getJumlahPinjaman() + (long)(super.getJumlahPinjaman() * getBungaPinjaman() * periodeWaktu);
    }
    
    public long hitungTotalPembayaran(double tambahan) {
        return super.getJumlahPinjaman() + (long)(super.getJumlahPinjaman() * (getBungaPinjaman() + tambahan) * durasiInvestasi);
    }
    
    @Override
    void displayDetailInfo() {
        super.displayDetailInfo();
        System.out.println("Jenis Peminjaman: Investasi");
        System.out.println("Jenis Investasi: " + jenisInvestasi);
        System.out.println("Durasi Investasi: " + durasiInvestasi + " bulan");
        System.out.println("Total Pembayaran dengan Durasi: " + formatRupiah(hitungTotalPembayaran()));
    }

    @Override
    public String toString() {
        return super.toString() + ", Jenis: Investasi, Investasi: " + jenisInvestasi + ", Durasi: " + durasiInvestasi + " bulan";
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
            System.out.println("5. Lihat Detail Peminjaman");
            System.out.println("6. Simulasi Pembayaran");
            System.out.println("7. Keluar");
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
                    lihatDetailPeminjaman();
                    break;
                case 6:
                    simulasiPembayaran();
                    break;
                case 7:
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
        System.out.print("Masukkan Jumlah Pinjaman (tanpa titik/koma): ");
        long jumlahPinjaman = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Masukkan Tanggal Pinjaman (dd/mm/yyyy): ");
        String tanggalPinjaman = scanner.nextLine();

        System.out.println("Pilih Jenis Peminjaman:");
        System.out.println("1. Modal Kerja");
        System.out.println("2. Investasi");
        System.out.print("Pilihan: ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        Peminjaman peminjaman;
        if (jenis == 1) {
            System.out.print("Masukkan Kebutuhan Modal Kerja: ");
            String kebutuhan = scanner.nextLine();
            
            System.out.print("Gunakan bunga default (3%)? (y/n): ");
            String pilihBunga = scanner.nextLine();
            
            if (pilihBunga.equalsIgnoreCase("y")) {
                peminjaman = new PeminjamanModalKerja(nextId++, namaUMKM, jumlahPinjaman, tanggalPinjaman, kebutuhan);
            } else {
                System.out.print("Masukkan persentase bunga (contoh: 0.05 untuk 5%): ");
                double bunga = scanner.nextDouble();
                scanner.nextLine();
                peminjaman = new PeminjamanModalKerja(nextId++, namaUMKM, jumlahPinjaman, tanggalPinjaman, kebutuhan, bunga);
            }
        } else if (jenis == 2) {
            System.out.print("Masukkan Jenis Investasi: ");
            String investasi = scanner.nextLine();
            System.out.print("Masukkan Durasi Investasi (dalam bulan): ");
            int durasi = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Gunakan bunga default (7%)? (y/n): ");
            String pilihBunga = scanner.nextLine();
            
            if (pilihBunga.equalsIgnoreCase("y")) {
                peminjaman = new PeminjamanInvestasi(nextId++, namaUMKM, jumlahPinjaman, tanggalPinjaman, investasi, durasi);
            } else {
                System.out.print("Masukkan persentase bunga (contoh: 0.05 untuk 5%): ");
                double bunga = scanner.nextDouble();
                scanner.nextLine();
                peminjaman = new PeminjamanInvestasi(nextId++, namaUMKM, jumlahPinjaman, tanggalPinjaman, investasi, durasi, bunga);
            }
        } else {
            System.out.println("Jenis tidak valid. Membatalkan input.");
            return;
        }

        daftarPeminjaman.add(peminjaman);
        System.out.println("Peminjaman berhasil ditambahkan.");
        peminjaman.displayInfo();
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
        long jumlahPinjaman = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Masukkan Tanggal Pinjaman baru (dd/mm/yyyy): ");
        String tanggalPinjaman = scanner.nextLine();
        System.out.print("Masukkan Status Pinjaman (Aktif/Lunas): ");
        String statusPinjaman = scanner.nextLine();
        System.out.print("Masukkan Bunga Pinjaman baru (contoh: 0.05 untuk 5%): ");
        double bungaPinjaman = scanner.nextDouble();
        scanner.nextLine();

        peminjaman.setNamaUMKM(namaUMKM);
        peminjaman.setJumlahPinjaman(jumlahPinjaman);
        peminjaman.setTanggalPinjaman(tanggalPinjaman);
        peminjaman.setStatusPinjaman(statusPinjaman);
        peminjaman.setBungaPinjaman(bungaPinjaman);
        
        if (peminjaman instanceof PeminjamanModalKerja) {
            System.out.print("Masukkan Kebutuhan Modal Kerja baru: ");
            String kebutuhan = scanner.nextLine();
            ((PeminjamanModalKerja) peminjaman).setKebutuhanModal(kebutuhan);
        } else if (peminjaman instanceof PeminjamanInvestasi) {
            System.out.print("Masukkan Jenis Investasi baru: ");
            String jenisInvestasi = scanner.nextLine();
            ((PeminjamanInvestasi) peminjaman).setJenisInvestasi(jenisInvestasi);
            
            System.out.print("Masukkan Durasi Investasi baru (dalam bulan): ");
            int durasi = scanner.nextInt();
            scanner.nextLine();
            ((PeminjamanInvestasi) peminjaman).setDurasiInvestasi(durasi);
        }
        
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
    
    private static void lihatDetailPeminjaman() {
        System.out.print("Masukkan ID Peminjaman yang akan dilihat detailnya: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Peminjaman peminjaman = cariPeminjamanById(id);
        if (peminjaman == null) {
            System.out.println("Peminjaman dengan ID " + id + " tidak ditemukan.");
            return;
        }

        peminjaman.displayDetailInfo();
    }
    
    private static void simulasiPembayaran() {
        System.out.print("Masukkan ID Peminjaman untuk simulasi: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Peminjaman peminjaman = cariPeminjamanById(id);
        if (peminjaman == null) {
            System.out.println("Peminjaman dengan ID " + id + " tidak ditemukan.");
            return;
        }
        
        System.out.println("\n=== Simulasi Pembayaran ===");
        System.out.println("UMKM: " + peminjaman.getNamaUMKM());
        System.out.println("Jumlah Pinjaman: " + peminjaman.formatRupiah(peminjaman.getJumlahPinjaman()));
        System.out.println("Bunga: " + (peminjaman.getBungaPinjaman() * 100) + "%");
        
        System.out.println("Total Pembayaran (Normal): " + peminjaman.formatRupiah(peminjaman.hitungTotalPembayaran()));
        
        System.out.print("Masukkan periode pembayaran (bulan): ");
        int periode = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Total Pembayaran dengan periode " + periode + " bulan: " + 
                peminjaman.formatRupiah(peminjaman.hitungTotalPembayaran(periode)));
        
        if (peminjaman instanceof PeminjamanInvestasi) {
            System.out.print("Masukkan tambahan bunga untuk simulasi (contoh: 0.01 untuk 1%): ");
            double tambahan = scanner.nextDouble();
            scanner.nextLine();
            
            long totalDenganTambahan = ((PeminjamanInvestasi) peminjaman).hitungTotalPembayaran(tambahan);
            System.out.println("Total Pembayaran dengan tambahan bunga " + (tambahan * 100) + "%: " + 
                    peminjaman.formatRupiah(totalDenganTambahan));
        }
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