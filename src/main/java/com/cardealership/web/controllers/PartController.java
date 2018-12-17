package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.parts.CreatePartBindingModel;
import com.cardealership.domain.model.binding.parts.EditPartBindingModel;
import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;
import com.cardealership.domain.model.view.parts.PartViewModel;
import com.cardealership.domain.model.view.suppliers.SupplierForCreatingPartModel;
import com.cardealership.service.PartService;
import com.cardealership.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/parts")
public class PartController extends BaseController {

    private final SupplierService supplierService;

    private final PartService partService;

    private final ModelMapper modelMapper;

    public PartController(SupplierService supplierService, PartService partService, ModelMapper modelMapper) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView createPart(@ModelAttribute CreatePartBindingModel createPartBindingModel) {
        Set<SupplierForCreatingPartModel> suppliers = this.supplierService.findAll();
        return super.view("/views/parts/create", suppliers);
    }

    @PostMapping("/create")
    public ModelAndView confirmCreatePart(@ModelAttribute CreatePartBindingModel createPartBindingModel) {
        PartServiceModel partServiceModel = new PartServiceModel();

        SupplierServiceModel supplier = this.supplierService.findById(createPartBindingModel.getSupplierId());

        partServiceModel.setName(createPartBindingModel.getName());
        partServiceModel.setPrice(createPartBindingModel.getPrice());
        partServiceModel.setSupplier(supplier);

        this.partService.craete(partServiceModel);
        return super.redirect("/");
    }

    @GetMapping("/all")
    public ModelAndView allParts() {
        List<PartServiceModel> partServiceModels = this.partService.findAll();
        List<PartViewModel> partViewModels = new ArrayList<>();
        partServiceModels.forEach(serviceModel -> {
            PartViewModel partViewModel = this.modelMapper.map(serviceModel, PartViewModel.class);
            partViewModels.add(partViewModel);
        });
        return super.view("/views/parts/all", partViewModels);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPart(@PathVariable Long id) {
        PartServiceModel serviceModel = this.partService.findById(id);
        EditPartBindingModel bindingModel = this.modelMapper.map(serviceModel, EditPartBindingModel.class);
        bindingModel.setSupplierName(serviceModel.getSupplier().getName());
        return super.view("/views/parts/edit", bindingModel);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPartConfirm(@ModelAttribute EditPartBindingModel bindingModel) {
        PartServiceModel serviceModel = this.modelMapper.map(bindingModel, PartServiceModel.class);
        this.partService.edit(serviceModel);
        return super.redirect("/");
    }
}