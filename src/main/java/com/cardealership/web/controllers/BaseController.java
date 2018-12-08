package com.cardealership.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    private static final String LAYOUT_VIEW_NAME = "layout";

    protected BaseController() {
    }

    public ModelAndView view(String viewName) {
        return this.view(viewName, null, null);
    }

    public ModelAndView view(String viewName, Object viewModel) {
        return this.view(viewName, viewModel, null);
    }

    public ModelAndView view(String viewName, Object viewModel, String title) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LAYOUT_VIEW_NAME);

        if (viewModel != null) {
            modelAndView.addObject("viewName", viewName);
            String viewModelName = String.valueOf(viewModel.getClass().getSimpleName().charAt(0)).toLowerCase() +
                    viewModel.getClass().getSimpleName().substring(1);
            modelAndView.addObject(viewModelName, viewModel);
        }

        title = title == null ? "Car dealership" : title;

        modelAndView.addObject("title", title);
        return modelAndView;
    }

    public ModelAndView redirect(String redirectUrl) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:" + redirectUrl);

        return modelAndView;
    }
}
