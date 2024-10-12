package com.web.repository;

import com.web.dto.DanhMucDto;
import com.web.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {

    @Query(value = "select c.id, c.ten, \n" +
            "(SELECT count(s.id) from san_pham s where s.danh_muc_id = c.id) as quantity from danh_muc c", nativeQuery = true)
    public List<DanhMucDto> findAllQuantity();
}
