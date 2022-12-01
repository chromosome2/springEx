package springMVC.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController{
	
	//http://127.0.0.1:8090/SpringMVC/member/loginForm.jsp
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id="";
		String pwd="";
		String viewName=getViewName(request);
		ModelAndView mav=new ModelAndView();
		request.setCharacterEncoding("utf-8");
		id=request.getParameter("id");
		pwd=request.getParameter("pwd");
		mav.addObject("id",id);//뷰로 보낼 데이터 값 설정
		mav.addObject("pwd",pwd);
		mav.setViewName(viewName);//포워딩할 jsp이름을 설정
		return mav;
	}
	
	//http://127.0.0.1:8090/SpringMVC/member/memberForm.jsp
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		request.setCharacterEncoding("utf-8");
		String viewName=getViewName(request);
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		mav.addObject("id",id);
		mav.addObject("pwd",pwd);
		mav.addObject("name",name);
		mav.addObject("email",email);
		mav.setViewName(viewName);
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath=request.getContextPath();//내 경로. 프로젝트 이름까지 가져오기
		String uri=(String)request.getAttribute("javax.servlet.include.request_uri");
		System.out.println(uri);
		if(uri == null || uri.trim().equals("")) {
			uri=request.getRequestURI();
		}
		int begin=0, end;
		if(!((contextPath==null) || ("".equals(contextPath)))) {
			begin=contextPath.length();
		}
		if(uri.indexOf(";")!=-1) {
			end=uri.indexOf(";");//요청 uri에 ;가 있을 경우 ;의 위치를 구함
		}else if(uri.indexOf("?")!=-1) {
			end=uri.indexOf("?");
		}else {
			end=uri.length();
		}
		String fileName=uri.substring(begin,end);
		if(fileName.lastIndexOf(".")!=-1) {
			//.do 앞까지의 문자열을 구함.
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
		}
		if(fileName.lastIndexOf("/")!=-1) {
			fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
			//예를 들어 127.0.0.1:8090/sevletMVC/member/login.do라면
			//.do를 빼고 / 앞까지도 다 빼서 login < 이것만 가져오려고 하는거임.
		}
		System.out.println(uri+" / "+fileName);
		return fileName;
	}
}
