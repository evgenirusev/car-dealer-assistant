package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.suppliers.CreateSupplierBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/suppliers")
public class SupplierController extends BaseController {

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute CreateSupplierBindingModel createSupplierBindingModel) {
        return super.view("/views/suppliers/create", createSupplierBindingModel);
    }

    @PostMapping("/create")
    public ModelAndView confirmCreate(@ModelAttribute CreateSupplierBindingModel createSupplierBindingModel) {
        return super.redirect("/");
    }

}