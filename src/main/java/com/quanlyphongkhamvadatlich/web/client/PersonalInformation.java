package com.quanlyphongkhamvadatlich.web.client;

import com.quanlyphongkhamvadatlich.entity.Customer;
import com.quanlyphongkhamvadatlich.service.CutomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/client")
public class PersonalInformation {
    @Autowired
    private CutomerService cutomerService;
    @GetMapping("/personalinfo/{id}")
    public String personalinfo(Model model, @PathVariable Long id){
        Customer customer = cutomerService.getCustomerByUserId(id);
        model.addAttribute("customer", customer);
            return "client/pages/personalinfo";
    }
    @GetMapping("/personalinfo/edit")
    public String editpersonalinfo(){
        return "client/pages/editpersonalinfo";
    }
}
