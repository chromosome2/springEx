package springMybatis.ex01.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import springMybatis.ex01.member.service.MemberService;
import springMybatis.ex01.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController{

	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=getViewName(request);
		List memberList=memberService.listMembers();
		ModelAndView mav=new ModelAndView(viewName);
		mav.addObject("memberList",memberList);
		return mav;
	}
	
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=getViewName(request);
		ModelAndView mav=new ModelAndView(viewName);
		return mav;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO=new MemberVO();
		bind(request,memberVO);//���� ���� �ߴ� request.getParameter �� memberVO.set �߶� �۾�. �갡 �ڵ����� ����. Springframework���� �����ϴ� Multiactioncontroller���� �������ִ� bind��.
		//vo�� Į���� �޾ƿ��� name�� ���ƾ� ����.
		int result=memberService.addMember(memberVO);
		ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
		//redirect�� �ϴ� ������ �츮�� �ʿ��Ѱ� listMembers.jsp�� �ƴϱ� ����. listMembers.do�̱� ������.
		return mav;
	}

	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		memberService.removeMember(id);
		ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	public ModelAndView modMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=getViewName(request);
		String id=request.getParameter("id");
		MemberVO memberVO=new MemberVO();
		memberVO=memberService.findMember(id);
		ModelAndView mav=new ModelAndView(viewName);
		mav.addObject("member",memberVO);
		return mav;
	}

	@Override
	public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO=new MemberVO();
		bind(request, memberVO);
		int result=memberService.updateMember(memberVO);
		ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath=request.getContextPath();//�� ���. ������Ʈ �̸����� ��������
		String uri=(String)request.getAttribute("javax.servlet.include.request_uri");
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
		return fileName;
	}

}
