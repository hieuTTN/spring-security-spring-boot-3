package com.web.api;

import com.web.repository.AnhSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AnhSanPhamApi {


    @Autowired
    private AnhSanPhamRepository anhSanPhamRepository;

    @DeleteMapping("/admin/delete-anh-san-pham")
    public void deleteAnhSp(@RequestParam("id") Long id){
        anhSanPhamRepository.deleteById(id);
    }
}
