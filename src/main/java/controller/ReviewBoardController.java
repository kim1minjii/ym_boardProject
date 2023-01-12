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

import dto.rPageDTO;
import dto.PageDTO;
import dto.ecBoardDTO;
import service.ecBoardService;

// http://localhost:8090/myapp/rlist.do

@Controller
public class ReviewBoardController {

	private ecBoardService review_service;
	private rPageDTO rpdto;

	private int currentPage;

	public ReviewBoardController() {

	}

	public void setReview_service(ecBoardService review_service) {
		this.review_service = review_service;
	}



	@RequestMapping(value = "/rwrite.do", method = RequestMethod.GET)
	public ModelAndView writeMethod(ecBoardDTO dto, rPageDTO pv, ModelAndView mav) {
		if (dto.getRef() != 0) { // 답변글이면
			mav.addObject("currentPage", pv.getCurrentPage());
			mav.addObject("dto", dto);
		}
		mav.setViewName("rwrite");
		return mav;
	}// end writeMethod()

	@RequestMapping(value = "/rwrite.do", method = RequestMethod.POST)
	public String writeProMethod(ecBoardDTO dto, rPageDTO pv, HttpServletRequest request) {
		//첨부파일처리
		MultipartFile file = dto.getFilename();
		//주석풀면 썸머노트 저장안됨
//		if (!file.isEmpty()) {
//			UUID random = saveCopyFile(file, request);
//			dto.setUpload(random + "_" + file.getOriginalFilename());
//		}
//		
		
		
		
		
		dto.setIp(request.getRemoteAddr());

		review_service.insertProcess(dto);

		// 답변글이면
		if (dto.getRef() != 0) {
			return "redirect:/rlist.do?courrentPage=" + pv.getCurrentPage();
		} else { // 제목글
			return "redirect:/rlist.do";
		}
	} // end writeProMethod()

	@RequestMapping(value = "/rupdate.do", method = RequestMethod.GET)
	public ModelAndView updateMethod(int num, int currentPage, ModelAndView mav) {
		mav.addObject("dto", review_service.updateSelectProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("rupdate");
		return mav;
	}// end updateMethod()

	@RequestMapping(value = "/rupdate.do", method = RequestMethod.POST)
	public String updateProMethod(ecBoardDTO dto, int currentPage, HttpServletRequest request) {
		MultipartFile file = dto.getFilename();
		if (!file.isEmpty()) {
			UUID random = saveCopyFile(file, request);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}

		review_service.updateProcess(dto, urlPath(request));
		return "redirect:/rlist.do?currentPage=" + currentPage;
	}// end updateProMethod()

	@RequestMapping(value = "/rdelete.do")
	public String deleteMethod(int num, int currentPage, HttpServletRequest request) {
		review_service.deleteProcess(num, urlPath(request));

		int totalRecord = review_service.countProcess();
		this.rpdto = new rPageDTO(this.currentPage, totalRecord);

		return "redirect:/rlist.do?currentPage=" + this.rpdto.getCurrentPage();
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

	@RequestMapping("/rview.do")
	public ModelAndView viewMethod(int currentPage, int num, ModelAndView mav) {
		mav.addObject("dto", review_service.contentProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("rview");
		return mav;
	}// viewMethod()

	@RequestMapping("/rcontentdownload.do")
	public ModelAndView downMethod(int num, ModelAndView mav) {
		mav.addObject("num", num);
		mav.setViewName("download");
		return mav;
	}// end downMethod()

	
	
	
	@RequestMapping(value = "/rlist.do", method = RequestMethod.GET)
	public ModelAndView listMethod(rPageDTO pv, ModelAndView mav,
			@RequestParam(value = "searchKey", required = false, defaultValue = "subject") String searchKey,
			@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
			) {
		int totalRecord = review_service.countProcess();
		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0)
				this.currentPage = 1;
			else
				this.currentPage = pv.getCurrentPage();
		
			this.rpdto = new rPageDTO(currentPage, totalRecord, searchKey, searchWord);
			
			List<ecBoardDTO> aList = review_service.listProcess(this.rpdto);
			mav.addObject("cntt", review_service.countProcess());
			mav.addObject("aList", aList);
			mav.addObject("pv", this.rpdto);
		}
		
		mav.setViewName("rlist");
		return mav;
	}// end listMethod()
	
	
	

}// end class