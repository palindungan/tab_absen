<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Akun_saya extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_akun_saya");
    }

    function list_admin_get()
    {
        // mengambil data dari database
        $query = $this->M_akun_saya->tampil_data('admin');

        // variable array
        $result = array();
        $result['admin'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_admin' => $row["id_admin"],
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'foto' => $row["foto"]
                );

                array_push($result['admin'], $data);

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

    function tambah_admin_post()
    {
        // ambil data
        $id_admin = $this->M_akun_saya->get_no();
        $nama = $this->post('nama');
        $username = $this->post('username');
        $password = $this->post('password');
        $foto = $this->post('foto');

        $nama_foto = "DEFFPE";

        if (!empty($foto)) {
            $nama_foto = 'F' . $id_admin;
        }

        $data = array(
            'id_admin'   => $id_admin,
            'nama'          => $nama,
            'username'      => $username,
            'password'      => password_hash($password, PASSWORD_DEFAULT),
            'foto'          => $nama_foto
        );

        $insert =  $this->M_akun_saya->input_data('admin', $data);
        if ($insert) {

            if (!empty($foto)) {
                $path = "./upload/image/admin/$nama_foto.jpg";
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

    function ambil_data_admin_post()
    {
        $id_admin = $this->post('id_admin');

        // variable array
        $result = array();
        $result['admin'] = array();

        $data_id = array(
            'id_admin' => $id_admin
        );

        // mengambil data dari database
        $query = $this->M_akun_saya->get_data('admin', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'foto' => $row["foto"]
                );

                array_push($result['admin'], $data);

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

    function update_admin_post()
    {
        $id_admin = $this->post('id_admin');
        $nama = $this->post('nama');
        $username = $this->post('username');
        $password = $this->post('password');
        $foto = $this->post('foto');
        $nama_foto = 'F' . $id_admin;

        $data = array();

        if (empty($password)) {
            $data = array(
                'id_admin'      => $id_admin,
                'nama'          => $nama,
                'username'      => $username,
                'foto'          => $nama_foto
            );
        } else {
            $data = array(
                'id_admin'      => $id_admin,
                'nama'          => $nama,
                'username'      => $username,
                'password'      => password_hash($password, PASSWORD_DEFAULT),
                'foto'          => $nama_foto
            );
        }

        $where = array(
            'id_admin' => $id_admin
        );

        if (!empty($foto)) {

            $cek_foto = "";

            // mengambil data dari database
            $query = $this->M_akun_saya->get_data('admin', $where);
            foreach ($query->result_array() as $row) {

                $cek_foto = $row["foto"];
            }

            if ($cek_foto != "DEFFPE") {
                // lokasi gambar berada
                $path = './upload/image/admin/';
                $format = '.jpg';
                unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
            }

            $path2 = "./upload/image/admin/$nama_foto.jpg";
            file_put_contents($path2, base64_decode($foto));
        }

        $update =  $this->M_akun_saya->update_data($where, 'admin', $data);
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

    function delete_admin_post()
    {
        $id_admin = $this->post('id');

        $where = array(
            'id_admin' => $id_admin
        );

        $cek_foto = "";

        // mengambil data dari database
        $query = $this->M_akun_saya->get_data('admin', $where);
        foreach ($query->result_array() as $row) {
            $cek_foto = $row["foto"];
        }

        if ($cek_foto != "DEFFPE") {

            $nama_foto = 'F' . $id_admin;

            // lokasi gambar berada
            $path = './upload/image/admin/';
            $format = '.jpg';
            unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
        }

        $hapus =  $this->M_akun_saya->hapus_data($where, "admin");
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
