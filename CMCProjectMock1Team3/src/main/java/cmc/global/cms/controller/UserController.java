package cmc.global.cms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import  cmc.global.cms.entity.User;
import  cmc.global.cms.service.impl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@PostMapping("/user/add")
	public User addUser(@RequestBody User user) {
		if(!"".equals(user.getFullName()) || !"".equals(user.getEmail()) || !"".equals(user.getMobile())) {
			userService.save(user);
			return user;
		}
		return user;
	}
	
	@PutMapping("/user/edit/{id}")
	public User editUser(@RequestBody User user) {
		if(!"".equals(user.getFullName()) || !"".equals(user.getEmail()) || !"".equals(user.getMobile())) {
			userService.save(user);
			return user;
		}
		return user;
	}
	
	@DeleteMapping("/user/delete/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		 userService.deleteUser(id);
	}
	
	@GetMapping("/user")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> findById(@PathVariable("id") Integer id){
		return userService.findById(id);
		
	}
	
	@GetMapping("/user/search/{keyword}")
	public List<User> findByUser_F_E_S(@PathVariable("keyword") String keyword){
		if("undefined".equals(keyword)) {
			userService.findAll();
		}else {
			return userService.findByUser_F_E_S(keyword);
		}
		return userService.findAll();
	}
	
	@GetMapping("/user/filter/{fullname}/{email}/{status}")
	public List<User> filterUserByF_E_S(@PathVariable("fullname") String fullname,@PathVariable("email") String email,@PathVariable("status") int status){
		if("undefined".equals(fullname) || "undefined".equals(email) || (status <0 && status >2)) {
			return userService.findAll();
		}else {
			return userService.filterUserByF_E_S(fullname, email, status);
		}
		
	}
	
	@GetMapping("/user/filter_F_E/{fullname}/{email}")
	public List<User> filterUserByF_E(@PathVariable("fullname") String fullname,@PathVariable("email") String email){
		return userService.filterUserByF_E(fullname, email);
	}
	
	@GetMapping("/user/{fullname}/{status}")
	public List<User> filterUserByF_S(@PathVariable("fullname") String fullname,@PathVariable("status") int status){
		return userService.filterUserByF_S(fullname,status);
	}
	
	@GetMapping("/user/{email}/{status}")
	public List<User> filterUserByE_S(@PathVariable("email") String email,@PathVariable("status") int status){
		return userService.filterUserByE_S(email,status);	
	}
	
}
