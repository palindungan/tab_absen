<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Mata_pelajaran extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_kode");
    }

    function list_mata_pelajaran_get()
    {
        // mengambil data dari database
        $query = $this->M_universal->tampil_data('mata_pelajaran');

        // variable array
        $result = array();
        $result['data_result'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama' => $row["nama"]
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

    function add_mata_pelajaran_post()
    {
        // ambil data
        $id_mata_pelajaran = $this->M_kode->get_id_mata_pelajaran();
        $nama = $this->post('nama');

        $data = array(
            'id_mata_pelajaran'   => $id_mata_pelajaran,
            'nama'          => $nama
        );

        $insert =  $this->M_universal->input_data('mata_pelajaran', $data);
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

    function update_mata_pelajaran_post()
    {
        $id_mata_pelajaran = $this->post('id_mata_pelajaran');
        $nama = $this->post('nama');

        $data = array();

        $data = array(
            'id_mata_pelajaran' => $id_mata_pelajaran,
            'nama'          => $nama
        );

        $where = array(
            'id_mata_pelajaran' => $id_mata_pelajaran
        );

        $update =  $this->M_universal->update_data($where, 'mata_pelajaran', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengupdate Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Mengupdate Data";
            $this->response($result, 200);
        }
    }

    function delete_mata_pelajaran_post()
    {
        $id_mata_pelajaran = $this->post('id');

        $where = array(
            'id_mata_pelajaran' => $id_mata_pelajaran
        );

        $hapus =  $this->M_universal->hapus_data($where, "mata_pelajaran");
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
