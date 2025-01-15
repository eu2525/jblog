package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public List<PostVo> getPostList(String id, Long categoryId) {
		
		if(categoryId == 0L) {
			return postRepository.findByUserId(id);
		} else {
			return postRepository.findByCategoryId(id, categoryId);
		}
	}

	public PostVo getPost(String id, Long categoryId, Long postId) {
		if(postId == 0L) {
			return null;
		} else {
			return postRepository.findByPostId(id, categoryId, postId);
		}
	}

}
