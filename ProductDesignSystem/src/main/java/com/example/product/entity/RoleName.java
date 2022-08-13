package com.example.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="userrole",uniqueConstraints= {@UniqueConstraint(columnNames= {"name"})})
public class RoleName {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;

}
