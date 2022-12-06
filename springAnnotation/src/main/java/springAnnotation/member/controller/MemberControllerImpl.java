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
	
	//������Ʈ ���� ��ü�� �˻��ؼ�, �ش� Ÿ���� �ν��Ͻ��� 1���� �ִ� ��� �� �ν��Ͻ��� �ڵ����� ����
	@Autowired//autowired�� ����ϸ� set�� ���� �����൵��.
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
	
	private String getViewName(HttpServletRequest request) throws Exception{//�ѹ������ ���� ���� ����.
		String contextPath=request.getContextPath();//�� ���! ������Ʈ �̸�����.
		String uri=(String)request.getAttribute("javax.servlet.include.request_uri");
		if(uri == null || uri.trim().equals("")) {//trim�� ��������. url�� �����̽� �������!
			uri=request.getRequestURI();
		}
		int begin=0, end;
		if(!((contextPath==null) || ("".equals(contextPath)))) {
			begin=contextPath.length();
		}
		if(uri.indexOf(";")!=-1) {//ã�Ҵٸ� �� ������ ����
			end=uri.indexOf(";"); //��ûuri�� ;�� ���� ��� ;�� ��ġ�� ����
		}else if(uri.indexOf("?")!=-1) {
			end=uri.indexOf("?");//��û uri�� ?�� ���� ��� ?�� ��ġ�� ����.
		}else {
			end=uri.length();
		}
		String fileName=uri.substring(begin,end);
		if(fileName.lastIndexOf(".")!=-1) {
			//.do �տ������� ���ڿ��� ����
			fileName=fileName.substring(0,fileName.lastIndexOf("."));//fileName�� '.do'���� Ȯ���ڸ� �����ϰ� ��.
		}
		if(fileName.lastIndexOf("/")!=-1) {
			fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
			//���� ��� 127.0.0.1:8090/sevletMVC/member/login.do���
			//.do�� ���� / �ձ����� �� ���� login < �̰͸� ���������� �ϴ°���.
		}
		return fileName;
	}
}
