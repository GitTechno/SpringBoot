package com.nt.test.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
   
	private Integer userId;
	private String username;
	private String password;
	private String email;
	private long phno;
	private String[] countries;
	private Boolean deletedFlag=true;

}
