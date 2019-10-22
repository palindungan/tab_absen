<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Login extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_login");
    }

    function admin_post()
    {
        // ambil data
        $username = $this->post('username');
        $password = $this->post('password');

        // data array untuk where db
        $where = array(
            'username' => $username
        );

        // mengambil jumlah baris
        $cek = $this->M_login->get_data('admin', $where)->num_rows();

        // cek apakah ada data dari username
        if ($cek > 0) {

            // mengambil data dari database berdasarkan username
            $query = $this->M_login->get_data('admin', $where);

            // variable array
            $result = array();
            $result['login'] = array();

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // dicek apakah data inputan sama dengan data di database
                if (password_verify($password, $row["password"])) {

                    // ambil detail data db
                    $data = array(
                        'id_admin' => $row["id_admin"],
                        'nama' => $row["nama"],
                        'username' => $row["username"]
                    );

                    array_push($result['login'], $data);

                    // membuat array untuk di transfer
                    $result["success"] = "1";
                    $result["message"] = "success berhasil masuk";
                    $this->response($result, 200);
                } else {
                    // membuat array untuk di transfer ke API
                    $result["success"] = "0";
                    $result["message"] = "error password anda salah";
                    $this->response($result, 502);
                }
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error username tidak ditemukan";
            $this->response($result, 404);
        }
    }

    function pengajar_post()
    {
        // ambil data
        $username = $this->post('username');
        $password = $this->post('password');

        // data array untuk where db
        $where = array(
            'username' => $username
        );

        // mengambil jumlah baris
        $cek = $this->M_login->get_data('pengajar', $where)->num_rows();

        // cek apakah ada data dari username
        if ($cek > 0) {

            // mengambil data dari database berdasarkan username
            $query = $this->M_login->get_data('pengajar', $where);

            // variable array
            $result = array();
            $result['login'] = array();

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // dicek apakah data inputan sama dengan data di database
                if (password_verify($password, $row["password"])) {

                    // ambil detail data db
                    $data = array(
                        'id_pengajar' => $row["id_pengajar"],
                        'nama' => $row["nama"],
                        'username' => $row["username"]
                    );

                    array_push($result['login'], $data);

                    // membuat array untuk di transfer
                    $result["success"] = "1";
                    $result["message"] = "success berhasil masuk";
                    $this->response($result, 200);
                } else {
                    // membuat array untuk di transfer ke API
                    $result["success"] = "0";
                    $result["message"] = "error password anda salah";
                    $this->response($result, 502);
                }
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error username tidak ditemukan";
            $this->response($result, 404);
        }
    }

    function wali_murid_post()
    {
        // ambil data
        $username = $this->post('username');
        $password = $this->post('password');

        // data array untuk where db
        $where = array(
            'username' => $username
        );

        // mengambil jumlah baris
        $cek = $this->M_login->get_data('wali_murid', $where)->num_rows();

        // cek apakah ada data dari username
        if ($cek > 0) {

            // mengambil data dari database berdasarkan username
            $query = $this->M_login->get_data('wali_murid', $where);

            // variable array
            $result = array();
            $result['login'] = array();

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // dicek apakah data inputan sama dengan data di database
                if (password_verify($password, $row["password"])) {

                    // ambil detail data db
                    $data = array(
                        'id_wali_murid' => $row["id_wali_murid"],
                        'nama' => $row["nama"],
                        'username' => $row["username"]
                    );

                    array_push($result['login'], $data);

                    // membuat array untuk di transfer
                    $result["success"] = "1";
                    $result["message"] = "success berhasil masuk";
                    $this->response($result, 200);
                } else {
                    // membuat array untuk di transfer ke API
                    $result["success"] = "0";
                    $result["message"] = "error password anda salah";
                    $this->response($result, 502);
                }
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error username tidak ditemukan";
            $this->response($result, 404);
        }
    }
}
