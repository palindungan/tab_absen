<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Murid extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_kode");
    }

    function list_murid_get()
    {
        // mengambil data dari database
        $query = $this->M_universal->tampil_data('list_murid');

        // variable array
        $result = array();
        $result['data_result'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_murid' => $row["id_murid"],
                    'nama' => $row["nama"],
                    'foto' => $row["foto"],
                    'id_wali_murid' => $row["id_wali_murid"],
                    'nama_wali_murid' => $row["nama_wali_murid"],
                    'username' => $row["username"],
                    'alamat' => $row["alamat"],
                    'no_hp' => $row["no_hp"]
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
            $result["message"] = "Data Masih Kosong";
            $this->response($result, 200);
        }
    }

    function add_murid_post()
    {
        // ambil data
        $id_murid = $this->M_kode->get_id_murid();
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

        $insert =  $this->M_universal->input_data('murid', $data);
        if ($insert) {

            if (!empty($foto)) {
                $path = "./upload/image/murid/$nama_foto.jpg";
                file_put_contents($path, base64_decode($foto));
            }

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

        if (!empty($foto)) {

            $cek_foto = "";

            // mengambil data dari database
            $query = $this->M_universal->get_data('murid', $where);
            foreach ($query->result_array() as $row) {

                $cek_foto = $row["foto"];
            }

            if ($cek_foto != "DEFFMR") {
                // lokasi gambar berada
                $path = './upload/image/murid/';
                $format = '.jpg';
                unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
            }

            $path2 = "./upload/image/murid/$nama_foto.jpg";
            file_put_contents($path2, base64_decode($foto));
        }

        $update =  $this->M_universal->update_data($where, 'murid', $data);
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

    function delete_murid_post()
    {
        $id_murid = $this->post('id');

        $where = array(
            'id_murid' => $id_murid
        );

        $cek_foto = "";
        $nama = "";

        // mengambil data dari database
        $query = $this->M_universal->get_data('murid', $where);

        foreach ($query->result_array() as $row) {
            $cek_foto = $row["foto"];
            $nama = $row["nama"];
        }

        if ($cek_foto != "DEFFMR") {

            $nama_foto = 'F' . $id_murid;

            // lokasi gambar berada
            $path = './upload/image/murid/';
            $format = '.jpg';
            unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
        }

        $hapus =  $this->M_universal->hapus_data($where, "murid");
        if ($hapus) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menghapus Data " . $nama;
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menghapus Data";
            $this->response($result, 200);
        }
    }
}
