package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.suppliers.CreateSupplierBindingModel;
import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;
import com.cardealership.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/suppliers")
public class SupplierController extends BaseController {

    private final SupplierService supplierService;

    private final ModelMapper modelMapper;

    public SupplierController(SupplierService supplierService, ModelMapper modelMapper) {
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute CreateSupplierBindingModel createSupplierBindingModel) {
        return super.view("/views/suppliers/create", createSupplierBindingModel);
    }

    @PostMapping("/create")
    public ModelAndView confirmCreate(@ModelAttribute CreateSupplierBindingModel createSupplierBindingModel) {
        SupplierServiceModel supplierServiceModel = this.modelMapper.map(createSupplierBindingModel, SupplierServiceModel.class);
        this.supplierService.createSupplier(supplierServiceModel);
        return super.redirect("/");
    }
}