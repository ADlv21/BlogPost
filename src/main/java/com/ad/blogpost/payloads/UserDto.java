package com.ad.blogpost.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Minimum 2 letter Name")
    private String name;

    @Email(message = "Email address not valid")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 20, message = "Password must be between or equal to 8 and 20 characters")
    private String password;

    private String about;

}
