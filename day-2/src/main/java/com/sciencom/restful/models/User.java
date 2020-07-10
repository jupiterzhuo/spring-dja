package com.sciencom.restful.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name",length = 100)
	@NotNull
	private String firstName;
	
	@Column(name = "last_name",length = 100)
    private String lastName;
    
	@Column(name = "user_name",length = 50)
    @NotNull(message = "Username not Null")
	@NotEmpty(message = "username not empty")
    private String userName;
    
	@Column(name = "email",length = 100,unique = true)
    @Email(message = "Email Must Valid")
    private String email;
    
	@Column(name = "password",length = 100)
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_user_roles",
                joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
                inverseJoinColumns =@JoinColumn(name="role_id",referencedColumnName = "id"))
    private List<Role> roles;
	

}
