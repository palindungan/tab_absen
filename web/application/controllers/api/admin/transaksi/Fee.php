<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Fee extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/admin/transaksi/M_fee");
    }

    function tambah_transaksi_post()
    {
        $id_penggajian = $this->M_fee->get_no_transaksi();
        $id_pengajar = $this->post('id_pengajar');
        $id_admin = $this->post('id_admin');
        $total_pertemuan = $this->post('total_pertemuan');
        $total_harga_fee = $this->post('total_harga_fee');
        $tanggal = date('Y-m-d H:i:s');

        $data = array(
            'id_penggajian' => $id_penggajian,
            'id_pengajar' => $id_pengajar,
            'id_admin' => $id_admin,
            'waktu' => $tanggal,
            'total_pertemuan' => $total_pertemuan,
            'total_harga_fee' => $total_harga_fee
        );

        $insert =  $this->M_fee->input_data('penggajian', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menambah Penggajian FEE";
            $result["id_penggajian"] =  $id_penggajian;
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menambah Penggajian FEE";
            $this->response(array($result, 200));
        }
    }

    function tambah_detail_transaksi_post()
    {
        $id_penggajian = $this->post('id_penggajian');
        $id_pertemuan = $this->post('id_pertemuan');

        $where = array(
            'id_pertemuan' => $id_pertemuan
        );

        $data_update = array(
            'status_fee' => 'Sudah Terbayar'
        );

        $update = $this->M_fee->update_data($where, 'pertemuan', $data_update);

        if ($update) {

            $data = array(
                'id_penggajian' => $id_penggajian,
                'id_pertemuan' => $id_pertemuan
            );

            $insert =  $this->M_fee->input_data('detail_penggajian', $data);
            if ($insert) {

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Berhasil Menambah Detail Penggajian FEE";
                $this->response($result, 200);
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["message"] = "Gagal Menambah Detail Penggajian FEE";
                $this->response(array($result, 200));
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Update Data Pertemuan FEE";
            $this->response(array($result, 200));
        }
    }

    function ambil_data_pertemuan_for_fee_post()
    {
        $id_pengajar = $this->post('id_pengajar');

        $where = array(
            'id_pengajar' => $id_pengajar,
            'status_pertemuan' => 'Selesai',
            'status_konfirmasi' => 'Valid',
            'status_fee' => 'Belum Terbayar'
        );

        // variable array
        $result = array();
        $result['list_pertemuan'] = array();

        $query = array();
        $query = $this->M_fee->get_data('list_pertemuan', $where);

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
        $query = $this->M_fee->get_data('list_penggajian', $where);

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
        $query = $this->M_fee->get_data('list_detail_penggajian', $where);

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
