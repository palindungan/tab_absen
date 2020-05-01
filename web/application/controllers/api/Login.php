<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Login extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function masuk_post()
    {
        // ambil data
        $username = $this->post('username');
        $password = $this->post('password');
        $hak_akses = $this->post('hak_akses');

        // variable array
        $result = array();
        $result['data_result'] = array();

        // data array untuk where db
        $where = array(
            'username' => $username
        );

        // mengambil data
        $query = $this->M_universal->get_data($hak_akses, $where);

        // cek apakah ada data dari username
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // dicek apakah data inputan sama dengan data di database
                if (password_verify($password, $row["password"])) {

                    // ambil detail data db

                    if ($hak_akses == "admin") {
                        $data = array(
                            'id_user' => $row["id_admin"],
                            'nama' => $row["nama"],
                            'username' => $row["username"],
                            'foto' => $row["foto"]
                        );
                    } elseif ($hak_akses == "pengajar") {
                        $data = array(
                            'id_user' => $row["id_pengajar"],
                            'nama' => $row["nama"],
                            'username' => $row["username"],
                            'foto' => $row["foto"]
                        );
                    } elseif ($hak_akses == "wali_murid") {
                        $data = array(
                            'id_user' => $row["id_wali_murid"],
                            'nama' => $row["nama"],
                            'username' => $row["username"]
                        );
                    }

                    array_push($result['data_result'], $data);

                    // membuat array untuk di transfer
                    $result["success"] = "1";
                    $result["message"] = "Success Berhasil Masuk";
                    $this->response($result, 200);
                } else {
                    // membuat array untuk di transfer ke API
                    $result["success"] = "0";
                    $result["message"] = "Error Password Anda Salah";
                    $this->response($result, 200);
                }
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Error Username Tidak Ditemukan";
            $this->response($result, 200);
        }
    }
}
