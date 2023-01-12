package service;

import java.util.List;

import dto.PageDTO;
import dto.ecBoardDTO;
import dto.rPageDTO;
import dto.PageDTO;

public interface ecBoardService {
	public int countProcess();
	public List<ecBoardDTO> listProcess(rPageDTO rpdto);
	public void insertProcess(ecBoardDTO dto);
	public ecBoardDTO contentProcess(int num);
	public void reStepProcess(ecBoardDTO dto);
	public ecBoardDTO updateSelectProcess(int num);
	public void updateProcess(ecBoardDTO dto, String urlpath);
	public void deleteProcess(int num, String urlpath);
	public String fileSelectprocess(int num);
	List<ecBoardDTO> listProcess(PageDTO pv);

}
