package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.web.enums.TrangThai;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "trang_thai_don_hang")
@Getter
@Setter
public class TrangThaiDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    @ManyToOne
    @JoinColumn(name = "nguoi_tao")
    private User user;

    @ManyToOne
    @JoinColumn(name = "don_hang_id")
    private DonHang donHang;
}
