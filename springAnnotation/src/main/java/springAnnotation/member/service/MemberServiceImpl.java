package springAnnotation.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import springAnnotation.member.dao.MemberDAO;
import springAnnotation.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public List listMembers() throws DataAccessException {
		return memberDAO.selectAllMemberList();
	}

	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.insertMember(memberVO);
	}

	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}

	@Override
	public MemberVO findMember(String id) throws DataAccessException {
		MemberVO memberVO=(MemberVO)memberDAO.findMember(id);
		return memberVO;
	}

	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.updateMember(memberVO);
	}
	
	

}
