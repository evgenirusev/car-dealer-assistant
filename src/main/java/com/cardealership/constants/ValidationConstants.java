package com.cardealership.constants;

public class ValidationConstants {

    // USER
    public static final String USERNAME_LENGTH = "Username should be between 4 and 20 symbols";
    public static final String PASSWORD_LENGTH = "Password should be between 4 and 30 symbols";
    public static final String ENTER_VALID_EMAIL = "Email should be at least 3 symbols.";

    // CAR
    public static final String CAR_BRAND_LENGTH = "Brand should be between 2 and 40 symbols";
    public static final String CAR_MODEL_LENGTH = "Car model should be between 2 and 40 symbols";
    public static final String MINIMUM_AMOUNT_OF_CAR_PARTS = "Car should have atleast 1 part";

    // CUSTOMER
    public static final String CUSTOMER_NAME_LENGTH = "Customer name should be between 2 and 60 symbols";
    public static final String CUSTOMER_BIRTH_DATE = "Please specify customer birth date";

    // SUPPLIER
    public static final String SUPPLIER_NAME_LENGTH = "Supplier name should be between 2 and 60 symbols";

    // SALE
    public static final String CUSTOMER_REQUIRED = "Customer participating in the sale is required";
    public static final String CAR_REQUIRED = "Car to be sold required";
}
