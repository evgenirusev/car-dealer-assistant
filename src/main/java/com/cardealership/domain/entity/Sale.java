package com.cardealership.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount")
    private double discount;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    private Car car;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}