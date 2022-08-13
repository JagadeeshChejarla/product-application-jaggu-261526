package com.example.product.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="productusers", uniqueConstraints= {@UniqueConstraint(columnNames= {"username"}),
                               @UniqueConstraint(columnNames= {"email"})

})
public class ProductUser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String username;
	private String email;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="productuser_roles",
			joinColumns= @JoinColumn(name="user_id",referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id"))
	private Set<RoleName> roles;
	

}
