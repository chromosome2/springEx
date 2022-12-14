package springAnnotation.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;

import springAnnotation.member.vo.MemberVO;

public interface MemberService {
	public List listMembers() throws DataAccessException;
	public int addMember(MemberVO memberVO) throws DataAccessException;
	public int removeMember(String id) throws DataAccessException;
	public MemberVO findMember(String id) throws DataAccessException;
	public int updateMember(MemberVO memberVO) throws DataAccessException;

}
