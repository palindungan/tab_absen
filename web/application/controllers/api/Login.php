<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Login extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
        $this->load->model("api/M_login");
    }

    //Mengirim atau menambah data kontak baru
    function index_post()
    {

        $username = $this->post('username');
        $password = $this->post('password');

        // mengambil jumlah baris
        $cek = $this->M_login->ambil_data($username)->num_rows();

        // cek apakah ada data dari username
        if ($cek > 0) {

            // mengambil data dari database berdasarkan username
            $query = $this->M_login->ambil_data($username);

            // variable array
            $result = array();
            $result['login'] = array();

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // dicek apakah data inputan sama dengan data di database
                if (password_verify($password, $row["password"])) {

                    // kumpulan data
                    $data = array(
                        'id_customer' => $row["id_customer"],
                        'nm_customer' => $row["nm_customer"],
                        'almt_customer' => $row["almt_customer"],
                        'jenkel_customer' => $row["jenkel_customer"],
                        'no_hp' => $row["no_hp"],
                        'email' => $row["email"]
                    );

                    array_push($result['login'], $data);

                    // membuat array untuk di transfer
                    $result["success"] = "1";
                    $result["message"] = "success";
                    $this->response($result, 200);
                } else {
                    // membuat array untuk di transfer ke API
                    $result["success"] = "0";
                    $result["message"] = "error password";
                    $this->response($result, 502);
                }
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error username";
            $this->response($result, 404);
        }
    }
}
