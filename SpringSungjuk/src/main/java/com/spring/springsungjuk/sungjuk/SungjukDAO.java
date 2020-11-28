package com.spring.springsungjuk.sungjuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.springsungjuk.JDBCUtil;
import com.spring.springsungjuk.SpringVO;


@Repository
public class SungjukDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ArrayList<SpringVO> getSungjuklist() {
		SpringVO vo = null;
		ArrayList<SpringVO> sungjuk_list = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement("select * from sungjuk order by hakbun");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sungjuk_list = new ArrayList<SpringVO>();
				do {
					vo = new SpringVO();
					vo.setHakbun(rs.getString("hakbun"));
					vo.setName(rs.getString("name"));
					vo.setKor(rs.getInt("kor"));
					vo.setMath(rs.getInt("math"));
					vo.setEng(rs.getInt("eng"));
					vo.setTot(rs.getInt("tot"));
					vo.setAvg(rs.getDouble("avg"));
					vo.setGrade(rs.getString("grade"));
					sungjuk_list.add(vo);

				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return sungjuk_list;
	}

	public int insertSungjuk(SpringVO springVO) {

		int result = 0;

		try {
			conn = JDBCUtil.getConnection();
			springVO.process_sungjuk();
			pstmt = conn.prepareStatement("insert into sungjuk values (?,?,?,?,?,?,?,?)");
			pstmt.setString(1, springVO.getHakbun());
			pstmt.setString(2, springVO.getName());
			pstmt.setInt(3, springVO.getKor());
			pstmt.setInt(4, springVO.getMath());
			pstmt.setInt(5, springVO.getEng());
			pstmt.setInt(6, springVO.getTot());
			pstmt.setDouble(7, springVO.getAvg());
			pstmt.setString(8, springVO.getGrade());
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("성적입력 오류");
			ex.printStackTrace();
		} finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return result;
	}

	public SpringVO selectSungjuk(String hakbun) {
		SpringVO vo = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement("select * from sungjuk where hakbun = ?");
			pstmt.setString(1, hakbun);
			rs = pstmt.executeQuery();
			rs.next();
				vo = new SpringVO();
				vo.setHakbun(rs.getString("hakbun"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setMath(rs.getInt("math"));
				vo.setEng(rs.getInt("eng"));
				vo.setTot(rs.getInt("tot"));
				vo.setAvg(rs.getDouble("avg"));
				vo.setGrade(rs.getString("grade"));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return vo;
	}

	public int deleteSungjuk(SpringVO springVO) {
		int result = 0;

		try {
			conn = JDBCUtil.getConnection();
			springVO.process_sungjuk();
			pstmt = conn.prepareStatement("delete from sungjuk where hakbun =?");
			pstmt.setString(1, springVO.getHakbun());
			result = pstmt.executeUpdate();

		} catch (

		Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return result;
	}

	public int updateSungjuk(SpringVO springVO) {
		int result = 0;

		try {
			conn = JDBCUtil.getConnection();
			springVO.process_sungjuk();
			pstmt = conn.prepareStatement("Update sungjuk Set name = ? ,kor =? ,math =? ,eng =? ,tot =? ,avg =?, grade =? Where hakbun =?");
			pstmt.setString(1, springVO.getName());
			pstmt.setInt(2, springVO.getKor());
			pstmt.setInt(3, springVO.getMath());
			pstmt.setInt(4, springVO.getEng());
			pstmt.setInt(5, springVO.getTot());
			pstmt.setDouble(6, springVO.getAvg());
			pstmt.setString(7, springVO.getGrade());
			pstmt.setString(8, springVO.getHakbun());
			result = pstmt.executeUpdate();

		} catch (

		Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.closeResource(pstmt, conn);
		}
		return result;
	}

}
