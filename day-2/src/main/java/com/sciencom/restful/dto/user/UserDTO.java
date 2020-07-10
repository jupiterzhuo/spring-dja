package com.sciencom.restful.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserDTO {
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
}
