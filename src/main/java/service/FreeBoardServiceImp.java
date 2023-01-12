package service;

import java.io.File;
import java.util.List;

import dao.ncBoardDAO;
import dto.PageDTO;
import dto.ncBoardDTO;

public class FreeBoardServiceImp implements ncBoardService {
	private ncBoardDAO free_dao;

	public FreeBoardServiceImp() {

	}

	public void setFree_dao(ncBoardDAO free_dao) {
		this.free_dao = free_dao;
	}

	@Override
	public int countProcess() {
		return free_dao.count();
	}

	@Override
	public List<ncBoardDTO> listProcess(PageDTO pv) {
		return free_dao.list(pv);
	}

	@Override
	public void insertProcess(ncBoardDTO dto) {

		// 답변글이면
		if (dto.getRef() != 0) {
			free_dao.reStepCount(dto);
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);
		}

		free_dao.save(dto);
	}

	@Override
	public ncBoardDTO contentProcess(int num) {
		free_dao.readCount(num);
		return free_dao.content(num);
	}

	@Override
	public void reStepProcess(ncBoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public ncBoardDTO updateSelectProcess(int num) {
		return free_dao.content(num);
	}

	@Override
	public void updateProcess(ncBoardDTO dto, String urlpath) {
		String filename = dto.getUpload();

		// 수정한 파일이 있으면
		if (filename != null) {
			String path = free_dao.getFile(dto.getNum());
			// 기존 첨부파일이 있으면
			if (path != null) {
				File file = new File(urlpath, path);
				file.delete();
			}

		}

		free_dao.update(dto);

	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		String path = free_dao.getFile(num);

		// num컬럼에 해당하는 첨부파일이 있으면
		if (path != null) {
			File fe = new File(urlpath, path);
			fe.delete();
		}

		free_dao.delete(num);
	}

	@Override
	public String fileSelectprocess(int num) {
		return free_dao.getFile(num);
	}

}// end class
