-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 13 Jan 2020 pada 06.24
-- Versi server: 10.2.30-MariaDB-cll-lve
-- Versi PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bigstars_tab_absen`
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
('AD01', 'Admin Bigstars', 'bb110', '$2y$10$G.eqSW3QemJ15mXpIF/U3uezqDJHQPlPlvJUKY6TQZW2ugTwVGoYK', 'FAD01'),
('AD02', 'rizkika', 'kaka', '$2y$10$uNGzA6kD9BDaSRCirVujVOAY57Z4DsTpS3hVQQfpfwvWWHmk/JdcS', 'FAD02');

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
('BS200110-0001', 'WM005', 'AD01', '2020-01-10 17:39:49', 1, 40000),
('BS200110-0002', 'WM004', 'AD01', '2020-01-10 17:40:26', 1, 45000);

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
(7, 'BS200110-0001', 'PT200110-0002'),
(8, 'BS200110-0002', 'PT200110-0001');

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
(52, 'KL001', 'MR002'),
(53, 'KL002', 'MR003'),
(55, 'KL004', 'MR024'),
(56, 'KL004', 'MR025'),
(57, 'KL005', 'MR026'),
(58, 'KL006', 'MR008'),
(60, 'KL007', 'MR032'),
(61, 'KL008', 'MR033'),
(62, 'KL009', 'MR033'),
(63, 'KL010', 'MR017'),
(64, 'KL011', 'MR005'),
(65, 'KL012', 'MR005'),
(66, 'KL013', 'MR005'),
(67, 'KL014', 'MR006'),
(68, 'KL015', 'MR006'),
(69, 'KL016', 'MR006'),
(70, 'KL017', 'MR029'),
(71, 'KL018', 'MR022'),
(72, 'KL019', 'MR039'),
(73, 'KL020', 'MR040'),
(74, 'KL021', 'MR039'),
(75, 'KL022', 'MR040'),
(77, 'KL023', 'MR020'),
(78, 'KL024', 'MR019'),
(79, 'KL025', 'MR020'),
(80, 'KL026', 'MR015'),
(81, 'KL027', 'MR035'),
(82, 'KL028', 'MR014'),
(84, 'KL029', 'MR004'),
(85, 'KL030', 'MR009'),
(86, 'KL031', 'MR031'),
(87, 'KL032', 'MR027'),
(88, 'KL033', 'MR028'),
(89, 'KL034', 'MR018'),
(90, 'KL035', 'MR007'),
(91, 'KL036', 'MR013'),
(92, 'KL037', 'MR014'),
(93, 'KL038', 'MR037'),
(94, 'KL039', 'MR004'),
(95, 'KL040', 'MR001'),
(96, 'KL041', 'MR038'),
(100, 'KL042', 'MR042'),
(97, 'KL043', 'MR041'),
(98, 'KL044', 'MR036'),
(99, 'KL045', 'MR030'),
(101, 'KL046', 'MR042');

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
(7, 'PG200110-0001', 'PT200110-0002'),
(8, 'PG200110-0001', 'PT200110-0001'),
(9, 'PG200110-0002', 'PT200110-0003');

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
(44, 'PT200112-0001', 'MR002'),
(48, 'PT200112-0005', 'MR008'),
(53, 'PT200112-0006', 'MR020'),
(54, 'PT200112-0007', 'MR015');

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
('KL001', 'PE001', 'MP01', 'Senin sd Jumat', '17:00:00', '18:30:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL002', 'PE001', 'MP01', 'Selasa Dan Jumat', '19:00:00', '20:00:00', 23000, 40000, 'null', 'kosong', 'Normal'),
('KL004', 'PE001', 'MP01', 'Senin sd Rabu (Alice/Cristo)', '15:00:00', '16:30:00', 25000, 45000, 'null', 'kosong', 'Normal'),
('KL005', 'PE001', 'MP01', 'Senin sd Rabu', '15:00:00', '16:30:00', 30000, 65000, 'null', 'kosong', 'Normal'),
('KL006', 'PE002', 'MP01', 'Selasa, Kamis, Jumat (Jenice)', '18:00:00', '19:00:00', 43000, 65000, 'null', 'kosong', 'Normal'),
('KL007', 'PE004', 'MP01', 'Kamis (vino)', '15:00:00', '16:30:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL008', 'PE005', 'MP01', 'Rabu Sabtu (atma)', '18:00:00', '19:00:00', 23000, 35000, 'null', 'kosong', 'Normal'),
('KL009', 'PE005', 'MP01', 'Senin Jumat (atma)', '16:00:00', '17:00:00', 23000, 35000, 'null', 'kosong', 'Normal'),
('KL010', 'PE005', 'MP01', 'Selasa Kamis (matthew)', '14:00:00', '15:00:00', 25000, 35000, 'null', 'kosong', 'Normal'),
('KL011', 'PE006', 'MP01', 'Selasa Kamis', '16:00:00', '17:00:00', 24000, 43000, 'null', 'kosong', 'Normal'),
('KL012', 'PE006', 'MP01', 'Rabu', '18:00:00', '19:00:00', 24000, 43000, 'null', 'kosong', 'Normal'),
('KL013', 'PE006', 'MP01', 'Jumat', '15:00:00', '16:00:00', 24000, 43000, 'null', 'kosong', 'Normal'),
('KL014', 'PE006', 'MP01', 'Selasa Kamis (lydia)', '17:00:00', '18:00:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL015', 'PE006', 'MP01', 'Rabu (lydia)', '19:00:00', '20:00:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL016', 'PE006', 'MP01', 'Jumat (lydia)', '16:00:00', '17:00:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL017', 'PE007', 'MP01', 'Rabu Jumat (gladis)', '15:00:00', '17:00:00', 40000, 50000, 'null', 'kosong', 'Normal'),
('KL018', 'PE007', 'MP01', 'Selasa Rabu Kamis (zea)', '18:00:00', '19:30:00', 25000, 45000, 'null', 'kosong', 'Normal'),
('KL019', 'PE008', 'MP02', 'Sabtu', '18:00:00', '19:30:00', 30000, 0, 'null', 'kosong', 'Normal'),
('KL020', 'PE008', 'MP02', 'Minggu', '10:00:00', '11:30:00', 30000, 0, 'null', 'kosong', 'Normal'),
('KL021', 'PE009', 'MP02', 'Minggu', '18:00:00', '19:30:00', 30000, 0, 'null', 'kosong', 'Normal'),
('KL022', 'PE009', 'MP02', 'Azzhara', '10:00:00', '11:30:00', 30000, 0, 'null', 'kosong', 'Normal'),
('KL023', 'PE009', 'MP01', 'Senin (Ivan)', '15:00:00', '16:00:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL024', 'PE009', 'MP01', 'Senim', '16:00:00', '17:00:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL025', 'PE009', 'MP01', 'Selasa (evan atau ivan)', '15:00:00', '16:00:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL026', 'PE009', 'MP01', 'Selasa (tata)', '17:30:00', '18:30:00', 25000, 40000, 'null', 'kosong', 'Normal'),
('KL027', 'PE010', 'MP01', 'Senin Rabu (marco)', '18:00:00', '19:30:00', 35000, 55000, 'null', 'kosong', 'Normal'),
('KL028', 'PE011', 'MP01', 'Rabu (rangga)', '15:00:00', '16:00:00', 25000, 40000, 'null', 'kosong', 'Normal'),
('KL029', 'PE012', 'MP01', 'Senin sd Jumat (heaven)', '17:00:00', '18:30:00', 25000, 40000, 'null', 'kosong', 'Normal'),
('KL030', 'PE013', 'MP01', 'Senin sd Jumat (kayla)', '15:00:00', '16:00:00', 25000, 43000, 'null', 'kosong', 'Normal'),
('KL031', 'PE013', 'MP01', 'Rabu Kamis Jumat (alin)', '16:30:00', '17:30:00', 23000, 40000, 'null', 'kosong', 'Normal'),
('KL032', 'PE013', 'MP01', 'Senin Rabu (michael ph)', '18:00:00', '19:30:00', 30000, 50000, 'null', 'kosong', 'Normal'),
('KL033', 'PE013', 'MP01', 'Senin Rabu Jumat (michael sbh)', '17:00:00', '18:30:00', 27000, 45000, 'null', 'kosong', 'Normal'),
('KL034', 'PE015', 'MP01', 'Selasa (Irish)', '15:00:00', '16:00:00', 23000, 25000, 'null', 'kosong', 'Normal'),
('KL035', 'PE016', 'MP01', 'Kamis Senin (electra)', '15:30:00', '16:30:00', 30000, 50000, 'null', 'kosong', 'Normal'),
('KL036', 'PE018', 'MP01', 'Rabu Jumat (grace)', '16:00:00', '17:30:00', 30000, 40000, 'null', 'kosong', 'Normal'),
('KL037', 'PE018', 'MP01', 'Selasa', '15:00:00', '16:00:00', 25000, 40000, 'null', 'kosong', 'Normal'),
('KL038', 'PE019', 'MP01', 'Senin sd Jumat (devin)', '15:00:00', '17:00:00', 37000, 60000, 'null', 'kosong', 'Normal'),
('KL039', 'PE022', 'MP01', 'senin kamis (heaven)', '15:00:00', '16:00:00', 27000, 35000, 'null', 'kosong', 'Normal'),
('KL040', 'PE022', 'MP01', 'Senin Selasa Kamis Minggu (carlos)', '18:00:00', '19:00:00', 30000, 50000, 'null', 'kosong', 'Normal'),
('KL041', 'PE020', 'MP01', 'Senin sd Jumat (keno)', '15:00:00', '17:00:00', 37000, 55000, 'null', 'kosong', 'Normal'),
('KL042', 'PE020', 'MP01', 'Rabu (jes)', '14:00:00', '15:30:00', 24000, 40000, 'null', 'kosong', 'Normal'),
('KL043', 'PE017', 'MP01', 'Rabu Jumat (vania)', '14:00:00', '15:00:00', 24000, 35000, 'null', 'kosong', 'Normal'),
('KL044', 'PE023', 'MP01', 'Selasa Kamis (Jason)', '18:00:00', '19:30:00', 35000, 57000, 'null', 'kosong', 'Normal'),
('KL045', 'PE023', 'MP01', 'Selasa Jumat (Grace)', '16:00:00', '18:00:00', 37000, 57000, 'null', 'kosong', 'Normal'),
('KL046', 'PE021', 'MP01', 'Sabtu (jeslin)', '14:00:00', '15:00:00', 25000, 40000, 'null', 'kosong', 'Normal');

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
('MP01', 'All Mapel'),
('MP02', 'B Inggris');

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
('MR001', 'WM001', 'Carlos', 'FMR001'),
('MR002', 'WM004', 'Kevin SDK', 'DEFFMR'),
('MR003', 'WM005', 'Michelle', 'FMR003'),
('MR004', 'WM006', 'Heaven', 'FMR004'),
('MR005', 'WM002', 'Kevin RH', 'DEFFMR'),
('MR006', 'WM002', 'Lydia', 'DEFFMR'),
('MR007', 'WM008', 'Electra', 'DEFFMR'),
('MR008', 'WM008', 'Jenice', 'DEFFMR'),
('MR009', 'WM010', 'Kayla', 'DEFFMR'),
('MR010', 'WM011', 'Fael', 'DEFFMR'),
('MR011', 'WM011', 'Tirza', 'DEFFMR'),
('MR012', 'WM013', 'feli', 'DEFFMR'),
('MR013', 'WM013', 'Grace Avarina', 'DEFFMR'),
('MR014', 'WM014', 'Rangga', 'DEFFMR'),
('MR015', 'WM015', 'Tata', 'DEFFMR'),
('MR016', 'WM016', 'Gwen', 'DEFFMR'),
('MR017', 'WM017', 'Matthew', 'DEFFMR'),
('MR018', 'WM018', 'Irish', 'DEFFMR'),
('MR019', 'WM019', 'Evan', 'DEFFMR'),
('MR020', 'WM019', 'Ivan', 'DEFFMR'),
('MR021', 'WM019', 'Twins', 'DEFFMR'),
('MR022', 'WM020', 'Zea', 'DEFFMR'),
('MR023', 'WM020', 'Rei', 'DEFFMR'),
('MR024', 'WM021', 'Alice atau Christo', 'FMR024'),
('MR026', 'WM021', 'Alice & Christo', 'FMR026'),
('MR027', 'WM022', 'Michael PH', 'DEFFMR'),
('MR028', 'WM023', 'Michael SBH', 'DEFFMR'),
('MR029', 'WM024', 'Gladys', 'DEFFMR'),
('MR030', 'WM024', 'Grace Amanda', 'DEFFMR'),
('MR031', 'WM025', 'Alin', 'DEFFMR'),
('MR032', 'WM026', 'Vino', 'DEFFMR'),
('MR033', 'WM027', 'Atma', 'DEFFMR'),
('MR034', 'WM028', 'Razan', 'DEFFMR'),
('MR035', 'WM029', 'Marco', 'DEFFMR'),
('MR036', 'WM031', 'Jason', 'DEFFMR'),
('MR037', 'WM030', 'Devin', 'DEFFMR'),
('MR038', 'WM030', 'Keno', 'DEFFMR'),
('MR039', 'WM033', 'Alhabsi', 'DEFFMR'),
('MR040', 'WM034', 'Azzahra', 'DEFFMR'),
('MR041', 'WM035', 'vania', 'DEFFMR'),
('MR042', 'WM032', 'Jeslin', 'DEFFMR');

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
('PE001', 'Miss Novi', 'novibs', '$2y$10$wNmdFD00C/lAMCXkEcBuVOZ4OAhmwgohqojBnjFjDIFuv7UV.34FK', 'Jl. Kalimantan', '089624941716', 'FPE001'),
('PE002', 'Mr Anton', 'antonbs', '$2y$10$huumbKwGiq5omCcMZaTGV.4YZcDuHuYOYAVii1Lz/hNgo7mlDmH42', 'Jl. Mastrip', '082234164472', 'FPE002'),
('PE003', 'Miss Beta', 'betabs', '$2y$10$H.FXrfbrvAAmlKj79g8zw.e3PnouPKKCmn02Bp9ZHRizigGLj75CG', 'Jl. Jawa', '082231936063', 'FPE003'),
('PE004', 'Miss Della', 'dellabs', '$2y$10$S2rOSnOrlZqzovPZkc5ihuIDOQ1J8AjsbjIeOIVzsz.F.gNmg/1GO', 'Gebang Jember', '081231251178', 'FPE004'),
('PE005', 'Miss Devi', 'devibs', '$2y$10$d6LZmi8ue.ItzVgpaxJc4OBOU1YiFU7iyiwOzKbsihKyN0Ajgq1ly', 'Jl. Karimata', '081232383608', 'FPE005'),
('PE006', 'Miss Eka', 'ekabs', '$2y$10$sNQNAxjJAOU1YlNkaKN9euX6tAEZf.Ch5dOdopO2y5BWF/N3ZLM7u', 'Jl. Mastrip', '085259185433', 'FPE006'),
('PE007', 'Miss Fatimah', 'fatimahbs', '$2y$10$ScM/qe6kDx9eXWPXGJnkauSndnaQOHx5iq.ZZ6Ooq5K3pE7ndmyD6', 'Panti Jember', '082122015820', 'FPE007'),
('PE008', 'Miss Lela', 'lelabs', '$2y$10$GiWtbVCkEd/7Ajloa7zW9uzD0.mMsXEPfwkEdAu5Kzexhv1P.wEC6', 'Jl. Mojopahit', '085330765833', 'FPE008'),
('PE009', 'Miss Linda', 'lindabs', '$2y$10$wDPjoryRqs5U3IYrT61ouu1mj7qVUeHThLz4IoeYMn/lq/F/IJjmy', 'Mangli Jember', '089617399346', 'FPE009'),
('PE010', 'Miss Nona', 'nonabs', '$2y$10$p6cBv1.SyinrZqh16uyv9OGwtkSBDNdpXPoVepTEjP5ESiaZy34pe', 'Jl. Mastrip', '089635753244', 'FPE010'),
('PE011', 'Miss Nazila', 'nazilabs', '$2y$10$mLGDhCMSvkd8rxbC/U0rDO6HvmNS6Y3OxOrkv2ilBUPBOk/loEczm', 'Patrang Jember', '082132535366', 'FPE011'),
('PE012', 'Miss Nancy', 'nancybs', '$2y$10$TKyd1xokyPHjBTVfm0pqqu17fropLZ.96iMKcI3RG1sEmo.RgX64i', 'Kreongan Jember', '081357922644', 'FPE012'),
('PE013', 'Miss Ninis', 'ninisbs', '$2y$10$q7BPLVFwU5P1tqy509SUdOOOKvVeCXyddGdqz3/msoOijU/k6qheO', 'Patrang Jember', '089520900271', 'FPE013'),
('PE014', 'Miss Nurul', 'nurulbs', '$2y$10$GAZ1bmpkKxd1b1zZ0EJ/9eszeGDK0nLHcHgoQT2eb0fCHKYqML8D2', 'Mangli Jember', '089687141710', 'FPE014'),
('PE015', 'Miss Rara', 'rarabs', '$2y$10$ZHY69Zz566HQneVfXJeu0.EaJKtiPjNZM9ceVPR8p81/JlTDG2lBG', 'Jember', '085695618541', 'FPE015'),
('PE016', 'Miss Riska', 'riskabs', '$2y$10$QhcocgpUa3jKBWNy.tOx/OwyHlJoXaOSpVrSHL.zBe7nSS0eOi4uW', 'Jember', '082266626220', 'FPE016'),
('PE017', 'Miss Titis', 'titisbs', '$2y$10$at.YLkTVdPJoTBrGxjymYOFGucZ1MIHbV9T6kOlJIliDKwUDGHQJK', 'Jember', '081234016761', 'FPE017'),
('PE018', 'Miss Winnie', 'winniebs', '$2y$10$QKRubNRYe264mGmQWeDspOEChLs2wh.xE/4tcEtBlvFUQmy62Utqe', 'Jember', '-', 'FPE018'),
('PE019', 'Zulfikar', 'zulfikarbs', '$2y$10$lW5Z8Wg8uFUU2b.5N0.24uDJe2y62quc788EsNHI3fScIKq0qrqG2', 'Antirogo - Jember', '081336414173', 'FPE019'),
('PE020', 'Miss Alvi', 'alvibs', '$2y$10$oInfKW/5gOH7qcyqlm/O3eIYARgt3W2n/k6o3AtEhblRa8KExBiuq', 'Jember', '08989796916', 'FPE020'),
('PE021', 'Miss dita', 'ditabs', '$2y$10$lSFhg.GW5/KgwVjoHzUrSOz8o7/Un3lfBLdGEhJtRH3lXoR4NBMjq', 'Jember', '081333620754', 'FPE021'),
('PE022', 'Miss Bella', 'bellabs', '$2y$10$mQsAqK1cbrq0cQIJPaACI.ukvLhbX33fKk4QGuyiSUxcuFuJ.GqRu', 'Jember', '082231922238', 'FPE022'),
('PE023', 'Miss Ayu', 'ayubs', '$2y$10$fxElfiQo4KqPe4gfAuyvEOGgHdfx5b03l.TtM4.uNteqS9kOGht/e', 'Jember', '081917165152', 'DEFFPE');

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
('PG200110-0001', 'PE001', 'AD01', '2020-01-10 17:39:22', 2, 50000),
('PG200110-0002', 'PE002', 'AD01', '2020-01-10 17:44:47', 1, 27000);

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
('PT200112-0001', 'PE001', 'KL001', 'Minggu', '2020-01-12 16:07:15', '2020-01-12 16:07:15', '-8.168099597096443', '113.72573917731643', 'null', 'null', 'Belum Selesai', 'Invalid', 'Belum Terbayar', 'Belum Lunas', 27000, 45000, 'kosong'),
('PT200112-0005', 'PE002', 'KL006', 'Minggu', '2020-01-12 16:21:58', '2020-01-12 16:21:58', '-8.1682476', '113.7255413', 'null', 'null', 'Belum Selesai', 'Invalid', 'Belum Terbayar', 'Belum Lunas', 43000, 65000, 'kosong'),
('PT200112-0006', 'PE009', 'KL025', 'Minggu', '2020-01-12 19:33:59', '2020-01-12 19:34:55', '-8.177964091300964', '113.65730881690979', '-8.1780245', '113.6575317', 'Selesai', 'Invalid', 'Belum Terbayar', 'Belum Lunas', 27000, 45000, 'Writing and Speaking \"My Holiday\" '),
('PT200112-0007', 'PE009', 'KL026', 'Minggu', '2020-01-12 19:35:46', '2020-01-12 19:36:21', '-8.1780245', '113.6575317', '-8.1780245', '113.6575317', 'Selesai', 'Invalid', 'Belum Terbayar', 'Belum Lunas', 25000, 40000, 'Speaking and Writing \"Holiday\" using Simple Past Tense\n');

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
('WM001', 'Ibu Lia', 'carlosbs', '$2y$10$D3wLXcD1iVCPhtUBz/LQDuenheCk5rNhlSYxuZGyN5I2bHnyKj5BO', 'Kaliwates Jember', '08113572881'),
('WM002', 'Mama Kevin dan Lydya', 'kevinrhbs', '$2y$10$gaIu8PxMvn8VJ521hLhMWeRjDQbClydq2W0qdXrS0nowGdD9fBCxu', 'Perumahan gunung batu blok f no.25', '081332159775'),
('WM004', 'Mama Kevin SDK', 'kevinsdkbs', '$2y$10$iRivNWs9a14y5Xd1NuOL8e1kDVkQhWQP4Fte6x4NqxI4DHi.xec3e', 'Kaliwates - Jember', '081234566157'),
('WM005', 'Mama Michelle', 'michellebs', '$2y$10$Sw8Kx3Txd0xqyoBAg0vy7.F9N19yQup3IiM5mND0MXhMz9M2gJaAy', 'Jl. Diponegoro - Jember', '081217385526'),
('WM006', 'Mama Heaven', 'heavenbs', '$2y$10$riFhU7EE4QDCsGXDCajnbezKseGTvENeRd8EuFhKGX0Htizl0zguS', 'Argopuro - Jember', '0811355036'),
('WM008', 'Mama Electra Dan Jenice', 'electrabs', '$2y$10$9wXyK/Ju/GWWPPsBrpa9eOgjKPRAgddKoHtuaJQ24q6M65eyzN3eG', 'Electra\nJln.Wijaya Kusuma no.66 Jember', '0811358851'),
('WM010', 'Mama Kayla', 'kaylabs', '$2y$10$Ne3y3.IDaEjbkRy8k74RHuW0fJ82R5YpyZUz2Bbe.E60oMGq3wDxy', 'Perum Bukit Permai Pajajaran Cluster No. 1 Jember', '082139377979'),
('WM011', 'Mama Fael dan Tirza', 'faelbs', '$2y$10$WRVmZ855sDYtiiHbBLMHVOrBu3Rl4roEJoJ4LU9WbiiBm.W6q/DGy', 'perumahan taman gading ww 9', '081252786950'),
('WM013', 'Mama Indah', 'gracebs', '$2y$10$35aPz8Pu6jLpmmsPt3U5quWIRlWEfjYZ5Rk0fEYHdqVZSV8gWt.p6', 'Perum san cefila b36 Jember', '082231264104'),
('WM014', 'Mama Rangga', 'ranggabs', '$2y$10$J4dYDUJVvI4GpCm9zKZtyOb4vzHY1qH0BLAfY6mDmzVkkFhYZyhFu', 'Perum Bernadyland cluster magnolia bloe E1 Jl. Cendrawasih Slawu Kec Patrang', '082332424949'),
('WM015', 'Mama Tata', 'tatabs', '$2y$10$TbHjL1L3D04GJDLnTg6xq.ii1Ysgwpf4C/y97LhSGbYGonMTxIQvu', 'Gebang Jember', '082139306666'),
('WM016', 'Mama Gwen', 'gwenbs', '$2y$10$Aim6V1j33tUixKzoO8l50eFz4ze6GTxNzpbkaYnCYkTg0AHfWKW1C', 'Jl. Dr. Soetomo No. 32 Jember', '081217167483'),
('WM017', 'Mama Matthew', 'matthewbs', '$2y$10$EZo7UPOo1w//XgYzpd958OWcQ5jt1Iw6pla7pZ66CWqkMWnI8vMUC', 'Jl letjen suprapto no 25. Jember', '082233573031'),
('WM018', 'Mama Irish', 'irishbs', '$2y$10$05gYGrpjvVx8Yl6royLEuuiAJnRl6sr4sLI7daHsdmoYlxPDDq0I6', 'Jember', '081232304439'),
('WM019', 'Mama Evan Ivan', 'twinbs', '$2y$10$JB9E9.Yhc9oAHZu4Zzxafej./HC.K7J3cnphllUdCIDtEyzKAqE3O', 'Perum argopuro wa 3 no 7', '082132082710'),
('WM020', 'Mama Zea', 'zeabs', '$2y$10$tp9oJ8YYodsPRuzHZ.fjIe91bvPvEvzEOpSpIAhfRQpNxBSJ./6Dq', 'Jl argopuro dusun satrean no 8 M rt 001 rw 008 kel. Rambigundam kec. Rambipuji', '081220161221'),
('WM021', 'Mama Alice Christo', 'alicebs', '$2y$10$xKPaF/J/k5N2Y7pQtWqcX.jiH114deil0CHJ6iLdsW.J.uTmIxVqG', 'Jember', '081336322266'),
('WM022', 'Mama Michael PH', 'michaelphbs', '$2y$10$ehdxdCdG.IdXxXpFl84JWON6Uh6gkVCcc0N2iu9Y27Ydf0V3QlUIC', 'Jember', '081230469356'),
('WM023', 'Mama Michael SBH', 'michaelsbh', '$2y$10$nJ4x9pRwhQNoVoBzjGnac.VtVqvoiE5Th8oHmtr9Eho.G.hUuaYRi', 'Jember', '081232323230'),
('WM024', 'Mama Gladys', 'gladysbs', '$2y$10$KkDzImriVWrqIg6I5HB6BOp8I4ekIuulYjNMqKretPqUJXavG6ozq', 'Jln bondoyudo 6 Jember', '08113501688'),
('WM025', 'Mama Alin', 'alinbs', '$2y$10$4819s4WD10fouPVhW9ey7ulT4ePYKPYVusn.pL8tpGyveHcFU3g8.', 'Perum Bukit Permai Pajajaran Cluster No. 3 Jember', '082234700724'),
('WM026', 'Mama Vino', 'vinobs', '$2y$10$1VNI9eh8oIjV/niA44iLEuJumE.ADFhiYBPCijgS6rDaIxxp6L0Au', 'Jember', '081336844974'),
('WM027', 'Mama Adma', 'admabs', '$2y$10$L.xMVBPTmCYjVky4BwQZ8u6D85HSl1NkYS9UNq5/102jfVu7De15C', 'Puri bunga nirwana tebet i-13 Jember', '082236429204'),
('WM028', 'Mama Razan', 'razanbs', '$2y$10$Dcnhkp24sHFPLKzLxfpajuriTqRAjkzdPpkV8CalRPpp6tAr2gfoK', 'Jember', '0852496108829'),
('WM029', 'Mama Marco', 'marcobs', '$2y$10$6/qYPfTuO87SomsrBOrBAuUvQhMq19MUX9oWbkfSqm8snE926vOIq', 'Jember', '081230860633'),
('WM030', 'Mama Devin', 'devinbs', '$2y$10$AjCMnoWkvBzAxQ0rH7G7Duy/fRrhiPiUpsJhyAp7nyvx5csmio8Ze', 'Jalan Majapahit R 25 A', '082231417878'),
('WM031', 'Mama Jason', 'jasonbs', '$2y$10$DgxX2C84oY5OFElMjwMQUOpATyWClxXzB7eGm8Gcd28h3WIvFIyrm', 'Jl. Majapahit Di blok EE no 4 Jember', '081221101999'),
('WM032', 'Mama Jeslin', 'jesbs', '$2y$10$eI7.zpclWWVzAbrzyc2J2eNXLiJNhS47s7FAreorv0/wvPvx7CQY.', 'Jember', '08123534585'),
('WM033', 'alhabsi', 'alhabsibs', '$2y$10$QgDT0.8JqGi6iCZYHCqaCeHPJsDRG3my.PWaO6l3m2iRu0khcbphK', '.', '.'),
('WM034', 'Azzahra', 'azzahrabs', '$2y$10$keJRCpN81RfVPC7x8c.HtOgBg/48ekNs6f.dGxS.CANGbhHtm0Q7m', '.', '.'),
('WM035', 'Mama Vania', 'vaniabs', '$2y$10$rlAylToWGdkCzwFRfXPOkOAS4mVW88J91.T0jtDJy.M1.F2vJZLAS', 'Jember', '081331908081');

-- --------------------------------------------------------

--
-- Struktur untuk view `list_detail_bayar_spp`
--
DROP TABLE IF EXISTS `list_detail_bayar_spp`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_detail_bayar_spp`  AS  select `pr`.`id_pertemuan` AS `id_pertemuan`,`pn`.`id_pengajar` AS `id_pengajar`,`pn`.`nama` AS `nama_pengajar`,`pn`.`username` AS `username`,`pn`.`foto` AS `foto`,`pr`.`hari` AS `hari_btn`,`pr`.`waktu_mulai` AS `waktu_mulai`,`pr`.`waktu_berakhir` AS `waktu_berakhir`,`pr`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`pr`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`pr`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`pr`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`pr`.`status_pertemuan` AS `status_pertemuan`,`pr`.`status_konfirmasi` AS `status_konfirmasi`,`pr`.`status_fee` AS `status_fee`,`pr`.`status_spp` AS `status_spp`,`pr`.`deskripsi` AS `deskripsi`,`kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari_jadwal`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`pr`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran`,`dbs`.`id_detail_bayar_spp` AS `id_detail_bayar_spp`,`dbs`.`id_bayar_spp` AS `id_bayar_spp` from ((((`pertemuan` `pr` join `pengajar` `pn` on(`pr`.`id_pengajar` = `pn`.`id_pengajar`)) join `kelas_pertemuan` `kp` on(`pr`.`id_kelas_p` = `kp`.`id_kelas_p`)) join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) join `detail_bayar_spp` `dbs` on(`pr`.`id_pertemuan` = `dbs`.`id_pertemuan`)) order by `pr`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_detail_penggajian`
--
DROP TABLE IF EXISTS `list_detail_penggajian`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_detail_penggajian`  AS  select `pr`.`id_pertemuan` AS `id_pertemuan`,`pn`.`id_pengajar` AS `id_pengajar`,`pn`.`nama` AS `nama_pengajar`,`pn`.`username` AS `username`,`pn`.`foto` AS `foto`,`pr`.`hari` AS `hari_btn`,`pr`.`waktu_mulai` AS `waktu_mulai`,`pr`.`waktu_berakhir` AS `waktu_berakhir`,`pr`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`pr`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`pr`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`pr`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`pr`.`status_pertemuan` AS `status_pertemuan`,`pr`.`status_konfirmasi` AS `status_konfirmasi`,`pr`.`status_fee` AS `status_fee`,`pr`.`status_spp` AS `status_spp`,`pr`.`deskripsi` AS `deskripsi`,`kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari_jadwal`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`pr`.`harga_fee` AS `harga_fee`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran`,`dpg`.`id_penggajian` AS `id_penggajian`,`dpg`.`id_detail_penggajian` AS `id_detail_penggajian` from ((((`pertemuan` `pr` join `pengajar` `pn` on(`pr`.`id_pengajar` = `pn`.`id_pengajar`)) join `kelas_pertemuan` `kp` on(`pr`.`id_kelas_p` = `kp`.`id_kelas_p`)) join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) join `detail_penggajian` `dpg` on(`pr`.`id_pertemuan` = `dpg`.`id_pertemuan`)) order by `pr`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_kelas`
--
DROP TABLE IF EXISTS `list_kelas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_kelas`  AS  select `kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`kp`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`kp`.`id_sharing` AS `id_sharing`,`kp`.`nama_sharing` AS `nama_sharing`,`kp`.`status` AS `status`,`pe`.`id_pengajar` AS `id_pengajar`,`pe`.`nama` AS `nama_pengajar`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_pelajaran` from ((`kelas_pertemuan` `kp` join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) join `pengajar` `pe` on(`kp`.`id_pengajar` = `pe`.`id_pengajar`)) order by `kp`.`id_kelas_p` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_murid`
--
DROP TABLE IF EXISTS `list_murid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_murid`  AS  select `m`.`id_murid` AS `id_murid`,`m`.`nama` AS `nama`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`wm`.`alamat` AS `alamat`,`m`.`foto` AS `foto` from (`murid` `m` join `wali_murid` `wm` on(`m`.`id_wali_murid` = `wm`.`id_wali_murid`)) order by `m`.`nama` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_murid_by_kelas`
--
DROP TABLE IF EXISTS `list_murid_by_kelas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_murid_by_kelas`  AS  select `m`.`id_murid` AS `id_murid`,`m`.`nama` AS `nama`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`wm`.`alamat` AS `alamat`,`m`.`foto` AS `foto`,`dkp`.`id_kelas_p` AS `id_kelas_p`,`dkp`.`id_detail_kelas_p` AS `id_detail_kelas_p` from ((`murid` `m` join `wali_murid` `wm` on(`m`.`id_wali_murid` = `wm`.`id_wali_murid`)) join `detail_kelas_pertemuan` `dkp` on(`m`.`id_murid` = `dkp`.`id_murid`)) order by `m`.`nama` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_penggajian`
--
DROP TABLE IF EXISTS `list_penggajian`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_penggajian`  AS  select `pg`.`id_penggajian` AS `id_penggajian`,`pg`.`waktu` AS `waktu`,`pg`.`total_pertemuan` AS `total_pertemuan`,`pg`.`total_harga_fee` AS `total_harga_fee`,`pe`.`id_pengajar` AS `id_pengajar`,`pe`.`nama` AS `nama_pengajar`,`ad`.`id_admin` AS `id_admin`,`ad`.`nama` AS `nama_admin` from ((`penggajian` `pg` join `pengajar` `pe` on(`pg`.`id_pengajar` = `pe`.`id_pengajar`)) join `admin` `ad` on(`pg`.`id_admin` = `ad`.`id_admin`)) order by `pg`.`id_penggajian` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_pertemuan`
--
DROP TABLE IF EXISTS `list_pertemuan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_pertemuan`  AS  select `pr`.`id_pertemuan` AS `id_pertemuan`,`pn`.`id_pengajar` AS `id_pengajar`,`pn`.`nama` AS `nama_pengajar`,`pn`.`username` AS `username`,`pn`.`foto` AS `foto`,`pr`.`hari` AS `hari_btn`,`pr`.`waktu_mulai` AS `waktu_mulai`,`pr`.`waktu_berakhir` AS `waktu_berakhir`,`pr`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`pr`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`pr`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`pr`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`pr`.`status_pertemuan` AS `status_pertemuan`,`pr`.`status_konfirmasi` AS `status_konfirmasi`,`pr`.`status_fee` AS `status_fee`,`pr`.`status_spp` AS `status_spp`,`pr`.`deskripsi` AS `deskripsi`,`kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari_jadwal`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`pr`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran` from (((`pertemuan` `pr` join `pengajar` `pn` on(`pr`.`id_pengajar` = `pn`.`id_pengajar`)) join `kelas_pertemuan` `kp` on(`pr`.`id_kelas_p` = `kp`.`id_kelas_p`)) join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) order by `pr`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_pertemuan_belum_selesai`
--
DROP TABLE IF EXISTS `list_pertemuan_belum_selesai`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_pertemuan_belum_selesai`  AS  select `lp`.`id_pertemuan` AS `id_pertemuan`,`lp`.`id_pengajar` AS `id_pengajar`,`lp`.`nama_pengajar` AS `nama_pengajar`,`lp`.`username` AS `username`,`lp`.`foto` AS `foto`,`lp`.`hari_btn` AS `hari_btn`,`lp`.`waktu_mulai` AS `waktu_mulai`,`lp`.`waktu_berakhir` AS `waktu_berakhir`,`lp`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`lp`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`lp`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`lp`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`lp`.`status_pertemuan` AS `status_pertemuan`,`lp`.`status_konfirmasi` AS `status_konfirmasi`,`lp`.`status_fee` AS `status_fee`,`lp`.`status_spp` AS `status_spp`,`lp`.`deskripsi` AS `deskripsi`,`lp`.`id_kelas_p` AS `id_kelas_p`,`lp`.`hari_jadwal` AS `hari_jadwal`,`lp`.`jam_mulai` AS `jam_mulai`,`lp`.`jam_berakhir` AS `jam_berakhir`,`lp`.`harga_fee` AS `harga_fee`,`lp`.`harga_spp` AS `harga_spp`,`lp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`lp`.`nama_mata_pelajaran` AS `nama_mata_pelajaran` from `list_pertemuan` `lp` where `lp`.`status_pertemuan` = 'Belum Selesai' order by `lp`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_pertemuan_selesai`
--
DROP TABLE IF EXISTS `list_pertemuan_selesai`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_pertemuan_selesai`  AS  select `lp`.`id_pertemuan` AS `id_pertemuan`,`lp`.`id_pengajar` AS `id_pengajar`,`lp`.`nama_pengajar` AS `nama_pengajar`,`lp`.`username` AS `username`,`lp`.`foto` AS `foto`,`lp`.`hari_btn` AS `hari_btn`,`lp`.`waktu_mulai` AS `waktu_mulai`,`lp`.`waktu_berakhir` AS `waktu_berakhir`,`lp`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`lp`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`lp`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`lp`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`lp`.`status_pertemuan` AS `status_pertemuan`,`lp`.`status_konfirmasi` AS `status_konfirmasi`,`lp`.`status_fee` AS `status_fee`,`lp`.`status_spp` AS `status_spp`,`lp`.`deskripsi` AS `deskripsi`,`lp`.`id_kelas_p` AS `id_kelas_p`,`lp`.`hari_jadwal` AS `hari_jadwal`,`lp`.`jam_mulai` AS `jam_mulai`,`lp`.`jam_berakhir` AS `jam_berakhir`,`lp`.`harga_fee` AS `harga_fee`,`lp`.`harga_spp` AS `harga_spp`,`lp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`lp`.`nama_mata_pelajaran` AS `nama_mata_pelajaran` from `list_pertemuan` `lp` where `lp`.`status_pertemuan` = 'Selesai' order by `lp`.`id_pertemuan` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_pertemuan_spp`
--
DROP TABLE IF EXISTS `list_pertemuan_spp`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `list_pertemuan_spp`  AS  select `pr`.`id_pertemuan` AS `id_pertemuan`,`pn`.`id_pengajar` AS `id_pengajar`,`pn`.`nama` AS `nama_pengajar`,`pn`.`username` AS `username`,`pn`.`foto` AS `foto`,`pr`.`hari` AS `hari_btn`,`pr`.`waktu_mulai` AS `waktu_mulai`,`pr`.`waktu_berakhir` AS `waktu_berakhir`,`pr`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`pr`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`pr`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`pr`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`pr`.`status_pertemuan` AS `status_pertemuan`,`pr`.`status_konfirmasi` AS `status_konfirmasi`,`pr`.`status_fee` AS `status_fee`,`pr`.`status_spp` AS `status_spp`,`pr`.`deskripsi` AS `deskripsi`,`kp`.`id_kelas_p` AS `id_kelas_p`,`kp`.`hari` AS `hari_jadwal`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`pr`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid` from ((((((`pertemuan` `pr` join `pengajar` `pn` on(`pr`.`id_pengajar` = `pn`.`id_pengajar`)) join `kelas_pertemuan` `kp` on(`pr`.`id_kelas_p` = `kp`.`id_kelas_p`)) join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) join `detail_pertemuan` `dp` on(`pr`.`id_pertemuan` = `dp`.`id_pertemuan`)) join `murid` `m` on(`dp`.`id_murid` = `m`.`id_murid`)) join `wali_murid` `wm` on(`m`.`id_wali_murid` = `wm`.`id_wali_murid`)) group by `pr`.`id_pertemuan`,`wm`.`id_wali_murid` ;

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
  MODIFY `id_detail_bayar_spp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `detail_kelas_pertemuan`
--
ALTER TABLE `detail_kelas_pertemuan`
  MODIFY `id_detail_kelas_p` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT untuk tabel `detail_penggajian`
--
ALTER TABLE `detail_penggajian`
  MODIFY `id_detail_penggajian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `detail_pertemuan`
--
ALTER TABLE `detail_pertemuan`
  MODIFY `id_detail_pertemuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

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
  ADD CONSTRAINT `murid_ibfk_1` FOREIGN KEY (`id_wali_murid`) REFERENCES `wali_murid` (`id_wali_murid`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
