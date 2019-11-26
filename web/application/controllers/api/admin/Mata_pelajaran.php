<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Mata_pelajaran extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/admin/M_mata_pelajaran");
    }

    function list_mata_pelajaran_get()
    {
        // mengambil data dari database
        $query = $this->M_mata_pelajaran->tampil_data('mata_pelajaran');

        // variable array
        $result = array();
        $result['mata_pelajaran'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama' => $row["nama"]
                );

                array_push($result['mata_pelajaran'], $data);

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

    function tambah_mata_pelajaran_post()
    {
        // ambil data
        $id_mata_pelajaran = $this->M_mata_pelajaran->get_no();
        $nama = $this->post('nama');

        $data = array(
            'id_mata_pelajaran'   => $id_mata_pelajaran,
            'nama'          => $nama
        );

        $insert =  $this->M_mata_pelajaran->input_data('mata_pelajaran', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "success";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error";
            $this->response(array($result, 502));
        }
    }

    function ambil_data_mata_pelajaran_post()
    {
        $id_mata_pelajaran = $this->post('id_mata_pelajaran');

        // variable array
        $result = array();
        $result['mata_pelajaran'] = array();

        $data_id = array(
            'id_mata_pelajaran' => $id_mata_pelajaran
        );

        // mengambil data dari database
        $query = $this->M_mata_pelajaran->get_data('mata_pelajaran', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                );

                array_push($result['mata_pelajaran'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "success berhasil mengambil data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error data tidak ada";
            $this->response($result, 502);
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

        $update =  $this->M_mata_pelajaran->update_data($where, 'mata_pelajaran', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "success";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error";
            $this->response(array($result, 502));
        }
    }

    function delete_mata_pelajaran_post()
    {
        $id_mata_pelajaran = $this->post('id');

        $where = array(
            'id_mata_pelajaran' => $id_mata_pelajaran
        );

        $hapus =  $this->M_mata_pelajaran->hapus_data($where, "mata_pelajaran");
        if ($hapus) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "success";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error";
            $this->response(array($result, 502));
        }
    }
}
