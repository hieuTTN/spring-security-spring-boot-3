package com.web.entity;

import com.web.enums.LoaiThanhToan;
import com.web.enums.TrangThai;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

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
    private TrangThai trangThai;

    @Enumerated(EnumType.STRING)
    private LoaiThanhToan loaiThanhToan;

    @ManyToOne
    @JoinColumn(name = "nguoi_tao")
    private User user;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.REMOVE)
    private List<ChiTietDonHang> chiTietDonHangs;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.REMOVE)
    private List<TrangThaiDonHang> trangThaiDonHangs;
}
