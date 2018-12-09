package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.parts.CreatePartBindingModel;
import com.cardealership.domain.model.view.suppliers.SupplierForCreatingPartModel;
import com.cardealership.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/parts")
public class PartController extends BaseController {

    private final SupplierService supplierService;

    public PartController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute CreatePartBindingModel createPartBindingModel) {
        Set<SupplierForCreatingPartModel> suppliers = this.supplierService.findAllSuppliers();
        return super.view("/parts/create", suppliers);
    }
}