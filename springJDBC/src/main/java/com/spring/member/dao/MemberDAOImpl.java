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
	//https://velog.io/@seculoper235/JDBC-5%ED%8E%B8-jdbcTemplate << ���� �� �Ǿ�����.
	private JdbcTemplate jdbcTemplate;//jdbc�� �߽� ��ũ�÷ο��� �⺻ �۾��� �ٷ�.
	//sql�������� �����ϰ� ������ ����� ���ν��� ȣ���� �����ϰų� resultSet �ν��Ͻ��� ���� �ݺ� ����, ��ȯ�� �Ű����� ���� �����ϴ� ��.
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
		/*�������� JDBC�� Connection ��ü�� �ʿ��ߵ���, Spring JDBC�� DataSource�� �ʿ�� �Ѵ�.*/
	}

	@Override
	public List selectAllMembers() throws DataAccessException {
		String query="select * from membertbl order by joinDate desc";
		List memberList=new ArrayList();
		memberList=this.jdbcTemplate.query(query, new RowMapper() {//RowMapper�� ����ϸ�, ���ϴ� ������ ������� ��ȯ�� �� �ִ�.			
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
		int result=jdbcTemplate.update(query);//���� ���� ����� ����� ���� ������ ����
		System.out.println(result);
		return result;
	}

}

/*
���� JDBC������
ResultSet���� ���� ���� �ް�,
�״��� User ��ü�� ��Ƽ�
��ȯ! �ϴ� ������ ������.

spring JDBC�� rowMapper�� �����.
RowMapper�� mapRow �޼ҵ�� �̷��� ResultSet�� ����Ѵ�.
mapRow(ResultSet rs, int count);
ResultSet�� ���� ��ƿͼ� memberVO��ü�� �����ϰ� count��ŭ �ݺ�.
count�� �˾Ƽ� �����ֹǷ�, �ۼ��� �ʿ����.
*/