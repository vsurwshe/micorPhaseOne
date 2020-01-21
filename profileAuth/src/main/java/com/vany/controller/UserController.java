package com.vany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.model.UserDet;
import com.vany.repository.UserRepository;

@RestController
public class UserController {
	

	
	@GetMapping(value = "/users")
	public String getUsers() {
		return "Hello User";
	}

}
