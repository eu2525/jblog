package jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.PostService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}") // 정규 표현식을 통해서 assets/경로를 살림
public class BlogController {
	private BlogService blogService;
	private CategoryService categoryService;
	private PostService postService;
	
	public BlogController(BlogService blogService, CategoryService categoryService, PostService postService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
	}
	
	@RequestMapping({"", "/{path1}" , "/{path1}/{path2}" })
	public String main(
			@PathVariable("id") String id,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2,
			Model model
			) {
		Long categoryId = 0L;
		Long postId = 0L;
		
		if(path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if (path1.isPresent()) {
			categoryId = path1.get();
		}
				
		// Blog 
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		// Category List
		List<CategoryVo> categoryList = categoryService.getCategoryByUserId(id);
		model.addAttribute("categoryList", categoryList);
		// Post List
		List<PostVo> postList = postService.getPostList(id, categoryId);
		model.addAttribute("postList", postList);
		// MAIN글 
		PostVo mainPost = postService.getPost(id, categoryId, postId);
		model.addAttribute("mainPost", mainPost);
		
		return "blog/main";
	}
	
	// @Auth
	@RequestMapping("/admin")
	public String adminDefault(@PathVariable("id") String id) {
		// SiteVo를 받아서 해당 페이지를 렌더링해서 보내줌...?
		return "blog/admin-basic";
	}
	
	
}
