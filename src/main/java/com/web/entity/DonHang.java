package com.web.entity;

import com.web.enums.LoaiThanhToan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "don_hang")
@Getter
@Setter
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Date ngayTao;

    private Time gioTao;

    private Double tongTien;

    private String tenKhachHang;

    private String soDienThoai;

    private String diaChi;

    private String ghiChu;

    @Enumerated(EnumType.STRING)
    private LoaiThanhToan loaiThanhToan;

    @ManyToOne
    @JoinColumn(name = "nguoi_tao")
    private User user;


}
