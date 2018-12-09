package com.cardealership.service;

import com.cardealership.domain.entity.Customer;
import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
