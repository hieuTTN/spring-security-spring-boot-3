package com.web.controller.user;

import com.web.entity.ChiTietDonHang;
import com.web.entity.DonHang;
import com.web.entity.SanPham;
import com.web.entity.TrangThaiDonHang;
import com.web.enums.LoaiThanhToan;
import com.web.enums.TrangThai;
import com.web.repository.ChiTietDonHangRepository;
import com.web.repository.DonHangRepository;
import com.web.repository.SanPhamRepository;
import com.web.repository.TrangThaiDonHangRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Controller
public class DonHangController {

    @Autowired
    private DonHangRepository donHangRepository;

    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private TrangThaiDonHangRepository trangThaiDonHangRepository;

    @PostMapping("/dat-hang-khong-dang-nhap")
    public String datHangKhongDangNhap(HttpServletRequest request, @RequestParam Long id, @RequestParam Integer soluong,
                                       @RequestParam String fullName, @RequestParam String phone, @RequestParam String address,
                                       @RequestParam String ghichudonhang){
        SanPham sanPham = sanPhamRepository.findById(id).get();
        DonHang donHang = new DonHang();
        donHang.setDiaChi(address);
        donHang.setGhiChu(ghichudonhang);
        donHang.setGioTao(new Time(System.currentTimeMillis()));
        donHang.setTenKhachHang(fullName);
        donHang.setLoaiThanhToan(LoaiThanhToan.DAT_HANG_KHONG_DANG_NHAP);
        donHang.setNgayTao(new Date(System.currentTimeMillis()));
        donHang.setSoDienThoai(phone);
        donHang.setTongTien(sanPham.getGia() * soluong);
        donHang.setTrangThai(TrangThai.DANG_CHO_XAC_NHAN);
        donHangRepository.save(donHang);

        ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
        chiTietDonHang.setDonHang(donHang);
        chiTietDonHang.setGiaTien(sanPham.getGia());
        chiTietDonHang.setSanPham(sanPham);
        chiTietDonHang.setSoLuong(soluong);
        chiTietDonHangRepository.save(chiTietDonHang);

        sanPham.setSoLuong(sanPham.getSoLuong() - soluong);
        sanPhamRepository.save(sanPham);

        TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang();
        trangThaiDonHang.setDonHang(donHang);
        trangThaiDonHang.setCreatedDate(LocalDateTime.now());
        trangThaiDonHang.setTrangThai(TrangThai.DANG_CHO_XAC_NHAN);
        trangThaiDonHangRepository.save(trangThaiDonHang);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer+"&dat-hang-thanh-cong=true";
    }
}
