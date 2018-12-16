package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.suppliers.CreateSupplierBindingModel;
import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;
import com.cardealership.domain.model.view.suppliers.SuppliersByImporterViewModel;
import com.cardealership.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        return super.view("/views/suppliers/create");
    }

    @PostMapping("/create")
    public ModelAndView confirmCreate(@ModelAttribute CreateSupplierBindingModel createSupplierBindingModel) {
        SupplierServiceModel supplierServiceModel = this.modelMapper.map(createSupplierBindingModel, SupplierServiceModel.class);
        this.supplierService.create(supplierServiceModel);
        return super.redirect("/");
    }

    @GetMapping("/local")
    public ModelAndView localSuppliers() {
        Set<SupplierServiceModel> suppliers = this.supplierService.findAllByImporter(false);
        List<SuppliersByImporterViewModel> supplierViewModels = new ArrayList<>();
        suppliers.forEach(supplierServiceModel -> {
            supplierViewModels.add(this.modelMapper.map(supplierServiceModel, SuppliersByImporterViewModel.class));
        });
        return super.view("views/suppliers/all", supplierViewModels);
    }

    @GetMapping("/importers")
    public ModelAndView importerSuppliers() {
        Set<SupplierServiceModel> suppliers = this.supplierService.findAllByImporter(true);
        List<SuppliersByImporterViewModel> supplierViewModels = new ArrayList<>();
        suppliers.forEach(supplierServiceModel -> {
            supplierViewModels.add(this.modelMapper.map(supplierServiceModel, SuppliersByImporterViewModel.class));
        });
        return super.view("views/suppliers/all", supplierViewModels);
    }
}