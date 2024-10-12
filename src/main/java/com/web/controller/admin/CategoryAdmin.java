package com.web.controller.admin;

import com.web.entity.DanhMuc;
import com.web.entity.User;
import com.web.enums.Role;
import com.web.repository.DanhMucRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class CategoryAdmin {

    @Autowired
    private DanhMucRepository danhMucRepository;

    @RequestMapping(value = {"/danh-muc"}, method = RequestMethod.GET)
    public String danhMucGet(Model model) {
        model.addAttribute("danhMucList", danhMucRepository.findAllQuantity());
        model.addAttribute("danhMuc", new DanhMuc());
        return "admin/danhmuc";
    }

    @PostMapping("/danh-muc")
    public String postDanhMuc(@ModelAttribute DanhMuc danhMuc) {
        danhMucRepository.save(danhMuc);
        return "redirect:danh-muc?add-success=true";
    }


    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") Long id){
        danhMucRepository.deleteById(id);
        return "redirect:danh-muc?delete-success=true";
    }
}
