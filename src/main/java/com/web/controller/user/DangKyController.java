package com.web.controller.user;

import com.web.entity.User;
import com.web.enums.Role;
import com.web.repository.UserRepository;
import com.web.validate.RegisValidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Controller
public class DangKyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisValidate regisValidate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = {"/dang-ky"}, method = RequestMethod.GET)
    public String addblog(Model model) {
        model.addAttribute("user", new User());
        return "user/regis";
    }

    @PostMapping("/dang-ky")
    public String regisUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        System.out.println(user.getEmail());
        regisValidate.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/regis";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActived(true);
        user.setRole(Role.ROLE_USER);
        User result = userRepository.save(user);
        return "redirect:/dang-ky?success=true";
    }
}
