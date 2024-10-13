package com.web.controller.user;

import com.web.entity.*;
import com.web.repository.GioHangRepository;
import com.web.repository.SanPhamRepository;
import com.web.utils.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class GioHangController {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private UserUtils userUtils;

    @RequestMapping(value = {"/gio-hang"}, method = RequestMethod.GET)
    public String gioHang(Model model) {
        List<GioHang> list = gioHangRepository.findByUser(userUtils.getUserWithAuthority().getId());
        Double d = 0D;
        for(GioHang g : list){
            d += g.getSoLuong() * g.getSanPham().getGia();
        }
        model.addAttribute("gioHangList",list);
        model.addAttribute("tongTien",d);
        return "user/giohang";
    }

    @PostMapping("/add-gio-hang")
    public String add(RedirectAttributes redirectAttributes, HttpServletRequest request,
                      @RequestParam Integer soLuong, @RequestParam Long id){
        User user = userUtils.getUserWithAuthority();
        Optional<GioHang> gioHang = gioHangRepository.findByUserAndIdSp(user.getId(), id);
        SanPham sanPham = sanPhamRepository.findById(id).get();
        if(gioHang.isPresent()){
            redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công!");
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        GioHang gh = new GioHang();
        gh.setSoLuong(soLuong);
        gh.setSanPham(sanPham);
        gh.setUser(user);
        gioHangRepository.save(gh);
        redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công!");
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/update-sl")
    public String upDateSl(RedirectAttributes redirectAttributes, HttpServletRequest request,
                      @RequestParam Integer soLuong, @RequestParam Long id){
        GioHang gioHang = gioHangRepository.findById(id).get();
        gioHang.setSoLuong(gioHang.getSoLuong() + soLuong);
        if(gioHang.getSoLuong() == 0){
            gioHangRepository.delete(gioHang);
        }
        else {
            gioHangRepository.save(gioHang);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/delete-giohang")
    public String deleteDonHang(RedirectAttributes redirectAttributes, @RequestParam("id") Long id){
        gioHangRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa giỏ hàng thành công!");
        return "redirect:gio-hang";
    }
}
