<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Pengajar extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_pengajar");
    }

    function list_pengajar_get()
    {
        // mengambil data dari database
        $query = $this->M_pengajar->tampil_data('pengajar');

        // variable array
        $result = array();
        $result['pengajar'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pengajar' => $row["id_pengajar"],
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'alamat' => $row["alamat"],
                    'no_hp' => $row["no_hp"],
                    'foto' => $row["foto"]
                );

                array_push($result['pengajar'], $data);

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

    function tambah_pengajar_post()
    {
        // ambil data
        $id_pengajar = $this->M_pengajar->get_no();
        $nama = $this->post('nama');
        $username = $this->post('username');
        $password = $this->post('password');
        $alamat = $this->post('alamat');
        $no_hp = $this->post('no_hp');
        $foto = $this->post('foto');

        $nama_foto = "DEFFPE";

        if ($foto != "") {
            $nama_foto = 'F' . $id_pengajar;
        }

        $data = array(
            'id_pengajar'   => $id_pengajar,
            'nama'          => $nama,
            'username'      => $username,
            'password'      => password_hash($password, PASSWORD_DEFAULT),
            'alamat'        => $alamat,
            'no_hp'         => $no_hp,
            'foto'          => $nama_foto
        );

        $insert =  $this->M_pengajar->input_data('pengajar', $data);
        if ($insert) {

            if ($foto != "") {
                $path = "./upload/image/pengajar/$nama_foto.jpg";
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

    function ambil_data_pengajar_post()
    {
        $id_pengajar = $this->post('id_pengajar');

        // variable array
        $result = array();
        $result['pengajar'] = array();

        $data_id = array(
            'id_pengajar' => $id_pengajar
        );

        // mengambil data dari database
        $query = $this->M_pengajar->get_data('pengajar', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'alamat' => $row["alamat"],
                    'no_hp' => $row["no_hp"],
                    'foto' => $row["foto"]
                );

                array_push($result['pengajar'], $data);

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

    function update_pengajar_post()
    {
        $id_pengajar = $this->post('id_pengajar');
        $nama = $this->post('nama');
        $username = $this->post('username');
        $password = $this->post('password');
        $alamat = $this->post('alamat');
        $no_hp = $this->post('no_hp');

        $nama_foto = 'F' . $id_pengajar;
        $foto = $this->post('foto');

        $data = array(
            'id_pengajar'   => $id_pengajar,
            'nama'          => $nama,
            'username'      => $username,
            'password'      => password_hash($password, PASSWORD_DEFAULT),
            'alamat'        => $alamat,
            'no_hp'         => $no_hp,
            'foto'          => $nama_foto
        );

        $data_id = array(
            'id_pengajar' => $id_pengajar
        );

        // mengambil data dari database
        $query = $this->M_pengajar->get_data('pengajar', $data_id);

        // mengeluarkan data dari database
        foreach ($query->result_array() as $row) {

            // ambil detail data db
            $data = array(
                'nama' => $row["nama"],
                'username' => $row["username"],
                'alamat' => $row["alamat"],
                'no_hp' => $row["no_hp"],
                'foto' => $row["foto"]
            );
        }
    }
}
