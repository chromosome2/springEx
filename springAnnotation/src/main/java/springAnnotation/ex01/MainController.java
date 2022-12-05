package springAnnotation.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController") //bean의 id를 mainController로
@RequestMapping("/test")
public class MainController {
	
	@RequestMapping(value="/main1.do", method=RequestMethod.GET)
	public ModelAndView mainMethod1(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","대한민국");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/main2.do", method=RequestMethod.GET)
	public ModelAndView mainMethod2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","한국");
		mav.setViewName("main");
		return mav;
	}

}
