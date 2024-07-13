package com.my.user.controller;

import com.my.user.business.SCUserBusiness;
import com.my.user.model.SCUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SCUserBusiness scUserBusiness;

    @GetMapping
    public SCUser getExampleById() {
        return scUserBusiness.list();
    }


    @PostMapping
    public List<SCUser> createExample(@RequestBody String example) {
        return scUserBusiness.findAll();
//        return "Open feign Provider createExample " + example + " ,Success!!";
    }

    @DeleteMapping("/{id}")
    public String deleteExample(@PathVariable Long id) {
        return "Open feign Provider delete id " + id + " ,Success!!";
    }
}
