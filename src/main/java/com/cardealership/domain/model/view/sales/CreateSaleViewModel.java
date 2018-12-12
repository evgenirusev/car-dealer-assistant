package com.cardealership.domain.model.view.sales;

import com.cardealership.domain.model.binding.sale.CreateSaleBindingModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;

import java.util.List;

public class CreateSaleViewModel {
    private CreateSaleBindingModel createSaleBindingModel;

    private List<CarForCreatingSaleViewModel> carsForCreatingSaleList;

    private List<CustomerForCreatingSaleModel> customersForCreatingSaleList;

    public CreateSaleViewModel() {
    }

    public CreateSaleBindingModel getCreateSaleBindingModel() {
        return createSaleBindingModel;
    }

    public void setCreateSaleBindingModel(CreateSaleBindingModel createSaleBindingModel) {
        this.createSaleBindingModel = createSaleBindingModel;
    }

    public List<CarForCreatingSaleViewModel> getCarsForCreatingSaleList() {
        return carsForCreatingSaleList;
    }

    public void setCarsForCreatingSaleList(List<CarForCreatingSaleViewModel> carsForCreatingSaleList) {
        this.carsForCreatingSaleList = carsForCreatingSaleList;
    }

    public List<CustomerForCreatingSaleModel> getCustomersForCreatingSaleList() {
        return customersForCreatingSaleList;
    }

    public void setCustomersForCreatingSaleList(List<CustomerForCreatingSaleModel> customersForCreatingSaleList) {
        this.customersForCreatingSaleList = customersForCreatingSaleList;
    }
}