package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.parts.CreatePartBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/parts")
public class PartController extends BaseController {

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute CreatePartBindingModel createPartBindingModel) {
        // TODO: Pass Set<Suppliers>
        return super.view("/parts/create");
    }
}