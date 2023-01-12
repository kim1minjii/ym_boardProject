package dao;

import java.util.List;

import dto.PageDTO;
import dto.ncBoardDTO;

public interface ncBoardDAO {

	public int count();
	public List<ncBoardDTO> list(PageDTO pv);
	public void readCount(int num);
	public ncBoardDTO content(int num);
	public void reStepCount(ncBoardDTO dto);
	public void save(ncBoardDTO dto);
	public ncBoardDTO updateNum(int num);
	public void update(ncBoardDTO dto);
	public void delete(int num);
	public String getFile(int num);

}
