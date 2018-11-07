package com.thinkgem.jeesite.modules.movies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.modules.postManeger.controller.MyBaseController;

@Controller
@RequestMapping(value = "${webPath}/movies/")
public class MovieController extends MyBaseController {
	@RequestMapping("make")
	public String moviesMake() {
         return "modules/movies/make";
	}
	
	@RequestMapping("audioTest")
	public String audioTest() {
		return "modules/movies/audio";
	}
}
