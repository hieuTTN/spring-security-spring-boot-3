package com.web.controller.admin;

import com.web.entity.DanhMuc;
import com.web.entity.User;
import com.web.enums.Role;
import com.web.repository.DanhMucRepository;
import com.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class TaiKhoanAdmin {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/tai-khoan"}, method = RequestMethod.GET)
    public String danhMucGet(Model model, @RequestParam(value = "role", required = false) Role role) {
        if(role == null){
            model.addAttribute("taiKhoanList", userRepository.findAll());
        }
        else{
            model.addAttribute("taiKhoanList", userRepository.findByRole(role));
        }
        model.addAttribute("roleSelect",role);
        return "admin/taikhoan";
    }

    @PostMapping("/update-role")
    public String updateRole(@RequestParam Long idUser, @RequestParam Role role) {
        User user = userRepository.findById(idUser).get();
        user.setRole(role);
        userRepository.save(user);
        return "redirect:tai-khoan?update-role-success=true";
    }


    @GetMapping("/delete-user")
    public String deleteCategory(@RequestParam("id") Long id){
        userRepository.deleteById(id);
        return "redirect:tai-khoan?delete-success=true";
    }
}
