package com.cardealership.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    private static final String LAYOUT_VIEW_NAME = "layout";
    private static final String DEFAULT_APP_TITLE = "Car Dealer";
    private static final String KEY_VIEW_NAME = "viewName";
    private static final String KEY_TITLE_NAME = "title";

    protected BaseController() {
    }

    public ModelAndView view(String viewName, Object viewModel, String title) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LAYOUT_VIEW_NAME);
        modelAndView.addObject(KEY_VIEW_NAME, viewName);
        title = title == null ? DEFAULT_APP_TITLE : title;
        modelAndView.addObject(KEY_TITLE_NAME, title);
        return modelAndView;
    }

    public ModelAndView view(String viewName) {
        return this.view(viewName, null, null);
    }

    public ModelAndView view(String viewName, Object viewModel) {
        return this.view(viewName, viewModel, null);
    }

    public ModelAndView redirect(String redirectUrl) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:" + redirectUrl);

        return modelAndView;
    }
}