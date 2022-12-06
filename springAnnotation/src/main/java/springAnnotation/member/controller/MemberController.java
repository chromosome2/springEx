package springAnnotation.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springAnnotation.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView listMembers(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	//MemberInfo 클래스는 beans 클래스여야 한다.
	public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	public ModelAndView modMember(@RequestParam("id") String id, HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	public ModelAndView updateMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}
