package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import dto.ncBoardDTO;
import dto.PageDTO;

public class NoticeBoardDaoImp implements ncBoardDAO {
	private SqlSessionTemplate sqlSession;

	public NoticeBoardDaoImp() {

	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count() {
		return sqlSession.selectOne("noticeBoard.count");
	}

	@Override
	public List<ncBoardDTO> list(PageDTO pv) {	
		return sqlSession.selectList("noticeBoard.list", pv);
	}

	@Override
	public void readCount(int num) {
		sqlSession.update("noticeBoard.readCount", num);

	}

	@Override
	public ncBoardDTO content(int num) {
		return sqlSession.selectOne("noticeBoard.view", num);
	}

	@Override
	public void reStepCount(ncBoardDTO dto) {
		sqlSession.update("noticeBoard.reStepCount", dto);

	}

	@Override
	public void save(ncBoardDTO dto) {
		sqlSession.insert("noticeBoard.save", dto);
	}

	@Override
	public ncBoardDTO updateNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ncBoardDTO dto) {
		sqlSession.update("noticeBoard.update", dto);

	}

	@Override
	public void delete(int num) {
		sqlSession.delete("noticeBoard.delete", num);

	}

	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("noticeBoard.uploadFile", num);
	}
	
}// end class
