<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Absen extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/pengajar/M_absen");
        date_default_timezone_set('Asia/Jakarta');
    }

    function tambah_absen_post()
    {
        // ambil data

        $hari_kelas = $this->post('hari_kelas');

        $id_pertemuan = $this->M_absen->get_no();
        $id_pengajar = $this->post('id_pengajar');
        $id_kelas_p = $this->post('id_kelas_p');

        $tanggal = date('Y-m-d H:i:s');

        $hari = date('l', strtotime($tanggal));

        if ($hari == "Sunday") {
            $hari = "Minggu";
        } elseif ($hari == "Monday") {
            $hari = "Senin";
        } elseif ($hari == "Tuesday") {
            $hari = "Selasa";
        } elseif ($hari == "Wednesday") {
            $hari = "Rabu";
        } elseif ($hari == "Thursday") {
            $hari = "Kamis";
        } elseif ($hari == "Friday") {
            $hari = "Jum'at";
        } elseif ($hari == "Saturday") {
            $hari = "Sabtu";
        }

        $waktu_mulai = $tanggal;
        $waktu_berakhir = $tanggal;

        $lokasi_mulai_la = $this->post('lokasi_mulai_la');
        $lokasi_mulai_lo = $this->post('lokasi_mulai_lo');

        $lokasi_berakhir_la = "null";
        $lokasi_berakhir_lo = "null";

        $status_fee = "Belum Terbayar";
        $status_spp = "Belum Lunas";
        $status_pertemuan = "Belum Selesai";
        $status_konfirmasi = "Invalid";

        $deskripsi = "kosong";

        $harga_fee = $this->post('harga_fee');

        $data = array(
            'id_pertemuan'   => $id_pertemuan,
            'id_pengajar'   => $id_pengajar,
            'id_kelas_p'   => $id_kelas_p,
            'hari'   => $hari,
            'waktu_mulai'   => $waktu_mulai,
            'waktu_berakhir'   => $waktu_berakhir,
            'lokasi_mulai_la'   => $lokasi_mulai_la,
            'lokasi_mulai_lo'   => $lokasi_mulai_lo,
            'lokasi_berakhir_la'   => $lokasi_berakhir_la,
            'lokasi_berakhir_lo'   => $lokasi_berakhir_lo,
            'status_fee'   => $status_fee,
            'status_spp'   => $status_spp,
            'status_pertemuan'   => $status_pertemuan,
            'status_konfirmasi'   => $status_konfirmasi,
            'deskripsi'   => $deskripsi,
            'harga_fee' => $harga_fee
        );

        $where = array(
            'id_pengajar' => $id_pengajar,
            'status_pertemuan' => "Belum Selesai"
        );

        // mengambil data dari database
        $cek = $this->M_absen->get_data('list_pertemuan', $where);

        if ($cek->num_rows() > 0) {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Anda Sudah Melakukan Absen , Akhiri kelas yang masih berjalan !";
            $this->response($result, 200);
        } else {
            $insert =  $this->M_absen->input_data('pertemuan', $data);
            if ($insert) {

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Berhasil Melakukan Input Absen";
                $result["id_pertemuan"] = $id_pertemuan;
                $this->response($result, 200);
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["message"] = "Gagal Melakukan Input Absen";
                $this->response($result, 200);
            }
        }
    }

    function ambil_list_pertemuan_aktif_post()
    {
        $id_pengajar = $this->post('id_pengajar');

        $where = array(
            'id_pengajar' => $id_pengajar
        );

        // variable array
        $result = array();
        $result['list_pertemuan_belum_selesai'] = array();

        // mengambil data dari database

        $query = array();

        if ($id_pengajar == "Semua") {
            $query = $this->M_absen->tampil_data('list_pertemuan_belum_selesai');
        } else {
            $query = $this->M_absen->get_data('list_pertemuan_belum_selesai', $where);
        }

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pertemuan' => $row["id_pertemuan"],

                    'nama_pengajar' => $row["nama_pengajar"],
                    'username' => $row["username"],
                    'foto' => $row["foto"],

                    'hari_btn' => $row["hari_btn"],
                    'waktu_mulai' => $row["waktu_mulai"],
                    'waktu_berakhir' => $row["waktu_berakhir"],
                    'lokasi_mulai_la' => $row["lokasi_mulai_la"],
                    'lokasi_mulai_lo' => $row["lokasi_mulai_lo"],
                    'lokasi_berakhir_la' => $row["lokasi_berakhir_la"],
                    'lokasi_berakhir_lo' => $row["lokasi_berakhir_lo"],

                    'status_fee' => $row["status_fee"],
                    'status_spp' => $row["status_spp"],
                    'status_pertemuan' => $row["status_pertemuan"],
                    'status_konfirmasi' => $row["status_konfirmasi"],

                    'deskripsi' => $row["deskripsi"],
                    'harga_fee' => $row["harga_fee"],

                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari_jadwal' => $row["hari_jadwal"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],

                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran' => $row["nama_mata_pelajaran"]
                );

                array_push($result['list_pertemuan_belum_selesai'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "success berhasil mengambil data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "data tidak ada dalam database";
            $this->response($result, 200);
        }
    }

    function ambil_data_pertemuan_post()
    {
        $id_pertemuan = $this->post('id_pertemuan');

        $where = array(
            'id_pertemuan' => $id_pertemuan
        );

        // variable array
        $result = array();
        $result['list_pertemuan'] = array();

        // mengambil data dari database
        $query = $this->M_absen->get_data('list_pertemuan', $where);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pengajar' => $row["id_pengajar"],
                    'nama_pengajar' => $row["nama_pengajar"],
                    'username' => $row["username"],
                    'foto' => $row["foto"],

                    'hari_btn' => $row["hari_btn"],
                    'waktu_mulai' => $row["waktu_mulai"],
                    'waktu_berakhir' => $row["waktu_berakhir"],
                    'lokasi_mulai_la' => $row["lokasi_mulai_la"],
                    'lokasi_mulai_lo' => $row["lokasi_mulai_lo"],
                    'lokasi_berakhir_la' => $row["lokasi_berakhir_la"],
                    'lokasi_berakhir_lo' => $row["lokasi_berakhir_lo"],

                    'status_fee' => $row["status_fee"],
                    'status_spp' => $row["status_spp"],
                    'status_pertemuan' => $row["status_pertemuan"],
                    'status_konfirmasi' => $row["status_konfirmasi"],

                    'deskripsi' => $row["deskripsi"],
                    'harga_fee' => $row["harga_fee"],

                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari_jadwal' => $row["hari_jadwal"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],

                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran' => $row["nama_mata_pelajaran"]
                );

                array_push($result['list_pertemuan'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "success berhasil mengambil data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error data tidak ada";
            $this->response($result, 200);
        }
    }

    function delete_pertemuan_post()
    {
        $id_pertemuan = $this->post('id');

        $where = array(
            'id_pertemuan' => $id_pertemuan
        );

        $hapus =  $this->M_absen->hapus_data($where, "pertemuan");
        if ($hapus) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menghapus Data Pertemuan !";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menghapus Data Pertemuan !";
            $this->response(array($result, 200));
        }
    }

    function update_pertemuan_post()
    {
        $id_pertemuan = $this->post('id_pertemuan');

        $where = array(
            'id_pertemuan' => $id_pertemuan
        );

        // ambil data
        $tanggal = date('Y-m-d H:i:s');

        $waktu_berakhir = $tanggal;

        $lokasi_berakhir_la = $this->post('lokasi_berakhir_la');
        $lokasi_berakhir_lo = $this->post('lokasi_berakhir_lo');

        $status_pertemuan = "Selesai";

        $deskripsi = $this->post('deskripsi');

        $data = array();
        $data = array(
            'waktu_berakhir' => $waktu_berakhir,
            'lokasi_berakhir_la' => $lokasi_berakhir_la,
            'lokasi_berakhir_lo' => $lokasi_berakhir_lo,
            'status_pertemuan' => $status_pertemuan,
            'deskripsi' => $deskripsi
        );

        $update =  $this->M_absen->update_data($where, 'pertemuan', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengakhiri Pertemuan";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Mengakhiri Pertemuan";
            $this->response(array($result, 200));
        }
    }

    function riwayat_pertemuan_post()
    {
        $id_pengajar = $this->post('id_pengajar');

        $where = array(
            'id_pengajar' => $id_pengajar
        );

        // variable array
        $result = array();
        $result['list_pertemuan_selesai'] = array();

        $query = array();

        // mengambil data dari database
        if ($id_pengajar == "Semua") {
            $query = $this->M_absen->tampil_data('list_pertemuan_selesai');
        } else {
            $query = $this->M_absen->get_data('list_pertemuan_selesai', $where);
        }

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pertemuan' => $row["id_pertemuan"],

                    'nama_pengajar' => $row["nama_pengajar"],
                    'username' => $row["username"],
                    'foto' => $row["foto"],

                    'hari_btn' => $row["hari_btn"],
                    'waktu_mulai' => $row["waktu_mulai"],
                    'waktu_berakhir' => $row["waktu_berakhir"],
                    'lokasi_mulai_la' => $row["lokasi_mulai_la"],
                    'lokasi_mulai_lo' => $row["lokasi_mulai_lo"],
                    'lokasi_berakhir_la' => $row["lokasi_berakhir_la"],
                    'lokasi_berakhir_lo' => $row["lokasi_berakhir_lo"],

                    'status_fee' => $row["status_fee"],
                    'status_spp' => $row["status_spp"],
                    'status_pertemuan' => $row["status_pertemuan"],
                    'status_konfirmasi' => $row["status_konfirmasi"],

                    'deskripsi' => $row["deskripsi"],
                    'harga_fee' => $row["harga_fee"],

                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari_jadwal' => $row["hari_jadwal"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],

                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran' => $row["nama_mata_pelajaran"]
                );

                array_push($result['list_pertemuan_selesai'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "success berhasil mengambil data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "data tidak ada dalam database";
            $this->response($result, 200);
        }
    }

    function validasi_pertemuan_post()
    {
        $id_pertemuan = $this->post('id');

        $where = array(
            'id_pertemuan' => $id_pertemuan
        );

        // ambil data

        $status_konfirmasi = $this->post('status_konfirmasi');

        $data = array();
        $data = array(
            'status_konfirmasi' => $status_konfirmasi
        );

        $update =  $this->M_absen->update_data($where, 'pertemuan', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Validasi Pertemuan";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Validasi Pertemuan";
            $this->response(array($result, 200));
        }
    }

    function ambil_data_pertemuan_selesai_valid_belum_terbayar_post()
    {
        $id_pengajar = $this->post('id_pengajar');

        $where = array(
            'id_pengajar' => $id_pengajar
        );

        // variable array
        $result = array();
        $result['list_pertemuan_selesai_valid_belum_terbayar'] = array();

        $query = array();
        $query = $this->M_absen->get_data('list_pertemuan_selesai_valid_belum_terbayar', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pertemuan' => $row["id_pertemuan"],

                    'nama_pengajar' => $row["nama_pengajar"],
                    'username' => $row["username"],
                    'foto' => $row["foto"],

                    'hari_btn' => $row["hari_btn"],
                    'waktu_mulai' => $row["waktu_mulai"],
                    'waktu_berakhir' => $row["waktu_berakhir"],
                    'lokasi_mulai_la' => $row["lokasi_mulai_la"],
                    'lokasi_mulai_lo' => $row["lokasi_mulai_lo"],
                    'lokasi_berakhir_la' => $row["lokasi_berakhir_la"],
                    'lokasi_berakhir_lo' => $row["lokasi_berakhir_lo"],

                    'status_fee' => $row["status_fee"],
                    'status_spp' => $row["status_spp"],
                    'status_pertemuan' => $row["status_pertemuan"],
                    'status_konfirmasi' => $row["status_konfirmasi"],

                    'deskripsi' => $row["deskripsi"],
                    'harga_fee' => $row["harga_fee"],

                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari_jadwal' => $row["hari_jadwal"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],

                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran' => $row["nama_mata_pelajaran"]
                );

                array_push($result['list_pertemuan_selesai_valid_belum_terbayar'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "success berhasil mengambil data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "data tidak ada dalam database";
            $this->response($result, 200);
        }
    }
}
