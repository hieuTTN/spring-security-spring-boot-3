package com.web.controller.user;

import com.web.entity.SanPham;
import com.web.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SanPhamUserController {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String home(Model model, Pageable pageable) {
        Page<SanPham> page = sanPhamRepository.findAll(pageable);
        model.addAttribute("sanPhamList", page.getContent());
        model.addAttribute("tongSoTrang", page.getTotalPages());
        model.addAttribute("pageable", pageable);
        return "user/index";
    }

    @RequestMapping(value = { "/chitietsanpham"}, method = RequestMethod.GET)
    public String chiTietSanPham(Model model, @RequestParam(required = false) Long id) {
        if(id == null){
            return "redirect:/index";
        }
        model.addAttribute("sanPham", sanPhamRepository.findById(id).get());
        return "user/chitietsanpham";
    }

    @RequestMapping(value = { "/muangay"}, method = RequestMethod.GET)
    public String muaNgay(Model model, @RequestParam(required = false) Long id) {
        if(id == null){
            return "redirect:/index";
        }
        model.addAttribute("sanPham", sanPhamRepository.findById(id).get());
        return "user/muangay";
    }
}
