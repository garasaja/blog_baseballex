<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<div class="row">
<div class="col-sm-3">
  <h2>팀목록</h2>            
  <table class="table table-striped">
    <thead>
      <tr>
        <th>팀목록</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="player" items="${playerlist}">
    	<tr>
        	<td><a href="#" onclick="SelectTeam('${player.team}')">${player.team}</a></td>      
      	</tr>
    </c:forEach>              
    </tbody>
  </table>
</div>

<div class="col-sm-3">
  <h2>선수목록</h2>            
  <table class="table table-striped">
  
    <thead>
      <tr>
        <th>선수목록</th>
      </tr>
    </thead>
    <tbody id="body_player">
                   
    </tbody>
  </table>
</div>

<div class="col-sm-3">
  <h2>선수정보</h2>            
  <table class="table table-striped">
  
    <thead>
      <tr>
        <th>선수정보</th>
      </tr>
    </thead>
    <tbody id="body_playerinfo">
                  
    </tbody>
  </table>
</div>

</div>
</div>

<script>
function SelectTeam(team){
	console.log("SelectTeam ", team);
	var data = {
				team:team
			}
	
	$.ajax({
		url:"/baseball/base?cmd=selectteamProc",
		type:"post",//방식
		data:JSON.stringify(data),
		dataType:"json",//받는타입
		contentType: "Application/x-www-urlencoded; charset=utf-8",//보내는타입	
	}).done( (result)=> {
		console.log("SelectTeam : ", result);
		$("#body_player").empty();

		for (var player of result) {
			var trItem = "<tr>";			
			// trItem += "<td> <a href='#' onclick=\"SelectPlayer()\">" + player.name + "</td>";
			trItem += "<td> <a href='#' onclick=\"SelectPlayer('"+ player.name +"')\">" + player.name + "</td>";
			trItem += "</tr>";

			$("#body_player").append(trItem);
			console.log("trItem :", trItem);
		}
		
	}).fail( (error)=> {
		
	})	
}

function SelectPlayer(name){
	console.log("선수이름 : ",name);
	var data = {
				name:name
			}
	
	$.ajax({
		url:"/baseball/base?cmd=selectplayerProc",
		type:"post",//방식
		data:JSON.stringify(data),
		dataType:"json",//받는타입
		contentType: "Application/x-www-urlencoded; charset=utf-8",//보내는타입	
	}).done( (result)=> {
		$("#body_playerinfo").empty();

		for (var player of result) {
			var trItem = "<tr>";			
			trItem += "<td>" + player.num + "</td>";	
			trItem += "<td>" + player.name + "</td>"
			trItem += "<td>" + player.position + "</td>"
			trItem += "</tr>";

			$("#body_playerinfo").append(trItem);
		}
		
	}).fail( (error)=> {
		
	})	
}


</script>

</body>
</html>
