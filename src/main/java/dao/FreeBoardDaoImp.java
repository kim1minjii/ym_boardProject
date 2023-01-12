package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import dto.ncBoardDTO;
import dto.PageDTO;

public class FreeBoardDaoImp implements ncBoardDAO {
	private SqlSessionTemplate sqlSession;

	public FreeBoardDaoImp() {

	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count() {
		return sqlSession.selectOne("freeBoard.count");
	}

	@Override
	public List<ncBoardDTO> list(PageDTO pv) {
		return sqlSession.selectList("freeBoard.list", pv);
	}

	@Override
	public void readCount(int num) {
		sqlSession.update("freeBoard.readCount", num);

	}

	@Override
	public ncBoardDTO content(int num) {
		return sqlSession.selectOne("freeBoard.view", num);
	}

	@Override
	public void reStepCount(ncBoardDTO dto) {
		sqlSession.update("freeBoard.reStepCount", dto);

	}

	@Override
	public void save(ncBoardDTO dto) {
		sqlSession.insert("freeBoard.save", dto);
	}

	@Override
	public ncBoardDTO updateNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ncBoardDTO dto) {
		sqlSession.update("freeBoard.update", dto);

	}

	@Override
	public void delete(int num) {
		sqlSession.delete("freeBoard.delete", num);

	}

	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("freeBoard.uploadFile", num);
	}

}// end class
