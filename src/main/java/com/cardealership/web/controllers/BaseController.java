package com.cardealership.web.controllers;

import org.springframework.web.servlet.ModelAndView;
abstract class BaseController {

    private static final String APPLICATION_TITLE = "Car Dealership";
    private static final String BASE_PAGE_LAYOUT = "layout";
    private static final String PROPERTY_VIEW_NAME = "viewName";
    private static final String PROPERTY_VIEW_MODEL = "viewModel";
    private static final String PROPERTY_TITLE = "title";
    private static final String REDIRECT_KEYWORD = "redirect:";

    final ModelAndView view(final String viewName, final Object viewModel, String title) {

        title = title == null ? APPLICATION_TITLE : title;

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(BASE_PAGE_LAYOUT);
        modelAndView.addObject(PROPERTY_VIEW_NAME, viewName);
        modelAndView.addObject(PROPERTY_VIEW_MODEL, viewModel);
        modelAndView.addObject(PROPERTY_TITLE, title);

        return modelAndView;
    }

    final ModelAndView view(final String viewName, final String title) {
        return this.view(viewName, null, title);
    }

    final ModelAndView view(final String viewName, final Object viewModel) {
        return this.view(viewName, viewModel, null);
    }

    final ModelAndView view(final String viewName) {
        return this.view(viewName, null, null);
    }

    final ModelAndView redirect(final String redirectUrl, final Object viewModel) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(PROPERTY_VIEW_MODEL, viewModel);
        modelAndView.setViewName(REDIRECT_KEYWORD + redirectUrl);
        return modelAndView;
    }

    final ModelAndView redirect(final String redirectUrl) {
        return this.redirect(redirectUrl, null);
    }
}
