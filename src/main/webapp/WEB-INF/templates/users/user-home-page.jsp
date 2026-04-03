<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" href="static/css/app.css">

</head>
<body class="my-custom-bg-1">

	<%@ include file="user-navbar.jsp"%>

	<section
		class="container border shadow-lg col-10 col-md-6 col-lg-4 py-3 rounded card-bg-1"
		style="margin-top: 150px;">
		<form action="quiz/create" method="post">
			<p class="px-2 fw-bold fs-4">Play Quiz</p>
			<div class="d-flex px-2 mb-4 gap-4">
				<!-- Question Category -->
				<input type="hidden" name="email" value="${email }" /> <select
					name="questionCategoryId" class="form-select" required>
					<option class="d-none" value="">Select Question
						Category...</option>

					<!-- Show all categories -->
					<c:forEach items="${categoryList }" var="category">

						<option value="${category.getCategoryId() }">
							${category.getCategoryName() }</option>

					</c:forEach>
				</select> <input type="submit" class="btn btn-success" value="Create">

			</div>
		</form>

		<p class="container text-danger">
			<c:if test="${noQuestionFromCategory != null }">
				${noQuestionFromCategory }
			</c:if>
			<span class="fw-bold"> <c:if test="${categoryNameNotFoundQuestions != null }">
					${categoryNameNotFoundQuestions }
				</c:if>
			</span>
		</p>

	</section>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>