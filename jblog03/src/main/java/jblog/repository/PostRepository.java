package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {
	private SqlSession sqlSession;
	
	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<PostVo> findByUserId(String userId) {
		return sqlSession.selectList("post.findAll", userId);
	}

	public List<PostVo> findByCategoryId(String userId, Long categoryId) {
		System.out.println(userId + "  " + categoryId);
		return sqlSession.selectList("post.findByCategoryId",  Map.of("id", userId, "categoryId", categoryId));
	}

	public PostVo findByPostId(String userId, Long categoryId, Long postId) {
		return sqlSession.selectOne("post.findByPostId", Map.of("id", userId, "categoryId", categoryId, "postId", postId));
	}

	public void deleteAll(Long categoryId) {
		sqlSession.delete("post.deleteAll", categoryId);
	}

	public void insert(String userId, String categoryName, String title, String contents) {
		sqlSession.insert("post.insert", Map.of("userId", userId, "categoryName", categoryName, "title", title, "contents", contents));
	}
	
}
