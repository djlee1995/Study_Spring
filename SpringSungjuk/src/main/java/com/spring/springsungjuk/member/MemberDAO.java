package com.spring.springsungjuk.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.springsungjuk.JDBCUtil;
import com.spring.springsungjuk.SpringVO;

@Repository
public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int insertMember(SpringVO springVO) {
		int result = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement("insert into smember values (?,?,?,?,?,?)");
			pstmt.setString(1,springVO.getHakbun());
			pstmt.setString(2,springVO.getPassword());
			pstmt.setString(3,springVO.getName());
			pstmt.setInt(4,springVO.getAge());
			pstmt.setString(5,springVO.getGender());
			pstmt.setString(6,springVO.getEmail());
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("���Կ���");
			ex.printStackTrace();
		}
		finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return result;
	}
	public int userCheck(SpringVO springVO) {
		String dbpasswd = "";
		int x = -1;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement("select * from smember where hakbun = ?");
			pstmt.setString(1, springVO.getHakbun());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("password");
				if (dbpasswd.equals(springVO.getPassword())) 
					x=1;
				else 
					x=0;
			}
			else 
				x=-1;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return x;
	}
	public ArrayList<SpringVO> getMemberlist(){
		SpringVO vo = null;
		ArrayList<SpringVO> member_list = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement("select * from smember order by hakbun");
			rs=pstmt.executeQuery();
			if (rs.next()) {
				member_list = new ArrayList<SpringVO>();
				do {
					vo = new SpringVO();
					vo.setHakbun(rs.getString("hakbun"));
					vo.setPassword(rs.getString("password"));
					vo.setName(rs.getString("name"));
					vo.setAge(rs.getInt("age"));
					vo.setGender(rs.getString("gender"));
					vo.setEmail(rs.getString("email"));
					member_list.add(vo);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return  member_list;
	}
	public SpringVO selectMember(SpringVO springVO) {
		SpringVO vo =null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement("select * from smember where hakbun = ?");
			pstmt.setString(1,springVO.getHakbun());
			rs=pstmt.executeQuery();
			rs.next();
			
			vo = new SpringVO();
			vo.setHakbun(rs.getString("hakbun"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setAge(rs.getInt("age"));
			vo.setGender(rs.getString("gender"));
			vo.setEmail(rs.getString("email"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return vo;
	}
	public int deleteMember(SpringVO springVO) {
		int result = 0;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement("delete from smember where hakbun = ?");
			pstmt.setString(1,springVO.getHakbun());
			result=pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return result;
	}
	public int updateMember(SpringVO springVO) {
		int result = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement("Update smember Set password=?, name=?, age=?, gender=?, email=? Where hakbun=?");
			pstmt.setString(1,springVO.getPassword());
			pstmt.setString(2,springVO.getName());
			pstmt.setInt(3,springVO.getAge());
			pstmt.setString(4,springVO.getGender());
			pstmt.setString(5,springVO.getEmail());
			pstmt.setString(6,springVO.getHakbun());
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("��������");
			ex.printStackTrace();
		}
		finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return result;
	}
	
}
