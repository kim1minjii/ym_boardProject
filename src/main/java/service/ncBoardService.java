package service;

import java.util.List;

import dto.PageDTO;
import dto.ncBoardDTO;

public interface ncBoardService {
	public int countProcess();
	public List<ncBoardDTO> listProcess(PageDTO pv);
	public void insertProcess(ncBoardDTO dto);
	public ncBoardDTO contentProcess(int num);
	public void reStepProcess(ncBoardDTO dto);
	public ncBoardDTO updateSelectProcess(int num);
	public void updateProcess(ncBoardDTO dto, String urlpath);
	public void deleteProcess(int num, String urlpath);
	public String fileSelectprocess(int num);
}
