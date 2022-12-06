package springAnnotation.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import springAnnotation.member.service.MemberService;
import springAnnotation.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl extends MultiActionController implements MemberController{
	
	@Autowired
	private MemberService memberService;
	
	//프로젝트 내부 전체를 검색해서, 해당 타입의 인스턴스가 1개만 있는 경우 그 인스턴스를 자동으로 연결
	@Autowired//autowired를 사용하면 set을 따로 안해줘도됨.
	private MemberVO memberVO;

	@Override
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=getViewName(request);
		List memberList=memberService.listMembers();
		ModelAndView mav=new ModelAndView(viewName);
		mav.addObject("memberList",memberList);
		return mav;
	}

	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		int result=memberService.addMember(memberVO);
		ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@RequestMapping(value="/member/memberForm.do", method=RequestMethod.GET)
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=getViewName(request);
		ModelAndView mav=new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/member/removeMember.do", method=RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/member/modMember.do", method=RequestMethod.GET)
	public ModelAndView modMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=getViewName(request);
		MemberVO memberVO=new MemberVO();
		memberVO=memberService.findMember(id);
		ModelAndView mav=new ModelAndView(viewName);
		mav.addObject("member",memberVO);
		return mav;
	}

	@Override
	@RequestMapping(value="/member/updateMember.do", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int result=memberService.updateMember(memberVO);
		ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception{//한번만들어 놓고 재사용 가능.
		String contextPath=request.getContextPath();//내 경로! 프로젝트 이름까지.
		String uri=(String)request.getAttribute("javax.servlet.include.request_uri");
		if(uri == null || uri.trim().equals("")) {//trim은 공백제거. url에 스페이스 들어갔을까봐!
			uri=request.getRequestURI();
		}
		int begin=0, end;
		if(!((contextPath==null) || ("".equals(contextPath)))) {
			begin=contextPath.length();
		}
		if(uri.indexOf(";")!=-1) {//찾았다면 이 조건을 만족
			end=uri.indexOf(";"); //요청uri에 ;가 있을 경우 ;의 위치를 구함
		}else if(uri.indexOf("?")!=-1) {
			end=uri.indexOf("?");//요청 uri에 ?가 있을 경우 ?의 위치를 구함.
		}else {
			end=uri.length();
		}
		String fileName=uri.substring(begin,end);
		if(fileName.lastIndexOf(".")!=-1) {
			//.do 앞에까지의 문자열을 구함
			fileName=fileName.substring(0,fileName.lastIndexOf("."));//fileName에 '.do'같은 확장자만 제외하고 들어감.
		}
		if(fileName.lastIndexOf("/")!=-1) {
			fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
			//예를 들어 127.0.0.1:8090/sevletMVC/member/login.do라면
			//.do를 빼고 / 앞까지도 다 빼서 login < 이것만 가져오려고 하는거임.
		}
		return fileName;
	}
}
