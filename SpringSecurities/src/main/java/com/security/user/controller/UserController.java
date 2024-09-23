package com.security.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class UserController {
	

	@GetMapping("/normal")
	public ResponseEntity<String>  normalUser()
	{
		return ResponseEntity.ok("Yes I amd Normal Person here !..........");
	}
	
	@GetMapping("/admin")
	public ResponseEntity<String>  adminUser()
	{
		return ResponseEntity.ok("Yeas ia amd admin here .....");
	}
	
	@GetMapping("/public")
	public ResponseEntity<String>  publicUser()
	{
		return ResponseEntity.ok("Yeas ia amd..public here  ...");
	}
	
	
	
	

}
