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
	
	public UserService(UserRepository userRepository, BlogRepository blogRepository) {
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
		int userCount = userRepository.insert(userVo);	
		if (userCount == 0) {
			return;
		}
		// 기본 블로그 생성
		// int blogCount = blogRepository.insert(userVo.getId());
		
		// 기본 카테고리 생성
		// int categoryCount = categoryRepository.insert(userVo.getId());
	}
	
}
