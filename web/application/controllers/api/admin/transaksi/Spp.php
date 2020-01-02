<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Spp extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/admin/transaksi/M_spp");
    }

    function tambah_transaksi_post()
    {
        $id_bayar_spp = $this->M_spp->get_no_transaksi();
        $id_wali_murid = $this->post('id_wali_murid');
        $id_admin = $this->post('id_admin');
        $tanggal = date('Y-m-d H:i:s');
        $total_pertemuan = $this->post('total_pertemuan');
        $total_spp = $this->post('total_spp');

        $data = array(
            'id_bayar_spp' => $id_bayar_spp,
            'id_wali_murid' => $id_wali_murid,
            'id_admin' => $id_admin,
            'waktu' => $tanggal,
            'total_pertemuan' => $total_pertemuan,
            'total_spp' => $total_spp
        );

        $insert =  $this->M_spp->input_data('bayar_spp', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menambah Pembayaran Spp";
            $result["id_bayar_spp"] =  $id_bayar_spp;
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menambah Pembayaran Spp";
            $this->response(array($result, 200));
        }
    }

    function tambah_detail_transaksi_post()
    {
        $id_bayar_spp = $this->post('id_bayar_spp');
        $id_pertemuan = $this->post('id_pertemuan');

        $where = array(
            'id_pertemuan' => $id_pertemuan
        );

        $data_update = array(
            'status_spp' => 'Sudah Lunas'
        );

        $update = $this->M_spp->update_data($where, 'pertemuan', $data_update);

        if ($update) {

            $data = array(
                'id_bayar_spp' => $id_bayar_spp,
                'id_pertemuan' => $id_pertemuan
            );

            $insert =  $this->M_spp->input_data('detail_bayar_spp', $data);
            if ($insert) {

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Berhasil Menambah Detail Pembayaran Spp";
                $this->response($result, 200);
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["message"] = "Gagal Menambah Detail Pembayaran Spp";
                $this->response(array($result, 200));
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Update Data Pertemuan";
            $this->response(array($result, 200));
        }
    }

    function ambil_data_pertemuan_for_spp_post()
    {
        $id_wali_murid = $this->post('id_wali_murid');

        $where = array(
            'id_wali_murid' => $id_wali_murid,
            'status_pertemuan' => 'Selesai',
            'status_konfirmasi' => 'Valid',
            'status_spp' => 'Belum Lunas'
        );

        // variable array
        $result = array();
        $result['list_pertemuan_spp'] = array();

        $query = array();
        $query = $this->M_spp->get_data('list_pertemuan_spp', $where);

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
                    'harga_spp' => $row["harga_spp"],

                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari_jadwal' => $row["hari_jadwal"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],

                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran' => $row["nama_mata_pelajaran"],

                    'id_wali_murid' => $row["id_wali_murid"],
                    'nama_wali_murid' => $row["nama_wali_murid"]
                );

                array_push($result['list_pertemuan_spp'], $data);

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

    function ambil_data_penggajian_post()
    {
        $id_pengajar = $this->post('id_pengajar');

        $where = array(
            'id_pengajar' => $id_pengajar
        );

        // variable array
        $result = array();
        $result['penggajian'] = array();

        $query = array();
        $query = $this->M_spp->get_data('list_penggajian', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_penggajian' => $row["id_penggajian"],
                    'id_pengajar' => $row["id_pengajar"],
                    'id_admin' => $row["id_admin"],
                    'waktu' => $row["waktu"],
                    'total_pertemuan' => $row["total_pertemuan"],
                    'total_harga_fee' => $row["total_harga_fee"]
                );

                array_push($result['penggajian'], $data);

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

    function ambil_data_detail_penggajian_post()
    {
        $id_pengajar = $this->post('id_pengajar');
        $id_penggajian = $this->post('id_penggajian');

        $where = array(
            'id_penggajian' => $id_penggajian
        );

        // variable array
        $result = array();
        $result['list_pertemuan'] = array();

        $query = array();
        $query = $this->M_spp->get_data('list_detail_penggajian', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_penggajian' => $row["id_penggajian"],

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

                array_push($result['list_pertemuan'], $data);

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
