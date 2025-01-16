package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private CategoryRepository categoryRepository;
	
	public UserService(UserRepository userRepository, BlogRepository blogRepository, CategoryRepository categoryRepository) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
	@Transactional
	public void join(UserVo userVo) {
		// 유저 생성
		userRepository.insert(userVo);	

		// 기본 블로그 생성
		String blogTitle = userVo.getId() + " 블로그";
		String blogProfile = "/assets/upload-images/spring-logo.jpg";
		blogRepository.insert(userVo.getId(), blogTitle, blogProfile);
		
		// 기본 카테고리 생성
		String categoryName = "기본";
		String categoryDescription = "기본 카테고리입니다.";
		categoryRepository.insert(userVo.getId(), categoryName, categoryDescription);
	}
	
}
