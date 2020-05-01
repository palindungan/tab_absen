<?php
class M_universal extends CI_Model
{
    function tampil_data($table)
    {
        $this->db->limit(60);  // Produces: LIMIT
        return $this->db->get($table);
    }

    function tampil_data_order_by($table, $kolom, $by)
    {
        $this->db->limit(60);  // Produces: LIMIT
        $this->db->order_by($kolom, $by);
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
        $this->db->limit(60);  // Produces: LIMIT
        return $this->db->get_where($table, $where);
    }

    function get_data_group_by($table, $where, $group_by, $order_by)
    {
        $this->db->limit(60);  // Produces: LIMIT
        $this->db->group_by($group_by);
        $this->db->order_by($order_by, "DESC");
        return $this->db->get_where($table, $where);
    }

    function update_data($where, $table, $data)
    {
        $this->db->where($where);
        $status = $this->db->update($table, $data);
        return $status;
    }
}
