package com.cardealership.service;

import com.cardealership.domain.entity.Customer;
import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;
import com.cardealership.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCustomer(CustomerServiceModel customerServiceModel) {
        Customer customer = new Customer();
        LocalDate customerBirthDate = LocalDate.parse(customerServiceModel.getBirthDate());
        customer.setName(customerServiceModel.getName());
        customer.setBirthDate(customerBirthDate);
        customer.setDriverYoung(LocalDate.now().getYear() - customerBirthDate.getYear() < 19);
        this.customerRepository.save(customer);
    }

    @Override
    public List<CustomerViewModel> getAllCustomersOrderByBirthDateAscendingOrder() {
        List<CustomerViewModel> customerViewModels = new ArrayList<>();
        this.customerRepository.getCustomersByOrderByBirthDateAsc().forEach(
                customer -> {
                    customerViewModels.add(this.modelMapper.map(customer, CustomerViewModel.class));
                }
        );
        return customerViewModels;
    }

    @Override
    public List<CustomerViewModel> getAllCustomersOrderByBirthDateDescendingOrder() {
        List<CustomerViewModel> customerViewModels = new ArrayList<>();
        this.customerRepository.getCustomersByOrderByBirthDateDesc().forEach(
                customer -> {
                    customerViewModels.add(this.modelMapper.map(customer, CustomerViewModel.class));
                }
        );
        return customerViewModels;
    }

    @Override
    public List<CustomerForCreatingSaleModel> findAllCustomersForCreatingSale() {
        List<Customer> customerEntities = this.customerRepository.findAll();
        List<CustomerForCreatingSaleModel> customerModels = new ArrayList<>();
        customerEntities.forEach(customerEntity -> {
            CustomerForCreatingSaleModel customerModel
                    = this.modelMapper.map(customerEntity, CustomerForCreatingSaleModel.class);
            customerModels.add(customerModel);
        });
        return customerModels;
    }
}