<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Admin extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/home/M_admin");
    }

    function list_count_get()
    {

        // variable array
        $result = array();
        $result['data'] = array();

        // mengambil data dari database
        $pengajar = $this->M_admin->count_num_row('pengajar');
        $murid = $this->M_admin->count_num_row('murid');
        $wali_murid = $this->M_admin->count_num_row('wali_murid');
        $mata_pelajaran = $this->M_admin->count_num_row('mata_pelajaran');

        // ambil detail data db
        $data = array(
            'pengajar'          => $pengajar,
            'murid'              => $murid,
            'wali_murid'        => $wali_murid,
            'mata_pelajaran'    => $mata_pelajaran
        );

        array_push($result['data'], $data);

        // membuat array untuk di transfer
        $result["success"] = "1";
        $result["message"] = "success berhasil mengambil data";
        $this->response($result, 200);
    }
}
