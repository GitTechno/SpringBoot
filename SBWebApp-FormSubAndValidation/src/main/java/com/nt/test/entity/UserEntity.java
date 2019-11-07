package com.nt.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Employe1")
public class UserEntity {
    
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Integer userId;
	@Column(name="USER_NAME")
	private String username;
	@Column(name="PWD")
	private String password;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PHNO")
	private long phno;
	@Column(name="COUNTRIES")
	private String[] countries;
	@Column(name="STATUS")
	private Boolean deletedFlag=true;
	
}
