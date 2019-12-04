<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Kelas_pertemuan extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/admin/M_kelas_pertemuan");
    }

    function list_kelas_pertemuan_get()
    {
        // mengambil data dari database
        $query = $this->M_kelas_pertemuan->tampil_data('kelas_pertemuan');

        // variable array
        $result = array();
        $result['kelas_pertemuan'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_kelas_p' => $row["id_kelas_p"],
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'alamat' => $row["alamat"],
                    'no_hp' => $row["no_hp"]
                );

                array_push($result['kelas_pertemuan'], $data);

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

    function tambah_kelas_pertemuan_post()
    {
        // ambil data
        $id_kelas_p = $this->M_kelas_pertemuan->get_no();
        $id_pengajar = $this->post('id_pengajar');
        $id_mata_pelajaran = $this->post('id_mata_pelajaran');
        $hari = $this->post('hari');
        $jam_mulai = $this->post('jam_mulai');
        $jam_berakhir = $this->post('jam_berakhir');
        $harga_fee = $this->post('harga_fee');
        $id_sharing = "null";
        $nama_sharing = "kosong";

        $data = array(
            'id_kelas_p'   => $id_kelas_p,
            'id_pengajar'   => $id_pengajar,
            'id_mata_pelajaran'   => $id_mata_pelajaran,
            'hari'   => $hari,
            'jam_mulai'   => $jam_mulai,
            'jam_berakhir'   => $jam_berakhir,
            'harga_fee'   => $harga_fee,
            'id_sharing'   => $id_sharing,
            'nama_sharing'   => $nama_sharing,
        );

        $insert =  $this->M_kelas_pertemuan->input_data('kelas_pertemuan', $data);
        if ($insert) {

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

    function ambil_data_kelas_pertemuan_post()
    {
        $id_pengajar = $this->post('id_pengajar');

        // variable array
        $result = array();
        $result['list_kelas'] = array();

        $data_id = array(
            'id_pengajar' => $id_pengajar
        );

        // mengambil data dari database
        $query = $this->M_kelas_pertemuan->get_data('list_kelas', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_kelas_p' => $row["id_kelas_p"],
                    'hari' => $row["hari"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],
                    'harga_fee' => $row["harga_fee"],
                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_pelajaran' => $row["nama_pelajaran"],
                    'id_pengajar' => $row["id_pengajar"],
                    'nama_pengajar' => $row["nama_pengajar"],
                    'id_sharing' => $row["id_sharing"],
                    'nama_sharing' => $row["nama_sharing"]
                );

                array_push($result['list_kelas'], $data);

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

    function ambil_data_detail_kelas_pertemuan_post()
    {
        $id_kelas_p = $this->post('id_kelas_p');

        // variable array
        $result = array();
        $result['list_murid_by_kelas'] = array();

        $data_id = array(
            'id_kelas_p' => $id_kelas_p
        );

        // mengambil data dari database
        $query = $this->M_kelas_pertemuan->get_data('list_murid_by_kelas', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_detail_kelas_p' => $row["id_detail_kelas_p"],
                    'id_murid' => $row["id_murid"],
                    'nama' => $row["nama"],
                    'id_wali_murid' => $row["id_wali_murid"],
                    'nama_wali_murid' => $row["nama_wali_murid"],
                    'alamat' => $row["alamat"],
                    'foto' => $row["foto"],
                    'id_kelas_p' => $row["id_kelas_p"]
                );

                array_push($result['list_murid_by_kelas'], $data);

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

    function delete_kelas_pertemuan_post()
    {
        $id_kelas_p = $this->post('id');

        $where = array(
            'id_kelas_p' => $id_kelas_p
        );

        $hapus =  $this->M_kelas_pertemuan->hapus_data($where, "kelas_pertemuan");
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

    function delete_detail_kelas_pertemuan_post()
    {
        $id_detail_kelas_p = $this->post('id_detail_kelas_p');

        $where = array(
            'id_detail_kelas_p' => $id_detail_kelas_p
        );

        $hapus =  $this->M_kelas_pertemuan->hapus_data($where, "detail_kelas_pertemuan");
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
