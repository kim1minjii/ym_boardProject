package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import dto.ecBoardDTO;
import dto.PageDTO;
import dto.rPageDTO;

public class QnaBoardDaoImp implements ecBoardDAO {
	private SqlSessionTemplate sqlSession;

	public QnaBoardDaoImp() {

	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count() {
		return sqlSession.selectOne("qnaBoard.count");
	}

	@Override
	public List<ecBoardDTO> list(PageDTO pv) {
		return sqlSession.selectList("qnaBoard.list", pv);
	}

	@Override
	public void readCount(int num) {
		sqlSession.update("qnaBoard.readCount", num);

	}

	@Override
	public ecBoardDTO content(int num) {
		return sqlSession.selectOne("qnaBoard.view", num);
	}

	@Override
	public void reStepCount(ecBoardDTO dto) {
		sqlSession.update("qnaBoard.reStepCount", dto);

	}

	@Override
	public void save(ecBoardDTO dto) {
		sqlSession.insert("qnaBoard.save", dto);
	}

	@Override
	public ecBoardDTO updateNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ecBoardDTO dto) {
		sqlSession.update("qnaBoard.update", dto);

	}

	@Override
	public void delete(int num) {
		sqlSession.delete("qnaBoard.delete", num);

	}

	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("qnaBoard.uploadFile", num);
	}

	@Override
	public List<ecBoardDTO> list(rPageDTO rpv) {
		// TODO Auto-generated method stub
		return null;
	}

}// end class
