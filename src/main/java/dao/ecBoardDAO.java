package dao;

import java.util.List;

import dto.PageDTO;
import dto.ecBoardDTO;
import dto.rPageDTO;

public interface ecBoardDAO {

	public int count();
	public List<ecBoardDTO> list(PageDTO pv);
	public List<ecBoardDTO> list(rPageDTO rpv);
	public void readCount(int num);
	public ecBoardDTO content(int num);
	public void reStepCount(ecBoardDTO dto);
	public void save(ecBoardDTO dto);
	public ecBoardDTO updateNum(int num);
	public void update(ecBoardDTO dto);
	public void delete(int num);
	public String getFile(int num);

}
