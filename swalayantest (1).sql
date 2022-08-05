-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 25, 2022 at 05:49 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `swalayantest`
--

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `kd_karyawan` int(11) NOT NULL,
  `nama_karyawan` varchar(60) NOT NULL,
  `no_telp` varchar(21) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`kd_karyawan`, `nama_karyawan`, `no_telp`, `email`) VALUES
(1, 'Adit', '0896379222', 'adit@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kd_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`kd_kategori`, `nama_kategori`) VALUES
(1, 'Makanan'),
(2, 'Minuman'),
(3, 'ATK');

-- --------------------------------------------------------

--
-- Table structure for table `konsumen`
--

CREATE TABLE `konsumen` (
  `kd_konsumen` int(11) NOT NULL,
  `nama_konsumen` varchar(60) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(21) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `konsumen`
--

INSERT INTO `konsumen` (`kd_konsumen`, `nama_konsumen`, `alamat`, `no_telp`, `email`, `tgl_lahir`) VALUES
(1, 'Andi', 'JL. kenangan', '089637301', 'andi@gmail.com', '2001-06-20');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `kd_produk` varchar(15) NOT NULL,
  `nama_produk` varchar(60) NOT NULL,
  `stok` int(8) NOT NULL,
  `harga_beli` int(10) NOT NULL,
  `harga_jual` int(10) NOT NULL,
  `kd_kategori` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`kd_produk`, `nama_produk`, `stok`, `harga_beli`, `harga_jual`, `kd_kategori`) VALUES
('1', 'Beng-beng', 12, 1500, 2000, 1),
('2', 'Kacang Kulit Garuda', 13, 3000, 3500, 1),
('3', 'Pulpen Joyko', 41, 2500, 3000, 3),
('4', 'Coca Cola', 16, 4800, 5000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `suplier`
--

CREATE TABLE `suplier` (
  `kd_suplier` int(11) NOT NULL,
  `nama_suplier` varchar(60) NOT NULL,
  `no_telp` varchar(21) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suplier`
--

INSERT INTO `suplier` (`kd_suplier`, `nama_suplier`, `no_telp`, `email`) VALUES
(1, 'Bowo', '089364749', 'bowo@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_pembelian`
--

CREATE TABLE `transaksi_pembelian` (
  `kd_transaksi_beli` varchar(20) NOT NULL,
  `kd_karyawan` int(11) NOT NULL,
  `kd_suplier` int(11) NOT NULL,
  `tgl_pembelian` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_pembelian`
--

INSERT INTO `transaksi_pembelian` (`kd_transaksi_beli`, `kd_karyawan`, `kd_suplier`, `tgl_pembelian`) VALUES
('TRB0001', 1, 1, '2022-06-25'),
('TRB0002', 1, 1, '2022-06-25');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_pembelian_detail`
--

CREATE TABLE `transaksi_pembelian_detail` (
  `kd_transaksi_beli` varchar(20) NOT NULL,
  `kd_produk` varchar(20) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_pembelian_detail`
--

INSERT INTO `transaksi_pembelian_detail` (`kd_transaksi_beli`, `kd_produk`, `jumlah`) VALUES
('TRB0001', '3', 3),
('TRB0002', '3', 5),
('TRB0002', '1', 2);

--
-- Triggers `transaksi_pembelian_detail`
--
DELIMITER $$
CREATE TRIGGER `tambah_stok` AFTER INSERT ON `transaksi_pembelian_detail` FOR EACH ROW BEGIN
	UPDATE produk SET stok = stok + new.Jumlah WHERE produk.kd_produk = NEW.kd_produk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_penjualan`
--

CREATE TABLE `transaksi_penjualan` (
  `kd_transaksi_jual` varchar(20) NOT NULL,
  `kd_konsumen` int(11) NOT NULL,
  `kd_karyawan` int(11) NOT NULL,
  `bayar` int(15) NOT NULL,
  `tgl_penjualan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_penjualan`
--

INSERT INTO `transaksi_penjualan` (`kd_transaksi_jual`, `kd_konsumen`, `kd_karyawan`, `bayar`, `tgl_penjualan`) VALUES
('TRJ0001', 1, 1, 20000, '2022-06-25'),
('TRJ0002', 1, 1, 20000, '2022-06-25'),
('TRJ0003', 1, 1, 5000, '2022-06-25');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_penjualan_detail`
--

CREATE TABLE `transaksi_penjualan_detail` (
  `kd_transaksi_jual` varchar(20) NOT NULL,
  `kd_produk` varchar(20) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_penjualan_detail`
--

INSERT INTO `transaksi_penjualan_detail` (`kd_transaksi_jual`, `kd_produk`, `jumlah`) VALUES
('TRJ0001', '3', 3),
('TRJ0001', '2', 2),
('TRJ0002', '4', 2),
('TRJ0002', '3', 3),
('TRJ0003', '3', 1);

--
-- Triggers `transaksi_penjualan_detail`
--
DELIMITER $$
CREATE TRIGGER `kurang_stock` AFTER INSERT ON `transaksi_penjualan_detail` FOR EACH ROW BEGIN
	UPDATE produk SET stok = stok - new.Jumlah WHERE produk.kd_produk = NEW.kd_produk;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`kd_karyawan`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kd_kategori`);

--
-- Indexes for table `konsumen`
--
ALTER TABLE `konsumen`
  ADD PRIMARY KEY (`kd_konsumen`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`kd_produk`),
  ADD KEY `kd_kategori` (`kd_kategori`);

--
-- Indexes for table `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`kd_suplier`);

--
-- Indexes for table `transaksi_pembelian`
--
ALTER TABLE `transaksi_pembelian`
  ADD PRIMARY KEY (`kd_transaksi_beli`),
  ADD KEY `kd_karyawan` (`kd_karyawan`),
  ADD KEY `kd_suplier` (`kd_suplier`);

--
-- Indexes for table `transaksi_pembelian_detail`
--
ALTER TABLE `transaksi_pembelian_detail`
  ADD KEY `kd_produk` (`kd_produk`),
  ADD KEY `kd_transaksi` (`kd_transaksi_beli`);

--
-- Indexes for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD PRIMARY KEY (`kd_transaksi_jual`),
  ADD KEY `kd_konsumen` (`kd_konsumen`),
  ADD KEY `kd_karyawan` (`kd_karyawan`);

--
-- Indexes for table `transaksi_penjualan_detail`
--
ALTER TABLE `transaksi_penjualan_detail`
  ADD KEY `kd_transaksi` (`kd_transaksi_jual`),
  ADD KEY `kd_produk` (`kd_produk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `kd_karyawan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `kd_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `konsumen`
--
ALTER TABLE `konsumen`
  MODIFY `kd_konsumen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `suplier`
--
ALTER TABLE `suplier`
  MODIFY `kd_suplier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`kd_kategori`) REFERENCES `kategori` (`kd_kategori`);

--
-- Constraints for table `transaksi_pembelian`
--
ALTER TABLE `transaksi_pembelian`
  ADD CONSTRAINT `transaksi_pembelian_ibfk_2` FOREIGN KEY (`kd_suplier`) REFERENCES `suplier` (`kd_suplier`),
  ADD CONSTRAINT `transaksi_pembelian_ibfk_3` FOREIGN KEY (`kd_karyawan`) REFERENCES `karyawan` (`kd_karyawan`);

--
-- Constraints for table `transaksi_pembelian_detail`
--
ALTER TABLE `transaksi_pembelian_detail`
  ADD CONSTRAINT `transaksi_pembelian_detail_ibfk_1` FOREIGN KEY (`kd_transaksi_beli`) REFERENCES `transaksi_pembelian` (`kd_transaksi_beli`),
  ADD CONSTRAINT `transaksi_pembelian_detail_ibfk_2` FOREIGN KEY (`kd_produk`) REFERENCES `produk` (`kd_produk`);

--
-- Constraints for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD CONSTRAINT `transaksi_penjualan_ibfk_2` FOREIGN KEY (`kd_karyawan`) REFERENCES `karyawan` (`kd_karyawan`),
  ADD CONSTRAINT `transaksi_penjualan_ibfk_3` FOREIGN KEY (`kd_konsumen`) REFERENCES `konsumen` (`kd_konsumen`);

--
-- Constraints for table `transaksi_penjualan_detail`
--
ALTER TABLE `transaksi_penjualan_detail`
  ADD CONSTRAINT `transaksi_penjualan_detail_ibfk_1` FOREIGN KEY (`kd_transaksi_jual`) REFERENCES `transaksi_penjualan` (`kd_transaksi_jual`),
  ADD CONSTRAINT `transaksi_penjualan_detail_ibfk_2` FOREIGN KEY (`kd_produk`) REFERENCES `produk` (`kd_produk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
