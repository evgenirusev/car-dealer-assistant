package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.suppliers.CreateSupplierBindingModel;
import com.cardealership.domain.model.binding.suppliers.EditSupplierBindingModel;
import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;
import com.cardealership.domain.model.view.suppliers.SuppliersByImporterViewModel;
import com.cardealership.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public ModelAndView confirmCreate(@Valid @ModelAttribute CreateSupplierBindingModel createSupplierBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("/views/suppliers/create");
        }

        SupplierServiceModel supplierServiceModel = this.modelMapper.map(createSupplierBindingModel, SupplierServiceModel.class);
        this.supplierService.create(supplierServiceModel);
        return super.redirect("/suppliers/local");
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

    @GetMapping("/edit/{id}")
    public ModelAndView editSupplier(@PathVariable long id) {
        SupplierServiceModel serviceModel = this.supplierService.findById(id);
        EditSupplierBindingModel bindingModel = this.modelMapper.map(serviceModel, EditSupplierBindingModel.class);
        return super.view("/views/suppliers/edit", bindingModel);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editSupplierContifrm(@ModelAttribute EditSupplierBindingModel bindingModel) {
        SupplierServiceModel serviceModel = this.modelMapper.map(bindingModel, SupplierServiceModel.class);
        this.supplierService.edit(serviceModel);
        if (serviceModel.isImporter()) {
            return super.redirect("/suppliers/importer");
        }
        return super.redirect("/suppliers/local");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        SupplierServiceModel serviceModel = this.supplierService.findById(id);
        EditSupplierBindingModel bindingModel = this.modelMapper.map(serviceModel, EditSupplierBindingModel.class);
        return super.view("views/suppliers/delete", bindingModel);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView confirmDelte(@PathVariable("id") Long id) {
        this.supplierService.deleteById(id);
        return super.redirect("/");
    }
}