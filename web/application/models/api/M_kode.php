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
}
