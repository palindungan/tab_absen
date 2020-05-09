<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Kelas_pertemuan extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_kode");
    }

    function list_kelas_post()
    {
        $id_pengajar = $this->post('id_pengajar');

        // variable array
        $result = array();
        $result['data_result'] = array();

        // mengambil data dari database
        $query = $this->M_universal->get_data_or('list_kelas', $id_pengajar, 'id_pengajar', 'id_sharing');
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                $data_id = array(
                    'id_kelas_p' => $row["id_kelas_p"]
                );

                $count = $this->M_universal->get_data('detail_kelas_pertemuan', $data_id)->num_rows();

                // ambil detail data db
                $data = array(
                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari' => $row["hari"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],
                    'harga_fee' => $row["harga_fee"],
                    'harga_spp' => $row["harga_spp"],
                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_pelajaran' => $row["nama_pelajaran"],
                    'id_pengajar' => $row["id_pengajar"],
                    'nama_pengajar' => $row["nama_pengajar"],
                    'id_sharing' => $row["id_sharing"],
                    'nama_sharing' => $row["nama_sharing"],
                    'jumlah_murid' => $count
                );

                array_push($result['data_result'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Success Berhasil Mengambil Data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data Masih kosong";
            $this->response($result, 200);
        }
    }

    function add_kelas_post()
    {
        // ambil data
        $id_kelas_p = $this->M_kode->get_id_kelas_p();
        $id_pengajar = $this->post('id_pengajar');
        $id_mata_pelajaran = $this->post('id_mata_pelajaran');
        $hari = $this->post('hari');
        $jam_mulai = $this->post('jam_mulai');
        $jam_berakhir = $this->post('jam_berakhir');
        $harga_fee = $this->post('harga_fee');
        $harga_spp = $this->post('harga_spp');
        $id_sharing = "null";
        $nama_sharing = "kosong";

        $data = array(
            'id_kelas_p'   => $id_kelas_p,
            'id_pengajar'   => $id_pengajar,
            'id_mata_pelajaran'   => $id_mata_pelajaran,
            'hari'   => $hari,
            'jam_mulai'   => $jam_mulai,
            'jam_berakhir'   => $jam_berakhir,
            'harga_fee'   => $harga_fee,
            'harga_spp'   => $harga_spp,
            'id_sharing'   => $id_sharing,
            'nama_sharing'   => $nama_sharing,
        );

        $insert =  $this->M_universal->input_data('kelas_pertemuan', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menambah Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menambah Data";
            $this->response($result, 200);
        }
    }

    function delete_kelas_post()
    {
        $id_kelas_p = $this->post('id');

        $where = array(
            'id_kelas_p' => $id_kelas_p
        );

        $hapus =  $this->M_universal->hapus_data($where, "kelas_pertemuan");
        if ($hapus) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menghapus Data ";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menghapus Data";
            $this->response($result, 200);
        }
    }
}
