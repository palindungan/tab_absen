<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Admin extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function update_post()
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
            $query = $this->M_universal->get_data('admin', $where);
            foreach ($query->result_array() as $row) {

                $cek_foto = $row["foto"];
            }

            if ($cek_foto != "DEFFAD") {
                // lokasi gambar berada
                $path = './upload/image/admin/';
                $format = '.jpg';
                unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
            }

            $path2 = "./upload/image/admin/$nama_foto.jpg";
            file_put_contents($path2, base64_decode($foto));
        }

        $update =  $this->M_universal->update_data($where, 'admin', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Update Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Update Data";
            $this->response(array($result, 502));
        }
    }
}
