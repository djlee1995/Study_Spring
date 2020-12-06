<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<style type="text/css">
form {
	width: 500px;
	margin: 10px auto;
}

ul {
	padding: 0;
	margin: 0;
	list-style: none;
}

ul li {
	padding: 0;
	margin: 0 0 10px 0;
}

label {
	width: 150px;
	float: left;
}

table {
	border: 1px solid gray;
	width: 500px;
	margin: 0 auto;
	border-collapse: collapse;
}


td {
	
	border: 1px solid gray;
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function update(){
	
}
//목록
function selectData() {
	$('#output').empty(); //table 내부 내용을 재거(초기화)
    
	$.ajax({
		url:'/springajax/getPeopleJSON.do', 
		type:'POST',
		//dataType : 'json', //서버에서 보내줄 데이터 타입
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success:function(data){
			$.each(data, function(index,item){
				var output = '';
				output +='<tr align="center">';
				output +='<td>'+ item.id + '</td>';
				output +='<td>'+ item.name + '</td>';
				output +='<td>'+ item.job + '</td>';
				output +='<td>'+ item.address + '</td>';
				output +='<td>'+ item.bloodtype + '</td>';
				output +='<td align="center"><a href=# ';
				output +='class="update"';
				output +='>수정</a> &ensp;&ensp;';
				output +='<a href="/springajax/deletePeople.do" ';
				output +='class="delete_data"';
				output +='id='+ item.id + '>삭제</a></td>';
				output +='</tr>';
				console.log("output:"+output);
				$('#output').append(output);
			});
		},
		error:function(){
			alert("ajax통신 실패1")
		
		}
		
	});
} // end function selectData();

$(document).ready(function(){
	
	$('#input_data').click(function(event){
		var params = $("#insert_form").serialize();//자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로 데이터를 변환하는 기술
		alert(params);
		jQuery.ajax({
			url : '/springajax/insertPeople.do',
			type : 'POST',
			data : params, //서버로 보낼 데이터
			/*(참고)파일 첨부시 필요함
			-cache : false로 선언 시 ajax 로 통신 중 cache 가 남아서 갱신된 데이터를 받아오지 못할 경우를 대비
			-contentType : false 로 선언 시 content-type 헤더가 multipart/form-data로 전송되게 함
			-processData : false 로 선언 시 formData 를 String 으로 변환하지 않음
			*/
			contentType : 'application/x-www-form-urlencoded;charset=utf-8',
			dataType : 'json', //서버에서 보내줄 데이터 타입
			success: function(retVal){
				if(retVal.res=="OK"){
					//데이터 성공일 때 이벤트 작성
					selectData();
					//초기화
					$('#id').val('');
					$('#name').val('');
					$('#job').val('');
					$('#address').val('');
					$('#bloodtype').val('');
				}
				else{
					alert("Insert Fail!!!");
				}
			},
			error:function(){
				alert("ajax통신 실패2!!!");
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
		
	});
	$(document).on('click', '.delete_data', function(event){
		jQuery.ajax({
			url : $(this).attr("href"),
			type : 'POST',
			data : {'id': $(this).attr("id")},
			contentType: 'application/x-www-form-urlencoded;charset=utf-8',
			dataType:'json',
			success: function(retVal) {
				if(retVal.res == "OK"){//데이터 성공일 떄 이벤트 작성
					selectData();
				}
				else{
					alert("Delete Fail!!!");
				}
			},
			error:function(){
				alert("ajax통신 실패3!!!");
			}	
			});
		//기본 이벤트 제거
		event.preventDefault();
	});
	$(document).on('click', '.update', function(event){
		tdrs= $(this).parent().parent().find("td");
		id=$(tdrs).eq(0).text();
		name=$(tdrs).eq(1).text();
		job=$(tdrs).eq(2).text();
		address=$(tdrs).eq(3).text();
		bloodtype=$(tdrs).eq(4).text();
		
       $(tdrs).eq(0).html(id+"<input size='5' type='hidden' name='id' id='id' value="+id+">");
       $(tdrs).eq(1).html("<input size='5' type='text' name='name' id='name' value="+name+">");
       $(tdrs).eq(2).html("<input size='5' type='text' name='job' id='job' value="+job+">");
       $(tdrs).eq(3).html("<input size='10' type='text' name='address' id='address' value="+address+">");
       $(tdrs).eq(4).html("<input size='5' type='text' name='bloodtype' id='bloodtype' value="+bloodtype+">");
       $(tdrs).eq(5).html("<a href='/springajax/updatePeople.do' class='update_data' id='"+id+"'>저장</a> &ensp;&ensp;"
    		   +"<a href='#' onclick='selectData();'> 목록 </a>");
      
       

	});
	$(document).on('click', '.update_data', function(event){
		var params = $("#update_form").serialize();//자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로 데이터를 변환하는 기술
		alert(params);
		jQuery.ajax({
			url : $(this).attr("href"),
			type : 'POST',
			data : params,
			contentType: 'application/x-www-form-urlencoded;charset=utf-8',
			dataType:'json',
			success: function(retVal) {
				if(retVal.res == "OK"){//데이터 성공일 떄 이벤트 작성
					selectData();
				}
				else{
					alert("Update Fail!!!");
				}
			},
			error:function(){
				alert("ajax통신 실패4!!!");
			}	
			});
		//기본 이벤트 제거
		event.preventDefault();
	});
selectData(); // page 로딩 되자마자 실행
});
02
</script>
</head>
<body>
<form id = "insert_form" method="post">
	<fieldset>
		<legend>데이터 추가</legend>
		<ul>
		 <li>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id">
		 </li>
		<li>
			<label for="name">이름</label>
			<input type="text" name="name" id="name">
		 </li>
		<li>
			<label for="job">직업</label>
			<input type="text" name="job" id="job">
		 </li>
		
		<li>
			<label for="adress">주소</label>
			<input type="text" name="address" id="address">
		 </li>
		 <li>
			<label for="bloodtype">혈액형</label>
			<input type="text" name="bloodtype" id="bloodtype">
		 </li>
		 <li>
			<input type="button" value="추가" id="input_data">
		 </li>
		
		
		</ul>
	</fieldset>
</form>
<form id="update_form" method="post">
	<table id="output"></table>
</form>
</body>
</html>
