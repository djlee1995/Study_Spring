<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>DB에 저장된 좌표로 여러개의 마커표시,클러스터러 사용</title>
</head>

<body>
<div id="map" style="width:1000px;height:1000px;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e9646f261380e768a278eb16f4f6768&libraries=services,clusterer"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {	
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
		mapOption = { 
		    center: new kakao.maps.LatLng(37.56656, 126.97817), // 지도의 중심좌표
		    level: 7 // 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		// 마커 클러스터러를 생성합니다 
	    var clusterer = new kakao.maps.MarkerClusterer({
	        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
	        averageCenter: true, // 클러스터러에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
	        minLevel: 7 // 클러스터 할 최소 지도 레벨 
	    });
	    var geocoder = new kakao.maps.services.Geocoder();
		
		var markers=[];
		$.ajax({
					url: '/kakaomap/place.do',
					//type: 'POST',
					//dataType : 'json',
					async:false, // 이 한줄만 추가해주시면 됩니다.
					contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					success : function(data){
					
						//console.log(result);
						var markers=$(data).map(function(index, position) {
							
							var marker;
							 geocoder.addressSearch(position.addr, function(result, status) {
								 
								 if (status === daum.maps.services.Status.OK) {
									 
									 //var pos= new kakao.maps.LatLng(result[0].y,result[0].x)
										
									 marker = new kakao.maps.Marker({
										map:map,
										position : new kakao.maps.LatLng(result[0].y,result[0].x),
										title: position.loc
								     });
									 
									 //console.log(pos)
									/* console.log(result[0].address.x);
									x = result[0].address.x;
									y = result[0].address.y; */
								 }
						     });
								 return marker;
								
							  /* var marker = new kakao.maps.Marker({
						            	map:map,
						                position : 
						                title: position.loc
						            
						      }); */
							  //return marker;
							 // markers.push(marker);
							//return marker;
						});
						        
						console.log(markers);
				        // 클러스터러에 마커들을 추가합니다
				        clusterer.addMarkers(markers);
       
					},
					error : function() {

						alert("ajax통신 실패2")

					}
						
			
				});
				for(var i=0; i<markers.length; i++){
					kakao.maps.event.addListener(markers[i], 'click', function() {
					    alert('marker click!');
					});
				}
				
				
				
	
			
			
	
				 
			
		
});
	
</script>
</body>
</html>