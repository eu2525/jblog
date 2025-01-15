package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	private PostRepository postRepository;
	
	public CategoryService(CategoryRepository categoryRepository, PostRepository postRepository) {
		this.categoryRepository = categoryRepository;
		this.postRepository = postRepository;
	}
	
	public List<CategoryVo> getCategoryByUserId(String userId) {
		return categoryRepository.findByUserId(userId);
	}

	public void insert(String userId, CategoryVo categoryVo) {
		categoryRepository.insert(userId, categoryVo.getName(), categoryVo.getDescription());
	}

	@Transactional
	public void deleteCategory(String userId, Long categoryId) {
		// Category에 해당하는 Post들 모두 삭제
		postRepository.deleteAll(categoryId);
		// Category 삭제
		categoryRepository.delete(userId, categoryId);
	}


}
