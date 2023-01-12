package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost:8090/myapp/index.do

@Controller
public class IndexController {

	@RequestMapping("/index.do")
	public String execute() {
		return "index";
	}// end execute()

	@RequestMapping("/recommand.do")
	public String recommand() {
		return "recommand";
	}

	@RequestMapping("/paldo.do")
	public String paldo() {
		return "paldo";
	}

	@RequestMapping("/calendar.do")
	public String calendar() {
		return "calendar";
	}

	@RequestMapping("/bookmark.do")
	public String bookmark() {
		return "bookmark";
	}



	@RequestMapping("/login.do")
	public String login() {
		return "login";
	}

	@RequestMapping("/join.do")
	public String join() {
		return "join";
	}

	@RequestMapping("/my.do")
	public String my() {
		return "my";
	}

	@RequestMapping("/detail.do")
	public String detail() {
		return "detail";
	}
}// end class
