-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Jan 2020 pada 04.58
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tab_absen`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` char(4) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `foto` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `nama`, `username`, `password`, `foto`) VALUES
('AD01', 'rizkika', 'kaka', '$2y$10$uNGzA6kD9BDaSRCirVujVOAY57Z4DsTpS3hVQQfpfwvWWHmk/JdcS', 'FAD01');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bayar_spp`
--

CREATE TABLE `bayar_spp` (
  `id_bayar_spp` char(13) NOT NULL,
  `id_wali_murid` char(5) NOT NULL,
  `id_admin` char(4) NOT NULL,
  `waktu` datetime NOT NULL,
  `total_pertemuan` int(2) NOT NULL,
  `total_spp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `bayar_spp`
--

INSERT INTO `bayar_spp` (`id_bayar_spp`, `id_wali_murid`, `id_admin`, `waktu`, `total_pertemuan`, `total_spp`) VALUES
('BS200104-0001', 'WM001', 'AD01', '2020-01-04 11:46:35', 2, 105000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_bayar_spp`
--

CREATE TABLE `detail_bayar_spp` (
  `id_detail_bayar_spp` int(11) NOT NULL,
  `id_bayar_spp` char(13) NOT NULL,
  `id_pertemuan` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_bayar_spp`
--

INSERT INTO `detail_bayar_spp` (`id_detail_bayar_spp`, `id_bayar_spp`, `id_pertemuan`) VALUES
(5, 'BS200104-0001', 'PT200104-0001'),
(6, 'BS200104-0001', 'PT200104-0003');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_kelas_pertemuan`
--

CREATE TABLE `detail_kelas_pertemuan` (
  `id_detail_kelas_p` int(11) NOT NULL,
  `id_kelas_p` char(5) NOT NULL,
  `id_murid` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_kelas_pertemuan`
--

INSERT INTO `detail_kelas_pertemuan` (`id_detail_kelas_p`, `id_kelas_p`, `id_murid`) VALUES
(46, 'KL001', 'MR002'),
(43, 'KL001', 'MR003'),
(45, 'KL002', 'MR001'),
(44, 'KL002', 'MR002'),
(32, 'KL003', 'MR001'),
(33, 'KL003', 'MR002'),
(47, 'KL003', 'MR003'),
(40, 'KL004', 'MR001'),
(39, 'KL004', 'MR002'),
(35, 'KL005', 'MR001'),
(36, 'KL005', 'MR002'),
(41, 'KL007', 'MR001'),
(42, 'KL007', 'MR002');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_penggajian`
--

CREATE TABLE `detail_penggajian` (
  `id_detail_penggajian` int(11) NOT NULL,
  `id_penggajian` char(13) NOT NULL,
  `id_pertemuan` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_penggajian`
--

INSERT INTO `detail_penggajian` (`id_detail_penggajian`, `id_penggajian`, `id_pertemuan`) VALUES
(1, 'PG200104-0001', 'PT200103-0001'),
(2, 'PG200104-0001', 'PT200104-0001'),
(3, 'PG200105-0001', 'PT200104-0002'),
(4, 'PG200105-0001', 'PT200104-0003');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_pertemuan`
--

CREATE TABLE `detail_pertemuan` (
  `id_detail_pertemuan` int(11) NOT NULL,
  `id_pertemuan` char(13) NOT NULL,
  `id_murid` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_pertemuan`
--

INSERT INTO `detail_pertemuan` (`id_detail_pertemuan`, `id_pertemuan`, `id_murid`) VALUES
(12, 'PT200103-0001', 'MR001'),
(13, 'PT200103-0001', 'MR002'),
(14, 'PT200104-0001', 'MR001'),
(15, 'PT200104-0001', 'MR002'),
(16, 'PT200104-0002', 'MR003'),
(17, 'PT200104-0003', 'MR001'),
(18, 'PT200104-0003', 'MR002'),
(21, 'PT200105-0001', 'MR001'),
(22, 'PT200105-0001', 'MR002'),
(23, 'PT200105-0001', 'MR003'),
(24, 'PT200105-0002', 'MR001'),
(25, 'PT200105-0002', 'MR002'),
(26, 'PT200105-0002', 'MR003'),
(30, 'PT200105-0003', 'MR001'),
(31, 'PT200105-0003', 'MR002'),
(32, 'PT200105-0003', 'MR003');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kelas_pertemuan`
--

CREATE TABLE `kelas_pertemuan` (
  `id_kelas_p` char(5) NOT NULL,
  `id_pengajar` char(5) NOT NULL,
  `id_mata_pelajaran` char(4) NOT NULL,
  `hari` varchar(50) NOT NULL,
  `jam_mulai` time NOT NULL,
  `jam_berakhir` time NOT NULL,
  `harga_fee` int(10) NOT NULL,
  `harga_spp` int(10) NOT NULL,
  `id_sharing` char(5) NOT NULL,
  `nama_sharing` varchar(50) NOT NULL,
  `status` enum('Normal','Terhapus') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kelas_pertemuan`
--

INSERT INTO `kelas_pertemuan` (`id_kelas_p`, `id_pengajar`, `id_mata_pelajaran`, `hari`, `jam_mulai`, `jam_berakhir`, `harga_fee`, `harga_spp`, `id_sharing`, `nama_sharing`, `status`) VALUES
('KL001', 'PE001', 'MP02', 'Senin', '18:30:00', '20:30:00', 15000, 20000, 'null', 'kosong', 'Normal'),
('KL002', 'PE001', 'MP01', 'Rabu', '20:30:00', '20:30:00', 30000, 35000, 'null', 'kosong', 'Normal'),
('KL003', 'PE002', 'MP02', 'Selasa', '19:30:00', '16:30:00', 20000, 25000, 'null', 'kosong', 'Normal'),
('KL004', 'PE002', 'MP02', 'Kamis', '18:30:00', '18:30:00', 20000, 25000, 'null', 'kosong', 'Normal'),
('KL005', 'PE002', 'MP02', 'senin-jumat', '21:45:00', '22:30:00', 60000, 70000, 'null', 'kosong', 'Normal'),
('KL006', 'PE002', 'MP02', 'senin - jumat', '18:30:00', '19:30:00', 60000, 70000, 'null', 'kosong', 'Normal'),
('KL007', 'PE003', 'MP02', 'senin - jumat', '10:30:00', '11:30:00', 60000, 70000, 'null', 'kosong', 'Normal');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_detail_bayar_spp`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_detail_bayar_spp` (
`id_pertemuan` char(13)
,`id_pengajar` char(5)
,`nama_pengajar` varchar(50)
,`username` varchar(50)
,`foto` char(6)
,`hari_btn` varchar(6)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`deskripsi` text
,`id_kelas_p` char(5)
,`hari_jadwal` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`harga_fee` int(10)
,`harga_spp` int(10)
,`id_mata_pelajaran` char(4)
,`nama_mata_pelajaran` varchar(50)
,`id_detail_bayar_spp` int(11)
,`id_bayar_spp` char(13)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_detail_penggajian`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_detail_penggajian` (
`id_pertemuan` char(13)
,`id_pengajar` char(5)
,`nama_pengajar` varchar(50)
,`username` varchar(50)
,`foto` char(6)
,`hari_btn` varchar(6)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`deskripsi` text
,`id_kelas_p` char(5)
,`hari_jadwal` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`harga_fee` int(10)
,`id_mata_pelajaran` char(4)
,`nama_mata_pelajaran` varchar(50)
,`id_penggajian` char(13)
,`id_detail_penggajian` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_kelas`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_kelas` (
`id_kelas_p` char(5)
,`hari` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`harga_fee` int(10)
,`harga_spp` int(10)
,`id_sharing` char(5)
,`nama_sharing` varchar(50)
,`status` enum('Normal','Terhapus')
,`id_pengajar` char(5)
,`nama_pengajar` varchar(50)
,`id_mata_pelajaran` char(4)
,`nama_pelajaran` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_murid`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_murid` (
`id_murid` char(5)
,`nama` varchar(50)
,`id_wali_murid` char(5)
,`nama_wali_murid` varchar(50)
,`alamat` text
,`foto` char(6)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_murid_by_kelas`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_murid_by_kelas` (
`id_murid` char(5)
,`nama` varchar(50)
,`id_wali_murid` char(5)
,`nama_wali_murid` varchar(50)
,`alamat` text
,`foto` char(6)
,`id_kelas_p` char(5)
,`id_detail_kelas_p` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_penggajian`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_penggajian` (
`id_penggajian` char(13)
,`waktu` datetime
,`total_pertemuan` int(2)
,`total_harga_fee` int(10)
,`id_pengajar` char(5)
,`nama_pengajar` varchar(50)
,`id_admin` char(4)
,`nama_admin` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_pertemuan`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_pertemuan` (
`id_pertemuan` char(13)
,`id_pengajar` char(5)
,`nama_pengajar` varchar(50)
,`username` varchar(50)
,`foto` char(6)
,`hari_btn` varchar(6)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`deskripsi` text
,`id_kelas_p` char(5)
,`hari_jadwal` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`harga_fee` int(10)
,`harga_spp` int(10)
,`id_mata_pelajaran` char(4)
,`nama_mata_pelajaran` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_pertemuan_belum_selesai`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_pertemuan_belum_selesai` (
`id_pertemuan` char(13)
,`id_pengajar` char(5)
,`nama_pengajar` varchar(50)
,`username` varchar(50)
,`foto` char(6)
,`hari_btn` varchar(6)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`deskripsi` text
,`id_kelas_p` char(5)
,`hari_jadwal` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`harga_fee` int(10)
,`harga_spp` int(10)
,`id_mata_pelajaran` char(4)
,`nama_mata_pelajaran` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_pertemuan_selesai`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_pertemuan_selesai` (
`id_pertemuan` char(13)
,`id_pengajar` char(5)
,`nama_pengajar` varchar(50)
,`username` varchar(50)
,`foto` char(6)
,`hari_btn` varchar(6)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`deskripsi` text
,`id_kelas_p` char(5)
,`hari_jadwal` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`harga_fee` int(10)
,`harga_spp` int(10)
,`id_mata_pelajaran` char(4)
,`nama_mata_pelajaran` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_pertemuan_spp`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_pertemuan_spp` (
`id_pertemuan` char(13)
,`id_pengajar` char(5)
,`nama_pengajar` varchar(50)
,`username` varchar(50)
,`foto` char(6)
,`hari_btn` varchar(6)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`deskripsi` text
,`id_kelas_p` char(5)
,`hari_jadwal` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`harga_fee` int(10)
,`harga_spp` int(10)
,`id_mata_pelajaran` char(4)
,`nama_mata_pelajaran` varchar(50)
,`id_wali_murid` char(5)
,`nama_wali_murid` varchar(50)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `mata_pelajaran`
--

CREATE TABLE `mata_pelajaran` (
  `id_mata_pelajaran` char(4) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mata_pelajaran`
--

INSERT INTO `mata_pelajaran` (`id_mata_pelajaran`, `nama`) VALUES
('MP01', 'ALL MAPEL'),
('MP02', 'B. INGGRIS');

-- --------------------------------------------------------

--
-- Struktur dari tabel `murid`
--

CREATE TABLE `murid` (
  `id_murid` char(5) NOT NULL,
  `id_wali_murid` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `foto` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `murid`
--

INSERT INTO `murid` (`id_murid`, `id_wali_murid`, `nama`, `foto`) VALUES
('MR001', 'WM001', 'Kevin', 'FMR001'),
('MR002', 'WM001', '888', 'FMR002');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengajar`
--

CREATE TABLE `pengajar` (
  `id_pengajar` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `foto` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengajar`
--

INSERT INTO `pengajar` (`id_pengajar`, `nama`, `username`, `password`, `alamat`, `no_hp`, `foto`) VALUES
('PE001', 'sulisa', 'lala', '$2y$10$UIRpeDyrnPChA7x0VECBJOwJUUEY2pIX.JigU63dYYPrrPQ..ZIii', 'jemberj', '0181826624', 'FPE001'),
('PE002', 'dita rahmania h', 'dita', '$2y$10$/KrZpNAQzoTbGkiKvTJqRuD3nwh8ZmdC4iYfm4LVArciFyJjN5bqW', 'lumajang', '081525252', 'FPE002'),
('PE003', 'Novi', 'novi_bigstars', '$2y$10$xCb5/fFkn6BHR.rUaT2nkuogFCiUh.puKCtI3gz7c4H/Am/cl6x7C', 'Jl. Kalimantan', '089624941716', 'DEFFPE');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penggajian`
--

CREATE TABLE `penggajian` (
  `id_penggajian` char(13) NOT NULL,
  `id_pengajar` char(5) NOT NULL,
  `id_admin` char(4) NOT NULL,
  `waktu` datetime NOT NULL,
  `total_pertemuan` int(2) NOT NULL,
  `total_harga_fee` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `penggajian`
--

INSERT INTO `penggajian` (`id_penggajian`, `id_pengajar`, `id_admin`, `waktu`, `total_pertemuan`, `total_harga_fee`) VALUES
('PG200104-0001', 'PE002', 'AD01', '2020-01-04 11:52:54', 2, 62000),
('PG200105-0001', 'PE001', 'AD01', '2020-01-05 07:44:12', 2, 45000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pertemuan`
--

CREATE TABLE `pertemuan` (
  `id_pertemuan` char(13) NOT NULL,
  `id_pengajar` char(5) NOT NULL,
  `id_kelas_p` char(5) NOT NULL,
  `hari` varchar(6) NOT NULL,
  `waktu_mulai` datetime NOT NULL,
  `waktu_berakhir` datetime NOT NULL,
  `lokasi_mulai_la` text NOT NULL,
  `lokasi_mulai_lo` text NOT NULL,
  `lokasi_berakhir_la` text NOT NULL,
  `lokasi_berakhir_lo` text NOT NULL,
  `status_pertemuan` enum('Selesai','Belum Selesai','Batal') NOT NULL,
  `status_konfirmasi` enum('Valid','Invalid') NOT NULL,
  `status_fee` enum('Belum Terbayar','Sudah Terbayar') NOT NULL,
  `status_spp` enum('Belum Lunas','Sudah Lunas') NOT NULL,
  `harga_fee` int(10) NOT NULL,
  `harga_spp` int(10) NOT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pertemuan`
--

INSERT INTO `pertemuan` (`id_pertemuan`, `id_pengajar`, `id_kelas_p`, `hari`, `waktu_mulai`, `waktu_berakhir`, `lokasi_mulai_la`, `lokasi_mulai_lo`, `lokasi_berakhir_la`, `lokasi_berakhir_lo`, `status_pertemuan`, `status_konfirmasi`, `status_fee`, `status_spp`, `harga_fee`, `harga_spp`, `deskripsi`) VALUES
('PT200103-0001', 'PE002', 'KL003', 'Jum\'at', '2020-01-03 06:29:11', '2020-01-03 06:29:23', '-8.156506666666667', '113.72076833333334', '-8.156506666666667', '113.72076833333334', 'Selesai', 'Valid', 'Sudah Terbayar', 'Sudah Lunas', 2000, 1500, 'jejej'),
('PT200104-0001', 'PE002', 'KL005', 'Sabtu', '2020-01-04 02:46:51', '2020-01-04 02:46:59', '-8.156545', '113.72071833333332', '-8.156545', '113.72071833333332', 'Selesai', 'Valid', 'Sudah Terbayar', 'Sudah Lunas', 60000, 70000, 'jsmss'),
('PT200104-0002', 'PE001', 'KL001', 'Sabtu', '2020-01-04 02:47:40', '2020-01-04 02:47:46', '-8.156493333333334', '113.72067666666665', '-8.156493333333334', '113.72067666666665', 'Selesai', 'Valid', 'Sudah Terbayar', 'Belum Lunas', 15000, 20000, 'ksmsms'),
('PT200104-0003', 'PE001', 'KL002', 'Sabtu', '2020-01-04 02:47:52', '2020-01-04 02:47:58', '-8.155846666666667', '113.72334833333335', '-8.155846666666667', '113.72334833333335', 'Selesai', 'Valid', 'Sudah Terbayar', 'Sudah Lunas', 30000, 35000, 'jsmms'),
('PT200105-0001', 'PE002', 'KL003', 'Minggu', '2020-01-05 06:27:19', '2020-01-05 06:59:33', '-8.156578333333332', '113.72069166666667', '-8.156463333333333', '113.72088833333333', 'Selesai', 'Valid', 'Belum Terbayar', 'Belum Lunas', 20000, 25000, ''),
('PT200105-0002', 'PE002', 'KL003', 'Minggu', '2020-01-05 07:03:33', '2020-01-05 07:04:24', '-8.15642', '113.72060000000002', '-8.15661', '113.72073999999999', 'Selesai', 'Valid', 'Belum Terbayar', 'Belum Lunas', 20000, 25000, 'xnsms'),
('PT200105-0003', 'PE002', 'KL003', 'Minggu', '2020-01-05 10:25:40', '2020-01-05 10:25:51', '-8.156606666666667', '113.72071', '-8.156606666666667', '113.72071', 'Selesai', 'Invalid', 'Belum Terbayar', 'Belum Lunas', 20000, 25000, 'ubuyv');

-- --------------------------------------------------------

--
-- Struktur dari tabel `wali_murid`
--

CREATE TABLE `wali_murid` (
  `id_wali_murid` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `wali_murid`
--

INSERT INTO `wali_murid` (`id_wali_murid`, `nama`, `username`, `password`, `alamat`, `no_hp`) VALUES
('WM001', 'B DINAR', 'dinar_bigstars', '$2y$10$aREcL1zQzYn1htQsFds7duXSxF9o0OC61Aw9gpme/E6xQGP8egakq', 'JEMBER', '08634543677'),
('WM002', 'suheni', 'heni', '$2y$10$7jx1PewXCJy6bWiuSHXTHO40WN0luALPq/w5Pj0Vj6FcESuSgRBSi', 'jember', '019282');

-- --------------------------------------------------------

--
-- Struktur untuk view `list_detail_bayar_spp`
--
DROP TABLE IF EXISTS `list_detail_bayar_spp`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_detail_bayar_spp`  AS  select `pr`.`id_pertemuan` AS `id_pertemuan`,`pn`.`id_pengajar` AS `id_pengajar`,`pn`.`nama` AS `nama_pengajar`,`pn`.`username` AS `username`,`pn`.`foto` AS `foto`,`pr`.`hari` AS `hari_btn`,`pr`.`waktu_mulai` AS `waktu_mulai`,`pr`.`waktu_berakhir` AS `waktu_berakhir`,`pr`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`pr`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`pr`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`pr`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`pr`.`status_pertemuan` AS `status_pertemuan`,`pr`.`status_konfirmasi` AS `status_konfirmasi`,`pr`.`status_fee` AS `status_fee`,`pr`.`status_spp` AS `status_spp`,`pr`.`deskripsi` AS `deskripsi`,`kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari_jadwal`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`pr`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran`,`dbs`.`id_detail_bayar_spp` AS `id_detail_bayar_spp`,`dbs`.`id_bayar_spp` AS `id_bayar_spp` from ((((`pertemuan` `pr` join `pengajar` `pn` on((`pr`.`id_pengajar` = `pn`.`id_pengajar`))) join `kelas_pertemuan` `kp` on((`pr`.`id_kelas_p` = `kp`.`id_kelas_p`))) join `mata_pelajaran` `mp` on((`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`))) join `detail_bayar_spp` `dbs` on((`pr`.`id_pertemuan` = `dbs`.`id_pertemuan`))) order by `pr`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_detail_penggajian`
--
DROP TABLE IF EXISTS `list_detail_penggajian`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_detail_penggajian`  AS  select `pr`.`id_pertemuan` AS `id_pertemuan`,`pn`.`id_pengajar` AS `id_pengajar`,`pn`.`nama` AS `nama_pengajar`,`pn`.`username` AS `username`,`pn`.`foto` AS `foto`,`pr`.`hari` AS `hari_btn`,`pr`.`waktu_mulai` AS `waktu_mulai`,`pr`.`waktu_berakhir` AS `waktu_berakhir`,`pr`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`pr`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`pr`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`pr`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`pr`.`status_pertemuan` AS `status_pertemuan`,`pr`.`status_konfirmasi` AS `status_konfirmasi`,`pr`.`status_fee` AS `status_fee`,`pr`.`status_spp` AS `status_spp`,`pr`.`deskripsi` AS `deskripsi`,`kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari_jadwal`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`pr`.`harga_fee` AS `harga_fee`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran`,`dpg`.`id_penggajian` AS `id_penggajian`,`dpg`.`id_detail_penggajian` AS `id_detail_penggajian` from ((((`pertemuan` `pr` join `pengajar` `pn` on((`pr`.`id_pengajar` = `pn`.`id_pengajar`))) join `kelas_pertemuan` `kp` on((`pr`.`id_kelas_p` = `kp`.`id_kelas_p`))) join `mata_pelajaran` `mp` on((`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`))) join `detail_penggajian` `dpg` on((`pr`.`id_pertemuan` = `dpg`.`id_pertemuan`))) order by `pr`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_kelas`
--
DROP TABLE IF EXISTS `list_kelas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_kelas`  AS  select `kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`kp`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`kp`.`id_sharing` AS `id_sharing`,`kp`.`nama_sharing` AS `nama_sharing`,`kp`.`status` AS `status`,`pe`.`id_pengajar` AS `id_pengajar`,`pe`.`nama` AS `nama_pengajar`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_pelajaran` from ((`kelas_pertemuan` `kp` join `mata_pelajaran` `mp` on((`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`))) join `pengajar` `pe` on((`kp`.`id_pengajar` = `pe`.`id_pengajar`))) order by `kp`.`id_kelas_p` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_murid`
--
DROP TABLE IF EXISTS `list_murid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_murid`  AS  select `m`.`id_murid` AS `id_murid`,`m`.`nama` AS `nama`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`wm`.`alamat` AS `alamat`,`m`.`foto` AS `foto` from (`murid` `m` join `wali_murid` `wm` on((`m`.`id_wali_murid` = `wm`.`id_wali_murid`))) order by `m`.`nama` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_murid_by_kelas`
--
DROP TABLE IF EXISTS `list_murid_by_kelas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_murid_by_kelas`  AS  select `m`.`id_murid` AS `id_murid`,`m`.`nama` AS `nama`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`wm`.`alamat` AS `alamat`,`m`.`foto` AS `foto`,`dkp`.`id_kelas_p` AS `id_kelas_p`,`dkp`.`id_detail_kelas_p` AS `id_detail_kelas_p` from ((`murid` `m` join `wali_murid` `wm` on((`m`.`id_wali_murid` = `wm`.`id_wali_murid`))) join `detail_kelas_pertemuan` `dkp` on((`m`.`id_murid` = `dkp`.`id_murid`))) order by `m`.`nama` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_penggajian`
--
DROP TABLE IF EXISTS `list_penggajian`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_penggajian`  AS  select `pg`.`id_penggajian` AS `id_penggajian`,`pg`.`waktu` AS `waktu`,`pg`.`total_pertemuan` AS `total_pertemuan`,`pg`.`total_harga_fee` AS `total_harga_fee`,`pe`.`id_pengajar` AS `id_pengajar`,`pe`.`nama` AS `nama_pengajar`,`ad`.`id_admin` AS `id_admin`,`ad`.`nama` AS `nama_admin` from ((`penggajian` `pg` join `pengajar` `pe` on((`pg`.`id_pengajar` = `pe`.`id_pengajar`))) join `admin` `ad` on((`pg`.`id_admin` = `ad`.`id_admin`))) order by `pg`.`id_penggajian` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_pertemuan`
--
DROP TABLE IF EXISTS `list_pertemuan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_pertemuan`  AS  select `pr`.`id_pertemuan` AS `id_pertemuan`,`pn`.`id_pengajar` AS `id_pengajar`,`pn`.`nama` AS `nama_pengajar`,`pn`.`username` AS `username`,`pn`.`foto` AS `foto`,`pr`.`hari` AS `hari_btn`,`pr`.`waktu_mulai` AS `waktu_mulai`,`pr`.`waktu_berakhir` AS `waktu_berakhir`,`pr`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`pr`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`pr`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`pr`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`pr`.`status_pertemuan` AS `status_pertemuan`,`pr`.`status_konfirmasi` AS `status_konfirmasi`,`pr`.`status_fee` AS `status_fee`,`pr`.`status_spp` AS `status_spp`,`pr`.`deskripsi` AS `deskripsi`,`kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari_jadwal`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`pr`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran` from (((`pertemuan` `pr` join `pengajar` `pn` on((`pr`.`id_pengajar` = `pn`.`id_pengajar`))) join `kelas_pertemuan` `kp` on((`pr`.`id_kelas_p` = `kp`.`id_kelas_p`))) join `mata_pelajaran` `mp` on((`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`))) order by `pr`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_pertemuan_belum_selesai`
--
DROP TABLE IF EXISTS `list_pertemuan_belum_selesai`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_pertemuan_belum_selesai`  AS  select `lp`.`id_pertemuan` AS `id_pertemuan`,`lp`.`id_pengajar` AS `id_pengajar`,`lp`.`nama_pengajar` AS `nama_pengajar`,`lp`.`username` AS `username`,`lp`.`foto` AS `foto`,`lp`.`hari_btn` AS `hari_btn`,`lp`.`waktu_mulai` AS `waktu_mulai`,`lp`.`waktu_berakhir` AS `waktu_berakhir`,`lp`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`lp`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`lp`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`lp`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`lp`.`status_pertemuan` AS `status_pertemuan`,`lp`.`status_konfirmasi` AS `status_konfirmasi`,`lp`.`status_fee` AS `status_fee`,`lp`.`status_spp` AS `status_spp`,`lp`.`deskripsi` AS `deskripsi`,`lp`.`id_kelas_p` AS `id_kelas_p`,`lp`.`hari_jadwal` AS `hari_jadwal`,`lp`.`jam_mulai` AS `jam_mulai`,`lp`.`jam_berakhir` AS `jam_berakhir`,`lp`.`harga_fee` AS `harga_fee`,`lp`.`harga_spp` AS `harga_spp`,`lp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`lp`.`nama_mata_pelajaran` AS `nama_mata_pelajaran` from `list_pertemuan` `lp` where (`lp`.`status_pertemuan` = 'Belum Selesai') order by `lp`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_pertemuan_selesai`
--
DROP TABLE IF EXISTS `list_pertemuan_selesai`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_pertemuan_selesai`  AS  select `lp`.`id_pertemuan` AS `id_pertemuan`,`lp`.`id_pengajar` AS `id_pengajar`,`lp`.`nama_pengajar` AS `nama_pengajar`,`lp`.`username` AS `username`,`lp`.`foto` AS `foto`,`lp`.`hari_btn` AS `hari_btn`,`lp`.`waktu_mulai` AS `waktu_mulai`,`lp`.`waktu_berakhir` AS `waktu_berakhir`,`lp`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`lp`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`lp`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`lp`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`lp`.`status_pertemuan` AS `status_pertemuan`,`lp`.`status_konfirmasi` AS `status_konfirmasi`,`lp`.`status_fee` AS `status_fee`,`lp`.`status_spp` AS `status_spp`,`lp`.`deskripsi` AS `deskripsi`,`lp`.`id_kelas_p` AS `id_kelas_p`,`lp`.`hari_jadwal` AS `hari_jadwal`,`lp`.`jam_mulai` AS `jam_mulai`,`lp`.`jam_berakhir` AS `jam_berakhir`,`lp`.`harga_fee` AS `harga_fee`,`lp`.`harga_spp` AS `harga_spp`,`lp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`lp`.`nama_mata_pelajaran` AS `nama_mata_pelajaran` from `list_pertemuan` `lp` where (`lp`.`status_pertemuan` = 'Selesai') order by `lp`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_pertemuan_spp`
--
DROP TABLE IF EXISTS `list_pertemuan_spp`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_pertemuan_spp`  AS  select `pr`.`id_pertemuan` AS `id_pertemuan`,`pn`.`id_pengajar` AS `id_pengajar`,`pn`.`nama` AS `nama_pengajar`,`pn`.`username` AS `username`,`pn`.`foto` AS `foto`,`pr`.`hari` AS `hari_btn`,`pr`.`waktu_mulai` AS `waktu_mulai`,`pr`.`waktu_berakhir` AS `waktu_berakhir`,`pr`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`pr`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`pr`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`pr`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`pr`.`status_pertemuan` AS `status_pertemuan`,`pr`.`status_konfirmasi` AS `status_konfirmasi`,`pr`.`status_fee` AS `status_fee`,`pr`.`status_spp` AS `status_spp`,`pr`.`deskripsi` AS `deskripsi`,`kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari_jadwal`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`pr`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid` from ((((((`pertemuan` `pr` join `pengajar` `pn` on((`pr`.`id_pengajar` = `pn`.`id_pengajar`))) join `kelas_pertemuan` `kp` on((`pr`.`id_kelas_p` = `kp`.`id_kelas_p`))) join `mata_pelajaran` `mp` on((`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`))) join `detail_pertemuan` `dp` on((`pr`.`id_pertemuan` = `dp`.`id_pertemuan`))) join `murid` `m` on((`dp`.`id_murid` = `m`.`id_murid`))) join `wali_murid` `wm` on((`m`.`id_wali_murid` = `wm`.`id_wali_murid`))) group by `pr`.`id_pertemuan`,`wm`.`id_wali_murid` ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `bayar_spp`
--
ALTER TABLE `bayar_spp`
  ADD PRIMARY KEY (`id_bayar_spp`);

--
-- Indeks untuk tabel `detail_bayar_spp`
--
ALTER TABLE `detail_bayar_spp`
  ADD PRIMARY KEY (`id_detail_bayar_spp`);

--
-- Indeks untuk tabel `detail_kelas_pertemuan`
--
ALTER TABLE `detail_kelas_pertemuan`
  ADD PRIMARY KEY (`id_detail_kelas_p`),
  ADD KEY `id_kelas_p` (`id_kelas_p`,`id_murid`);

--
-- Indeks untuk tabel `detail_penggajian`
--
ALTER TABLE `detail_penggajian`
  ADD PRIMARY KEY (`id_detail_penggajian`);

--
-- Indeks untuk tabel `detail_pertemuan`
--
ALTER TABLE `detail_pertemuan`
  ADD PRIMARY KEY (`id_detail_pertemuan`),
  ADD KEY `id_pertemuan` (`id_pertemuan`),
  ADD KEY `id_murid` (`id_murid`);

--
-- Indeks untuk tabel `kelas_pertemuan`
--
ALTER TABLE `kelas_pertemuan`
  ADD PRIMARY KEY (`id_kelas_p`),
  ADD KEY `id_pengajar` (`id_pengajar`,`id_mata_pelajaran`);

--
-- Indeks untuk tabel `mata_pelajaran`
--
ALTER TABLE `mata_pelajaran`
  ADD PRIMARY KEY (`id_mata_pelajaran`);

--
-- Indeks untuk tabel `murid`
--
ALTER TABLE `murid`
  ADD PRIMARY KEY (`id_murid`),
  ADD KEY `id_wali_murid` (`id_wali_murid`);

--
-- Indeks untuk tabel `pengajar`
--
ALTER TABLE `pengajar`
  ADD PRIMARY KEY (`id_pengajar`);

--
-- Indeks untuk tabel `penggajian`
--
ALTER TABLE `penggajian`
  ADD PRIMARY KEY (`id_penggajian`);

--
-- Indeks untuk tabel `pertemuan`
--
ALTER TABLE `pertemuan`
  ADD PRIMARY KEY (`id_pertemuan`);

--
-- Indeks untuk tabel `wali_murid`
--
ALTER TABLE `wali_murid`
  ADD PRIMARY KEY (`id_wali_murid`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_bayar_spp`
--
ALTER TABLE `detail_bayar_spp`
  MODIFY `id_detail_bayar_spp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `detail_kelas_pertemuan`
--
ALTER TABLE `detail_kelas_pertemuan`
  MODIFY `id_detail_kelas_p` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT untuk tabel `detail_penggajian`
--
ALTER TABLE `detail_penggajian`
  MODIFY `id_detail_penggajian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `detail_pertemuan`
--
ALTER TABLE `detail_pertemuan`
  MODIFY `id_detail_pertemuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_kelas_pertemuan`
--
ALTER TABLE `detail_kelas_pertemuan`
  ADD CONSTRAINT `detail_kelas_pertemuan_ibfk_1` FOREIGN KEY (`id_kelas_p`) REFERENCES `kelas_pertemuan` (`id_kelas_p`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_pertemuan`
--
ALTER TABLE `detail_pertemuan`
  ADD CONSTRAINT `detail_pertemuan_ibfk_1` FOREIGN KEY (`id_pertemuan`) REFERENCES `pertemuan` (`id_pertemuan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `murid`
--
ALTER TABLE `murid`
  ADD CONSTRAINT `murid_ibfk_1` FOREIGN KEY (`id_wali_murid`) REFERENCES `wali_murid` (`id_wali_murid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
