package com.web.repository;

import com.web.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {

    @Query("select g from GioHang g where g.user.id = ?1")
    List<GioHang> findByUser(Long userId);

    @Query("select g from GioHang g where g.user.id = ?1 and g.sanPham.id = ?2")
    Optional<GioHang> findByUserAndIdSp(Long userId, Long idSp);

    @Query("select count(g.id) from GioHang g where g.user.id = ?1")
    Long soLuongGh(Long userId);
}
