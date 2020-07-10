package com.sciencom.restful.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class UserDTO {

	private String firstName;
	private String lastName;
    @NotNull(message = "Username not Null")
	@NotEmpty(message = "username not empty")
	private String userName;
    @NotNull(message = "Email not Null")
	@NotEmpty(message = "Email not empty")
	//@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$",message = "Email Must Valid")
	@Email(message = "Email Not Valid")
    private String email;
}
