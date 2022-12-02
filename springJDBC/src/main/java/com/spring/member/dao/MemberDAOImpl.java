package com.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mysql.jdbc.SQLError;
import com.spring.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO{
	//https://velog.io/@seculoper235/JDBC-5%ED%8E%B8-jdbcTemplate << 설명 잘 되어있음.
	private JdbcTemplate jdbcTemplate;//jdbc의 중심 워크플로우의 기본 작업을 다룸.
	//sql쿼리문을 실행하고 구문과 저장된 프로시저 호출을 수정하거나 resultSet 인스턴스에 대한 반복 실행, 반환된 매개변수 값을 추출하는 등.
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
		/*오리지널 JDBC가 Connection 객체가 필요했듯이, Spring JDBC는 DataSource를 필요로 한다.*/
	}

	@Override
	public List selectAllMembers() throws DataAccessException {
		String query="select * from membertbl order by joinDate desc";
		List memberList=new ArrayList();
		memberList=this.jdbcTemplate.query(query, new RowMapper() {//RowMapper를 사용하면, 원하는 형태의 결과값을 반환할 수 있다.			
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
				MemberVO memberVO=new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
				return memberVO;
			}
		});
		return memberList;
	}

	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		String id=memberVO.getId();
		String pwd=memberVO.getPwd();
		String name=memberVO.getName();
		String email=memberVO.getEmail();
		String query="insert into membertbl(id, pwd, name, email) values("+
		"'"+id+"', '"+pwd+"', '"+name+"', '"+email+"')";
		System.out.println(query);
		int result=jdbcTemplate.update(query);//쿼리 실행 결과로 변경된 행의 개수를 리턴
		System.out.println(result);
		return result;
	}

}

/*
기존 JDBC에서는
ResultSet으로 먼저 값을 받고,
그다음 User 객체에 담아서
반환! 하는 과정을 거쳤음.

spring JDBC는 rowMapper를 사용함.
RowMapper의 mapRow 메소드는 이러한 ResultSet을 사용한다.
mapRow(ResultSet rs, int count);
ResultSet에 값을 담아와서 memberVO객체에 저장하고 count만큼 반복.
count는 알아서 세어주므로, 작성할 필요없다.
*/