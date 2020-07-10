package com.sciencom.restful.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

//lombok annotations
@Data
@NoArgsConstructor

@Entity
@Table(name = "tbl_student")
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(length = 100,nullable = false)
	@NotEmpty(message = "Nama tidak boleh kosong")
	@NotBlank(message = "Nama tidak boleh kosong")
	private String name;
	
	@Min(value = 20,message = "Minimal Umur 20")
	@Max(value = 60,message = "Maximal Umur 60")
	private int age;
	
	//One To One Relation
	@OneToOne(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "student")
	private Contact contact;
	
	//One To many Relation
	@OneToMany(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "student")
	private List<Assignment> assignments;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tbl_student_subject",
		joinColumns = @JoinColumn(name="student_id",referencedColumnName = "id"),
		inverseJoinColumns =  @JoinColumn(name="subject_id",referencedColumnName = "id"))
	private List<Subject> subjects;
	
}
