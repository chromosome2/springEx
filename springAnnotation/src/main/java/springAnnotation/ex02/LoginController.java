package springAnnotation.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")
public class LoginController {
	@RequestMapping(value= {"/test/loginForm.do", "/test/loginForm2.do"},
			method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("loginForm");
		return mav; 
	}

	//null값은 아무것도 안뜨고 출력됨. 에러도 안남. 원래 하던 방식.
	/*@RequestMapping(value= "/test/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("result");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		return mav;
	}*/
	
	//@RequestParam 이용
	/*@RequestMapping(value= "/test/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@RequestParam("id") String id, @RequestParam("pwd") String pwd,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("result");
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		return mav;
	}*/
	
	/*@RequestMapping(value= "/test/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@RequestParam(value="id",required = true) String id, @RequestParam(value="pwd",required = true) String pwd,
			@RequestParam(value="email",required = true) String email, // required=true가 기본. 원래는 값이 null일 경우 에러가 난다고 함. 근데 나는 왜 안나지??
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("result");
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("email", email);
		return mav;
	}*/
	
	//Map을 이용한 방식. 매개변수가 많을때 사용.
	/*@RequestMapping(value= "/test/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@RequestParam Map<String, String> memInfo,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("result");
		mav.addObject("memInfo", memInfo);
		return mav;
	}*/
	
	//loginVO를 이용
	/*@RequestMapping(value= "/test/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@ModelAttribute("memInfo") LoginVO loginVO,//loginInfo를 memInfo라고 설정. add필요없음. 알아서 됨.
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("result");
		return mav;
	}*/
	
	@RequestMapping(value= "/test/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(Model model,//Model로 데이터를 반환
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		model.addAttribute("id", "hong");
		model.addAttribute("pwd", "1234");
		model.addAttribute("email", "hong@");
		return "result";
	}

}
