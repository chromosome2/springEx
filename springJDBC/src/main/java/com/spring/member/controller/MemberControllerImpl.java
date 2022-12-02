package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController{
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}

	//http://127.0.0.1:8090/springJDBC/member/listMembers.do
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=getViewName(request);
		List memberList=memberService.listMembers();
		ModelAndView mav=new ModelAndView(viewName);//응답할 view로 바로 값 저장
		mav.addObject("memberList",memberList);
		return mav;
	}

	//http://127.0.0.1:8090/springJDBC/member/memberForm.do
	@Override
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=getViewName(request);
		ModelAndView mav=new ModelAndView(viewName);
		return mav;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO=new MemberVO();
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		int result=memberService.addMember(memberVO);
		List memberList=memberService.listMembers();
		ModelAndView mav=new ModelAndView("listMembers");
		mav.addObject("memberList",memberList);
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath=request.getContextPath();//내 경로. 프로젝트 이름까지 가져오기
		String uri=(String)request.getAttribute("javax.servlet.include.request_uri");
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
		return fileName;
	}
	
	
}
