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
		mav.addObject("id",id);//��� ���� ������ �� ����
		mav.addObject("pwd",pwd);
		mav.setViewName(viewName);//�������� jsp�̸��� ����
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
		String contextPath=request.getContextPath();//�� ���. ������Ʈ �̸����� ��������
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
			end=uri.indexOf(";");//��û uri�� ;�� ���� ��� ;�� ��ġ�� ����
		}else if(uri.indexOf("?")!=-1) {
			end=uri.indexOf("?");
		}else {
			end=uri.length();
		}
		String fileName=uri.substring(begin,end);
		if(fileName.lastIndexOf(".")!=-1) {
			//.do �ձ����� ���ڿ��� ����.
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
		}
		if(fileName.lastIndexOf("/")!=-1) {
			fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
			//���� ��� 127.0.0.1:8090/sevletMVC/member/login.do���
			//.do�� ���� / �ձ����� �� ���� login < �̰͸� ���������� �ϴ°���.
		}
		System.out.println(uri+" / "+fileName);
		return fileName;
	}
}
