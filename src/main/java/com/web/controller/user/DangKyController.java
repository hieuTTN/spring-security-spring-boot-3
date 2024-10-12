package com.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DangKyController {

    @RequestMapping(value = {"/dang-ky"}, method = RequestMethod.GET)
    public String addblog() {
        return "user/regis";
    }
}
