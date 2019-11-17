<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Murid extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_murid");
    }

    function list_murid_get()
    {
        // mengambil data dari database
        $query = $this->M_murid->tampil_data('list_murid');

        // variable array
        $result = array();
        $result['murid'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_murid' => $row["id_murid"],
                    'nama' => $row["nama"],
                    'nama_wali_murid' => $row["nama_wali_murid"],
                    'alamat' => $row["alamat"],
                    'foto' => $row["foto"]
                );

                array_push($result['murid'], $data);

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

    function tambah_murid_post()
    {
        // ambil data
        $id_murid = $this->M_murid->get_no();
        $id_wali_murid = $this->post('id_wali_murid');
        $nama = $this->post('nama');
        $foto = $this->post('foto');

        $nama_foto = "DEFFMR";

        if (!empty($foto)) {
            $nama_foto = 'F' . $id_murid;
        }

        $data = array(
            'id_murid'   => $id_murid,
            'id_wali_murid'   => $id_wali_murid,
            'nama'   => $nama,
            'foto'          => $nama_foto
        );

        $insert =  $this->M_murid->input_data('murid', $data);
        if ($insert) {

            if (!empty($foto)) {
                $path = "./upload/image/murid/$nama_foto.jpg";
                file_put_contents($path, base64_decode($foto));
            }

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

    function ambil_data_murid_post()
    {
        $id_murid = $this->post('id_murid');

        // variable array
        $result = array();
        $result['murid'] = array();

        $data_id = array(
            'id_murid' => $id_murid
        );

        // mengambil data dari database
        $query = $this->M_murid->get_data('list_murid', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                    'id_wali_murid' => $row["id_wali_murid"],
                    'nama_wali_murid' => $row["nama_wali_murid"],
                    'alamat' => $row["alamat"],
                    'foto' => $row["foto"]
                );

                array_push($result['murid'], $data);

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

    function update_murid_post()
    {
        $id_murid = $this->post('id_murid');
        $id_wali_murid = $this->post('id_wali_murid');
        $nama = $this->post('nama');
        $foto = $this->post('foto');

        $nama_foto = 'F' . $id_murid;

        $data = array();

        $data = array(
            'id_wali_murid' => $id_wali_murid,
            'nama'          => $nama,
            'foto'          => $nama_foto
        );

        $where = array(
            'id_murid' => $id_murid
        );

        $update =  $this->M_murid->update_data($where, 'murid', $data);
        if ($update) {

            if (!empty($foto)) {

                $cek_foto = "";

                // mengambil data dari database
                $query = $this->M_murid->get_data('murid', $where);
                foreach ($query->result_array() as $row) {

                    $cek_foto = $row["foto"];
                }

                if ($cek_foto == "DEFFMR") {
                    // lokasi gambar berada
                    $path = './upload/image/murid/';
                    $format = '.jpg';
                    unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
                }

                $path2 = "./upload/image/murid/$nama_foto.jpg";
                file_put_contents($path2, base64_decode($foto));
            }

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

    function delete_murid_post()
    {
        $id_murid = $this->post('id');

        $where = array(
            'id_murid' => $id_murid
        );

        $hapus =  $this->M_murid->hapus_data($where, "murid");
        if ($hapus) {

            $cek_foto = "";

            // mengambil data dari database
            $query = $this->M_murid->get_data('murid', $where);
            foreach ($query->result_array() as $row) {
                $cek_foto = $row["foto"];
            }

            if ($cek_foto != "DEFFMR") {

                $nama_foto = 'F' . $id_murid;

                // lokasi gambar berada
                $path = './upload/image/murid/';
                $format = '.jpg';
                unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
            }

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
