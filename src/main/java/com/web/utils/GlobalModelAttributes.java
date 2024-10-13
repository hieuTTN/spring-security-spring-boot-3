package com.web.utils;

import com.web.entity.User;
import com.web.repository.DanhMucRepository;
import com.web.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalModelAttributes {

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private UserUtils userUtils;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("danhMucListGlobal", danhMucRepository.findAll());
        model.addAttribute("soLuongGhGlobal", 0);
        User user = userUtils.getUserWithAuthority();
        if (user != null){
            model.addAttribute("soLuongGhGlobal", gioHangRepository.soLuongGh(user.getId()));
        }
    }
}
