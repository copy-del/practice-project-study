<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="jumbotron text-center">
  <h1>데이터분석 SW엔지니어 양성과정</h1>
  <p>스프링부트를 사용하여, 간단한 게시판을 만들어보자</p>
</div>

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">데이터분석과정 게시판</div>
		<div class="panel-body">
		<form action="upload" method="post" enctype="multipart/form-data">
			<table class="table table-bordered table-striped">
				<tr>
					<th>파일</th>
					<td>
						<input class="form-control" type ="file" name = "file">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-primary btn-sm">글쓰기</button>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</div>





</body>
</html>