<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Fee extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/admin/transaksi/M_fee");
    }

    function tambah_transaksi_post()
    {
        $id_penggajian = $this->M_fee->get_no_transaksi();
        $id_pengajar = $this->post('id_pengajar');
        $id_admin = $this->post('id_admin');
        $total_pertemuan = $this->post('total_pertemuan');
        $total_harga_fee = $this->post('total_harga_fee');
        $tanggal = date('Y-m-d H:i:s');

        $data = array(
            'id_penggajian' => $id_penggajian,
            'id_pengajar' => $id_pengajar,
            'id_admin' => $id_admin,
            'waktu' => $tanggal,
            'total_pertemuan' => $total_pertemuan,
            'total_harga_fee' => $total_harga_fee
        );

        $insert =  $this->M_fee->input_data('penggajian', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menambah Penggajian FEE";
            $result["id_penggajian"] =  $id_penggajian;
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menambah Penggajian FEE";
            $this->response(array($result, 200));
        }
    }

    function tambah_detail_transaksi_post()
    {
        $id_penggajian = $this->post('id_penggajian');
        $id_pertemuan = $this->post('id_pertemuan');

        $where = array(
            'id_pertemuan' => $id_pertemuan
        );

        $data_update = array(
            'status_fee' => 'Sudah Terbayar'
        );

        $update = $this->M_fee->update_data($where, 'pertemuan', $data_update,);

        if ($update) {

            $data = array(
                'id_penggajian' => $id_penggajian,
                'id_pertemuan' => $id_pertemuan
            );

            $insert =  $this->M_fee->input_data('detail_penggajian', $data);
            if ($insert) {

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Berhasil Menambah Detail Penggajian FEE";
                $this->response($result, 200);
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["message"] = "Gagal Menambah Detail Penggajian FEE";
                $this->response(array($result, 200));
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Update Data Pertemuan FEE";
            $this->response(array($result, 200));
        }
    }
}
