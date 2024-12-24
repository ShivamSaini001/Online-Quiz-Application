<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Result</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" href="static/css/app.css">
</head>
<body class="my-custom-bg-1">

	<section class="mx-auto" style="max-width: 800px; min-height: 92vh;">
		<div
			class="container row row-cols-1 my-5 mx-auto card-bg-1 shadow-lg rounded rounded-xl py-3">
			<div class="d-flex justify-content-between">
				<p class="fs-4 fw-bold">Quiz Result</p>
				<div class="pt-1 fs-5">
					<span>${quizResult.userId }</span>
				</div>
			</div>
			<hr>

			<div class="d-flex justify-content-between">
				<p>
					<span class="fw-bold">Category Name: </span>${quizResult.category.categoryName}</p>
				<div class="d-flex flex-column">
					<p>
						<span class="fw-bold">Date: </span>${quizResult.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</p>
					<p>
						<span class="fw-bold">Time: </span>${quizResult.time.format(DateTimeFormatter.ofPattern("hh:mm a"))}</p>
				</div>
			</div>
			<div>
				<span>Correct Answer: </span>
				<div class="progress mb-3">
					<div class="progress-bar progress-bar-striped bg-success"
						style="width: ${quizResult.calculatePersentage(quizResult.totalQuestions, quizResult.numberOfCorrectAnswer) }%;">
						${quizResult.numberOfCorrectAnswer }/${quizResult.totalQuestions }</div>

					<c:if test="${quizResult.numberOfCorrectAnswer <= 0 }">
						<span class="ms-3">0/${quizResult.totalQuestions}</span>
					</c:if>
				</div>
			</div>
			<div>
				<span>Wrong Answer: </span>
				<div class="progress mb-3">
					<div class="progress-bar progress-bar-striped bg-danger"
						style="width: ${quizResult.calculatePersentage(quizResult.totalQuestions, quizResult.numberOfWrongAnswer) }%;">
						${quizResult.numberOfWrongAnswer }/${quizResult.totalQuestions }</div>

					<c:if test="${quizResult.numberOfWrongAnswer <= 0 }">
						<span class="ms-3">0/${quizResult.totalQuestions}</span>
					</c:if>
				</div>
			</div>
			<div>
				<span>Not-Attempt: </span>
				<div class="progress mb-3">
					<div class="progress-bar progress-bar-striped bg-secondary"
						style="width: ${quizResult.calculatePersentage(quizResult.totalQuestions, quizResult.numberOfNotAttemptQuestions) }%;">
						${quizResult.numberOfNotAttemptQuestions }/${quizResult.totalQuestions }</div>

					<c:if test="${quizResult.numberOfNotAttemptQuestions <= 0 }">
						<span class="ms-3">0/${quizResult.totalQuestions}</span>
					</c:if>
				</div>
			</div>
			<div>
				<span>Total Score: </span>
				<div class="progress mb-3">
					<div class="progress-bar progress-bar-striped bg-primary"
						style="width: ${quizResult.scoreInPercentage }%;">${quizResult.scoreInPercentage }%</div>

					<c:if test="${quizResult.scoreInPercentage <= 0 }">
						<span class="ms-3">0%</span>
					</c:if>
				</div>
			</div>

			<div class="my-3 text-end">
				<a href="home" class="btn btn-secondary">Home Page</a>
			</div>
		</div>
	</section>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

	<script src="static/js/quiz.js"></script>
</body>
</html>