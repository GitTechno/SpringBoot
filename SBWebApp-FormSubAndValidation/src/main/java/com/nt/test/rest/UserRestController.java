package com.nt.test.rest;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.test.entity.UserEntity;
import com.nt.test.model.User;
import com.nt.test.repository.UserRepository;

@RestController
public class UserRestController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping(value="/get",produces= {"application/json","application/xml"})
	public User getUserById(@RequestParam("uid")Integer uid) {
	   //create model class obj
		User user=new User();
		//get data
		Optional<UserEntity> optEntity = userRepo.findById(uid);
        UserEntity userEntity = optEntity.get();
        //copy entity to model
        BeanUtils.copyProperties(userEntity, user);
        return user;
	}

}
