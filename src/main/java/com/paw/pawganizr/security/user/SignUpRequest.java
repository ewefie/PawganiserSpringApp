package com.paw.pawganizr.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class SignUpRequest {
    @NotNull(message = "Name has to be provided")
    @Length(min = 2, message = "Name has to be at at least 2 character long")
    @ApiModelProperty(notes = "The user's name", example = "John", required = true)
    private String name;

    @NotNull(message = "Email address has to provided")
    @Email(message = "Invalid email address")
    @ApiModelProperty(notes = "The user's email address", example = "john@doe.com", required = true)
    private String email;

    @NotBlank(message = "Password has to be provided")
    @ApiModelProperty(notes = "The user's password", example = "Password1", required = true)
    private String password;

    @JsonIgnore
    @AssertTrue(message = "Password has to be at least 8 characters and contain at least one digit and one upper case letter")
    public boolean isPasswordValid() {
        return nonNull(password) && password.length() >= 8 &&
                password.chars()
                        .anyMatch(Character::isLowerCase) &&
                password.chars()
                        .anyMatch(Character::isDigit) &&
                password.chars()
                        .anyMatch(Character::isUpperCase);
    }
}
