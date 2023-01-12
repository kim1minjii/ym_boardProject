package service;

import java.io.File;
import java.util.List;

import dao.ecBoardDAO;

import dto.rPageDTO;
import dto.PageDTO;
import dto.ecBoardDTO;

public class ReviewBoardServiceImp implements ecBoardService {
	private ecBoardDAO review_dao;

	public ReviewBoardServiceImp() {

	}

	public void setReview_dao(ecBoardDAO review_dao) {
		this.review_dao = review_dao;
	}

	@Override
	public int countProcess() {
		return review_dao.count();
	}

	@Override
	public List<ecBoardDTO> listProcess(rPageDTO pv) {
		return review_dao.list(pv);
	}

	@Override
	public void insertProcess(ecBoardDTO dto) {

		// 답변글이면
		if (dto.getRef() != 0) {
			review_dao.reStepCount(dto);
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);
		}

		review_dao.save(dto);
	}

	@Override
	public ecBoardDTO contentProcess(int num) {
		review_dao.readCount(num);
		return review_dao.content(num);
	}

	@Override
	public void reStepProcess(ecBoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public ecBoardDTO updateSelectProcess(int num) {
		return review_dao.content(num);
	}

	@Override
	public void updateProcess(ecBoardDTO dto, String urlpath) {
		String filename = dto.getUpload();

		// 수정한 파일이 있으면
		if (filename != null) {
			String path = review_dao.getFile(dto.getNum());
			// 기존 첨부파일이 있으면
			if (path != null) {
				File file = new File(urlpath, path);
				file.delete();
			}

		}

		review_dao.update(dto);

	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		String path = review_dao.getFile(num);

		// num컬럼에 해당하는 첨부파일이 있으면
		if (path != null) {
			File fe = new File(urlpath, path);
			fe.delete();
		}

		review_dao.delete(num);
	}

	@Override
	public String fileSelectprocess(int num) {
		return review_dao.getFile(num);
	}

	@Override
	public List<ecBoardDTO> listProcess(PageDTO pv) {
		// TODO Auto-generated method stub
		return null;
	}

}// end class
