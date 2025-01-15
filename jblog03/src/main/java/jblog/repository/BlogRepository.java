package jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(String userId, String title, String profile) {
		return sqlSession.insert("blog.insert", Map.of("id", userId, "title", title, "profile", profile));
	}

	public BlogVo find(String userId) {
		return sqlSession.selectOne("blog.find", userId);
	}

	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);
	}

}
