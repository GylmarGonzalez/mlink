package com.mlink.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestC {

    @GetMapping("/")
	public String index() {
		return "Test app Mlink ";
	}
    
}
