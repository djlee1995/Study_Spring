<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insertProcess.me" name="insertform" method="post">
		<center>
			<table border="1">
				<tr>
					<td colspan="2" align="center"><b><font size="5">성적입력
								페이지</font></b></td>
				</tr>
				<tr>
					<td>학번:</td>
					<td><input type="text" name="hakbun" /></td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>국어:</td>
					<td><input type="text" name="kor" maxlength="2"  /></td>
				</tr>
				<tr>
					<td>영어:</td>
					<td><input type="text" name="eng" maxlength="2" /></td>
				</tr>
				<tr>
					<td>수학:</td>
					<td><input type="text" name="math" maxlength="2" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><a
						href="javascript:insertform.submit()">성적추가</a>&nbsp;&nbsp; <a
						href="javascript:insertform.reset()">다시작성</a></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>