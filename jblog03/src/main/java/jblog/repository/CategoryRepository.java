package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(String userId, String name, String description) {
		return sqlSession.insert("category.insert", Map.of("id", userId, "name", name, "description", description));
	}

	public List<CategoryVo> findByUserId(String userId) {
		return sqlSession.selectList("category.findAll", userId);
	}

	public void delete(String userId, Long categoryId) {
		sqlSession.delete("category.delete", Map.of("id", userId, "categoryId", categoryId));
	}
	
}
