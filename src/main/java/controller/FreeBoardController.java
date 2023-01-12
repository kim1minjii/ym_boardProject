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
import dto.ncBoardDTO;
import service.ncBoardService;

// http://localhost:8090/myapp/flist.do

@Controller
public class FreeBoardController {

	private ncBoardService free_service;
	private PageDTO pdto;
	private int currentPage;

	public FreeBoardController() {

	}

	@RequestMapping(value = "/flist.do", method = RequestMethod.GET)
	public ModelAndView listSearchMethod(PageDTO pv, ModelAndView mav,
			@RequestParam(value = "searchKey", required = false, defaultValue = "subject") String searchKey,
			@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord) {
		
		int totalRecord = free_service.countProcess();
		
		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0)
				this.currentPage = 1;
			else
				this.currentPage = pv.getCurrentPage();

			this.pdto = new PageDTO(currentPage, totalRecord, searchKey, searchWord);

			List<ncBoardDTO> aList = free_service.listProcess(this.pdto);
			mav.addObject("cntt", free_service.countProcess());
			mav.addObject("aList", aList);
			mav.addObject("pv", this.pdto);

		}

		mav.setViewName("flist");
		return mav;
	}

	public void setFree_service(ncBoardService free_service) {
		this.free_service = free_service;
	}

	@RequestMapping(value = "/fwrite.do", method = RequestMethod.GET)
	public ModelAndView writeMethod(ncBoardDTO dto, PageDTO pv, ModelAndView mav) {
		if (dto.getRef() != 0) { // 답변글이면
			mav.addObject("currentPage", pv.getCurrentPage());
			mav.addObject("dto", dto);
		}
		mav.setViewName("fwrite");
		return mav;
	}// end writeMethod()

	@RequestMapping(value = "/fwrite.do", method = RequestMethod.POST)
	public String writeProMethod(ncBoardDTO dto, PageDTO pv, HttpServletRequest request) {
		// 첨부파일 처리
		MultipartFile file = dto.getFilename();
		// 주석 풀면 글쓰기 처리 안됨
//      if (!file.isEmpty()) { 
//         UUID random = saveCopyFile(file, request); 
//         dto.setUpload(random + "_" + file.getOriginalFilename()); 
//      }

		// ip 값
		dto.setIp(request.getRemoteAddr());

		free_service.insertProcess(dto);

		// 답변글이면
		if (dto.getRef() != 0) {
			return "redirect:/flist.do?courrentPage=" + pv.getCurrentPage();
		} else { // 제목글
			return "redirect:/flist.do";
		}
	}// end writeProMethod()

	@RequestMapping(value = "/fupdate.do", method = RequestMethod.GET)
	public ModelAndView updateMethod(int num, int currentPage, ModelAndView mav) {
		mav.addObject("dto", free_service.updateSelectProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("fupdate");
		return mav;
	}// end updateMethod()

	@RequestMapping(value = "/fupdate.do", method = RequestMethod.POST)
	public String updateProMethod(ncBoardDTO dto, int currentPage, HttpServletRequest request) {
		MultipartFile file = dto.getFilename();
		if (!file.isEmpty()) {
			UUID random = saveCopyFile(file, request);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}

		free_service.updateProcess(dto, urlPath(request));
		return "redirect:/flist.do?currentPage=" + currentPage;
	}// end updateProMethod()

	@RequestMapping(value = "/fdelete.do")
	public String deleteMethod(int num, int currentPage, HttpServletRequest request) {
		free_service.deleteProcess(num, urlPath(request));

		int totalRecord = free_service.countProcess();
		this.pdto = new PageDTO(this.currentPage, totalRecord);

		return "redirect:/flist.do?currentPage=" + this.pdto.getCurrentPage();
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

	@RequestMapping("/fview.do")
	public ModelAndView viewMethod(int currentPage, int num, ModelAndView mav) {
		mav.addObject("dto", free_service.contentProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("fview");
		return mav;
	}// viewMethod()

	@RequestMapping("/fcontentdownload.do")
	public ModelAndView downMethod(int num, ModelAndView mav) {
		mav.addObject("num", num);
		mav.setViewName("fdownload");
		return mav;
	}// end downMethod()

}// end class