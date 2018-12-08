package com.cardealership.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    private static final String LAYOUT_VIEW_NAME = "layout";
    private static final String DEFAULT_APP_TITLE = "Car Dealership";
    private static final String VIEW_NAME_TEMPLATE_LAYOUT_ATTRIBUTE = "viewName";
    private static final String TITLE_TEMPLATE_LAYOUT_ATTRIBUTE = "title";
    private static final String SPRING_REDIRECT_KETWORD = "redirect:";

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
        modelAndView.addObject(VIEW_NAME_TEMPLATE_LAYOUT_ATTRIBUTE , viewName);

        if (viewModel != null) {
            String viewModelName = String.valueOf(viewModel.getClass().getSimpleName().charAt(0)).toLowerCase() +
                    viewModel.getClass().getSimpleName().substring(1);
            modelAndView.addObject(viewModelName, viewModel);
        }

        title = title == null ? DEFAULT_APP_TITLE : title;

        modelAndView.addObject(TITLE_TEMPLATE_LAYOUT_ATTRIBUTE, title);
        return modelAndView;
    }

    public ModelAndView redirect(String redirectUrl) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(SPRING_REDIRECT_KETWORD + redirectUrl);

        return modelAndView;
    }
}
