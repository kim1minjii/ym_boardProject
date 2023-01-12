package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import dto.ecBoardDTO;
import dto.PageDTO;
import dto.rPageDTO;

public class ReviewBoardDaoImp implements ecBoardDAO {
	private SqlSessionTemplate sqlSession;

	public ReviewBoardDaoImp() {

	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count() {
		return sqlSession.selectOne("reviewBoard.count");
	}

	@Override
	public List<ecBoardDTO> list(rPageDTO rpv) {
		return sqlSession.selectList("reviewBoard.list", rpv);
	}

	@Override
	public void readCount(int num) {
		sqlSession.update("reviewBoard.readCount", num);

	}

	@Override
	public ecBoardDTO content(int num) {
		return sqlSession.selectOne("reviewBoard.view", num);
	}

	@Override
	public void reStepCount(ecBoardDTO dto) {
		sqlSession.update("reviewBoard.reStepCount", dto);

	}

	@Override
	public void save(ecBoardDTO dto) {
		sqlSession.insert("reviewBoard.save", dto);
	}

	@Override
	public ecBoardDTO updateNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ecBoardDTO dto) {
		sqlSession.update("reviewBoard.update", dto);

	}

	@Override
	public void delete(int num) {
		sqlSession.delete("reviewBoard.delete", num);

	}

	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("reviewBoard.uploadFile", num);
	}

	@Override
	public List<ecBoardDTO> list(PageDTO pv) {
		// TODO Auto-generated method stub
		return null;
	}

}// end class
