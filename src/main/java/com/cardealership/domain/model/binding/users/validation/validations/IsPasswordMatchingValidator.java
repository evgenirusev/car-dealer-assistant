package com.cardealership.domain.model.binding.users.validation.validations;

import com.cardealership.domain.model.binding.users.CreateUserBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching,Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof CreateUserBindingModel){
            CreateUserBindingModel userModel = (CreateUserBindingModel) object;
            return userModel.getPassword().equals(userModel.getConfirmPassword());
        }
        return false;
    }
}
