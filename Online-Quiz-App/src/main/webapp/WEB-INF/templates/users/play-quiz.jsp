<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Play Quiz</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" href="static/css/app.css">

</head>
<body class="my-custom-bg-1 overflow-x-hidden">

	<section
		class="container border bg-light shadow-lg col-10 col-md-8 col-lg-6 py-3 rounded" style="margin-top: 80px;">

		<div class="container" id="quiz-list">
			<h3 class="mb-4">Quiz Created Successfully...</h3>
			<table class="table table-hover table-success table-striped">
				<thead>
					<tr class="text-center">
						<th scope="col">Category Name</th>
						<th scope="col">Total Quest</th>
						<th scope="col">Time per Question</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${quizDetails != null }">
						<tr class="text-center">
							<th scope="row">${quizDetails.category.categoryName }</th>
							<td>${quizDetails.totalQuestions }</td>
							<td>30 sec</td>
							<td>
								<button class="btn btn-success" id="start-quiz-btn">Start
									Quiz</button>
							</td>
						</tr>
					</c:if>
				</tbody>

			</table>
		</div>

		<form class="d-none" action="quiz/getNextQuestion" method="post"
			id="quiz-form">
			<p class="px-2 fw-bold fs-4">
				Question No: <span id="question-number"></span>/<span
					id="total-questions"></span>
			</p>
			<hr>
			<div class="d-flex flex-column px-2 gap-2">

				<input type="hidden" name="questionNumber" value="0">

				<p class="m-o p-o fs-5 fw-bold" id="question-text">Question Text</p>

				<div class="container d-flex flex-column gap-3 col-auto ms-0">

					<input type="radio" class="btn-check" name="questionAnswer"
						value="option1" id="option1" autocomplete="off"> <label
						class="btn btn-outline-primary text-start" for="option1">option1</label>

					<input type="radio" class="btn-check" name="questionAnswer"
						value="option2" id="option2" autocomplete="off"> <label
						class="btn btn-outline-primary text-start" for="option2">option2</label>

					<input type="radio" class="btn-check" name="questionAnswer"
						value="option3" id="option3" autocomplete="off"> <label
						class="btn btn-outline-primary text-start" for="option3">option3</label>

					<input type="radio" class="btn-check" name="questionAnswer"
						value="option4" id="option4" autocomplete="off"> <label
						class="btn btn-outline-primary text-start" for="option4">option4</label>

				</div>

				<hr>

				<div class="container text-end">
					<input type="submit" value="Check Answer" class="btn btn-success">
				</div>
			</div>
		</form>
		<div class="container d-flex justify-content-between">
			<div class="text-start w-auto">
				<p id="message1"></p>
				<p id="message2"></p>
			</div>
			<div>
				<button type="button" id="next-question-btn"
					class="btn btn-success d-none">Next Question</button>
				<a href="saveResult" id="quiz-result-btn"
					class="btn btn-success d-none">See Quiz Result</a>
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