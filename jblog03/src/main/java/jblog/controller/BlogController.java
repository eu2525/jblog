package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jblog")
public class BlogController {

	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id) {
		// SiteVo를 받아서 해당 페이지를 렌더링해서 보내줌...?
		return "blog/blog-main";
	}
}
