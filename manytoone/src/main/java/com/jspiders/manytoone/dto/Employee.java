package com.jspiders.manytoone.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

@Entity
@Data
@Table(name = "employee1_info")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false , unique = false)
	private String name;
	@Column(nullable = false , unique = true)
	private String email;
	@Column(nullable = false , unique = true)
	private long mobile;
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	Company company;
}