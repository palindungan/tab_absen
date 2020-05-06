<?php
class M_kode extends CI_Model
{
    // autogenerate kode / ID
    function get_id_pengajar()
    {
        $field = "id_pengajar";
        $tabel = "pengajar";
        $digit = "3";
        $kode = "PE";

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
            }
        } else {
            $kd = "PE001";
        }
        return $kd;
    }

    function get_id_murid()
    {
        $field = "id_murid";
        $tabel = "murid";
        $digit = "3";
        $kode = "MR";

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
            }
        } else {
            $kd = "MR001";
        }
        return $kd;
    }

    function get_id_wali_murid()
    {
        $field = "id_wali_murid";
        $tabel = "wali_murid";
        $digit = "3";
        $kode = "WM";

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
            }
        } else {
            $kd = "WM001";
        }
        return $kd;
    }

    function get_id_mata_pelajaran()
    {
        $field = "id_mata_pelajaran";
        $tabel = "mata_pelajaran";
        $digit = "2";
        $kode = "MP";

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
            }
        } else {
            $kd = "MP01";
        }
        return $kd;
    }
}
