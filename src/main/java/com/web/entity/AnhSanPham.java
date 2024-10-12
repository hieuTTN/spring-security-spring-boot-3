package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "anh_san_pham")
@Getter
@Setter
public class AnhSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String linkAnh;

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    @JsonBackReference
    private SanPham sanPham;
}
