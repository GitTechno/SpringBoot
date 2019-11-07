package com.nt.test.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nt.test.constants.AppConstants;
import com.nt.test.entity.UserEntity;
import com.nt.test.model.User;
import com.nt.test.properties.AppProperties;
import com.nt.test.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private AppProperties props;
	
	@Autowired
	private UserRepository userRepo;
    
	@RequestMapping(value="/registerUser")
	public String loadForm(Model model) {
	   User userObj=new User();
	   model.addAttribute("user",userObj);
	   return AppConstants.LOGICAL_VIEW_NAME;
	}
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public String registerForm(@ModelAttribute("user")User user,Model model) {
	   	System.out.println(user);
	   	//create entity class object
	   	UserEntity entity=new UserEntity();
	   	//copy model obj to entity obj
	   	BeanUtils.copyProperties(user, entity);
	   	//invoke String data predefine method
	   	UserEntity saveRepo=userRepo.save(entity);
	   	if(saveRepo.getUserId()!=null) {
	   		String msg=props.getMessages().get(AppConstants.REG_SUCC_MSG);
	   		System.out.println(props.getMessages());
	   		model.addAttribute(AppConstants.REG_SUCC_MSG,msg);
	   	}
	   	else {
	   		String msg=props.getMessages().get(AppConstants.REG_FAILURE_MSG);
	   	}
	   	return AppConstants.LOGICAL_VIEW_NAME;//logical view name
	}
	
	@RequestMapping(value="/viewUser")
	public String displayAllRecords(Model model,
			                        @RequestParam("pn")Integer currPage) {
		Integer pageSize=3;
		
		PageRequest pageReq=PageRequest.of(currPage-1,pageSize);
		
		//Page<UserEntity> userEntity= userRepo.findAll(pageReq);
		Page<UserEntity> userEntity= userRepo.getUserAfterSoftDelete(pageReq);
		List<UserEntity> userList=userEntity.getContent();
		
		int tp=userEntity.getTotalPages();
		List<User> listUser=new ArrayList();
		for(UserEntity entity:userEntity) {
			User user=new User();
			//copy
			BeanUtils.copyProperties(entity, user);
			listUser.add(user);
			System.out.println(user);
		}
		model.addAttribute("viewUser",listUser);
		model.addAttribute("tp",tp);
		model.addAttribute("cp",currPage);
		return "displayReg";
	}
	
	@RequestMapping(value="/viewUser1")
	public String displayAllRecords1(Model model) {
		Iterable<UserEntity> userEntity = userRepo.findAll();
		List<User> listUser=new ArrayList();
		for(UserEntity entity:userEntity) {
			User user=new User();
			//copy
			BeanUtils.copyProperties(entity, user);
			listUser.add(user);
			System.out.println(user);
		}
		model.addAttribute("viewUser",listUser);
		return "displayReg";
	}

	
/*	@RequestMapping(value="/deleteUser")
	public String deleteById(@RequestParam("userId")Integer userId) {
		userRepo.deleteById(userId);
		return "Redirect:registerUser";
	}*/
	
	@RequestMapping(value="/editUser")
	public String editUserById(Model model,
			            @RequestParam("userId") Integer userId) {
		//create model class Object
		User user=new User();
		//retrive data from db table
		Optional<UserEntity> optUser=userRepo.findById(userId);
		if(optUser.isPresent()) {
			//get data from entity class object
			UserEntity entity=optUser.get();
			//copy data from entity to model(user)
			BeanUtils.copyProperties(entity, user);
		}
		loadFormData(model);
		model.addAttribute("user",user);
		return "editUser";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Model model,
			             @ModelAttribute("user")User user) {
      UserEntity entity=new UserEntity();
      //copy model obj data to entity class obj
      BeanUtils.copyProperties(user, entity);
      //entity.setUserId(userId);
      userRepo.save(entity);
      model.addAttribute("msg","Updation Successful");
      return "editUser";
		
	}
	
	@RequestMapping(value="/allEmail")
	public @ResponseBody List<String> getAllUserEmail(){
	 return userRepo.getAllEmail();	
	}
	
	@RequestMapping(value="/getEmail")
	public @ResponseBody String getEmailById(@RequestParam("uid")Integer uid) {
		String email=userRepo.getUserById(uid);
		return email;
	}
	
	@RequestMapping(value="/deleteUser")
	public String softDeletedUser(@RequestParam("userId")Integer uid) {
		userRepo.updateDelete(uid);
		return"redirect:/viewUser?pn=1";
	}
	
	
	public static String loadFormData(Model model) {
		List<String> coutriesList=new ArrayList<String>();
		coutriesList.add("India");
		coutriesList.add("UK");
		coutriesList.add("Germany");
		coutriesList.add("USA");
		model.addAttribute("countriesList",coutriesList);
		return "userReg";
	}
}