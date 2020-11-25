package com.spring.springform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@RequestMapping(value = "inputForm.me")
	public String inputForm() {

		return "inputForm";
	}

	@RequestMapping(value = "updateForm.me")
	public String updateForm(Model model, @RequestParam(value = "empno", required = false) int empno) {
		System.out.println("update.me in");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpVO empvo = null;
		
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

			Class.forName(driver);
			
			
			
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement("Select * from emp_copy where empno=?");
			
			pstmt.setInt(1, empno);
			rs=pstmt.executeQuery();
			rs.next();
			
			
			empvo = new EmpVO();
			empvo.setEmpno(rs.getInt(1));
			empvo.setEname(rs.getString(2));
			empvo.setJob(rs.getString(3));
			empvo.setMgr(rs.getInt(4));
			empvo.setHiredate(rs.getDate(5));
			empvo.setSal(rs.getDouble(6));
			empvo.setComm(rs.getDouble(7));
			empvo.setDeptno(rs.getInt(8));
			model.addAttribute("empvo",empvo);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "updateForm";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "inputProcess.me")
	public String inputProcess(EmpVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String sql = "insert into emp_copy (empno,ename,job,mgr,hiredate,sal,comm,deptno) values (?,?,?,?,sysdate,?,?,?)";
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmpno());
			pstmt.setString(2, vo.getEname());
			pstmt.setString(3, vo.getJob());
			pstmt.setInt(4, vo.getMgr());
			pstmt.setDouble(5, vo.getSal());
			pstmt.setDouble(6, vo.getComm());
			pstmt.setInt(7, vo.getDeptno());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "index";
	}

	@RequestMapping(value = "selectProcess.me")
	// 의존성 주입
	public String selectProcess(Model model) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement("select * from emp_copy order by ename asc");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpVO empvo = new EmpVO();
				empvo.setEmpno(rs.getInt("empno"));
				empvo.setEname(rs.getString("ename"));
				empvo.setJob(rs.getString("job"));
				empvo.setMgr(rs.getInt("mgr"));
				empvo.setHiredate(rs.getDate("hiredate"));
				empvo.setSal(rs.getDouble("sal"));
				empvo.setComm(rs.getDouble("comm"));
				empvo.setDeptno(rs.getInt("deptno"));
				list.add(empvo);

			}
			model.addAttribute("list", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "list";
	}

	@RequestMapping(value = "EmpDeptProcess.me")
	// 의존성 주입
	public String EmpDeptProcess(Model model) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpDeptVO> list = new ArrayList<EmpDeptVO>();

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement(
					"SELECT e.empno,e.ename,e.job,e.deptno,d.dname,d.loc FROM emp_copy e,dept_copy d WHERE e.deptno = d.deptno");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpDeptVO empdeptvo = new EmpDeptVO();
				empdeptvo.setEmpno(rs.getInt("empno"));
				empdeptvo.setEname(rs.getString("ename"));
				empdeptvo.setJob(rs.getString("job"));
				empdeptvo.setDeptno(rs.getInt("deptno"));
				empdeptvo.setDname(rs.getString("dname"));
				empdeptvo.setLoc(rs.getString("loc"));
				list.add(empdeptvo);

			}
			model.addAttribute("list", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "empdept_list";
	}

	@RequestMapping(value = "selectDept.me")
	public String selectDept(Model model,
			@RequestParam(value = "deptno", required = false, defaultValue = "1") int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DeptVO deptvo = null;

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement("select * from dept_copy where deptno=?");
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			rs.next();
			deptvo = new DeptVO();
			deptvo.setDeptno(rs.getInt("deptno"));
			deptvo.setDname(rs.getString("dname"));
			deptvo.setLoc(rs.getString("loc"));
			model.addAttribute("deptvo", deptvo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "deptview";
	}

	@RequestMapping(value = "delete.me")
	public String delete(Model model, @RequestParam(value = "empno", required = false) int empno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement("Delete from emp_copy where empno=?");
			pstmt.setInt(1, empno);
			pstmt.executeUpdate();
			rs.next();
			model.addAttribute("empno", empno);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/selectProcess.me";
	}

	@RequestMapping(value = "update.me")
	public String update(EmpVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpVO empvo = null;

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String sql = "Update emp_copy Set ename=?, job=?, mgr=?, sal=?, comm=?, deptno=? Where empno=?";
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getEname());
			pstmt.setString(2, vo.getJob());
			pstmt.setInt(3, vo.getMgr());
			pstmt.setDouble(4, vo.getSal());
			pstmt.setDouble(5, vo.getComm());
			pstmt.setInt(6, vo.getDeptno());
			pstmt.setInt(7, vo.getEmpno());
			pstmt.executeUpdate();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/selectProcess.me";
	}

}
