<?php
class M_absen extends CI_Model
{
    function tampil_data($table)
    {
        return $this->db->get($table);
    }

    function input_data($table, $data)
    {
        $status = $this->db->insert($table, $data);
        return $status;
    }

    function hapus_data($where, $table)
    {
        $this->db->where($where);
        $status = $this->db->delete($table);
        return $status;
    }

    function get_data($table, $where)
    {
        return $this->db->get_where($table, $where);
    }

    function update_data($where, $table, $data)
    {
        $this->db->where($where);
        $status = $this->db->update($table, $data);
        return $status;
    }

    // autogenerate kode / ID
    // PT191218-0001
    function get_no()
    {
        $field = "id_pertemuan";
        $tabel = "pertemuan";
        $digit = "4";
        $ymd = date('ymd');

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel WHERE SUBSTR($field, 3, 6) = $ymd LIMIT 1");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = sprintf('%0' . $digit . 's',  $tmp); // 0 berapa kali + $tmp
            }
        } else {
            $kd = "0001";
        }
        date_default_timezone_set('Asia/Jakarta');
        return 'PT' . date('ymd') . '-' . $kd; // SELECT SUBSTR('PT191218-0001', 3, 6); dari digit ke 3 sampai 6 digit seanjutnya
    }

    function get_no2()
    {
        $field = "id_penggajian";
        $tabel = "penggajian";
        $digit = "4";
        $ymd = date('ymd');

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel WHERE SUBSTR($field, 3, 6) = $ymd LIMIT 1");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = sprintf('%0' . $digit . 's',  $tmp); // 0 berapa kali + $tmp
            }
        } else {
            $kd = "0001";
        }
        date_default_timezone_set('Asia/Jakarta');
        return 'PG' . date('ymd') . '-' . $kd; // SELECT SUBSTR('PT191218-0001', 3, 6); dari digit ke 3 sampai 6 digit seanjutnya
    }
}
