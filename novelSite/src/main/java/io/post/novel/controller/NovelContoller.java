package io.post.novel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NovelContoller {

	@GetMapping("/novel/write")
	public String toNovelPage() {
		
		return "novel/new_novel";
	}
	
}
