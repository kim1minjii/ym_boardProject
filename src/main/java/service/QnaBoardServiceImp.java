package service;

import java.io.File;
import java.util.List;

import dao.ecBoardDAO;
import dto.PageDTO;
import dto.rPageDTO;
import dto.ecBoardDTO;

public class QnaBoardServiceImp implements ecBoardService {
	private ecBoardDAO qna_dao;

	public QnaBoardServiceImp() {

	}

	public void setQna_dao(ecBoardDAO qna_dao) {
		this.qna_dao = qna_dao;
	}

	@Override
	public int countProcess() {
		return qna_dao.count();
	}

	@Override
	public List<ecBoardDTO> listProcess(PageDTO pv) {
		return qna_dao.list(pv);
	}

	@Override
	public void insertProcess(ecBoardDTO dto) {

		// 답변글이면
		if (dto.getRef() != 0) {
			qna_dao.reStepCount(dto);
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);
		}

		qna_dao.save(dto);
	}

	@Override
	public ecBoardDTO contentProcess(int num) {
		qna_dao.readCount(num);
		return qna_dao.content(num);
	}

	@Override
	public void reStepProcess(ecBoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public ecBoardDTO updateSelectProcess(int num) {
		return qna_dao.content(num);
	}

	@Override
	public void updateProcess(ecBoardDTO dto, String urlpath) {
		String filename = dto.getUpload();

		// 수정한 파일이 있으면
		if (filename != null) {
			String path = qna_dao.getFile(dto.getNum());
			// 기존 첨부파일이 있으면
			if (path != null) {
				File file = new File(urlpath, path);
				file.delete();
			}

		}

		qna_dao.update(dto);

	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		String path = qna_dao.getFile(num);

		// num컬럼에 해당하는 첨부파일이 있으면
		if (path != null) {
			File fe = new File(urlpath, path);
			fe.delete();
		}

		qna_dao.delete(num);
	}

	@Override
	public String fileSelectprocess(int num) {
		return qna_dao.getFile(num);
	}

	@Override
	public List<ecBoardDTO> listProcess(rPageDTO rpdto) {
		// TODO Auto-generated method stub
		return null;
	}

	
}// end class
