<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Absen extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/pengajar/M_absen");
    }

    function tambah_absen_post()
    {
        // ambil data
        $id_pertemuan = $this->M_absen->get_no();
        $id_pengajar = $this->post('id_pengajar');
        $id_kelas_p = $this->post('id_kelas_p');

        $hari = "Senin"; // ambil hari ini
        $waktu_mulai = "09:00";
        $waktu_berakhir = "09:30";

        $lokasi_mulai_la = $this->post('lokasi_mulai_la');
        $lokasi_mulai_lo = $this->post('lokasi_mulai_lo');

        $lokasi_berakhir_la = "null";
        $lokasi_berakhir_lo = "null";

        $status_fee = "Belum Terbayar";
        $status_spp = "Belum Lunas";
        $status_pertemuan = "Belum Selesai";
        $status_konfirmasi = "Invalid";

        $deskripsi = "kosong";

        $data = array(
            'id_pertemuan'   => $id_pertemuan,
            'id_pengajar'   => $id_pengajar,
            'id_kelas_p'   => $id_kelas_p,
            'hari'   => $hari,
            'waktu_mulai'   => $waktu_mulai,
            'waktu_berakhir'   => $waktu_berakhir,
            'lokasi_mulai_la'   => $lokasi_mulai_la,
            'lokasi_mulai_lo'   => $lokasi_mulai_lo,
            'lokasi_berakhir_la'   => $lokasi_berakhir_la,
            'lokasi_berakhir_lo'   => $lokasi_berakhir_lo,
            'status_fee'   => $status_fee,
            'status_spp'   => $status_spp,
            'status_pertemuan'   => $status_pertemuan,
            'status_konfirmasi'   => $status_konfirmasi,
            'deskripsi'   => $deskripsi
        );

        $insert =  $this->M_absen->input_data('pertemuan', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "success";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error";
            $this->response(array($result, 200));
        }
    }
}
