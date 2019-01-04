package com.cardealership.domain.model.view.sales;

import com.cardealership.domain.model.binding.sale.CreateSaleBindingModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleViewModel;

import java.util.List;

public class CreateSaleViewModel {
    private CreateSaleBindingModel createSaleBindingModel;

    private List<CarForCreatingSaleViewModel> carsForCreatingSaleList;

    private List<CustomerForCreatingSaleViewModel> customersForCreatingSaleList;

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

    public List<CustomerForCreatingSaleViewModel> getCustomersForCreatingSaleList() {
        return customersForCreatingSaleList;
    }

    public void setCustomersForCreatingSaleList(List<CustomerForCreatingSaleViewModel> customersForCreatingSaleList) {
        this.customersForCreatingSaleList = customersForCreatingSaleList;
    }
}