package com.cardealership.domain.model.binding.users;

import com.cardealership.constants.Constants;
import com.cardealership.domain.model.binding.users.validation.validations.IsEmailRegistered;
import com.cardealership.domain.model.binding.users.validation.validations.IsPasswordMatching;
import com.cardealership.domain.model.binding.users.validation.validations.IsUsernameTaken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@IsPasswordMatching
public class CreateUserBindingModel {
    @Size(min = 4, max = 20, message = Constants.USERNAME_LENGTH)
    @IsUsernameTaken
    private String username;

    @Size(min = 4, max = 30, message = Constants.PASSWORD_LENGTH)
    private String password;

    private String confirmPassword;

    @NotEmpty(message = Constants.ENTER_VALID_EMAIL)
    @Email(message = Constants.ENTER_VALID_EMAIL)
    @IsEmailRegistered
    private String email;

    private boolean isAdmin;

    public CreateUserBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}