package com.web.controller.admin;

import com.web.entity.ChiTietDonHang;
import com.web.entity.DonHang;
import com.web.entity.TrangThaiDonHang;
import com.web.enums.TrangThai;
import com.web.repository.DonHangRepository;
import com.web.repository.SanPhamRepository;
import com.web.repository.TrangThaiDonHangRepository;
import com.web.utils.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class DonHangAdmin {

    @Autowired
    private DonHangRepository donHangRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private TrangThaiDonHangRepository trangThaiDonHangRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @RequestMapping(value = {"/don-hang"}, method = RequestMethod.GET)
    public String sanPhamGet(Model model) {
        model.addAttribute("donHangList", donHangRepository.findAll());
        model.addAttribute("trangThaiList", TrangThai.values());
        return "admin/donhang";
    }

    @PostMapping("/cap-nhat-trang-thai")
    public String datHangKhongDangNhap(RedirectAttributes redirectAttributes, @RequestParam Long iddonhangupdate,
                                       @RequestParam TrangThai trangthaiupdate){
        DonHang donHang = donHangRepository.findById(iddonhangupdate).get();
        donHang.setTrangThai(trangthaiupdate);
        donHangRepository.save(donHang);
        TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang();
        trangThaiDonHang.setDonHang(donHang);
        trangThaiDonHang.setTrangThai(trangthaiupdate);
        trangThaiDonHang.setCreatedDate(LocalDateTime.now());
        trangThaiDonHang.setUser(userUtils.getUserWithAuthority());
        trangThaiDonHangRepository.save(trangThaiDonHang);
        redirectAttributes.addFlashAttribute("message", "Cập nhật trạng thái đơn hàng thành công!");
        return "redirect:don-hang";
    }


    @GetMapping("/delete-don-hang")
    public String deleteDonHang(RedirectAttributes redirectAttributes, @RequestParam("id") Long id){
        DonHang donHang = donHangRepository.findById(id).get();
        for(ChiTietDonHang c : donHang.getChiTietDonHangs()){
            c.getSanPham().setSoLuong(c.getSanPham().getSoLuong() + c.getSoLuong());
            sanPhamRepository.save(c.getSanPham());
        }
        donHangRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa đơn hàng thành công!");
        return "redirect:don-hang";
    }
}
