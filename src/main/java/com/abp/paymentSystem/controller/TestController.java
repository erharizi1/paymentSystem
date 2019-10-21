package com.abp.paymentSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	/*
	 * @RequestMapping("/about") public String showaboutpage() { return "about"; }
	 */
	@RequestMapping("/contact")
	public String showContactpage() {
		return "contact";
	}
//	@RequestMapping("/login5")
//		public String showLoginpage() {
//			return "login5";
//		}
	
}
