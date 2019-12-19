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
    }

    function tambah_absen_post()
    {
        // ambil data
        $id_pertemuan = $this->M_absen->get_no();
        $id_pengajar = $this->post('id_pengajar');
        $id_kelas_p = $this->post('id_kelas_p');

        $hari = "Senin"; // ambil hari ini
        $waktu_mulai = "09:00";
        $waktu_berakhir = "09:30";

        $lokasi_mulai_la = $this->post('lokasi_mulai_la');
        $lokasi_mulai_lo = $this->post('lokasi_mulai_lo');

        $lokasi_berakhir_la = "null";
        $lokasi_berakhir_lo = "null";

        $status_fee = "Belum Terbayar";
        $status_spp = "Belum Lunas";
        $status_pertemuan = "Belum Selesai";
        $status_konfirmasi = "Invalid";

        $deskripsi = "kosong";

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
            'deskripsi'   => $deskripsi
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
        $query = $this->M_absen->get_data('list_pertemuan_belum_selesai', $where);
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

                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari_jadwal' => $row["hari_jadwal"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],
                    'harga_fee' => $row["harga_fee"],

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

                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari_jadwal' => $row["hari_jadwal"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],
                    'harga_fee' => $row["harga_fee"],

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
}
