package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(String userId, String title, String profile) {
		return sqlSession.insert("blog.insert", userId);
	}

}
