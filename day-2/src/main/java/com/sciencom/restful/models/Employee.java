package com.sciencom.restful.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "tbl_employee")
@IdClass(Employee.idClassEmployee.class)
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String employeeId;
	@Id
	private String companyId;
	private String name;
	
	@Data
	static class idClassEmployee implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String employeeId;
		private String companyId;
	}
}
