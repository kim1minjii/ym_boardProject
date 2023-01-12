package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.PageDTO;
import dto.ecBoardDTO;
import service.ecBoardService;

// http://localhost:8090/myapp/qlist.do

@Controller
public class QnaBoardController {

	private ecBoardService qna_service;
	private PageDTO pdto;
	private int currentPage;

	public QnaBoardController() {

	}

	public void setQna_service(ecBoardService qna_service) {
		this.qna_service = qna_service;
	}

	@RequestMapping("/qlist.do")
	public ModelAndView listMethod(PageDTO pv, ModelAndView mav) {
		int totalRecord = qna_service.countProcess();
		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0)
				this.currentPage = 1;
			else
				this.currentPage = pv.getCurrentPage();

			this.pdto = new PageDTO(currentPage, totalRecord);
			List<ecBoardDTO> aList = qna_service.listProcess(this.pdto);

			mav.addObject("aList", aList);
			mav.addObject("pv", this.pdto);
		}

		mav.setViewName("qlist");
		return mav;
	}// end listMethod()

	@RequestMapping(value = "/qwrite.do", method = RequestMethod.GET)
	public ModelAndView writeMethod(ecBoardDTO dto, PageDTO pv, ModelAndView mav) {
		if (dto.getRef() != 0) { // 답변글이면
			mav.addObject("currentPage", pv.getCurrentPage());
			mav.addObject("dto", dto);
		}
		mav.setViewName("qwrite");
		return mav;
	}// end writeMethod()

	@RequestMapping(value = "/qwrite.do", method = RequestMethod.POST)
	public String writeProMethod(ecBoardDTO dto, PageDTO pv, HttpServletRequest request) {
		MultipartFile file = dto.getFilename();
		// 주석 풀면 글쓰기 처리 안됨
//		if (!file.isEmpty()) {
//			UUID random = saveCopyFile(file, request);
//			dto.setUpload(random + "_" + file.getOriginalFilename());
//		}

		// ip 값
		dto.setIp(request.getRemoteAddr());

		qna_service.insertProcess(dto);

		// 답변글이면
		if (dto.getRef() != 0) {
			return "redirect:/qlist.do?courrentPage=" + pv.getCurrentPage();
		} else { // 제목글
			return "redirect:/qlist.do";
		}
	}// end writeProMethod()

	@RequestMapping(value = "/qupdate.do", method = RequestMethod.GET)
	public ModelAndView updateMethod(int num, int currentPage, ModelAndView mav) {
		mav.addObject("dto", qna_service.updateSelectProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("qupdate");
		return mav;
	}// end updateMethod()

	@RequestMapping(value = "/qupdate.do", method = RequestMethod.POST)
	public String updateProMethod(ecBoardDTO dto, int currentPage, HttpServletRequest request) {
		MultipartFile file = dto.getFilename();
		if (!file.isEmpty()) {
			UUID random = saveCopyFile(file, request);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}

		qna_service.updateProcess(dto, urlPath(request));
		return "redirect:/qlist.do?currentPage=" + currentPage;
	}// end updateProMethod()

	@RequestMapping(value = "/qdelete.do")
	public String deleteMethod(int num, int currentPage, HttpServletRequest request) {
		qna_service.deleteProcess(num, urlPath(request));

		int totalRecord = qna_service.countProcess();
		this.pdto = new PageDTO(this.currentPage, totalRecord);

		return "redirect:/qlist.do?currentPage=" + this.pdto.getCurrentPage();
	}// end deleteMethod()

	private UUID saveCopyFile(MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();

		// 중복파일명을 처리하기 위해 난수 발생
		UUID random = UUID.randomUUID();

		File fe = new File(urlPath(request));
		if (!fe.exists()) {
			fe.mkdir();
		}

		File ff = new File(urlPath(request), random + "_" + fileName);

		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return random;
	}// end saveCopyFile()

	private String urlPath(HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		// D:\HOJIN\spring_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_08_board
		System.out.println("root:" + root);
		String saveDirectory = root + "temp" + File.separator;
		return saveDirectory;
	}// end urlPath

	@RequestMapping("/qview.do")
	public ModelAndView viewMethod(int currentPage, int num, ModelAndView mav) {
		mav.addObject("dto", qna_service.contentProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("qview");
		return mav;
	}// viewMethod()

	@RequestMapping("/qcontentdownload.do")
	public ModelAndView downMethod(int num, ModelAndView mav) {
		mav.addObject("num", num);
		mav.setViewName("qdownload");
		return mav;
	}// end downMethod()

	@RequestMapping(value = "/qlist.do", method = RequestMethod.GET)
	public ModelAndView listSearchMethod(PageDTO pv, ModelAndView mav,
			@RequestParam(value = "searchKey", required = false, defaultValue = "subject") String searchKey,
			@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord) {
		int totalRecord = qna_service.countProcess();
		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0)
				this.currentPage = 1;
			else
				this.currentPage = pv.getCurrentPage();
			this.pdto = new PageDTO(currentPage, totalRecord, searchKey, searchWord);

			List<ecBoardDTO> aList = qna_service.listProcess(this.pdto);
			mav.addObject("cntt", qna_service.countProcess());
			mav.addObject("aList", aList);
			mav.addObject("pv", this.pdto);

		}

		mav.setViewName("qlist");
		return mav;
	}

}// end class