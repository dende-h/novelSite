package io.post.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.post.novel.service.NovelService;

@Controller
public class NovelController {
	
	@Autowired
	NovelService novelService;
	
	@GetMapping(value = "/top")
	public String topDislay(){
		return "top";
	}
	
}
