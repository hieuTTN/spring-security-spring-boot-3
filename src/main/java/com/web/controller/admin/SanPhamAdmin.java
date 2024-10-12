package com.web.controller.admin;

import com.web.entity.AnhSanPham;
import com.web.entity.DanhMuc;
import com.web.entity.SanPham;
import com.web.enums.Role;
import com.web.repository.AnhSanPhamRepository;
import com.web.repository.DanhMucRepository;
import com.web.repository.SanPhamRepository;
import com.web.repository.UserRepository;
import com.web.utils.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SanPhamAdmin {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private AnhSanPhamRepository anhSanPhamRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @RequestMapping(value = {"/san-pham"}, method = RequestMethod.GET)
    public String sanPhamGet(Model model) {
        model.addAttribute("sanPhamList", sanPhamRepository.findAll());
        return "admin/sanpham";
    }

    @RequestMapping(value = {"/add-san-pham"}, method = RequestMethod.GET)
    public String sanPhamAdd(Model model, @RequestParam(required = false) Long id) {
        if(id == null){
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("type", "add");
        }
        else{
            model.addAttribute("sanPham", sanPhamRepository.findById(id).get());
            model.addAttribute("type", "update");
        }
        model.addAttribute("danhMucList", danhMucRepository.findAll());
        return "admin/add-san-pham";
    }

    @PostMapping("/add-san-pham")
    public String postSanPham(@ModelAttribute SanPham sanPham, @RequestParam("imgbanner") MultipartFile imgbanner,
                              @RequestParam("listfile") List<MultipartFile> listfile) {
        String img = "";
        if(sanPham.getId() != null){
            SanPham ex = sanPhamRepository.findById(sanPham.getId()).get();
            img = ex.getAnh();
        }
        if(!imgbanner.isEmpty()){
            img = cloudinaryService.uploadFile(imgbanner);
        }
        sanPham.setAnh(img);
        SanPham result = sanPhamRepository.save(sanPham);
        List<String> list = cloudinaryService.uploadMultiFile(listfile);
        for(String s : list){
            AnhSanPham anhSanPham = new AnhSanPham();
            anhSanPham.setSanPham(result);
            anhSanPham.setLinkAnh(s);
            anhSanPhamRepository.save(anhSanPham);
        }
        return "redirect:add-san-pham?add-success=true";
    }


    @GetMapping("/delete-sanpham")
    public String deleteCategory(@RequestParam("id") Long id){
        sanPhamRepository.deleteById(id);
        return "redirect:san-pham?delete-success=true";
    }
}
