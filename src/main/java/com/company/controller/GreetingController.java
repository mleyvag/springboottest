package com.company.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.service.GreetingService;

//@Controller
@RestController
public class GreetingController {

	private final GreetingService greetingService;

	public GreetingController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@RequestMapping("/greeting")
	public
//	@ResponseBody 
	String greeting() {
		return this.greetingService.greet();
	}

	@RequestMapping("/model")
	public GreetingModel getModel() {
		return new GreetingModel("1", "greet");
	}

	@RequestMapping("/models")
	public List<GreetingModel> getModels() {
		return Arrays.asList(
				new GreetingModel("1", "a"),
				new GreetingModel("2", "b"),
				new GreetingModel("3", "c"));
	}

}

class GreetingModel {
	private String id;
	private String description;

	public GreetingModel(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
