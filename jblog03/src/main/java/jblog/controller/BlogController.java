package jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jblog.security.Auth;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.FileUploadService;
import jblog.service.PostService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/{userId:(?!assets).*}") // 정규 표현식을 통해서 assets/경로를 살림
public class BlogController {
	private BlogService blogService;
	private CategoryService categoryService;
	private PostService postService;
	private FileUploadService fileUploadService;
	
	public BlogController(BlogService blogService, CategoryService categoryService, PostService postService, FileUploadService fileUploadService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
		this.fileUploadService = fileUploadService;
	}
	
	@RequestMapping({"", "/{path1}" , "/{path1}/{path2}" })
	public String main(
			@PathVariable("userId") String userId,
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
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		// Category List
		List<CategoryVo> categoryList = categoryService.getCategoryByUserId(userId);
		model.addAttribute("categoryList", categoryList);
		// Post List
		List<PostVo> postList = postService.getPostList(userId, categoryId);
		model.addAttribute("postList", postList);
		// MAIN글 
		PostVo mainPost = postService.getPost(userId, categoryId, postId);
		model.addAttribute("mainPost", mainPost);
		
		return "blog/main";
	}
	
	/*
	 * Admin Basic
	 */
	
	@Auth
	@RequestMapping("/admin")
	public String adminDefault(@PathVariable("userId") String userId, Model model) {
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin-basic";
	}

	@Auth
	@RequestMapping("/admin/update")
	public String adminUpdate(
				@PathVariable("userId") String userId,
				BlogVo blogVo,
				 @RequestParam("file") MultipartFile multipartFile
		) {
		blogVo.setBlogId(userId);
		// 파일 저장
		String profile = fileUploadService.restore(multipartFile);
		if(profile != null) {
			blogVo.setProfile(profile);
		}		
		
		System.out.println("Update 드가자~" + blogVo);
		// Blog 정보 update
		blogService.updateBlog(blogVo);
		
		return "redirect:/"+ userId +"/admin";
	}

	
	
	/*
	 * Admin Category
	 */
	
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("userId") String userId, Model model) {
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		List<CategoryVo> categoryList = categoryService.getCategoryByUserId(userId);
		model.addAttribute("categoryList", categoryList);
		return "blog/admin-category";
	}
	
	@Auth 
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(@PathVariable("userId") String userId, CategoryVo categoryVo) {
		categoryService.insert(userId, categoryVo);
		return "redirect:/"+ userId +"/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/category/delete/{categoryId}")
	public String delete(
			@PathVariable("userId") String userId,
			@PathVariable("categoryId") Long categoryId) {		
		categoryService.deleteCategory(userId, categoryId);
		return "redirect:/"+ userId +"/admin/category";
	}
	
	/*
	 * Admin Write
	 */
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("id", userId);
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		List<CategoryVo> categoryList = categoryService.getCategoryByUserId(userId);
		model.addAttribute("categoryList", categoryList);
		return "blog/admin-write";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(
					@PathVariable("userId") String userId, 
					@RequestParam(value="category", required=true, defaultValue="") String category,
					PostVo postVo) {
		postService.insert(userId, category, postVo);
		return "redirect:/"+ userId +"/admin/write";
	}
	
}
