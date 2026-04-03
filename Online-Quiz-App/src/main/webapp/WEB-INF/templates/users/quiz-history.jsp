<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz History</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" href="static/css/app.css">

</head>
<body class="my-custom-bg-1">

	<%@ include file="user-navbar.jsp"%>

	<section class="mx-auto" style="max-width: 800px; min-height: 92vh;">
		<div
			class="container row row-cols-1 my-5 mx-auto card-bg-1 shadow-lg rounded rounded-xl py-3">

			<div>
				<p class="fs-4 fw-bold">Quiz History</p>
			</div>
			<hr>

			<!-- Table start -->
			<table class="table table-hover card-bg-1">
				<thead>
					<tr class="text-center">
						<th scope="col">S.No.</th>
						<th scope="col">Category</th>
						<th scope="col">Result</th>
						<th scope="col">Date</th>
						<th scope="col">Time</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="count" value="0" />
					<c:forEach items="${allQuizResults }" var="quizResult">
						<tr class="text-center">
							<c:set var="count" value="${count+1 }" />
							<th scope="row">${count }</th>
							<td>${quizResult.category.categoryName }</td>
							<td>${quizResult.scoreInPercentage }%</td>
							<td>${quizResult.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</td>
							<td>${quizResult.time.format(DateTimeFormatter.ofPattern("hh:mm a"))}</td>
							<td><a href="quiz/result/${quizResult.quizId }" class="link text-decoration-none">more...</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>